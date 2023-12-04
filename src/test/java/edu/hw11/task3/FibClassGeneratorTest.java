package edu.hw11.task3;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;

public class FibClassGeneratorTest {

    @SneakyThrows @Test
    @DisplayName("Тестирование FibClassGenerator#generate")
    public void generate() {
        Object object = FibClassGenerator.generate();
        Class<?> clazz = object.getClass();
        Method method = clazz.getMethod("fib", int.class);
        Object result = method.invoke(object, 10);
        Assertions.assertThat(result).isEqualTo(55L);
    }
}
