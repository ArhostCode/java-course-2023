package edu.hw6.task2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileCloneUtils {

    private FileCloneUtils() {
    }

    public static void cloneFile(Path path) {
        String dirName = path.getParent().toString();
        String fileName = path.getFileName().toString();

        int pointIndex = fileName.indexOf(".", 1);
        pointIndex = pointIndex == -1 ? fileName.length() : pointIndex;
        String baseFileName = fileName.substring(0, pointIndex);
        String extensions = fileName.substring(Math.min(pointIndex + 1, fileName.length()));

        Path copyPath = Path.of(dirName, baseFileName + " — копия." + extensions);
        int currentId = 2;
        while (Files.exists(copyPath)) {
            copyPath = Paths.get(dirName, "%s — копия (%d).%s".formatted(baseFileName, currentId, extensions));
            currentId++;
        }

        try {
            Files.copy(path, copyPath);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
