package com.example.uloha_2d_grafika;

public class Rectangle {
    int x;
    int y;
    int height;
    int width;

    public Rectangle(int x, int y) {
        this.x = x;
        this.y = y;
        this.height = 25;
        this.width = 25;
    }

    public Rectangle(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void incrementX() {
        x += 5;
    }

    public void decrementX() {
        x -= 5;
    }

    public void incrementY() {
        y += 5;
    }

    public void decrementY() {
        y -= 5;
    }
}
