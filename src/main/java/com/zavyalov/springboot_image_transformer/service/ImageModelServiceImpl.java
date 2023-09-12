package com.zavyalov.springboot_image_transformer.service;

import com.zavyalov.springboot_image_transformer.entity.ImageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ImageModelServiceImpl implements ImageModelService {

    @Autowired
    ImageModel imageModel;

    @Override
    public byte[] horizontalFlip(MultipartFile imageFile) {

        try {
            BufferedImage originalImage = ImageIO.read(imageFile.getInputStream());

            BufferedImage changedImage = new BufferedImage(
                    originalImage.getWidth()
                    , originalImage.getHeight()
                    , BufferedImage.TYPE_INT_RGB
            );

            WritableRaster raster = originalImage.getRaster();
            WritableRaster raster2 = changedImage.getRaster();

            for (int x = 0; x < raster.getWidth(); x++) {
                for (int y = 0; y < raster.getHeight(); y++) {
                    int[] pixel = raster.getPixel(x, y, new int[4]);
                    raster2.setPixel(x, raster.getHeight() - (y + 1), pixel);
                }
            }

            changedImage.setData(raster2);

            byte[] processedImageBytes;
            try(ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
                ImageIO.write(changedImage, "png", stream);
                processedImageBytes = stream.toByteArray();
            }
            return processedImageBytes;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] verticalFlip(MultipartFile imageFile) {

        try {
            BufferedImage originalImage = ImageIO.read(imageFile.getInputStream());

            BufferedImage changedImage = new BufferedImage(
                    originalImage.getWidth()
                    , originalImage.getHeight()
                    , BufferedImage.TYPE_INT_RGB
            );

            WritableRaster raster = originalImage.getRaster();
            WritableRaster raster2 = changedImage.getRaster();

            for (int x = 0; x < raster.getHeight(); x++) {
                for (int y = 0; y < raster.getWidth(); y++) {
                    int[] pixel = raster.getPixel(y, x, new int[4]);
                    raster2.setPixel(raster.getWidth() - (y + 1), x, pixel);
                }
            }

            changedImage.setData(raster2);

            byte[] processedImageBytes;
            try(ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
                ImageIO.write(changedImage, "png", stream);
                processedImageBytes = stream.toByteArray();
            }
            return processedImageBytes;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] rotateCW(MultipartFile imageFile) {

        try {
            BufferedImage originalImage = ImageIO.read(imageFile.getInputStream());

            BufferedImage changedImage = new BufferedImage(
                    originalImage.getHeight()
                    , originalImage.getWidth()
                    , BufferedImage.TYPE_INT_RGB
            );

            WritableRaster raster = originalImage.getRaster();
            WritableRaster raster2 = changedImage.getRaster();

            for (int x = 0; x < raster.getWidth(); x++) {
                for (int y = 0; y < raster.getHeight(); y++) {
                    int[] pixel = raster.getPixel(x, y, new int[4]);
                    raster2.setPixel(raster.getHeight() - (y + 1), x, pixel);
                }
            }


            changedImage.setData(raster2);

            byte[] processedImageBytes;
            try(ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
                ImageIO.write(changedImage, "png", stream);
                processedImageBytes = stream.toByteArray();
            }
            return processedImageBytes;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] rotateCWW(MultipartFile imageFile) {

        try {
            BufferedImage originalImage = ImageIO.read(imageFile.getInputStream());

            BufferedImage changedImage = new BufferedImage(
                    originalImage.getHeight()
                    , originalImage.getWidth()
                    , BufferedImage.TYPE_INT_RGB
            );

            WritableRaster raster = originalImage.getRaster();
            WritableRaster raster2 = changedImage.getRaster();

            for (int x = 0; x < raster.getHeight(); x++) {
                for (int y = 0; y < raster.getWidth(); y++) {
                    int[] pixel = raster.getPixel(y, x, new int[4]);
                    raster2.setPixel(x, raster.getWidth() - (y + 1), pixel);
                }
            }

            changedImage.setData(raster2);

            byte[] processedImageBytes;
            try(ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
                ImageIO.write(changedImage, "png", stream);
                processedImageBytes = stream.toByteArray();
            }
            return processedImageBytes;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] compress(MultipartFile imageFile, int height, int width) {

        try {
            BufferedImage originalImage = ImageIO.read(imageFile.getInputStream());

            BufferedImage changedImage = new BufferedImage(
                    width
                    , height
                    , BufferedImage.TYPE_INT_RGB
            );


            changedImage.createGraphics().drawImage(originalImage, 0, 0, width, height, null);



            byte[] processedImageBytes;
            try(ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
                ImageIO.write(changedImage, "png", stream);
                processedImageBytes = stream.toByteArray();
            }
            return processedImageBytes;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] crop(MultipartFile imageFile, int[] coords) {

        //x, y, OX, OY
        int coordX = coords[0];
        int coordY = coords[1];
        int xx1 = coords[2];
        int yy1 = coords[3];

        try {
            BufferedImage originalImage = ImageIO.read(imageFile.getInputStream());

            BufferedImage changedImage = new BufferedImage(
                    xx1
                    , yy1
                    , BufferedImage.TYPE_INT_RGB
            );

            WritableRaster raster = originalImage.getRaster();
            WritableRaster raster2 = changedImage.getRaster();

            for (int x = coordX; x < coordX + xx1; x++) {
                for (int y = coordY; y < coordY + yy1; y++) {
                    int[] pixel = raster.getPixel(x, y, new int[4]);
                    raster2.setPixel(x - coordX, y - coordY, pixel);
                }
            }

            changedImage.setData(raster2);

            byte[] processedImageBytes;
            try(ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
                ImageIO.write(changedImage, "png", stream);
                processedImageBytes = stream.toByteArray();
            }
            return processedImageBytes;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
