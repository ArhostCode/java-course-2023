package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

public class DirSearcherTask extends AbstractFileTask {
    private final int minFilesCount;

    public DirSearcherTask(File file, int minFilesCount) {
        super(file);
        this.minFilesCount = minFilesCount;
    }

    @Override
    protected List<File> processFilesInDirectory(File[] filesInDirectory) {
        List<ForkJoinTask<List<File>>> tasks = new ArrayList<>();
        List<File> result = new ArrayList<>();
        int filesCount = 0;
        for (File currentFile : filesInDirectory) {
            if (!currentFile.isDirectory()) {
                filesCount++;
                continue;
            }
            DirSearcherTask task = new DirSearcherTask(currentFile, minFilesCount);
            tasks.add(task.fork());
        }
        if (filesCount >= minFilesCount) {
            result.add(file);
        }
        tasks.stream().flatMap(t -> t.join().stream()).forEach(result::add);
        return result;
    }
}
