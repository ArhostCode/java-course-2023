package edu.hw2.task2;

public class Rectangle {

    private int height;
    private int width;

    public Rectangle() {
    }

    public Rectangle(int height, int width) {
        this.width = width;
        this.height = height;
    }

    public final Rectangle setWidth(int width) {
        return new Rectangle(height, width);
    }

    public final Rectangle setHeight(int height) {
        return new Rectangle(height, width);
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
