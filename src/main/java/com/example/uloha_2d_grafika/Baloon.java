package com.example.uloha_2d_grafika;

import javafx.scene.image.Image;

import java.net.URL;
import java.util.List;

public class Baloon extends Rectangle {
    public static final String TEXTURE_CENTER = "/images/pohyb20.png";
    public static final List<String> STANDARD_TEXTURES = List.of("pohyb1.png", "pohyb2.png", "pohyb3.png", "pohyb4.png", "pohyb5.png", "pohyb6.png", "pohyb7.png", "pohyb8.png", "pohyb9.png", "pohyb10.png", "pohyb11.png", "pohyb12.png", "pohyb13.png", "pohyb14.png", "pohyb15.png", "pohyb16.png", "pohyb17.png", "pohyb18.png", "pohyb19.png", "pohyb20.png");
    public static final List<String> POP_TEXTURES = List.of("pop1.png","pop2.png", "pop3.png", "pop4.png", "pop5.png", "pop6.png", "pop7.png", "pop8.png", "pop9.png", "pop10.png", "pop11.png");
    Image[] images;

    public Baloon(int x, int y) {
        super(x, y);
        loadTexture();
    }

    public Baloon(int x, int y, int height, int width) {
        super(x, y, height, width);
        loadTexture();
    }

    private void loadTexture() {
        images = new Image[1];
        URL imageURLCenter = getClass().getResource(TEXTURE_CENTER);
        if (imageURLCenter != null){
            images[0] = new Image(imageURLCenter.toString());
            setWidth((int) images[0].getWidth()/2);
            setHeight((int) images[0].getHeight()/2);
        }
        else {
            System.out.println("Texture could not be loaded.");
        }
    }

    public Image[] getImages() {
        return images;
    }
}
