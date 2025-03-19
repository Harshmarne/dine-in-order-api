package com.example.dio.controller;

import com.example.dio.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload/{foodItemId}")
    public String uploadImage(@RequestParam("file") MultipartFile file, @PathVariable long foodItemId) {
        try {
            return imageService.image(file, foodItemId); // Call the service to upload image
        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading image: " + e.getMessage();
        }
    }
}