package edu.hw2.task3;

public class DefaultConnectionManager implements ConnectionManager {
    @Override
    @SuppressWarnings("MagicNumber")
    public Connection getConnection() {
        final var fifty = 50;
        var num = RandomNumber.getRandomNumber();
        if (num < fifty) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
