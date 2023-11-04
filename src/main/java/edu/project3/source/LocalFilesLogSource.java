package edu.project3.source;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LocalFilesLogSource implements LogSource {

    private final List<String> paths;

    @Override
    public List<String> getLogs() {
        List<String> logs = new ArrayList<>();
        for (String path : paths) {
            logs.addAll(getLogsInFile(path));
        }
        return logs;
    }

    private List<String> getLogsInFile(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
