package edu.hw6.task3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.regex.Pattern;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    default AbstractFilter and(AbstractFilter filter) {
        return (path) -> accept(path) && filter.accept(path);
    }

    static AbstractFilter largerThan(int size) {
        return (path) -> path.toFile().length() > size;
    }

    static AbstractFilter magicNumber(int... magicNumbers) {
        return (path) -> {
            File file = path.toFile();
            try (InputStream inputStream = new FileInputStream(file)) {
                for (int magicNumber : magicNumbers) {
                    if (inputStream.read() != magicNumber) {
                        return false;
                    }
                }
            } catch (IOException e) {
                return false;
            }
            return true;
        };
    }

    static AbstractFilter globMatches(String glob) {
        return (path) -> {
            PathMatcher pathMatcher = path.getParent().getFileSystem().getPathMatcher("glob:" + glob);
            return pathMatcher.matches(path.getFileName());
        };
    }

    static AbstractFilter regexContains(String regex) {
        return (path) -> Pattern.compile(regex).matcher(path.toString()).find();
    }
}
