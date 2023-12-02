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
import edu.project4.transforms.HeartTransformation;
import java.nio.file.Path;
import java.util.List;

public final class FractalFlameGenerator {

    private FractalFlameGenerator() {
    }

    public static void generate(
        int width,
        int height,
        Rect area,
        Renderer renderer,
        List<ImageProcessor> processors,
        Path path,
        ImageFormat format
    ) {
        FractalImage image = renderer.render(width, height, area);
        for (ImageProcessor processor : processors) {
            processor.process(image);
        }
        ImageSaver saver = new FormatImageSaver(format);
        saver.save(image, path);
    }

    @SuppressWarnings({"checkstyle:UncommentedMain", "checkstyle:MagicNumber"})
    public static void main(String[] args) {
        generate(
            2000,
            1000,
            new Rect(-4, -3, 8, 6),
            new MultiThreadRenderer(
                5, 5, 1000000,
                5, List.of(new HeartTransformation())
            ),
            List.of(new LogGammaCorrectionImageProcessor()),
            Path.of("image.png"),
            ImageFormat.PNG
        );
    }
}
