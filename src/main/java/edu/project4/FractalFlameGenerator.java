package edu.project4;

import edu.project4.model.action.ImageProcessor;
import edu.project4.model.action.ImageSaver;
import edu.project4.model.image.FractalImage;
import edu.project4.model.saver.PngImageSaver;
import edu.project4.model.world.Rect;
import edu.project4.processor.LogGammaCorrectionImageProcessor;
import edu.project4.transforms.HeartTransformation;
import edu.project4.transforms.HyperbolicTransformation;
import edu.project4.transforms.SphereTransformation;
import java.nio.file.Path;
import java.util.List;

public class FractalFlameGenerator {

    public static void main(String[] args) {

        int width = 4000;
        int height = 2000;
        FractalImage image = FractalImage.create(width, height);
        Renderer renderer = new MultiThreadRenderer();
        image = renderer.render(
            image,
            new Rect(-4, -3, 8, 6),
            List.of(
                new HyperbolicTransformation(),
                new HeartTransformation()
            ),
            2,
            1000000,
            5L,
            (long) (Math.random() * 100000)
        );

//        ImageProcessor processor = new LogGammaCorrectionImageProcessor();
//        processor.process(image);
        ImageSaver saver = new PngImageSaver();
        saver.save(image, Path.of("image.png"));
    }
}
