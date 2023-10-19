package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        final var fifty = 50;
        var num = RandomNumber.getRandomNumber();
        if (num < fifty) {
            throw new ConnectionException("Что-то пошло не так");
        }
        LOGGER.info("Команда выполнена успешно");
    }

    @Override
    public void close() {
        LOGGER.info("Соединение закрыто");
    }
}
