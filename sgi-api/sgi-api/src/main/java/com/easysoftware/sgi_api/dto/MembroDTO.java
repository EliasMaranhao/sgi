package com.easysoftware.sgi_api.dto;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.easysoftware.sgi_api.entities.Endereco;
import com.easysoftware.sgi_api.entities.StatusMembro;

public record MembroDTO(
    Long id,
    String nome,
    String cpf,
    LocalDate dataBatismo,
    StatusMembro status,
    Long filial_id,        // Enviamos apenas o ID da igreja para simplificar
    Set<String> cargos,    // Apenas os nomes dos cargos atuais
    Endereco endereco,
    MultipartFile fotoUrl
) {}
