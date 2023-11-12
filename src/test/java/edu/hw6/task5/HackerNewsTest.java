package edu.hw6.task5;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HackerNewsTest {

    @Test
    @DisplayName("Тестирование HackerNews#hackerNewsTopStories (тест может упасть при изменениях на сервере)")
    public void hackerNewsTopStories_shouldReturnTopStories() {
        long[] topStories = HackerNews.hackerNewsTopStories();
        Assertions.assertThat(topStories)
            .isNotEmpty();
    }

    @Test
    @DisplayName("Тестирование HackerNews#news (использует HackerNews#hackerNewsTopStories)")
    public void news_shouldReturnNewsTitle() {
        long[] topStories = HackerNews.hackerNewsTopStories();
        String title = HackerNews.news(topStories[0]);
        Assertions.assertThat(title)
            .isNotEmpty();
    }

    @Test
    @DisplayName("Тестирование HackerNews#news на неверном id")
    public void news_shouldReturnNull() {
        String title = HackerNews.news(-6);
        Assertions.assertThat(title)
            .isNull();
    }

}
