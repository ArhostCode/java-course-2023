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
        cachedNames.remove(removed.name());
        cachedAddresses.remove(removed.address());
        cachedPhones.remove(removed.phoneNumber());
    }

    @Override
    @Nullable
    public List<Person> findByName(String name) {
        if (!cachedNames.containsKey(name)) {
            return null;
        }
        return cachedNames.get(name).stream().toList();
    }

    @Override
    @Nullable
    public List<Person> findByAddress(String address) {
        if (!cachedAddresses.containsKey(address)) {
            return null;
        }
        return cachedAddresses.get(address).stream().toList();
    }

    @Override
    @Nullable
    public List<Person> findByPhone(String phone) {
        if (!cachedPhones.containsKey(phone)) {
            return null;
        }
        return cachedPhones.get(phone).stream().toList();
    }

}
