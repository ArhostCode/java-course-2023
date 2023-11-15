package edu.hw6.task1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static java.util.Map.entry;

public class DiskMapTest {

    @Test
    @DisplayName("Тестирование DiskMap#put (используется DiskMap#entrySet)")
    public void put_shouldAddValueToMap() {
        DiskMap diskMap = createDiskMap();
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        Assertions.assertThat(diskMap.entrySet())
            .contains(
                entry("key1", "value1"),
                entry("key2", "value2")
            );
    }

    @Test
    @DisplayName("Тестирование DiskMap#get (используется DiskMap#put)")
    public void get_shouldReturnValue() {
        DiskMap diskMap = createDiskMap();
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        Assertions.assertThat(diskMap.get("key1"))
            .isEqualTo("value1");
    }

    @Test
    @DisplayName("Тестирование DiskMap#clear (используется DiskMap#entrySet и DiskMap#put)")
    public void clear_shouldClearMap() {
        DiskMap diskMap = createDiskMap();
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        diskMap.clear();
        Assertions.assertThat(diskMap.entrySet()).isEmpty();
    }

    @Test
    @DisplayName("Тестирование DiskMap#remove (используется DiskMap#entrySet и DiskMap#put)")
    public void remove_shouldRemoveElement() {
        DiskMap diskMap = createDiskMap();
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        diskMap.remove("key1");
        Assertions.assertThat(diskMap.entrySet()).containsExactly(entry("key2", "value2"));
    }

    @Test
    @DisplayName("Тестирование DiskMap#keySet (используется DiskMap#put)")
    public void keySet_shouldReturnCorrectElements() {
        DiskMap diskMap = createDiskMap();
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        Assertions.assertThat(diskMap.keySet())
            .containsExactlyInAnyOrder("key1", "key2");
    }

    @Test
    @DisplayName("Тестирование DiskMap#values (используется DiskMap#put)")
    public void values_shouldReturnCorrectElements() {
        DiskMap diskMap = createDiskMap();
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        Assertions.assertThat(diskMap.values())
            .containsExactlyInAnyOrder("value1", "value2");
    }

    @Test
    @DisplayName("Тестирование DiskMap#saveToFile (используется DiskMap#put)")
    public void saveToFile_shouldSaveElementsToFile() throws IOException {
        DiskMap diskMap = createDiskMap();
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        Path temp = Files.createTempFile("diskmap_dump", ".tmp");
        File tempFile = temp.toFile();
        tempFile.deleteOnExit();

        diskMap.saveToFile(temp);
        Assertions.assertThat(Files.readAllLines(temp)).containsExactly("key1:value1", "key2:value2");
    }

    @Test
    @DisplayName("Тестирование DiskMap#loadFromFile (используется DiskMap#put)")
    public void loadFromFile_shouldLoadElementsFromFile() throws IOException {
        DiskMap diskMap = createDiskMap();
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        Path temp = Files.createTempFile("diskmap_dump", ".tmp");
        Files.write(temp, "key3:value3\nkey4:value4".getBytes());
        temp.toFile().deleteOnExit();

        diskMap.loadFromFile(temp);
        Assertions.assertThat(diskMap.entrySet()).containsExactlyInAnyOrder(
            entry("key3", "value3"),
            entry("key4", "value4")
        );
    }

    @Test
    @DisplayName("Тестирование DiskMap#size (используется DiskMap#put)")
    public void size_shouldReturnCorrectCount() {
        DiskMap diskMap = createDiskMap();
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        Assertions.assertThat(diskMap.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Тестирование DiskMap#isEmpty (используется DiskMap#put)")
    public void isEmpty_shouldReturnFalse_whenNotEmpty() {
        DiskMap diskMap = createDiskMap();
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        Assertions.assertThat(diskMap.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Тестирование DiskMap#containsKey (используется DiskMap#put)")
    public void containsKey_shouldReturnTrue_whenContainsKey() {
        DiskMap diskMap = createDiskMap();
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        Assertions.assertThat(diskMap.containsKey("key1")).isTrue();
    }

    @Test
    @DisplayName("Тестирование DiskMap#containsKey (используется DiskMap#put)")
    public void containsKey_shouldReturnFalse_whenNotContainsKey() {
        DiskMap diskMap = createDiskMap();
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        Assertions.assertThat(diskMap.containsKey("key3")).isFalse();
    }

    @Test
    @DisplayName("Тестирование DiskMap#containsValue (используется DiskMap#put)")
    public void containsValue_shouldReturnTrue_whenContainsValue() {
        DiskMap diskMap = createDiskMap();
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        Assertions.assertThat(diskMap.containsValue("value1")).isTrue();
    }

    @Test
    @DisplayName("Тестирование DiskMap#containsValue (используется DiskMap#put)")
    public void containsValue_shouldReturnFalse_whenNotContainsValue() {
        DiskMap diskMap = createDiskMap();
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        Assertions.assertThat(diskMap.containsKey("value3")).isFalse();
    }

    private DiskMap createDiskMap() {
        DiskMap diskMap;
        try {
            diskMap = new DiskMap(Files.createTempFile("diskmap", ".tmp"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        diskMap.getPath().toFile().deleteOnExit();
        return diskMap;
    }

}
