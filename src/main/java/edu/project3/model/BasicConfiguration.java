package edu.project3.model;

import edu.project3.renderer.Renderer;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record BasicConfiguration(
    List<String> paths,
    OffsetDateTime from,
    OffsetDateTime to,
    Renderer renderer,
    boolean isRemote
) {
}
