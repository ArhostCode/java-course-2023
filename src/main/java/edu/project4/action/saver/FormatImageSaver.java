package edu.project4.action.saver;

import edu.project4.model.image.FractalImage;
import edu.project4.model.image.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class FormatImageSaver implements ImageSaver {

    private final ImageFormat format;

    @SneakyThrows
    @Override
    public void save(FractalImage image, Path path) {
        BufferedImage renderedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                Color color = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
                renderedImage.setRGB(x, y, color.getRGB());
            }
        }
        ImageIO.write(renderedImage, format.getFormatName(), path.toFile());
    }
}
