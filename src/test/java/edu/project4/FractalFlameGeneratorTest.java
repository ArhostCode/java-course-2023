package edu.project4;

import edu.project4.action.processor.LogGammaCorrectionImageProcessor;
import edu.project4.action.saver.ImageFormat;
import edu.project4.model.world.Rect;
import edu.project4.renderer.MultiThreadRenderer;
import edu.project4.transforms.HeartTransformation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.util.List;

public class FractalFlameGeneratorTest {
    @Test
    @DisplayName("Тестирование FractalFlameGenerator#generate")
    public void generate_shouldGenerateFlame(@TempDir File tempDir) {
        FractalFlameGenerator.generate(
            2000,
            1000,
            new Rect(-4, -3, 8, 6),
            new MultiThreadRenderer(
                5, 5, 100,
                5, List.of(new HeartTransformation())
            ),
            List.of(new LogGammaCorrectionImageProcessor()),
            new File(tempDir, "image.png").toPath(),
            ImageFormat.PNG
        );

        Assertions.assertThat(new File(tempDir, "image.png").exists()).isTrue();
    }
}
