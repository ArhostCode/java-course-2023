package edu.hw7.task3;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PersonDatabaseTest {

    @Test
    @DisplayName("Тестирование LockCachingPersonDatabase")
    public void lockDatabase_shouldReturnCorrectPerson_whenPersonAdded() {
        PersonDatabase personDatabase = new LockCachingPersonDatabase();
        testDatabase(personDatabase);
    }

    @Test
    @DisplayName("Тестирование CachingPersonDatabase")
    public void synchronizedDatabase_shouldReturnCorrectPerson_whenPersonAdded() {
        PersonDatabase personDatabase = new CachingPersonDatabase() {
            @SneakyThrows
            @Override
            public synchronized void add(Person person) {
                Thread.sleep(3000); // Imitation of slow database
                super.add(person);
            }
        };
        testDatabase(personDatabase);
    }

    @SneakyThrows
    private static void testDatabase(PersonDatabase personDatabase) {
        var executorService = Executors.newFixedThreadPool(5);
        var person = new Person(1, "Ivan", "Moscow", "1234567890");
        executorService.execute(() -> personDatabase.add(person));
        Thread.sleep(100);
        Future<List<Person>> futureByPhone = executorService.submit(() -> personDatabase.findByPhone("1234567890"));
        Future<List<Person>> futureByName = executorService.submit(() -> personDatabase.findByName("Ivan"));
        Future<List<Person>> futureByAddress = executorService.submit(() -> personDatabase.findByAddress("Moscow"));
        executorService.shutdown();
        assertAll(
            () -> Assertions.assertThat(futureByPhone.get()).contains(person),
            () -> Assertions.assertThat(futureByName.get()).contains(person),
            () -> Assertions.assertThat(futureByAddress.get()).contains(person)
        );
    }

}
