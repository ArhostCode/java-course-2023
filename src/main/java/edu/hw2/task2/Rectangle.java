package edu.hw2.task2;

public class Rectangle {
    private int width;
    private int height;

    public final Rectangle setWidth(int width) {
        this.width = width;
        return this;
    }

    public final Rectangle setHeight(int height) {
        this.height = height;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public double area() {
        return width * height;
    }
}
