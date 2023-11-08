package edu.hw5.chain;

import java.time.LocalDate;
import java.util.Optional;

public class DateParser5 extends DateParser {

    public DateParser5(DateParser nextParser) {
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
            var nums = text.split(" ");
            var days = Integer.parseInt(nums[0]);
            if (days > 0 && (nums[1].equals("day") || nums[1].equals("days")) && nums[2].equals("ago")) {
                var date = LocalDate.now();
                date = date.minusDays(days);
                return Optional.of(date);
            }
            return runNextParser(text);
        } catch (Exception ex) {
            return runNextParser(text);
        }
    }
}
