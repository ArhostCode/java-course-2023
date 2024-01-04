package edu.hw10.task2;

import java.io.File;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class CacheProxyTest {
    @SneakyThrows
    @Test
    @DisplayName("Тестирование CacheProxy#create")
    public void create_shouldReturnProxiedObject_withoutPersist(@TempDir File tempDir) {
        NewFibCalculator proxy = new FibImplWithoutPersist();
        proxy = CacheProxy.create(proxy, NewFibCalculator.class, tempDir.toPath());

        proxy.fib(10);
        long cachedNum = proxy.fib(10);

        Assertions.assertThat(cachedNum).isEqualTo(55);
    }

    interface NewFibCalculator {
        @Cache
        long fib(long number);
    }

    static class FibImplWithoutPersist implements NewFibCalculator {
        @Override
        @SneakyThrows
        public long fib(long number) {
            if (number < 2) {
                return number;
            }
            return fib(number - 1) + fib(number - 2);
        }
    }
}
