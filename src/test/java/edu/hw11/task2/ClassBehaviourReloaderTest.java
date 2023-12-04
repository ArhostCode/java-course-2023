package edu.hw11.task2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClassBehaviourReloaderTest {

    @Test
    @DisplayName("Тестирование ClassBehaviourReloader#reload")
    public void reload() {
        ClassBehaviourReloader.reload();
        Assertions.assertThat(ArithmeticUtils.sum(5, 5)).isEqualTo(25);
    }

}
