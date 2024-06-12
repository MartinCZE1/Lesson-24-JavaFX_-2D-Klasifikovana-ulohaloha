package com.example.uloha_2d_grafika;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollToEvent;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;


public class MainApplication extends Application {
    public static final int SCREEN_WIDTH = 1900;
    public static final int SCREEN_HEIGHT = 920;
    public boolean stop = false;
    public int speed = 1000000000;
    Direction direction = Direction.right;

    GraphicsContext graphicsContext;

    int pickRandomDirection;

    Baloon baloon;
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



        baloon = new Baloon(rd.nextInt(SCREEN_WIDTH), rd.nextInt(SCREEN_HEIGHT));

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
        Image texture = baloon.getImages()[0];
        pickRandomDirection = rd.nextInt(3) + 1;

        switch (pickRandomDirection) {
            case 1:
                stop = baloon.getY() < 0;
                if (!stop) baloon.decrementY();
                break;
            case 2:
                stop = baloon.getY() > SCREEN_HEIGHT - baloon.height;
                if (!stop) baloon.incrementY();
                break;
            case 3:
                stop = baloon.getX() < 0;
                if (!stop) baloon.decrementX();
                break;
            case 4:
                stop = baloon.getX() > SCREEN_WIDTH - baloon.height;
                if (!stop) baloon.incrementX();
                break;
        }

        graphicsContext.drawImage(texture, baloon.x, baloon.y, baloon.height, baloon.height);
    }

    private void clearScreen() {
        graphicsContext.clearRect(0, 0, SCREEN_WIDTH, SCREEN_WIDTH);
    }


    public static void main(String[] args) {
        launch();
    }
}