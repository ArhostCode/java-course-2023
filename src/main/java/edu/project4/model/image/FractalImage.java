package edu.project4.model.image;

public record FractalImage(Pixel[] data, int width, int height) {
    public static FractalImage create(int width, int height) {
        Pixel[] data = new Pixel[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                data[y * width + x] = new Pixel(0, 0, 0, 0,0);
            }
        }
        return new FractalImage(data, width, height);
    }

    public boolean contains(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public synchronized Pixel pixel(int x, int y) {
        if (!contains(x, y)) {
            return null;
        }
        return data[y * width + x];
    }
}
