package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HackerNewsTest {

    @Test
    @DisplayName("Получение статей")
    void getStoriesTest() {
        var hackerNews = new HackerNews();
        var data = hackerNews.hackerNewsTopStories();
        assertThat(data.length > 0).isTrue();
    }

    @Test
    @DisplayName("Получение названия конкретной статьи")
    void getSpecificStoryTest() throws IOException, InterruptedException {
        var hackerNews = new HackerNews();
        var title = hackerNews.news(37570037);
        assertThat(title).isEqualTo("JDK 21 Release Notes");
    }
}
