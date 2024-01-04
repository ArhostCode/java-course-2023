package edu.project3.source;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UrlLogSource implements LogSource {

    private final String url;

    @Override
    public List<String> getLogs() {
        try {
            InputStream inputStream = new URI(url).toURL().openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            return reader.lines().toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
