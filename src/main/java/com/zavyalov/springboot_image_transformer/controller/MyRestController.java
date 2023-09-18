package com.zavyalov.springboot_image_transformer.controller;

import com.zavyalov.springboot_image_transformer.service.ImageModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/process", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
@Tag(name = "MyRestController", description = "Endpoints for image processing operations")
public class MyRestController {
    @Autowired
    private ImageModelService imageModelService;

    @PostMapping(value = "/flip-h")
    @ResponseBody
    @Operation(summary = "The method flips the image horizontally.")
    public ResponseEntity<byte[]> horizontalFlip(@RequestPart(name = "file") @NotNull MultipartFile imageFile) {
        byte[] outputImage = imageModelService.horizontalFlip(imageFile);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(outputImage);
    }

    @PostMapping("/flip-v")
    @ResponseBody
    @Operation(summary = "This method flips the image vertically.")
    public ResponseEntity<byte[]> verticalFlip(@RequestParam("file") @NotNull MultipartFile imageFile) {
        byte[] outputImage = imageModelService.verticalFlip(imageFile);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(outputImage);
    }

    @PostMapping("/rotate-cw")
    @ResponseBody
    @Operation(summary = "This method rotates the image 90 degrees clockwise.")
    public ResponseEntity<byte[]> rotateCW(@RequestParam("file") @NotNull MultipartFile imageFile) {
        byte[] outputImage = imageModelService.rotateCW(imageFile);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(outputImage);
    }

    @PostMapping("/rotate-cww")
    @ResponseBody
    @Operation(summary = "This method rotates the image 90 degrees counterclockwise.")
    public ResponseEntity<byte[]> RotateCWW(@RequestParam("file") @NotNull MultipartFile imageFile) {
        byte[] outputImage = imageModelService.rotateCWW(imageFile);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(outputImage);
    }

    @PostMapping("/compress")
    @ResponseBody
    @Operation(summary = "This method compresses the image.")
    public ResponseEntity<byte[]> compress(@RequestParam("file") @NotNull MultipartFile imageFile, @RequestParam("height") int height, @RequestParam("width") int width) {
        byte[] outputImage = imageModelService.compress(imageFile, height, width);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(outputImage);
    }

    @PostMapping("/crop")
    @ResponseBody
    @Operation(summary = "This method crops the image.")
    public ResponseEntity<byte[]> crop(@RequestParam("file") @NotNull MultipartFile imageFile, @RequestParam("coords") int[] coords) {
        byte[] outputImage = imageModelService.crop(imageFile, coords);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(outputImage);
    }
}