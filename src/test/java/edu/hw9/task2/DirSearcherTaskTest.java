package edu.hw9.task2;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class DirSearcherTaskTest {

    volatile Path path;

    @SneakyThrows
    @Test
    public void test() {
        System.out.println(FilesTreeProcessor.filterRecursively(
            Path.of("R:\\Projects\\tmp").toFile(),
            file -> file.getName().endsWith(".txt")
        ));

    }

}
