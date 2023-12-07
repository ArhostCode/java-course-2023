package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.util.Random;
import lombok.Getter;
import lombok.ToString;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RandomObjectGeneratorTest {

    @Test
    @DisplayName("Тестирование RandomObjectGenerator#nextObject")
    public void nextObject_shouldReturnObjectWithRandomValues_whenMixedClasses() {
        RandomObjectGenerator generator = new RandomObjectGenerator(
            new Random(),
            RandomObjectGenerator.Mode.SKIP_NESTED_IF_CAN
        );
        MixedClasses mixedClasses = generator.nextObject(MixedClasses.class);
        Assertions.assertThat(mixedClasses.value).isNotNull();
        Assertions.assertThat(mixedClasses.value2).isNotNull();
        Assertions.assertThat(mixedClasses.value3).isNotNull();
        Assertions.assertThat(mixedClasses.value4).isNotNull();
        Assertions.assertThat(mixedClasses.value5).isNotNull();
        Assertions.assertThat(mixedClasses.value6).isNotNull();
        Assertions.assertThat(mixedClasses.value7).isNotNull();
        Assertions.assertThat(mixedClasses.value8).isNotNull();
    }

    @Test
    @DisplayName("Тестирование RandomObjectGenerator#nextObject")
    public void nextObject_shouldReturnObjectWithRandomValues() {
        RandomObjectGenerator generator = new RandomObjectGenerator(
            new Random(),
            RandomObjectGenerator.Mode.SKIP_NESTED_IF_CAN
        );
        User user = generator.nextObject(User.class, "createSuperUser");
        Assertions.assertThat(user.login).isNotNull();
        Assertions.assertThat(user.person).isNotNull();
        Assertions.assertThat(user.person.age).isLessThan(91).isGreaterThan(9);
    }

    @Test
    @DisplayName("Тестирование RandomObjectGenerator#nextObjectWithSpecifiedConstructor")
    public void nextObjectWithSpecifiedConstructor_shouldReturnObjectWithRandomValues() {
        RandomObjectGenerator generator = new RandomObjectGenerator(
            new Random(),
            RandomObjectGenerator.Mode.SKIP_NESTED_IF_CAN
        );
        User user = generator.nextObjectWithSpecifiedConstructor(User.class, Person.class);
        Assertions.assertThat(user.person).isNotNull();
        Assertions.assertThat(user.login).isEqualTo("help");
    }

    @Test
    @DisplayName("Тестирование RandomObjectGenerator#nextObjectWithSpecifiedConstructor with Exception")
    public void nextObjectWithSpecifiedConstructor_shouldThrowException_whenConstructorNotFound() {
        RandomObjectGenerator generator = new RandomObjectGenerator(
            new Random(),
            RandomObjectGenerator.Mode.SKIP_NESTED_IF_CAN
        );
        Assertions.assertThatThrownBy(() -> generator.nextObjectWithSpecifiedConstructor(User.class, String.class))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Тестирование RandomObjectGenerator#nextObject with Exception")
    public void nextObject_shouldThrowException_whenFactoryMethodNotFound() {
        RandomObjectGenerator generator = new RandomObjectGenerator(
            new Random(),
            RandomObjectGenerator.Mode.SKIP_NESTED_IF_CAN
        );
        Assertions.assertThatThrownBy(() -> generator.nextObject(User.class, "sameFactoryMethod"))
            .isInstanceOf(IllegalArgumentException.class);
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

        public User(@NotNull Person person) {
            this(person, "help");
        }

        public static User createSuperUser(@NotNull Person person, String login) {
            return new User(person, login);
        }
    }

    public record Person(String name, @Min(10) @Max(90) Integer age) {
    }

    public record MixedClasses(
        Integer value,
        Long value2,
        Double value3,
        String value4,
        Double value5,
        Boolean value6,
        Byte value7,
        Float value8) {
    }

}
