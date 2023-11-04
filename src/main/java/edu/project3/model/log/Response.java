package edu.project3.model.log;

import lombok.Builder;

@Builder
public record Response(int code, int bytesSend) {
}
