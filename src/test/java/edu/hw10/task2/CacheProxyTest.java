package edu.hw10.task2;

import java.nio.file.Path;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class CacheProxyTest {

    @SneakyThrows @Test
    public void test() {
        FibCalculator proxy = new FibImpl();
        proxy = CacheProxy.create(proxy, Path.of("."));
        System.out.println(proxy.fib(10, "her,\""));
        System.out.println(proxy.fib(10, "zalup"));
        System.out.println(proxy.fib(10, "her,\""));
    }

    class FibImpl implements FibCalculator {
        @Override
        @SneakyThrows
        @Cache(persist = false)
        public long fib(long number, String hello) {
            Thread.sleep(1000);
            return 10;
        }
    }

}
