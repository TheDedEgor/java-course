package edu.project1.classes;

import edu.project1.Interfaces.GuessResult;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class Session {

    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int attempts;

    public Session(String answer, int maxAttempts) {
        this.answer = answer;
        this.userAnswer = new char[answer.length()];
        Arrays.fill(userAnswer, '*');
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
    }

    public @NotNull GuessResult giveUp() {
        return new GuessResult.Defeat(userAnswer);
    }

    public @NotNull GuessResult guess(char guess) {
        var isGuess = isGuessed(guess);
        if (isGuess) {
            return new GuessResult.SuccessfulGuess(userAnswer);
        }
        attempts++;
        return new GuessResult.FailedGuess(userAnswer, attempts, maxAttempts);
    }

    public GuessResult checkEndGame() {
        if (attempts >= maxAttempts) {
            return new GuessResult.Defeat(userAnswer);
        }

        if (isWordGuessed()) {
            return new GuessResult.Win(userAnswer);
        }

        return null;
    }

    private boolean isGuessed(char guess) {
        boolean found = false;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == guess) {
                userAnswer[i] = guess;
                found = true;
            }
        }
        return found;
    }

    private boolean isWordGuessed() {
        for (var letter : userAnswer) {
            if (letter == '*') {
                return false;
            }
        }
        return true;
    }
}
