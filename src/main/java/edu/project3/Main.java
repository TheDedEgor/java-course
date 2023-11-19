package edu.project3;

import java.util.Arrays;

public final class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        var commandParameters = CommandParser.parseCommand(args);
        LogAnalyzer.run(commandParameters);
    }
}
