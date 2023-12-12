package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Predicate;

public class FilesFilterTask extends AbstractFileTask {
    private final Predicate<File> predicate;

    public FilesFilterTask(File file, Predicate<File> predicate) {
        super(file);
        this.predicate = predicate;
    }

    @Override
    protected List<File> processFilesInDirectory(File[] filesInDirectory) {
        List<ForkJoinTask<List<File>>> tasks = new ArrayList<>();
        List<File> result = new ArrayList<>();
        for (File currentFile : filesInDirectory) {
            if (currentFile.isDirectory()) {
                FilesFilterTask task = new FilesFilterTask(currentFile, predicate);
                tasks.add(task.fork());
                continue;
            }
            if (predicate.test(currentFile)) {
                result.add(currentFile);
            }
        }
        tasks.stream().flatMap(t -> t.join().stream()).forEach(result::add);
        return result;
    }

}
