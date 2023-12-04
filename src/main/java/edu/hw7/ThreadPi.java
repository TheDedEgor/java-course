package edu.hw7;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadPi extends Thread {
    private long circleCount = 0;

    private final int totalCount;

    public ThreadPi(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public void run() {
        var rand = ThreadLocalRandom.current();
        for (int i = 0; i < totalCount; i++) {
            var x = rand.nextDouble();
            var y = rand.nextDouble();
            if (x * x + y * y < 1) {
                circleCount++;
            }
        }
    }

    public long getCircleCount() {
        return circleCount;
    }
}
