package edu.hw10.task2;

import java.io.File;
import java.nio.file.Files;
import java.util.Properties;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class CacheProxyPersistTest {

    @SneakyThrows
    @Test
    @DisplayName("Тестирование CacheProxy#create")
    public void create_shouldReturnProxiedObject(@TempDir File tempDir) {
        FibCalculator proxy = new FibImpl();
        proxy = CacheProxy.create(proxy, FibCalculator.class, tempDir.toPath());

        long num = proxy.fib(10);
        long cachedNum = proxy.fib(10);
        Properties properties = new Properties();
        properties.load(Files.newBufferedReader(tempDir.toPath().resolve("fib_long")));

        Assertions.assertThat(cachedNum).isEqualTo(55);
        Assertions.assertThat(properties.getProperty("10;")).isEqualTo(String.valueOf(num));
    }

    @SneakyThrows
    @Test
    @DisplayName("Тестирование CacheProxy#create")
    public void create_shouldReturnProxiedObject_withoutParams(@TempDir File tempDir) {
        SomeInterface proxy = new SomeImpl();
        proxy = CacheProxy.create(proxy, SomeInterface.class, tempDir.toPath());

        long num = proxy.get();
        long cachedNum = proxy.get();

        Assertions.assertThat(cachedNum).isEqualTo(10);
        Assertions.assertThat(Files.readString(tempDir.toPath().resolve("get")))
            .isEqualTo("10");
    }

    static class FibImpl implements FibCalculator {
        @Override
        @SneakyThrows
        public long fib(long number) {
            if (number < 2) {
                return number;
            }
            return fib(number - 1) + fib(number - 2);
        }
    }

    interface SomeInterface {
        @Cache(persist = true)
        long get();
    }

    static class SomeImpl implements SomeInterface {
        @Override
        public long get() {
            return 10;
        }
    }

}
