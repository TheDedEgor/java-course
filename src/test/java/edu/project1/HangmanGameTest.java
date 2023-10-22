package edu.project1;

import edu.project1.Interfaces.GuessResult;
import edu.project1.classes.Session;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HangmanGameTest {

    @Test
    @DisplayName("Не угадал букву")
    void Test1() {
        var session = new Session("word", 1);
        var guessResult = session.guess('a');
        assertThat(guessResult instanceof GuessResult.FailedGuess).isEqualTo(true);
    }

    @Test
    @DisplayName("Угадал букву")
    void Test2() {
        var session = new Session("word", 1);
        var guessResult = session.guess('w');
        assertThat(guessResult instanceof GuessResult.SuccessfulGuess).isEqualTo(true);
    }

    @Test
    @DisplayName("Игра возвращает поражение")
    void Test3() {
        var session = new Session("word", 1);
        var guessResult = session.guess('a');
        assertThat(guessResult instanceof GuessResult.FailedGuess).isEqualTo(true);
        for (int i = 0; i < 5; i++) {
            var gameResult = session.checkEndGame();
            assertThat(gameResult instanceof GuessResult.Defeat).isEqualTo(true);
        }
    }

    @Test
    @DisplayName("Игра возвращает выйгрыш")
    void Test4() {
        var session = new Session("b", 1);
        var guessResult = session.guess('b');
        assertThat(guessResult instanceof GuessResult.SuccessfulGuess).isEqualTo(true);
        var gameResult = session.checkEndGame();
        assertThat(gameResult instanceof GuessResult.Win).isEqualTo(true);
    }
}
