package edu.hw6.task1;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class DiskMap implements Map<String, String> {

    private final Path path;

    public DiskMap(Path path) {
        this.path = path;
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void loadFromFile(Path path) {
        clear();
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> {
                String[] mapEntry = line.split(":");
                put(mapEntry[0], mapEntry[1]);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveToFile(Path path) {
        writeAllLines(path, entrySet().stream()
            .map(entry -> entry.getKey() + ":" + entry.getValue())
            .collect(Collectors.toList())
        );
    }

    @Override
    public int size() {
        return entrySet().size();
    }

    @Override
    public boolean isEmpty() {
        return entrySet().isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return keySet().contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    @Override
    public String get(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("Getting key must be not null");
        }
        Entry<String, String> currentEntry = entrySet().stream()
            .filter(entry -> entry.getKey().equals(key))
            .findFirst()
            .orElse(null);
        return currentEntry == null ? null : currentEntry.getValue();
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value must be not null");
        }
        var entrySet = entrySet();
        var currentEntry = getEntryByKey(key, entrySet);
        if (currentEntry != null) {
            String oldValue = currentEntry.getValue();
            currentEntry.setValue(value);
            writeAllLines(path, entrySet.stream().map(entry -> entry.getKey() + ":" + entry.getValue()).toList());
            return oldValue;
        } else {
            try {
                Files.writeString(path, (key + ":" + value + "\n"), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }

    @Override
    public String remove(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must be not null");
        }
        var entrySet = entrySet();
        var currentEntry = getEntryByKey(key, entrySet);
        if (currentEntry != null) {
            writeAllLines(path, entrySet.stream()
                .filter(entry -> !entry.getKey().equals(key))
                .map(entry -> entry.getKey() + ":" + entry.getValue())
                .toList());
            return currentEntry.getValue();
        }
        return null;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        try {
            Files.write(path, m.entrySet()
                    .stream()
                    .map(entry -> entry.getKey() + ":" + entry.getValue())
                    .toList(),
                StandardCharsets.UTF_8, StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clear() {
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE)) {
            channel.truncate(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return entrySet().stream()
            .map(Entry::getKey)
            .collect(Collectors.toSet());
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return entrySet().stream()
            .map(Entry::getValue)
            .toList();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        try (Stream<String> lines = Files.lines(path)) {
            return lines
                .filter(line -> !line.isEmpty())
                .map(line -> {
                    String[] mapEntry = line.split(":");
                    return new DiskMapEntry(mapEntry[0], mapEntry[1]);
                }).collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeAllLines(Path path, List<String> lines) {
        try {
            Files.write(path, lines, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Entry<String, String> getEntryByKey(Object key, Set<Entry<String, String>> entrySet) {
        return entrySet.stream()
            .filter(entry -> entry.getKey().equals(key))
            .findFirst()
            .orElse(null);
    }

    @Getter
    @ToString
    @AllArgsConstructor
    static final class DiskMapEntry implements Map.Entry<String, String> {
        private final String key;
        private String value;

        @Override
        public String setValue(String value) {
            String oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }

            return o instanceof Map.Entry<?, ?> e
                && Objects.equals(key, e.getKey())
                && Objects.equals(value, e.getValue());
        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }
    }
}
