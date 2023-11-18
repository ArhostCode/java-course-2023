package edu.project4.model.action;

import edu.project4.model.image.FractalImage;

@FunctionalInterface
public interface ImageProcessor {
    void process(FractalImage image);
}
