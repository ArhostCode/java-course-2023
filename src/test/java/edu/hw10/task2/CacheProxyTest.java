package edu.hw10.task2;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import java.io.Serializable;

public class CacheProxyTest {

    @SneakyThrows @Test
    public void test() {
        FibCalculator proxy = number -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 10;
        };
        proxy = CacheProxy.create(proxy);
        System.out.println(proxy.fib(10));
        System.out.println(proxy.fib(10));
    }

}
