package edu.hw2.task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    @SuppressWarnings("MagicNumber")
    public void execute(String command) {
        var rand = new Random();
        var num = rand.nextInt(0, 100);
        if (num < 50) {
            throw new ConnectionException("Что-то пошло не так");
        }
        LOGGER.info("Команда выполнена успешно");
    }

    @Override
    public void close() {
        LOGGER.info("Соединение закрыто");
    }
}
