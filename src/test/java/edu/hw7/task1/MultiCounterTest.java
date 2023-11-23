package edu.hw7.task1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MultiCounterTest {

    @SneakyThrows
    @Test
    @DisplayName("Тестирование MultiCounter#get и MultiCounter#increment")
    public void get_shouldReturnCorrectInt_whenIncremented() {
        final int threadCount = 5;
        var executorService = Executors.newFixedThreadPool(threadCount);
        var countDownLatch = new CountDownLatch(threadCount);
        var counter = new MultiCounter();
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        Assertions.assertThat(counter.get()).isEqualTo(threadCount * 1000);
    }
}
