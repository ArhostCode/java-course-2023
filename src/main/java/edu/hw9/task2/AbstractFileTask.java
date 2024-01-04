package edu.hw9.task2;

import java.io.File;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractFileTask extends RecursiveTask<List<File>> {

    protected final File file;

    @Override
    protected List<File> compute() {
        File[] filesInDirectory = file.listFiles();
        if (filesInDirectory == null) { // Not a directory
            return List.of();
        }
        return processFilesInDirectory(filesInDirectory);
    }

    protected abstract List<File> processFilesInDirectory(File[] filesInDirectory);
}
