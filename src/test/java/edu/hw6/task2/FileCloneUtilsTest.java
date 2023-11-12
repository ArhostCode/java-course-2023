package edu.hw6.task2;

import edu.hw6.task2.FileCloneUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileCloneUtilsTest {

    @Test
    @DisplayName("Тестирование FileCloneUtils#clone")
    public void clone_shouldCreateFileClone_whenFirstTime() throws IOException {
        Path filePath = Files.createTempFile("test", ".txt");
        String name = filePath.getFileName().toString();
        String baseName = name.substring(0, name.lastIndexOf("."));
        String extension = name.substring(name.lastIndexOf("."));

        Path copyPath = Path.of(filePath.getParent().toString(), baseName + " — копия" + extension);

        FileCloneUtils.cloneFile(filePath);
        Assertions.assertThat(Files.exists(copyPath)).isTrue();

        Files.delete(copyPath);
        Files.delete(filePath);
    }

    @Test
    @DisplayName("Тестирование FileCloneUtils#clone")
    public void clone_shouldCreateFileClone_whenSecondTime() throws IOException {
        Path filePath = Files.createTempFile("test", ".txt");
        String name = filePath.getFileName().toString();
        String baseName = name.substring(0, name.lastIndexOf("."));
        String extension = name.substring(name.lastIndexOf("."));

        Path copyPath1 = Path.of(filePath.getParent().toString(), baseName + " — копия" + extension);
        Path copyPath2 = Path.of(filePath.getParent().toString(), baseName + " — копия (2)" + extension);

        FileCloneUtils.cloneFile(filePath);
        FileCloneUtils.cloneFile(filePath);
        Assertions.assertThat(Files.exists(copyPath1)).isTrue();
        Assertions.assertThat(Files.exists(copyPath2)).isTrue();

        Files.delete(copyPath1);
        Files.delete(copyPath2);
        Files.delete(filePath);
    }

}
