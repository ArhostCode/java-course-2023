package edu.hw3.task8;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

public class BackwardIteratorTest {

    @Test
    @DisplayName("Тест BackwardIterator")
    public void backwardIterator_shouldOutInReverseOrder() {
        Collection<String> strings = List.of("!", "world", "Hello");

        BackwardIterator<String> iterator = new BackwardIterator<>(strings);

        Assertions.assertThat(iterator.next()).isEqualTo("Hello");
        Assertions.assertThat(iterator.next()).isEqualTo("world");
        Assertions.assertThat(iterator.hasNext()).isTrue();
        Assertions.assertThat(iterator.next()).isEqualTo("!");
        Assertions.assertThat(iterator.hasNext()).isFalse();
    }

}
