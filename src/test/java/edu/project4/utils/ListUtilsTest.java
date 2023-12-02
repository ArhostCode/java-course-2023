package edu.project4.utils;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ListUtilsTest {

    @Test
    @DisplayName("Тестирование ListUtils#random")
    public void random_shouldReturnRandomElement() {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        Integer actual = ListUtils.random(list);
        Assertions.assertThat(list).contains(actual);
    }

}
