package edu.project3.collector;

import edu.project3.model.log.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class MetricCollectorTest {

    protected LogsContainer createLogsContainer() {
        return new LogsContainer(
            List.of(
                createBase().build(),
                createBase().build(),
                createBase()
                    .ip("2.2.2.2")
                    .build(),
                createBase()
                    .ip("5.5.5.5")
                    .build(),
                createBase()
                    .request(new Request("GET", "/help2", "HTTP/1.1", "help"))
                    .response(new Response(500, 20))
                    .build()
            ),
            new LogsMetadata(List.of("test"), OffsetDateTime.MIN, OffsetDateTime.MAX)
        );
    }

    private NginxLog.NginxLogBuilder createBase() {
        return NginxLog.builder()
            .ip("1.1.1.1")
            .refer("-")
            .userName("-")
            .timeStamp(OffsetDateTime.of(2023, 10, 6, 6, 11, 11, 0, ZoneOffset.UTC))
            .request(
                Request.builder()
                    .url("/help")
                    .protocol("HTTP/1.1")
                    .userAgent("help")
                    .build()
            ).response(
                Response.builder()
                    .code(200)
                    .bytesSend(2000)
                    .build()
            );
    }
}
