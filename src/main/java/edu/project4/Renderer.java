package edu.project4;

import edu.project4.transforms.Transformation;
import edu.project4.model.image.FractalImage;
import edu.project4.model.world.Rect;
import java.util.List;

@FunctionalInterface
public interface Renderer {
    FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        long symmetry,
        long seed
    );
}
