package edu.hw6;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Pattern;

public class HackerNews {
    private final HttpClient client = HttpClient.newHttpClient();

    public long[] hackerNewsTopStories() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
                .build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            var body = response.body();
            var data = body.substring(1, body.length() - 1).split(",");
            return Arrays.stream(data).mapToLong(Long::parseLong).toArray();
        } catch (Exception ex) {
            return new long[0];
        }
    }

    public String news(long id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/item/" + id + ".json"))
            .build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        var body = response.body();
        var pattern = Pattern.compile("\"title\":.+?,");
        var matcher = pattern.matcher(body);
        if (matcher.find()) {
           return body.substring(matcher.start(), matcher.end() - 1).split(":")[1].replaceAll("\"", "");
        }
        return "";
    }
}
