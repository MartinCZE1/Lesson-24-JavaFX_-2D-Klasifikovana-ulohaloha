package com.example.uloha_2d_grafika;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;


public class MainApplication extends Application {
    public static final int SCREEN_WIDTH = 1900;
    public static final int SCREEN_HEIGHT = 920;
    public boolean stop = false;
    public int speed = 10;
    double clickedX;
    double clickedY;

    GraphicsContext graphicsContext;

    int pickRandomDirection = 1;

    Baloon baloon;
    Image texture;
    Image popTexture;

    boolean isClicked;

    Random rd = new Random();

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        Canvas canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        root.getChildren().add(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Klasifikovaná úloha");
        stage.show();

        baloon = new Baloon(rd.nextInt(SCREEN_WIDTH - 100), rd.nextInt(SCREEN_HEIGHT - 100));

        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            System.out.println("Clicked on " + mouseEvent.getX() + " " + mouseEvent.getY());
            clickedX = mouseEvent.getX();
            clickedY = mouseEvent.getY();

            if (clickedX >= baloon.x && clickedX <= baloon.x + baloon.width && clickedY >= baloon.y && clickedY <= baloon.y + baloon.height) {
                System.out.println("The baloon has been popped.");
                isClicked = true;
                baloon.setX(rd.nextInt(SCREEN_WIDTH - 100));
                baloon.setY(rd.nextInt(SCREEN_HEIGHT - 100));
                pickRandomDirection = rd.nextInt(3) + 1;
                System.out.println(pickRandomDirection);
            }
        });

        AnimationTimer animationTimer = new AnimationTimer() {
            long lastTick = 0;

            @Override
            public void handle(long now) {
                if (now - lastTick > 1000000000l / speed) {
                    lastTick = now;
                    tick();
                }
            }
        };
        animationTimer.start();
    }

    private void tick() {
        if (stop) {
            return;
        }

        clearScreen();
        texture = baloon.loadTexture();
        popTexture = baloon.loadPopTexture();

        if (pickRandomDirection == 1) { // upRight
            if (baloon.y <= 0 || baloon.x >= SCREEN_WIDTH - baloon.width) {
                pickRandomDirection = 2;
            } else {
                baloon.decrementY();
                baloon.incrementX();
            }
        } else if (pickRandomDirection == 2) { // upLeft
            if (baloon.y <= 0 || baloon.x <= 0) {
                pickRandomDirection = 3;
            } else {
                baloon.decrementY();
                baloon.decrementX();
            }
        } else if (pickRandomDirection == 3) { // downRight
            if (baloon.y >= SCREEN_HEIGHT - baloon.height || baloon.x >= SCREEN_WIDTH - baloon.width) {
                pickRandomDirection = 4;
            } else {
                baloon.incrementY();
                baloon.incrementX();
            }
        } else if (pickRandomDirection == 4) { // downLeft
            if (baloon.y >= SCREEN_HEIGHT - baloon.height || baloon.x <= 0) {
                pickRandomDirection = 1;
            } else {
                baloon.incrementY();
                baloon.decrementX();
            }
        }

        if (isClicked) {
            graphicsContext.drawImage(popTexture, baloon.x, baloon.y, baloon.height, baloon.height);
            isClicked = false;
        } else {
            graphicsContext.drawImage(texture, baloon.x, baloon.y, baloon.height, baloon.height);
        }
    }

    private void clearScreen() {
        graphicsContext.clearRect(0, 0, SCREEN_WIDTH, SCREEN_WIDTH);
    }


    public static void main(String[] args) {
        launch();
    }
}