package edu.hw2.task2;

public class Rectangle {

    private final int height;
    private final int width;

    public Rectangle() {
        this(0, 0);
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    // Immutable class return new object with given width and actual height
    public final Rectangle withWidth(int width) {
        return new Rectangle(width, height);
    }

    // Immutable class return new object with given height and actual width
    public final Rectangle withHeight(int height) {
        return new Rectangle(width, height);
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
