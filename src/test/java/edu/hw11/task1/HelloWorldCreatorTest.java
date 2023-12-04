package edu.hw11.task1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HelloWorldCreatorTest {

    @Test
    @DisplayName("Тестирование HelloWorldCreator#createHelloWorld")
    public void createHelloWorld_shouldReturnObjectReturnsHelloWorld() {
        var helloWorld = HelloWorldCreator.createHelloWorld();
        Assertions.assertThat(helloWorld.toString()).isEqualTo("Hello, ByteBuddy!");
    }
}
