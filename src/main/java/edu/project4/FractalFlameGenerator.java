package edu.project4;

import edu.project4.action.processor.ImageProcessor;
import edu.project4.action.saver.ImageSaver;
import edu.project4.model.image.FractalImage;
import edu.project4.action.saver.PngImageSaver;
import edu.project4.model.world.Rect;
import edu.project4.action.processor.LogGammaCorrectionImageProcessor;
import edu.project4.renderer.OneThreadRenderer;
import edu.project4.renderer.Renderer;
import edu.project4.transforms.HeartTransformation;
import java.nio.file.Path;
import java.util.List;

public class FractalFlameGenerator {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        int width = 2000;
        int height = 1000;
        FractalImage image;
        Renderer renderer = new OneThreadRenderer(
            5,
            5,
            1000000,
            5,
            List.of(
                new HeartTransformation()
            )
        );
        image = renderer.render(width, height, new Rect(-4, -3, 8, 6));
        ImageProcessor processor = new LogGammaCorrectionImageProcessor();
        processor.process(image);
        System.out.println((System.currentTimeMillis() - start) / 1000.0);
        ImageSaver saver = new PngImageSaver();
        saver.save(image, Path.of("image.png"));
    }
}
