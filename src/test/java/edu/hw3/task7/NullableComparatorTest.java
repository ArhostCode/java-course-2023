package edu.hw3.task7;

import java.util.TreeMap;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NullableComparatorTest {

    @Test
    public void compare_shouldAcceptNull() {
        TreeMap<String, String> tree = new TreeMap<>(new NullableComparator<>());
        tree.put("c", "test2");
        tree.put("d", "test3");
        tree.put(null, "test");
        tree.put("e", "test1");
        tree.put(null, "test");
        tree.put("a", "test4");
        tree.put("b", "test4");
        tree.put("f", "test4");
        tree.put(null, "test5");

        assertThat(tree.containsKey(null)).isTrue();
        assertThat(tree.get(null)).isEqualTo("test5");
    }

}
