package edu.project3.model.log;

import java.time.OffsetDateTime;
import java.util.List;

public record LogsMetadata(List<String> paths, OffsetDateTime from, OffsetDateTime to) {
}
