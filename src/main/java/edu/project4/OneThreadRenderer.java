package edu.project4;

import edu.project4.model.AffineCoefficient;
import edu.project4.model.image.FractalImage;
import edu.project4.model.image.Pixel;
import edu.project4.model.world.Point;
import edu.project4.model.world.Rect;
import edu.project4.transforms.AffineTransformation;
import edu.project4.transforms.Transformation;
import edu.project4.utils.FractalImageUtils;
import edu.project4.utils.ListUtils;
import edu.project4.utils.RectUtils;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OneThreadRenderer implements Renderer {
    private static final int STEPS_FOR_NORMALIZATION = 20;

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        long symmetry,
        long seed
    ) {
        // Generate random affine transformations
        List<AffineTransformation> affineTransformations = new ArrayList<>();
        Random random = new Random(seed);
        for (int i = 0; i < samples; i++) {
            affineTransformations.add(new AffineTransformation(AffineCoefficient.generateRandom(random)));
        }

        FractalImage image = new FractalImage(canvas.data(), canvas.width(), canvas.height());

        for (int num = 0; num < samples; ++num) {
            Point currentPoint = RectUtils.randomPoint(world);
            for (int step = -STEPS_FOR_NORMALIZATION; step < iterPerSample; ++step) {
                AffineTransformation affine = ListUtils.random(affineTransformations);
                Transformation variation = ListUtils.random(variations);
                currentPoint = affine.apply(currentPoint);
                currentPoint = variation.apply(currentPoint);
                currentPoint =
                    new Point(currentPoint.x(), currentPoint.y());
//                System.out.println(currentPoint);
                if (step > 0) {
                    double theta2 = 0.0;
                    for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, ++s) {
                        var pwr = RectUtils.rotatePoint(world, currentPoint, theta2);
                        if (!world.contains(pwr)) {
                            continue;
                        }

                        Pixel pixel = FractalImageUtils.resolvePixel(world, pwr, image);
                        if (pixel != null) {
                            Color color = affine.getAffineCoefficient().color();

                            if (pixel.getHitCount() == 0) {
                                pixel.setRGB(color.getRed(), color.getGreen(), color.getBlue());
                            } else {
                                pixel.setR((pixel.getR() + color.getRed()) / 2);
                                pixel.setG((pixel.getG() + color.getGreen()) / 2);
                                pixel.setB((pixel.getB() + color.getBlue()) / 2);
                            }
                            pixel.increaseHitCount();
                        }
                    }
                }
            }
        }
        return image;
    }
}
