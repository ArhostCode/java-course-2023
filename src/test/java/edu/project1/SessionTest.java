package edu.project1;

import edu.project1.game.Session;
import edu.project1.result.GuessResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {

    @Test
    @DisplayName("Проверка Session#guess на возвращение проигрыша игры при исчерпании попыток")
    public void guess_shouldReturnLoss_whenAttemptsMoreThanMaxAttempts() {
        Session session = new Session("test", 0, 3);
        session.guess("b");
        session.guess("b");
        GuessResult result = session.guess("b");

        assertThat(result).isInstanceOf(GuessResult.Defeat.class);
    }

    @Test
    @DisplayName("Проверка Session#guess на возвращение выигрыша игры")
    public void guess_shouldReturnWin_whenWordIsGuessed() {
        Session session = new Session("test", 0, 3);
        session.guess("t");
        session.guess("e");
        GuessResult result = session.guess("s");

        assertThat(result).isInstanceOf(GuessResult.Win.class);
    }

    @Test
    @DisplayName("Проверка Session#guess на возвращение угадывания буквы")
    public void guess_shouldReturnGuessed_whenLetterIsGuessed() {
        Session session = new Session("test", 0, 3);
        session.guess("t");
        GuessResult result = session.guess("s");

        assertThat(result).isInstanceOf(GuessResult.SuccessfulGuess.class);
    }

    @Test
    @DisplayName("Проверка Session#guess на возвращение неугаданной буквы")
    public void guess_shouldReturnNotGuessed_whenLetterIsNotGuessed() {
        Session session = new Session("test", 0, 3);
        session.guess("t");
        GuessResult result = session.guess("h");

        assertThat(result).isInstanceOf(GuessResult.FailedGuess.class);
    }

    @Test
    @DisplayName("Проверка Session#guess на возвращение результата, что буква угаданна")
    public void guess_shouldAlreadyGuessed_whenLetterIsAlreadyGuessed() {
        Session session = new Session("test", 0, 3);
        session.guess("t");
        GuessResult result = session.guess("t");

        assertThat(result).isInstanceOf(GuessResult.AlreadyOpenedGuess.class);
    }

    @Test
    @DisplayName("Проверка Session#guess на возвращение результата, что ввод некорректен")
    public void guess_shouldReturnIncorrectInput_whenInputIsIncorrect() {
        Session session = new Session("test", 0, 3);
        session.guess("t");
        GuessResult result = session.guess("tff");

        assertThat(result).isInstanceOf(GuessResult.IncorrectInputGuess.class);
    }

}
