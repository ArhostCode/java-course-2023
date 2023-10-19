package edu.project1;

import edu.project1.config.GameConfiguration;
import edu.project1.game.GameLoop;
import edu.project1.game.InputData;
import edu.project1.source.InMemoryWordSource;
import edu.project1.source.WordSource;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class IncorrectConfigurationTest {

    @Test
    @DisplayName("Проверка игры на выброс исключения при неверном количестве попыток")
    @Timeout(1)
    public void testIncorrectAttemptsCount() {
        GameConfiguration configuration = createConfiguration(0, new InMemoryWordSource());
        GameLoop gameLoop = createGameLoop(configuration);
        assertThatThrownBy(gameLoop::prepare).isInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest(name = "{0} - некорректное слово")
    @DisplayName("Проверка игры на выброс исключения при некорректном слове в WordSource")
    @ValueSource(strings = {
        "12323",
        ""
    })
    @Timeout(1)
    public void testIncorrectWordInWordSource(String in) {
        GameConfiguration configuration = createConfiguration(1, new WordSource() {
            @Override
            public @NotNull String randomWord() {
                return in;
            }
        });
        GameLoop gameLoop = createGameLoop(configuration);
        assertThatThrownBy(gameLoop::prepare).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Проверка игры на выброс исключения при null WordSource")
    @Timeout(1)
    public void testNullWordSource() {
        GameConfiguration configuration = createConfiguration(1, null);
        GameLoop gameLoop = createGameLoop(configuration);
        assertThatThrownBy(gameLoop::prepare).isInstanceOf(IllegalStateException.class);
    }

    private GameConfiguration createConfiguration(int attemptsCount, WordSource wordSource) {
        return new GameConfiguration() {
            @Override
            public int getMaxAttemptsCount() {
                return attemptsCount;
            }

            @Override
            public WordSource getWordSource() {
                return wordSource;
            }
        };
    }

    private GameLoop createGameLoop(GameConfiguration configuration) {
        return new GameLoop(configuration) {
            @Override
            public InputData processInput() {
                return null;
            }

            @Override
            public void print(String output) {

            }
        };
    }
}
