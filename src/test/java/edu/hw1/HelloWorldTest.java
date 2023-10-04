package edu.hw1;

import edu.hw1.task0.HelloWorld;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HelloWorldTest {

    @Test
    @DisplayName("Логирование \"Привет, мир!\"")
    void helloWorldTest(){
        HelloWorld.printHelloWorld();
    }

}
