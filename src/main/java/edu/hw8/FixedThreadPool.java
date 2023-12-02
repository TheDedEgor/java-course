package edu.hw8;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FixedThreadPool implements ThreadPool {

    private final int threadCount;
    private final Queue<Runnable> workQueue;
    private volatile boolean isRunning = true;

    private FixedThreadPool(int threadCount) {
        this.threadCount = threadCount;
        this.workQueue  = new ConcurrentLinkedQueue<>();
    }

    public static ThreadPool create(int threadCount) {
        return new FixedThreadPool(threadCount);
    }

    @Override
    public void start() {
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                while (isRunning) {
                    Runnable nextTask = workQueue.poll();
                    if (nextTask != null) {
                        nextTask.run();
                    }
                }
            }).start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (isRunning) {
            workQueue.offer(runnable);
        }
    }

    @Override
    public void close() {
        while (true) {
            if (!workQueue.isEmpty()) {
                continue;
            }
            isRunning = false;
            break;
        }
    }
}
