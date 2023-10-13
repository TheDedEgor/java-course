package edu.hw2.task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    @Override
    @SuppressWarnings("MagicNumber")
    public Connection getConnection() {
        var rand = new Random();
        var num = rand.nextInt(0, 100);
        if (num < 50) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
