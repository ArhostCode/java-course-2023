package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.StreamSupport;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw6.task3.AbstractFilter.globMatches;
import static edu.hw6.task3.AbstractFilter.largerThan;
import static edu.hw6.task3.AbstractFilter.magicNumber;
import static edu.hw6.task3.AbstractFilter.regexContains;

public class AbstractFilterTest {

    @Test
    @DisplayName("Тестирование AbstractFilter#largerThan")
    public void largerThan_shouldReturnCorrectPaths() throws IOException {
        List<Path> paths = prepareFiles();
        DirectoryStream.Filter<Path> filter = largerThan(5);

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(paths.get(0).getParent(), filter)) {
            List<Path> resolvedPaths = StreamSupport.stream(entries.spliterator(), false).toList();
            Assertions.assertThat(resolvedPaths).containsExactly(paths.get(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Тестирование AbstractFilter#magicNumber")
    public void magicNumber_shouldReturnCorrectPaths() throws IOException {
        List<Path> paths = prepareFiles();
        DirectoryStream.Filter<Path> filter = magicNumber(0x89, 'P', 'N', 'G');

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(paths.get(0).getParent(), filter)) {
            List<Path> resolvedPaths = StreamSupport.stream(entries.spliterator(), false).toList();
            Assertions.assertThat(resolvedPaths).containsExactly(paths.get(4), paths.get(5));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Тестирование AbstractFilter#globMatches")
    public void globMatches_shouldReturnCorrectPaths() throws IOException {
        List<Path> paths = prepareFiles();
        DirectoryStream.Filter<Path> filter = globMatches("*.txt");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(paths.get(0).getParent(), filter)) {
            List<Path> resolvedPaths = StreamSupport.stream(entries.spliterator(), false).toList();
            Assertions.assertThat(resolvedPaths)
                .containsExactlyInAnyOrder(paths.get(0), paths.get(1), paths.get(2), paths.get(3));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Тестирование AbstractFilter#regexContains")
    public void regexContains_shouldReturnCorrectPaths() throws IOException {
        List<Path> paths = prepareFiles();
        DirectoryStream.Filter<Path> filter = regexContains("g\\d");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(paths.get(0).getParent(), filter)) {
            List<Path> resolvedPaths = StreamSupport.stream(entries.spliterator(), false).toList();
            Assertions.assertThat(resolvedPaths)
                .containsExactlyInAnyOrder(paths.get(4), paths.get(5));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Path> prepareFiles() throws IOException {
        Path directory = Files.createTempDirectory("test");
        directory.toFile().deleteOnExit();
        List<Path> files = List.of(
            Files.createTempFile(directory, "test", ".txt"),
            Files.createTempFile(directory, "kest", ".txt"),
            Files.createTempFile(directory, "k-st", ".txt"),
            Files.createTempFile(directory, "gor", ".txt"),
            Files.createTempFile(directory, "g1", ".png"),
            Files.createTempFile(directory, "g2", ".png")
        );
        files.forEach(path -> {
            path.toFile().deleteOnExit();
            if (path.getFileName().toString().endsWith(".png")) {
                try {
                    Files.write(path, new byte[] {(byte) 0x89, 'P', 'N', 'G'});
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Files.writeString(files.get(0), "Programming is learned by writing programs. ― Brian Kernighan");
        return files;
    }

}
