package com.zavyalov.springboot_image_transformer.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageModelService {
    byte[] horizontalFlip(MultipartFile imageFile);

    byte[] rotateCW(MultipartFile imageFile);

    byte[] verticalFlip(MultipartFile imageFile);

    byte[] rotateCWW(MultipartFile imageFile);

    byte[] compress(MultipartFile imageFile, int height, int width);

    byte[] crop(MultipartFile imageFile, int[] coords);
}
