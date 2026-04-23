package com.easysoftware.sgi_api.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import org.springframework.web.multipart.MultipartFile;

import com.easysoftware.sgi_api.service.FileService;

@Service
public class FileServiceImpl implements FileService{

    // Define o diretório raiz (pode vir do application.properties)
    private final Path rootLocation = Paths.get("uploads");
    
    @Override
    public String uploadFile(MultipartFile file, String subDirectory) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Falha ao armazenar arquivo vazio.");
            }

            // Cria o caminho do subdiretório (ex: uploads/membros)
            Path destinationDirectory = this.rootLocation.resolve(subDirectory);
            Files.createDirectories(destinationDirectory);

            // Gera nome único para evitar sobrescrita
            String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String fileName = UUID.randomUUID().toString() + "." + extension;

            Path destinationFile = destinationDirectory.resolve(
                    Paths.get(fileName))
                    .normalize().toAbsolutePath();

            // Segurança: impede que o arquivo seja salvo fora do diretório raiz
            if (!destinationFile.getParent().equals(destinationDirectory.toAbsolutePath())) {
                throw new RuntimeException("Não é possível armazenar arquivo fora do diretório atual.");
            }

            try (var inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

            return fileName; 
        } catch (IOException e) {
            throw new RuntimeException("Falha ao armazenar o arquivo.", e);
        }
    }

    @Override
    public void deleteFile(String fileName, String subDirectory) {
       try {
            Path file = rootLocation.resolve(subDirectory).resolve(fileName);
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao deletar arquivo.", e);
        }
    }
    
}
