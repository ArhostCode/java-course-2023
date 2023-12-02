package edu.project4.saver;

import edu.project4.action.saver.FormatImageSaver;
import edu.project4.action.saver.ImageFormat;
import edu.project4.model.image.FractalImage;
import java.io.File;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class FormatImageSaverTest {

    @Test
    @DisplayName("Тестирование FormatImageSaver#save")
    public void save_shouldSaveImage(@TempDir File tempDir) {
        FractalImage image = FractalImage.create(10, 10);
        new FormatImageSaver(ImageFormat.PNG).save(image, new File(tempDir, "test.png").toPath());
        Assertions.assertThat(new File(tempDir, "test.png").exists()).isTrue();
    }
}
