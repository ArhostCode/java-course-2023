package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.util.Random;
import lombok.Getter;
import lombok.ToString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RandomObjectGeneratorTest {

    @Test
    @DisplayName("Тестирование RandomObjectGenerator#generate")
    public void generate_shouldReturnObjectWithRandomValues() {
        RandomObjectGenerator generator = new RandomObjectGenerator(
            new Random(),
            RandomObjectGenerator.Mode.SKIP_NESTED_IF_CAN
        );
        System.out.println(generator.nextObject(User.class, "createSuperUser"));
    }

    @Getter
    @ToString
    public static class User {

        private final Person person;
        private final String login;

        private User(Person person, String login) {
            this.person = person;
            this.login = login;
        }

        public static User createSuperUser(@NotNull Person person, String login) {
            return new User(person, login);
        }
    }

    public record Person(String name, @Min(10) @Max(90) Integer age) {
    }

}
