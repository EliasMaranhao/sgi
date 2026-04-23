package com.easysoftware.sgi_api.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadFile(MultipartFile file, String subDirectory);
    void deleteFile(String fileName, String subDirectory);
}
