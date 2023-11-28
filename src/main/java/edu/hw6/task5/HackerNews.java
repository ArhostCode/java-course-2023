package edu.hw6.task5;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HackerNews {

    private static final String TOP_NEWS_ENDPOINT = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String CURRENT_NEWS_ENDPOINT = "https://hacker-news.firebaseio.com/v0/item/%d.json";
    private static final Pattern TITLE_PATTERN = Pattern.compile(".*\"title\":\"([^\"]*)\".*");

    private HackerNews() {
    }

    public static long[] hackerNewsTopStories() {
        Optional<String> response = processRequest(TOP_NEWS_ENDPOINT);
        if (response.isEmpty()) {
            return new long[0];
        }
        String responseString = response.get();
        String normalized = responseString.substring(1, responseString.length() - 1);
        return Arrays.stream(normalized.split(",")).mapToLong(Long::parseLong).toArray();
    }

    public static String news(long id) {
        Optional<String> response = processRequest(CURRENT_NEWS_ENDPOINT.formatted(id));
        if (response.isEmpty()) {
            return null;
        }
        String responseString = response.get();
        Matcher matcher = TITLE_PATTERN.matcher(responseString);
        if (matcher.matches()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

    private static Optional<String> processRequest(String endpoint) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> response = client.send(
                HttpRequest.newBuilder().uri(URI.create(endpoint)).build(),
                HttpResponse.BodyHandlers.ofString()
            );
            return Optional.ofNullable(response.body());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
