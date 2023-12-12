package edu.project3.model.log;

import java.time.OffsetDateTime;
import lombok.Builder;

@Builder
public record NginxLog(
    String ip,
    String userName,
    OffsetDateTime timeStamp,
    Request request,
    Response response,
    String refer
) {

}
