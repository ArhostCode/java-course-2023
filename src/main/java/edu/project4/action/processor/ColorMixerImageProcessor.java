package edu.project4.action.processor;

import edu.project4.model.image.FractalImage;
import edu.project4.model.image.Pixel;
import java.util.ArrayList;
import java.util.List;

public class ColorMixerImageProcessor implements ImageProcessor {
    @Override
    public void process(FractalImage image) {
        for (int y = 0; y < image.height(); y += 2) {
            for (int x = 0; x < image.width(); x += 2) {
                List<Pixel> pixels = getPixels(image, x, y, 2, 2);
                int red = (int) pixels.stream().mapToInt(Pixel::getRed).average().orElse(0);
                int green = (int) pixels.stream().mapToInt(Pixel::getGreen).average().orElse(0);
                int blue = (int) pixels.stream().mapToInt(Pixel::getBlue).average().orElse(0);
                pixels.forEach(pixel -> pixel.setRGB(red, green, blue));
            }
        }
    }

    private List<Pixel> getPixels(FractalImage image, int x, int y, int xCount, int yCount) {
        List<Pixel> pixels = new ArrayList<>();
        for (int i = y; i < y + yCount; i++) {
            for (int j = x; j < x + xCount; j++) {
                Pixel pixel = image.pixel(j, i);
                if (pixel != null) {
                    pixels.add(pixel);
                }
            }
        }
        return pixels;
    }
}
