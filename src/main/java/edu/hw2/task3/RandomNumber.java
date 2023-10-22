package edu.hw2.task3;

import java.util.Random;

public class RandomNumber {
    private RandomNumber() {
    }

    public static int getRandomNumber() {
        var rand = new Random();
        final int hundred = 100;
        return rand.nextInt(0, hundred);
    }
}
