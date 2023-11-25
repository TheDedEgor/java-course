package edu.hw5.chain;

import java.time.LocalDate;
import java.util.Optional;

public class DateParser6 extends DateParser {

    public DateParser6(DateParser nextParser) {
        super(nextParser);
    }

    private Optional<LocalDate> runNextParser(String text) {
        if (nextParser == null) {
            return Optional.empty();
        }
        return nextParser.parse(text);
    }

    @Override
    public Optional<LocalDate> parse(String text) {
        try {
            LocalDate date;
            switch (text) {
                case "today" -> {
                    date = LocalDate.now();
                }
                case "tomorrow" -> {
                    date = LocalDate.now();
                    date = date.plusDays(1);
                }
                case "yesterday" -> {
                    date = LocalDate.now();
                    date = date.minusDays(1);
                }
                default -> {
                    return runNextParser(text);
                }
            }
            return Optional.of(date);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
