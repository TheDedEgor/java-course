package edu.project3;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        var commandParameters = CommandParser.parseCommand(args);
        LogAnalyzer.run(commandParameters);
    }
}
