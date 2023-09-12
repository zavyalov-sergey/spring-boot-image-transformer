package com.zavyalov.springboot_image_transformer.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;

@Component
public class ImageModel {
    private BufferedImage image;
    private Coords coords;


    public ImageModel() {
    }

    public Image getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Coords getCoordinates() {
        return coords;
    }

    public void setCoordinates(Coords coords) {
        this.coords = coords;
    }


    @Override
    public String toString() {
        if (getImage().equals(null)) {
            return "Image is null. Coords is: " + coords;
        } else {
            return "Image Exist! Coords is: " + coords;
        }
    }
}
