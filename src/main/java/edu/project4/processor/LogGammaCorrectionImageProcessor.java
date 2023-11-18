package edu.project4.processor;

import edu.project4.model.action.ImageProcessor;
import edu.project4.model.image.FractalImage;
import edu.project4.model.image.Pixel;

public class LogGammaCorrectionImageProcessor implements ImageProcessor {
    @Override
    public void process(FractalImage image) {
        double max = 0.0;
        double gamma = 1.5; //2.2
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                if (image.pixel(x, y).getHitCount() != 0) {
                    image.pixel(x, y).setNormal(Math.log10(image.pixel(x, y).getHitCount()));
                    if (image.pixel(x, y).getNormal() > max) {
                        max = image.pixel(x, y).getNormal();
                    }
                }
            }
        }
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                pixel.setNormal(image.pixel(x, y).getNormal() / max);
                pixel.setR((int) (pixel.getR() * Math.pow(pixel.getNormal(), (1.0 / gamma))));
                pixel.setG((int) (pixel.getG() * Math.pow(pixel.getNormal(), (1.0 / gamma))));
                pixel.setB((int) (pixel.getB() * Math.pow(pixel.getNormal(), (1.0 / gamma))));
            }
        }

    }
}
