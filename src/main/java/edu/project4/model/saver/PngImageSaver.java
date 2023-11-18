package edu.project4.model.saver;

import edu.project4.model.action.ImageSaver;
import edu.project4.model.image.FractalImage;
import edu.project4.model.image.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public class PngImageSaver implements ImageSaver {
    @Override
    public void save(FractalImage image, Path path) {
        BufferedImage png = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                Color color = new Color(pixel.getR(), pixel.getG(), pixel.getB());
                png.setRGB(x, y, color.getRGB());
            }
        }
        try {
            ImageIO.write(png, "png", path.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
