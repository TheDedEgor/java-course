package edu.project3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class CommandParser {

    public static CommandParameters parseCommand(String[] args) {
        var path = findParameter("--path", args);
        if (path == null) {
            throw new RuntimeException("Нет обязательного параметра --path");
        }
        var from = findParameter("--from", args);
        LocalDate fromDate = null;
        if (from != null) {
            fromDate = LocalDate.parse(from, DateTimeFormatter.ISO_LOCAL_DATE);
        }
        var to = findParameter("--to", args);
        LocalDate toDate = null;
        if (to != null) {
            toDate = LocalDate.parse(to, DateTimeFormatter.ISO_LOCAL_DATE);
        }
        var format  = findParameter("--format", args);
        return new CommandParameters(path, fromDate, toDate, format);
    }

    private static String findParameter(String parameter, String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(parameter)) {
                return args[i+1];
            }
        }
        return null;
    }
}
