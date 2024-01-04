package edu.hw8.task3;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;

public class ParallelPasswordsDecoder extends AbstractPasswordsDecoder {
    private final ExecutorService executorService;
    private final int threadCount;

    public ParallelPasswordsDecoder(Map<String, String> usersMap, int threadCount) {
        super(new ConcurrentHashMap<>(usersMap));
        this.executorService = Executors.newFixedThreadPool(threadCount);
        this.threadCount = threadCount;
    }

    @SneakyThrows
    @Override
    public List<User> decode() {
        int offset = -1;
        int passwordsPerThread = ALPHABET.length / threadCount;
        for (int thread = 0; thread < threadCount - 1; thread++) {
            final int start = offset;
            executorService.execute(() -> decodeInRange(start, start + passwordsPerThread));
            offset += passwordsPerThread;
        }
        final int start = offset;
        executorService.execute(() -> decodeInRange(start, ALPHABET.length));
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        return decoded;
    }

}
