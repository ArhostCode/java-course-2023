package edu.project4.model.action;

import edu.project4.model.image.FractalImage;
import java.nio.file.Path;

public interface ImageSaver {
    void save(FractalImage image, Path path);
}
