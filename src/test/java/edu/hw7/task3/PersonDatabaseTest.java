package edu.hw7.task3;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PersonDatabaseTest {

    private static Stream<Arguments> databaseArguments() {
        return Stream.of(
            Arguments.of(new LockCachingPersonDatabase()),
            Arguments.of(new SynchronizedCachingPersonDatabase() {
                @SneakyThrows
                @Override
                public synchronized void add(Person person) {
                    Thread.sleep(3000); // Imitation of slow database
                    super.add(person);
                }
            })
        );
    }

    @SneakyThrows
    @ParameterizedTest
    @DisplayName("Тестирование PersonDatabase")
    @MethodSource("databaseArguments")
    public void personDatabase_shouldReturnCorrectPerson_whenPersonAdded(AbstractPersonDatabase personDatabase) {
        var executorService = Executors.newFixedThreadPool(5);
        var person = new Person(1, "Ivan", "Moscow", "1234567890");
        executorService.execute(() -> personDatabase.add(person));
        Thread.sleep(100);
        Future<List<Person>> futureByPhone = executorService.submit(() -> personDatabase.findByPhone("1234567890"));
        Future<List<Person>> futureByName = executorService.submit(() -> personDatabase.findByName("Ivan"));
        Future<List<Person>> futureByAddress = executorService.submit(() -> personDatabase.findByAddress("Moscow"));
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        assertAll(
            () -> Assertions.assertThat(futureByPhone.get()).contains(person),
            () -> Assertions.assertThat(futureByName.get()).contains(person),
            () -> Assertions.assertThat(futureByAddress.get()).contains(person)
        );
    }

}
