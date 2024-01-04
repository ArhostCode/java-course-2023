package edu.hw9.task2;

import java.io.File;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;

public final class FilesTreeProcessor {
    private FilesTreeProcessor() {
    }

    public static List<File> searchDirectories(File file, int minFilesCount) {
        DirSearcherTask task = new DirSearcherTask(file, minFilesCount);
        ForkJoinPool pool = new ForkJoinPool();
        List<File> result = pool.invoke(task);
        pool.shutdown();
        return result;
    }

    public static List<File> filterRecursively(File file, Predicate<File> predicate) {
        FilesFilterTask task = new FilesFilterTask(file, predicate);
        ForkJoinPool pool = new ForkJoinPool();
        List<File> result = pool.invoke(task);
        pool.shutdown();
        return result;
    }
}
