package edu.hw6.task4;

import edu.hw6.task4.OutputStreamComposer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OutputStreamComposerTest {

    @Test
    @DisplayName("Тестирование OutputStreamComposer#write")
    public void write_shouldWriteToFile() throws IOException {
        Path path = Files.createTempFile("test", ".txt");
        OutputStreamComposer.write(path);
        Assertions.assertThat(Files.readString(path))
            .isEqualTo("Programming is learned by writing programs. ― Brian Kernighan");
    }

}
