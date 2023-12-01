package edu.project4;

import edu.project4.action.processor.ImageProcessor;
import edu.project4.action.processor.LogGammaCorrectionImageProcessor;
import edu.project4.action.saver.FormatImageSaver;
import edu.project4.action.saver.ImageFormat;
import edu.project4.action.saver.ImageSaver;
import edu.project4.model.image.FractalImage;
import edu.project4.model.world.Rect;
import edu.project4.renderer.MultiThreadRenderer;
import edu.project4.renderer.Renderer;
import edu.project4.transforms.SphereTransformation;
import java.nio.file.Path;
import java.util.List;

public final class FractalFlameGenerator {

    private FractalFlameGenerator() {
    }

    public static void generate() {
        int width = 2000;
        int height = 1000;
        FractalImage image;
        Renderer renderer = new MultiThreadRenderer(
            5,
            5,
            1000000,
            2,
            List.of(
                new SphereTransformation()
            )
        );
        image = renderer.render(width, height, new Rect(-4, -3, 8, 6));
        ImageProcessor processor = new LogGammaCorrectionImageProcessor();
        processor.process(image);
//        System.out.println((System.currentTimeMillis() - start) / 1000.0);
        ImageSaver saver = new FormatImageSaver(ImageFormat.BMP);
        saver.save(image, Path.of("image.bmp"));
    }

    public static void main(String[] args) {
        generate();
    }
}
