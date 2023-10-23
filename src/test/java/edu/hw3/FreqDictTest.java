package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FreqDictTest {

    @Test
    @DisplayName("Частотный словарь строк 1")
    void Test1() {
        var result = Task3.freqDict(List.of("a", "bb", "a", "bb"));
        var expected = new HashMap<Object, Integer>();
        expected.put("a", 2);
        expected.put("bb", 2);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Частотный словарь строк 2")
    void Test2() {
        var result = Task3.freqDict(List.of("this", "and", "that", "and"));
        var expected = new HashMap<Object, Integer>();
        expected.put("this", 1);
        expected.put("and", 2);
        expected.put("that", 1);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Частотный словарь строк 3")
    void Test3() {
        var result = Task3.freqDict(List.of("код", "код", "код", "bug"));
        var expected = new HashMap<Object, Integer>();
        expected.put("код", 3);
        expected.put("bug", 1);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Частотный словарь чисел")
    void Test4() {
        var result = Task3.freqDict(List.of(1, 1, 2, 2));
        var expected = new HashMap<Object, Integer>();
        expected.put(1, 2);
        expected.put(2, 2);
        assertThat(result).isEqualTo(expected);
    }
}
