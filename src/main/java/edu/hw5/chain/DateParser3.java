package edu.hw5.chain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DateParser3 extends DateParser {

    public DateParser3(DateParser nextParser) {
        super(nextParser);
    }

    @Override
    public Optional<LocalDate> parse(String text) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            var date = LocalDate.parse(text, formatter);
            return Optional.of(date);
        } catch (Exception ex) {
            if (nextParser == null) {
                return Optional.empty();
            }
            return nextParser.parse(text);
        }
    }
}
