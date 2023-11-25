package edu.hw5;

import edu.hw5.chain.ChainDateParser;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@SuppressWarnings("MagicNumber")
public class HomeWork5 {

    private HomeWork5() {
    }

    public static String averageTime(List<String> times) {
        var sumTime = Duration.ZERO;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, kk:mm");
        for (var item : times) {
            var time = item.split(" - ");
            var time1 = LocalDateTime.parse(time[0], formatter);
            var time2 = LocalDateTime.parse(time[1], formatter);
            sumTime = sumTime.plus(Duration.between(time1, time2));
        }
        var averageTime = sumTime.dividedBy(times.size());
        return String.format("%sч %sм", averageTime.toHours(), averageTime.toMinutesPart());
    }

    public static List<String> findAllFriday13(Integer year) {
        List<String> result = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            var date = LocalDate.of(year, month, 13);
            if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                result.add(date.toString());
            }
        }
        return result;
    }

    public static String nextFriday13(LocalDate inputDate) {
        var date = inputDate;
        while (date.getDayOfMonth() != 13) {
            date = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return date.toString();
    }

    public static Optional<LocalDate> parseDate(String text) {
        var chainParser = ChainDateParser.getChain();
        return chainParser.parse(text);
    }

    public static boolean validatePassword(String password) {
        return Pattern.matches(".*(~|!|@|#|\\$|%|\\^|&|\\*|\\|)+.*", password);
    }

    public static boolean validateLicensePlate(String text) {
        return Pattern.matches("^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}$", text);
    }

    public static boolean subsequenceString(String substr, String text) {
        StringBuilder regex = new StringBuilder(".*");
        for (var item : substr.toCharArray()) {
            regex.append(item).append(".*");
        }
        return Pattern.matches(regex.toString(), text);
    }

    public static boolean task71(String text) {
        return Pattern.matches("^[01]{2}0[01]*$", text);
    }

    public static boolean task72(String text) {
        return Pattern.matches("^([01])[01]*\\1$|^[01]$", text);
    }

    public static boolean task73(String text) {
        return Pattern.matches("^[01]{1,3}$", text);
    }

    public static boolean task81(String text) {
        return Pattern.matches("^[01]([01][01])*$", text);
    }

    public static boolean task82(String text) {
        return Pattern.matches("^0([01][01])*$|^1[01]([01][01])*$", text);
    }

    public static boolean task83(String text) {
        return Pattern.matches("^(1*01*01*0)*$", text);
    }

    public static boolean task84(String text) {
        return Pattern.matches("^(?!11$|111$)[01]*$", text);
    }

}
