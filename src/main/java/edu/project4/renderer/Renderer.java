package edu.project4.renderer;

import edu.project4.model.image.FractalImage;
import edu.project4.model.world.Rect;

@FunctionalInterface
public interface Renderer {
    FractalImage render(int width, int height, Rect world);
}
