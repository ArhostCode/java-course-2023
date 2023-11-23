package edu.hw7.task3;

import java.util.List;
import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;

public class SynchronizedCachingPersonDatabase extends AbstractPersonDatabase {

    @SneakyThrows
    @Override
    public synchronized void add(Person person) {
        super.add(person);
    }

    @Override
    public synchronized void delete(int id) {
        super.delete(id);
    }

    @Override
    @Nullable
    public synchronized List<Person> findByName(String name) {
        return super.findByName(name);
    }

    @Override
    @Nullable
    public synchronized List<Person> findByAddress(String address) {
        return super.findByAddress(address);
    }

    @Override
    @Nullable
    public synchronized List<Person> findByPhone(String phone) {
        return super.findByPhone(phone);
    }
}
