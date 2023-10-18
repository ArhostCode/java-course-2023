package edu.hw3.task7;

import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

public class NullableComparatorTest {

    @Test
    public void compare_shouldAcceptNull() {
        TreeMap<String, String> tree = new TreeMap<>(new NullableComparator<>());
        tree.put("a", "test2");
        tree.put("b", "test3");
        tree.put(null, "test");

        assertThat(tree.containsKey(null)).isTrue();
        assertThat(tree.get(null)).isEqualTo("test");
    }

}
