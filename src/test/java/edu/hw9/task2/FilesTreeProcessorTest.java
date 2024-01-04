package edu.hw9.task2;

import java.io.File;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class FilesTreeProcessorTest {

    @SneakyThrows
    @Test
    @DisplayName("Тестирование FilesTreeProcessor#searchDirectories")
    public void searchDirectories_shouldReturnDirectories_withFilesMoreThan2(@TempDir File tempDir) {
        initializeDirectory(tempDir);
        Assertions.assertThat(FilesTreeProcessor.searchDirectories(tempDir, 2))
            .containsExactlyInAnyOrder(new File(tempDir, "dir2"), new File(tempDir, "dir2/dir4"));
    }

    @SneakyThrows
    @Test
    @DisplayName("Тестирование FilesTreeProcessor#filterRecursively")
    public void filterRecursively_shouldReturnDirectories_withFilesMoreThan2(@TempDir File tempDir) {
        initializeDirectory(tempDir);
        Assertions.assertThat(FilesTreeProcessor.filterRecursively(tempDir, file -> file.getName().endsWith(".txt")))
            .containsExactlyInAnyOrder(
                new File(tempDir, "dir2/file1.txt"),
                new File(tempDir, "dir3/file3.txt"),
                new File(tempDir, "dir2/dir4/file4.txt"),
                new File(tempDir, "dir2/dir4/file5.txt")
            );
    }

    @SneakyThrows
    private static void initializeDirectory(File directory) {
        File dir2 = createDirectory(directory, "dir2");
        File dir3 = createDirectory(directory, "dir3");
        createFile(dir2, "file1.txt");
        createFile(dir2, "file2.ml");
        createFile(dir3, "file3.txt");

        File dir4 = createDirectory(dir2, "dir4");
        createFile(dir4, "file4.txt");
        createFile(dir4, "file5.txt");
    }

    private static File createDirectory(File parent, String name) {
        File directory = new File(parent, name);
        directory.mkdir();
        return directory;
    }

    @SneakyThrows
    private static File createFile(File parent, String name) {
        File directory = new File(parent, name);
        directory.createNewFile();
        return directory;
    }

}
