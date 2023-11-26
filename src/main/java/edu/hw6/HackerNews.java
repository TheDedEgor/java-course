package edu.hw6;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Pattern;

public class HackerNews {
    private final HttpClient client = HttpClient.newHttpClient();

    private static final Pattern PATTERN = Pattern.compile("\"title\":\"(.+?)\"");

    public long[] hackerNewsTopStories() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
                .build();
            var body = getResponseBody(request);
            var data = body.substring(1, body.length() - 1).split(",");
            return Arrays.stream(data).mapToLong(Long::parseLong).toArray();
        } catch (Exception ex) {
            return new long[0];
        }
    }

    public String news(long id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://hacker-news.firebaseio.com/v0/item/" + id + ".json"))
                .build();
            var body = getResponseBody(request);
            var matcher = PATTERN.matcher(body);
            if (matcher.find()) {
                return matcher.group(1);
            }
            return "";
        } catch (Exception ex) {
            return null;
        }
    }

    private String getResponseBody(HttpRequest request) {
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception ex) {
            return null;
        }
    }
}
