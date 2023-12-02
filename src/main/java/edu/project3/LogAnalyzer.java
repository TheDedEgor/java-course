package edu.project3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@SuppressWarnings({"RegexpSinglelineJava", "MagicNumber"})
public final class LogAnalyzer {

    private LogAnalyzer() {
    }

    private static final String LOG_ENTRY_PATTERN =
        "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\S+) \"(.*?)\" \"(.*?)\"$";

    public static void run(CommandParameters commandParameters) {
        var path = commandParameters.path();
        try {
            var logs = filterDate(commandParameters.from(), commandParameters.to(), getData(path));
            var statistics = getStatistics(logs);
            statisticsOutput(statistics, commandParameters);
        } catch (Exception ex) {
            System.out.println("Произошла ошибка при анализе логов");
        }
    }

    private static void statisticsOutput(LogStatistics logStatistics, CommandParameters commandParameters)
        throws Exception {
        var format = commandParameters.format();
        var output = getFormattedOutput(logStatistics, commandParameters);
        if (format != null) {
            var path = "./log-statistics";
            if (format.equals("markdown")) {
                path += ".md";
            } else if (format.equals("adoc")) {
                path += ".adoc";
            }
            var file = new File(path);
            try (FileWriter writer = new FileWriter(file, false)) {
                writer.write(output);
                writer.flush();
            }
        } else {
            System.out.print(output);
        }
    }

    private static String getFormattedOutput(LogStatistics logStatistics, CommandParameters commandParameters) {
        var format = "| %-25s | \t%-25s |%n";
        var fromDate = commandParameters.from();
        var toDate = commandParameters.to();
        var countString = "Количество";
        var result = "";
        result += "#### Общая информация\n\n";
        result += String.format(format, "Метрика", "Значение");
        result += "|:-------------------------:|:---------------------------:|\n";
        result += String.format(format, "Начальная дата", fromDate != null ? fromDate : "-");
        result += String.format(format, "Конечная дата", toDate != null ? toDate : "-");
        result += String.format(format, "Количество запросов", logStatistics.requestCount());
        result += String.format(format, "Средний размер ответа", logStatistics.responseAverageSize() + "b");
        result += "\n";

        format = "| %-12s | \t%-12s |%n";
        result += "#### Запрашиваемые ресурсы\n\n";
        result += String.format(format, "Ресурс", countString);
        result += "|:------------:|:-------------------:|\n";
        for (var item : logStatistics.requestedResources()) {
            result += String.format(format, item.getKey(), item.getValue());
        }
        result += "\n";

        format = "| %-5s | \t%-12s | \t%-12s |%n";
        result += "#### Коды ответа\n\n";
        result += String.format(format, "Код", "Имя", countString);
        result += "|:-----:|:------------------:|:-------------:|\n";
        for (var item : logStatistics.responseCodes()) {
            result += String.format(format, item.getKey().getCode(), item.getKey().getStatus(), item.getValue());
        }
        result += "\n";

        return result;
    }

    private static String mapToResource(String requestLine) {
        var path = requestLine.split(" ")[1];
        var idx = path.lastIndexOf("/");
        return path.substring(idx);
    }

    private static LogStatistics getStatistics(List<LogEntry> logs) {
        var requestCount = logs.size();
        var responseAverageSize = logs.stream().mapToLong(LogEntry::responseSize).sum() / requestCount;
        var groupingResources = logs.stream()
            .map(log -> mapToResource(log.requestLine()))
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        var requestedResources = groupingResources.entrySet().stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .limit(3)
            .toList();
        var groupingResponseCodes = logs.stream()
            .map(LogEntry::httpStatus)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        var responseCodes = groupingResponseCodes.entrySet().stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .limit(3)
            .toList();
        return new LogStatistics(requestCount, responseAverageSize, requestedResources, responseCodes);
    }

    private static boolean predicateFilter(LogEntry log, LocalDate from, LocalDate to) {
        boolean result = true;
        if (from != null) {
            result = log.timestamp().isAfter(from.atStartOfDay());
        }
        if (to != null && result) {
            result = log.timestamp().isBefore(to.atStartOfDay());
        }
        return result;
    }

    private static List<LogEntry> filterDate(LocalDate from, LocalDate to, List<LogEntry> logs) {
        return logs.stream().filter(log -> predicateFilter(log, from, to)).toList();
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

    private static HttpStatus getResponseStatus(Integer code) {
        return switch (code) {
            case 200 -> HttpStatus.OK;
            case 201 -> HttpStatus.Created;
            case 202 -> HttpStatus.Accepted;
            case 300 -> HttpStatus.MultipleChoices;
            case 302 -> HttpStatus.Found;
            case 304 -> HttpStatus.NotModified;
            case 400 -> HttpStatus.BadRequest;
            case 401 -> HttpStatus.Unauthorized;
            case 402 -> HttpStatus.PaymentRequired;
            case 403 -> HttpStatus.Forbidden;
            case 404 -> HttpStatus.NotFound;
            case 405 -> HttpStatus.MethodNotAllowed;
            case 500 -> HttpStatus.InternalServerError;
            case 501 -> HttpStatus.NotImplemented;
            case 502 -> HttpStatus.BadGateway;
            default -> HttpStatus.Unknown;
        };
    }

    private static LogEntry parseString(String string) {
        Pattern pattern = Pattern.compile(LOG_ENTRY_PATTERN);
        Matcher matcher = pattern.matcher(string);
        if (matcher.matches()) {
            var formatter = DateTimeFormatter.ofPattern("d/LLL/y:H:m:s Z", Locale.ENGLISH);
            String ipAddress = matcher.group(1);
            LocalDateTime timestamp = LocalDateTime.parse(matcher.group(4), formatter);
            String requestLine = matcher.group(5);
            HttpStatus httpStatus = getResponseStatus(Integer.parseInt(matcher.group(6)));
            String responseSize = matcher.group(7);
            String referrer = matcher.group(8);
            String userAgent = matcher.group(9);

            return new LogEntry(
                ipAddress,
                timestamp,
                requestLine,
                httpStatus,
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
