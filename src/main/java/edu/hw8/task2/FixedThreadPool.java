package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.SneakyThrows;

public final class FixedThreadPool implements ThreadPool {

    private final int poolSize;
    private final FixedThreadPoolWorker[] workers;
    private final BlockingQueue<Runnable> runnableBlockingQueue;
    private final AtomicInteger currentWorkersCount = new AtomicInteger(0);
    private final AtomicBoolean canAcceptTasks = new AtomicBoolean(false);

    private FixedThreadPool(int poolSize) {
        if (poolSize <= 0) {
            throw new IllegalArgumentException("Pool size must be greater than 0");
        }
        this.poolSize = poolSize;
        this.workers = new FixedThreadPoolWorker[poolSize];
        runnableBlockingQueue = new LinkedBlockingQueue<>();
    }

    public static FixedThreadPool create(int poolSize) {
        return new FixedThreadPool(poolSize);
    }

    @SneakyThrows
    public void execute(Runnable task) {
        if (canAcceptTasks.get()) {
            if (currentWorkersCount.get() < poolSize) {
                workers[currentWorkersCount.get()] = new FixedThreadPoolWorker(); // Lazy initialization
                currentWorkersCount.incrementAndGet();
            }
            runnableBlockingQueue.put(task);
        }
    }

    @Override
    public void start() {
        canAcceptTasks.set(true);
    }

    @Override
    public void awaitTermination() {
        for (FixedThreadPoolWorker worker : workers) {
            worker.join();
        }
    }

    @Override
    public void close() {
        canAcceptTasks.set(false);
    }

    public class FixedThreadPoolWorker implements Runnable {

        private Runnable task;
        private final Thread thread;

        public FixedThreadPoolWorker() {
            thread = new Thread(this);
            thread.start();
        }

        public void run() {
            while (canAcceptTasks.get() || !runnableBlockingQueue.isEmpty() || task != null) {
                if (task == null) {
                    task = runnableBlockingQueue.poll();
                    continue;
                }
                task.run();
                task = runnableBlockingQueue.poll();
            }
        }

        @SneakyThrows
        public void join() {
            thread.join();
        }
    }

}
