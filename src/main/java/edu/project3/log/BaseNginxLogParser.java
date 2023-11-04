package edu.project3.log;

import edu.project3.model.log.NginxLog;
import edu.project3.model.log.Request;
import edu.project3.model.log.Response;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseNginxLogParser implements NginxLogParser {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(
        "dd/LLL/yyyy:HH:mm:ss ZZ",
        Locale.US
    );

    @Override
    public List<NginxLog> parse(List<String> lines) {
        return lines.stream().map(this::parseLine).toList();
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private NginxLog parseLine(String line) {
        Matcher matcher = Pattern.compile("(.*) - (.*) \\[(.*)\\] \"(.*) (.*) (.*)\" (\\d+) (\\d+) \"(.*)\" \"(.*)\"")
            .matcher(line);
        if (!matcher.matches()) {
            throw new IllegalStateException("Log " + line + " is invalid");
        }
        return NginxLog
            .builder()
            .ip(matcher.group(1))
            .userName(matcher.group(2))
            .refer(matcher.group(9))
            .request(Request
                .builder()
                .method(matcher.group(4))
                .url(matcher.group(5))
                .protocol(matcher.group(6))
                .userAgent(matcher.group(10))
                .build())
            .response(Response
                .builder()
                .code(Integer.parseInt(matcher.group(7)))
                .bytesSend(Integer.parseInt(matcher.group(8)))
                .build())
            .timeStamp(OffsetDateTime.parse(matcher.group(3), dateTimeFormatter))
            .build();
    }
}
