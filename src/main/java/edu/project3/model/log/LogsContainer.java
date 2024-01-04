package edu.project3.model.log;

import java.util.List;

public record LogsContainer(List<NginxLog> logs, LogsMetadata metadata) {
}
