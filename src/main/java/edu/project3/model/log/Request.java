package edu.project3.model.log;

import lombok.Builder;

@Builder
public record Request(String method, String url, String protocol, String userAgent) {
}
