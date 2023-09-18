package com.zavyalov.springboot_image_transformer.controller;

import com.zavyalov.springboot_image_transformer.service.ImageModelService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/process")
@Tag(name = "MyRestController", description = "Endpoints for image processing operations")
public class MyRestController {
    @Autowired
    private ImageModelService imageModelService;


    @PostMapping("/flip-h")
    @ResponseBody
    @Operation(summary = "The method flips the image horizontally.")
    public  byte[] horizontalFlip(@RequestPart(name = "file") MultipartFile imageFile) {
        if (imageFile.isEmpty()) {
            return null;
        }
        byte[] outputImage = imageModelService.horizontalFlip(imageFile);

        return outputImage;
    }

    @PostMapping("/flip-v")
    @ResponseBody
    @Operation(summary = "This method flips the image vertically.")
    public byte[] verticalFlip(@RequestParam("file") MultipartFile imageFile) {
        if (imageFile.isEmpty()) {
            return null;
        }
        byte[] outputImage = imageModelService.verticalFlip(imageFile);

        return outputImage;
    }


    @PostMapping("/rotate-cw")
    @ResponseBody
    @Operation(summary = "This method rotates the image 90 degrees clockwise.")
    public byte[] rotateCW(@RequestParam("file") MultipartFile imageFile) {
        if (imageFile.isEmpty()) {
            return null;
        }
        byte[] outputImage = imageModelService.rotateCW(imageFile);

        return outputImage;

    }

    @PostMapping("/rotate-cww")
    @ResponseBody
    @Operation(summary = "This method rotates the image 90 degrees counterclockwise.")
    public byte[] RotateCWW(@RequestParam("file") MultipartFile imageFile) {
        if (imageFile.isEmpty()) {
            return null;
        }
        byte[] outputImage = imageModelService.rotateCWW(imageFile);

        return outputImage;

    }

    @PostMapping("/compress")
    @ResponseBody
    @Operation(summary = "This method compresses the image.")
    public byte[] compress(@RequestParam("file") MultipartFile imageFile, @RequestParam("height") int height, @RequestParam("width") int width) {
        if (imageFile.isEmpty() && height > 0 && width > 0) {
            return null;
        }
        byte[] outputImage = imageModelService.compress(imageFile, height, width);

        return outputImage;

    }


    @PostMapping("/crop")
    @ResponseBody
    @Operation(summary = "This method crops the image.")
    public byte[] crop(@RequestParam("file") MultipartFile imageFile, @RequestParam("coords") int[] coords) {
        if (imageFile.isEmpty()) {
            return null;
        }
        byte[] outputImage = imageModelService.crop(imageFile, coords);

        return outputImage;

    }





/*
    @PostMapping("/uploadAndProcess")
    @ResponseBody
    public byte[] uploadAndProcessImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            // Обработка случая, когда файл не был выбран
            return null;
        }

        try {
            // Читаем изображение из MultipartFile
            BufferedImage originalImage = ImageIO.read(file.getInputStream());

            // Здесь можно выполнить необходимую обработку изображения, например, изменение размера,
            // фильтры и т.д.
            // Пример изменения размера:
            int newWidth = 200; // Новая ширина
            int newHeight = 200; // Новая высота
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            resizedImage.createGraphics().drawImage(originalImage, 0, 0, newWidth, newHeight, null);

            // Сохраняем обработанное изображение в формате PNG в массив байтов
            byte[] processedImageBytes;
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                ImageIO.write(resizedImage, "png", baos);
                processedImageBytes = baos.toByteArray();
            }

            // Возвращаем обработанное изображение
            System.out.println("true");
            return processedImageBytes;
        } catch (IOException e) {
            e.printStackTrace();
            // Обработка ошибки
            return null;
        }
    }
*/

}

