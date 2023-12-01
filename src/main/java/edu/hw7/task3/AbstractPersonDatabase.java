package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;

public class AbstractPersonDatabase implements PersonDatabase {

    private final Map<Integer, Person> cachedIds = new HashMap<>();
    private final Map<String, List<Person>> cachedNames = new HashMap<>();
    private final Map<String, List<Person>> cachedAddresses = new HashMap<>();
    private final Map<String, List<Person>> cachedPhones = new HashMap<>();

    @SneakyThrows
    @Override
    public void add(Person person) {
        cachedIds.put(person.id(), person);
        cachedNames.computeIfAbsent(person.name(), name -> new ArrayList<>()).add(person);
        cachedAddresses.computeIfAbsent(person.address(), name -> new ArrayList<>()).add(person);
        cachedPhones.computeIfAbsent(person.phoneNumber(), name -> new ArrayList<>()).add(person);
    }

    @Override
    public void delete(int id) {
        Person removed = cachedIds.remove(id);
        if (removed == null) {
            return;
        }
        cachedNames.get(removed.name()).remove(removed);
        cachedAddresses.get(removed.address()).remove(removed);
        cachedPhones.get(removed.phoneNumber()).remove(removed);
    }

    @Override
    @Nullable
    public List<Person> findByName(String name) {
        return getFromCache(cachedNames, name);
    }

    @Override
    @Nullable
    public List<Person> findByAddress(String address) {
        return getFromCache(cachedAddresses, address);
    }

    @Override
    @Nullable
    public List<Person> findByPhone(String phone) {
        return getFromCache(cachedPhones, phone);
    }

    private List<Person> getFromCache(Map<String, List<Person>> cache, String key) {
        if (!cache.containsKey(key)) {
            return null;
        }
        return cache.get(key);
    }

}
