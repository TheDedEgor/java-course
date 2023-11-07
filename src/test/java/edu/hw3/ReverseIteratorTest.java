package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ReverseIteratorTest {

    @Test
    @DisplayName("Список чисел")
    void listNumbersTest() {
        var iterator = new Task8<>(List.of(1, 2, 3));
        var expected = 3;
        while (iterator.hasNext()) {
            assertThat(iterator.next()).isEqualTo(expected);
            expected--;
        }
    }

    @Test
    @DisplayName("Список строк")
    void listStringsTest() {
        var list = List.of("Hello", "Hi", "Welcome");
        var iterator = new Task8<>(list);
        var idx = 2;
        while (iterator.hasNext()) {
            assertThat(iterator.next()).isEqualTo(list.get(idx));
            idx--;
        }
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListTest() {
        var iterator = new Task8<>(List.of());
        var result = iterator.hasNext();
        assertThat(result).isFalse();
    }
}
