package edu.project4.processor;

import edu.project4.model.action.ImageProcessor;
import edu.project4.model.image.FractalImage;
import edu.project4.model.image.Pixel;
import java.util.ArrayList;
import java.util.List;

public class ColorMixerImageProcessor implements ImageProcessor {
    @Override
    public void process(FractalImage image) {
        for (int y = 0; y < image.height(); y += 5) {
            for (int x = 0; x < image.width(); x += 5) {
                List<Pixel> pixels = getPixels(image, x, y, 5, 5);
                int red = (int) pixels.stream().mapToInt(Pixel::getR).average().orElse(0);
                int green = (int) pixels.stream().mapToInt(Pixel::getG).average().orElse(0);
                int blue = (int) pixels.stream().mapToInt(Pixel::getB).average().orElse(0);
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
