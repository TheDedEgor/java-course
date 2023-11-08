package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TreeComparatorTest {

    @Test
    @DisplayName("Null нету")
    void notNullTest() {
        TreeMap<String, String> tree = new TreeMap<>(new Task7());
        tree.put("null", "test");
        assertThat(tree.containsKey(null)).isFalse();
    }

    @Test
    @DisplayName("Null есть")
    void NullTest() {
        TreeMap<String, String> tree = new TreeMap<>(new Task7());
        tree.put(null, "test");
        assertThat(tree.containsKey(null)).isTrue();
    }

    @Test
    @DisplayName("Несколько null")
    void twoNullTest() {
        TreeMap<String, String> tree = new TreeMap<>(new Task7());
        tree.put(null, "a");
        tree.put("1", "a");
        tree.put("2", "a");
        tree.put(null, "b");
        tree.put("3", "a");
        assertThat(tree.get(null)).isEqualTo("b");
    }
}
