package edu.hw1;

import edu.hw1.task1.VideoTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class VideoTimeTest {

    @Test
    @DisplayName("Тест VideoTime.extractInSeconds(String) с верными входными данными")
    public void extractInSeconds_shouldReturnCorrectAnswer() {
        int actual = VideoTime.extractInSeconds("13:56");
        assertEquals(836, actual);
    }

    @DisplayName("Тест VideoTime.extractInSeconds(String) с неверными входными данными")
    @ParameterizedTest(name = "{index} - {0} - неверные входные данные")
    @ValueSource(strings = {"-5:30", "3:3", "10:60", "9:f3", "02:-32"})
    public void extractInSeconds_shouldReturnMinusOne_whenInputFormatIsIncorrect(String testTime) {
        int actual = VideoTime.extractInSeconds(testTime);
        assertEquals(-1, actual);
    }

}
