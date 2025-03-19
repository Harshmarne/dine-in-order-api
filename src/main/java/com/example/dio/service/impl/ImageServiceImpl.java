package com.example.dio.service.impl;

import com.cloudinary.utils.ObjectUtils;
import com.example.dio.exception.FoodNotFoundException;
import com.example.dio.model.FoodImage;
import com.example.dio.model.FoodItem;
import com.example.dio.repositry.FoodItemRepositry;
import com.example.dio.repositry.ImageRepositry;
import com.example.dio.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
@Transactional
public class ImageServiceImpl implements ImageService {

    private final Cloudinary cloudinary;
    private final FoodItemRepositry foodItemRepositry;
    private final ImageRepositry imageRepositry;

    /**
     * @param file
     * @return
     */
    @Override
    public String image(MultipartFile file,long foodItemId) throws IOException {

        FoodItem foodItem = foodItemRepositry.findById(foodItemId)
                .orElseThrow(() -> new FoodNotFoundException("Food Not Found To Add In Cart"));

        byte[] fileBytes = file.getBytes();
        Map<String, Object> uploadResult = cloudinary.uploader().upload(fileBytes, ObjectUtils.emptyMap());

        FoodImage foodImage = new FoodImage();
        foodImage.setFoodItem(foodItem);
        foodImage.setImageURL((String) uploadResult.get("url"));

        imageRepositry.save(foodImage);

        return (String) uploadResult.get("url");
    }


    public String uploadImage(MultipartFile file) throws IOException {
        Map result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return result.get("url").toString();
    }
}
