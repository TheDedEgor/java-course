package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("MagicNumber")
public class Task1 {
    private final AtomicInteger count = new AtomicInteger(0);

    public Task1(int n) {
        createThreads(n);
    }

    private void createThreads(int n) {
        var thread1 = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                count.incrementAndGet();
            }
        });
        var thread2 = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                count.incrementAndGet();
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (Exception ignored) {
        }
    }

    public int getCount() {
        return count.get();
    }
}
