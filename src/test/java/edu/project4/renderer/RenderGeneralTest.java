package edu.project4.renderer;

import edu.project4.model.world.Rect;
import edu.project4.renderer.MultiThreadRenderer;
import edu.project4.renderer.OneThreadRenderer;
import edu.project4.transforms.HeartTransformation;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RenderGeneralTest {

    @Test
    @DisplayName("Тестирование MultiThreadRender#render")
    public void render_shouldRenderImage_whenUsedMultiThread() {
        Assertions.assertDoesNotThrow(() -> {
            new MultiThreadRenderer(
                3, 3, 100, 5, List.of(new HeartTransformation())
            ).render(
                2000, 1000, new Rect(0, 0, 2, 2)
            );
        });
    }

    @Test
    @DisplayName("Тестирование MultiThreadRender#render")
    public void render_shouldRenderImage_whenUsedOneThread() {
        Assertions.assertDoesNotThrow(() -> {
            new OneThreadRenderer(
                3, 3, 100, 5, List.of(new HeartTransformation())
            ).render(
                2000, 1000, new Rect(0, 0, 2, 2)
            );
        });
    }

}
