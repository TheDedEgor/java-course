package edu.hw7;

import java.util.Random;

@SuppressWarnings("MagicNumber")
public class CalculationPi {

    private final int totalCount;

    public CalculationPi(int totalCount) {
        this.totalCount = totalCount;
    }

    public double calculate() {
        double circleCount = 0;
        var rand = new Random();
        for (int i = 0; i < totalCount; i++) {
            var x = rand.nextDouble();
            var y = rand.nextDouble();
            if (x * x + y * y < 1) {
                circleCount++;
            }
        }
        return circleCount / totalCount * 4;
    }

    public double calculateInMultiThreading() {
        int threadCount = 6;
        ThreadPi[] threadsPi = new ThreadPi[threadCount];
        int n = totalCount / threadCount;
        for (int i = 0; i < threadCount; i++) {
            threadsPi[i] = new ThreadPi(n);
            threadsPi[i].start();
        }
        try {
            for (int i = 0; i < threadCount; i++) {
                threadsPi[i].join();
            }
        } catch (Exception ignored) { }

        double circleCount = 0;
        for (int i = 0; i < threadCount; i++) {
            circleCount += threadsPi[i].getCircleCount();
        }

        return circleCount / totalCount * 4;
    }
}
