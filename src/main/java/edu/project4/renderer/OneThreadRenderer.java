package edu.project4.renderer;

import edu.project4.model.image.FractalImage;
import edu.project4.model.world.Rect;
import edu.project4.transforms.AffineTransformation;
import edu.project4.transforms.Transformation;
import java.util.List;

public class OneThreadRenderer extends AbstractRenderer {

    public OneThreadRenderer(
        int affineCount,
        int samples,
        int iterPerSample,
        int symmetry,
        List<Transformation> variations
    ) {
        super(affineCount, samples, iterPerSample, symmetry, variations);
    }

    @Override
    public void renderAllImage(
        FractalImage image,
        Rect world,
        List<AffineTransformation> affineTransformations
    ) {
        for (int i = 0; i < samples; i++) {
            renderOneSample(image, world, affineTransformations);
        }
    }
}
