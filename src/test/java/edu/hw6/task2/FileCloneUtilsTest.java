package edu.hw6.task2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileCloneUtilsTest {

    @Test
    @DisplayName("Тестирование FileCloneUtils#clone")
    public void clone_shouldCreateFileClone_whenFirstTime(@TempDir Path tempDirectory) throws IOException {
        Path filePath = Files.createFile(tempDirectory.resolve("test.txt"));
        Path copyPath = Path.of(filePath.getParent().toString(), "test — копия.txt");

        FileCloneUtils.cloneFile(filePath);
        Assertions.assertThat(Files.exists(copyPath)).isTrue();
    }

    @Test
    @DisplayName("Тестирование FileCloneUtils#clone")
    public void clone_shouldCreateFileClone_whenSecondTime(@TempDir Path tempDirectory) throws IOException {
        Path filePath = Files.createFile(tempDirectory.resolve("test.txt"));
        Path copyPath1 = Path.of(filePath.getParent().toString(), "test — копия.txt");
        Path copyPath2 = Path.of(filePath.getParent().toString(), "test — копия (2).txt");

        FileCloneUtils.cloneFile(filePath);
        FileCloneUtils.cloneFile(filePath);
        Assertions.assertThat(Files.exists(copyPath1)).isTrue();
        Assertions.assertThat(Files.exists(copyPath2)).isTrue();
    }

}
