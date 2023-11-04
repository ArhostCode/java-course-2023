package edu.project3.log;

import edu.project3.model.log.NginxLog;
import edu.project3.model.log.Request;
import edu.project3.model.log.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class BaseNginxLogParserTest {

    @Test
    @DisplayName("Тестирование BaseNginxLogParser#parse на верных данных")
    public void parse_shouldReturnListOfNginxLog() {
        List<String> given = List.of(
            "199.111.24.111 - - [06/Oct/2023:06:11:11 +0000] \"GET /Balanced/monitoring/systematic_Decentralized-Diverse.jpg HTTP/1.1\" 200 2069 \"-\" \"Mozilla/5.0 (Macintosh; U; PPC Mac OS X 10_6_9 rv:4.0) Gecko/1951-30-10 Firefox/35.0\"",
            "27.170.230.206 - - [06/Oct/2023:06:11:11 +0000] \"GET /local%20area%20network.jpg HTTP/1.1\" 500 113 \"-\" \"Mozilla/5.0 (iPad; CPU OS 7_2_1 like Mac OS X; en-US) AppleWebKit/532.29.4 (KHTML, like Gecko) Version/5.0.5 Mobile/8B115 Safari/6532.29.4\"",
            "138.181.72.75 - - [06/Oct/2023:06:11:11 +0000] \"GET /hierarchy-Implemented-Polarised.php HTTP/1.1\" 200 1710 \"-\" \"Mozilla/5.0 (X11; Linux i686; rv:7.0) Gecko/1935-19-12 Firefox/37.0\""
        );

        Assertions.assertThat(new BaseNginxLogParser().parse(given))
            .containsExactly(
                createBuilder()
                    .ip("199.111.24.111")
                    .request(Request.builder()
                        .method("GET")
                        .url("/Balanced/monitoring/systematic_Decentralized-Diverse.jpg")
                        .protocol("HTTP/1.1")
                        .userAgent(
                            "Mozilla/5.0 (Macintosh; U; PPC Mac OS X 10_6_9 rv:4.0) Gecko/1951-30-10 Firefox/35.0")
                        .build())
                    .response(Response.builder()
                        .code(200)
                        .bytesSend(2069)
                        .build())
                    .build(),
                createBuilder()
                    .ip("27.170.230.206")
                    .request(Request.builder()
                        .method("GET")
                        .url("/local%20area%20network.jpg")
                        .protocol("HTTP/1.1")
                        .userAgent(
                            "Mozilla/5.0 (iPad; CPU OS 7_2_1 like Mac OS X; en-US) AppleWebKit/532.29.4 (KHTML, like Gecko) Version/5.0.5 Mobile/8B115 Safari/6532.29.4")
                        .build())
                    .response(Response.builder()
                        .code(500)
                        .bytesSend(113)
                        .build())
                    .build(),
                createBuilder()
                    .ip("138.181.72.75")
                    .request(Request.builder()
                        .method("GET")
                        .url("/hierarchy-Implemented-Polarised.php")
                        .protocol("HTTP/1.1")
                        .userAgent("Mozilla/5.0 (X11; Linux i686; rv:7.0) Gecko/1935-19-12 Firefox/37.0")
                        .build())
                    .response(Response.builder()
                        .code(200)
                        .bytesSend(1710)
                        .build())
                    .build()
            );
    }

    private NginxLog.NginxLogBuilder createBuilder() {
        OffsetDateTime time = OffsetDateTime.of(2023, 10, 6, 6, 11, 11, 0, ZoneOffset.UTC);

        return NginxLog.builder()
            .userName("-")
            .timeStamp(time)
            .refer("-");
    }

    @Test
    @DisplayName("Тестирование BaseNginxLogParser#parse на неверных данных")
    public void parse_shouldThrowExceptionWhenLogIsInvalid() {
        List<String> given = List.of(
            "199.111.24.111 [06/Oct/2023:06:11:11 +0000] \"GET /Balanced/monitoring/systematic_Decentralized-Diverse.jpg HTTP/1.1\" 200 2069 \"-\" \"Mozilla/5.0 (Macintosh; U; PPC Mac OS X 10_6_9 rv:4.0) Gecko/1951-30-10 Firefox/35.0\""
        );
        Assertions.assertThatThrownBy(() -> new BaseNginxLogParser().parse(given))
            .isInstanceOf(IllegalStateException.class);
    }

}
