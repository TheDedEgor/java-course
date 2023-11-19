package edu.project3;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LogAnalyzer {
    private static final String LOG_ENTRY_PATTERN =
        "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\S+) \"(.*?)\" \"(.*?)\"$";

    public static void run(CommandParameters commandParameters) {
        var path = commandParameters.path();
        try {
            var list = getData(path);
        } catch (Exception ex) {
            System.out.println("Произошла ошибка при анализе логов");
        }
    }

    private static List<LogEntry> getData(String path) throws Exception {
        if (path.contains("http")) {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .build();
            HttpClient client = HttpClient.newHttpClient();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            var body = response.body();
            return readData(body);
        } else {
            var file = new File(path);
            if (file.isDirectory()) {
                var files = file.listFiles();
                return readData(files);
            } else {
                return readData(file);
            }
        }
    }

    private static LogEntry parseString(String string) {
        Pattern pattern = Pattern.compile(LOG_ENTRY_PATTERN);
        Matcher matcher = pattern.matcher(string);
        if (matcher.matches()) {
            var formatter = DateTimeFormatter.ofPattern("d/L/y:H:m:s x");
            String ipAddress = matcher.group(1);
            LocalDateTime timestamp = null;
            try {
                timestamp = LocalDateTime.parse(matcher.group(4), formatter);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }ё
            String requestLine = matcher.group(5);
            String statusCode = matcher.group(6);
            String responseSize = matcher.group(7);
            String referrer = matcher.group(8);
            String userAgent = matcher.group(9);

            return new LogEntry(
                ipAddress,
                timestamp,
                requestLine,
                Integer.parseInt(statusCode),
                Long.parseLong(responseSize),
                referrer,
                userAgent
            );
        } else {
            throw new RuntimeException("Invalid log entry format");
        }
    }

    private static List<LogEntry> readData(File file) throws Exception {
        var result = new ArrayList<LogEntry>();
        try (var reader = new FileReader(file);
             var br = new BufferedReader(reader)) {
            String string;
            while ((string = br.readLine()) != null) {
                var logEntry = parseString(string);
                result.add(logEntry);
            }
            return result;
        }
    }

    private static List<LogEntry> readData(File[] files) throws Exception {
        var result = new ArrayList<LogEntry>();
        for (var file : files) {
            var list = readData(file);
            result.addAll(list);
        }
        return result;
    }

    private static List<LogEntry> readData(String body) {
        var result = new ArrayList<LogEntry>();
        var data = body.split("\n");
        for (var string : data) {
            var logEntry = parseString(string);
            result.add(logEntry);
        }
        return result;
    }
}
