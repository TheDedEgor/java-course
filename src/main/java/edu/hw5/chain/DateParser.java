package edu.hw5.chain;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DateParser {
    public DateParser nextParser;

    public DateParser(DateParser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract Optional<LocalDate> parse(String text);
}
