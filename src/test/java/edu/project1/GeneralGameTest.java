package edu.project1;

import edu.project1.config.GameConfiguration;
import edu.project1.game.GameLoop;
import edu.project1.game.InputData;
import edu.project1.source.WordSource;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GeneralGameTest {

    private GameLoop gameLoop;
    private GameConfiguration configuration;
    private final Queue<InputData> input = new ArrayDeque<>();
    private final Queue<String> output = new ArrayDeque<>();

    @BeforeAll
    public void initTestingEnvironment() {
        WordSource oneWordSource = createWordSource();

        // Initialize special testing configuration
        configuration = new GameConfiguration() {
            @Override
            public int getMaxAttemptsCount() {
                return 3;
            }

            @Override
            public WordSource getWordSource() {
                return oneWordSource;
            }
        };

        // Initialize special testing game loop
        gameLoop = new GameLoop(configuration) {
            @Override
            public InputData processInput() {
                return input.poll();
            }

            @Override
            public void print(String output) {
                GeneralGameTest.this.output.add(output);
            }
        };
    }

    private WordSource createWordSource() {
        return new WordSource() {
            @Override
            public @NotNull String randomWord() {
                return "dark";
            }
        };
    }

    @BeforeEach
    public void prepareEachTest() {
        input.clear();
        output.clear();
        gameLoop.prepare();
    }

    @Test
    @DisplayName("Проверка игры на проигрыш при остановке ввода")
    @Timeout(1)
    public void testDefeat() {
        input.offer(InputData.input("a"));
        input.offer(InputData.closed());
        gameLoop.run();

        String[] expected =
            new String[] {"Guess the letter", "Hit!", "The word: *a**", "You lost!", "Actual word: dark"};
        assertThat(Arrays.equals(expected, output.toArray())).isTrue();
    }

    @Test
    @DisplayName("Проверка игры на корректный выигрыш")
    @Timeout(1)
    public void testWin() {
        initInputFromStringByChar("dark");
        gameLoop.run();

        String[] expected =
            new String[] {"Guess the letter", "Hit!", "The word: d***", "Hit!", "The word: da**", "Hit!",
                "The word: dar*", "You won",
                "The word: dark"};
        assertThat(Arrays.equals(expected, output.toArray())).isTrue();
    }

    @Test
    @DisplayName("Проверка игры на вывод проигрыша, когда попытки закончены")
    @Timeout(1)
    public void testDefeatWhenAttemptsOver() {
        initInputFromStringByChar("demb");
        gameLoop.run();

        String[] expected =
            new String[] {"Guess the letter", "Hit!", "The word: d***", "Missed, mistake 1 out of 3.", "The word: d***",
                "Missed, mistake 2 out of 3.", "The word: d***", "You lost!", "Actual word: dark"};
        assertThat(Arrays.equals(expected, output.toArray())).isTrue();
    }

    @ParameterizedTest(name = "{0} - неверный ввод")
    @DisplayName("Проверка игры на ввод неподходящих символов или больше чем 1 буква за раз")
    @ValueSource(strings = {
        "he",
        "*",
        "7"
    })
    @Timeout(1)
    public void testIncorrectInput(String in) {
        input.offer(InputData.input(in));
        input.offer(InputData.closed());
        gameLoop.run();

        String[] expected =
            new String[] {"Guess the letter",
                "You have entered the wrong letter or several letters at a time. Please re-enter.",
                "The word: ****", "You lost!", "Actual word: dark"};
        assertThat(Arrays.equals(expected, output.toArray())).isTrue();
    }

    @Test
    @DisplayName("Проверка игры на ввод буквы, которая уже была открыта")
    @Timeout(1)
    public void testLetterIsAlreadyOpened() {
        input.offer(InputData.input("a"));
        input.offer(InputData.input("a"));
        input.offer(InputData.closed());
        gameLoop.run();

        String[] expected =
            new String[] {"Guess the letter", "Hit!", "The word: *a**", "The letter is already open.", "The word: *a**",
                "You lost!", "Actual word: dark"};
        assertThat(Arrays.equals(expected, output.toArray())).isTrue();
    }

    private void initInputFromStringByChar(String in) {
        for (int i = 0; i < in.length(); i++) {
            input.offer(InputData.input(String.valueOf(in.charAt(i))));
        }
    }
}
