package com.example.dio.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String image(MultipartFile file,long foodItemId) throws IOException;

    public String uploadImage(MultipartFile file) throws IOException;
}
