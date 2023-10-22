package edu.project1.Interfaces;

import org.jetbrains.annotations.NotNull;

public sealed interface GuessResult {
    char[] state();

    @NotNull String message();

    record Defeat(char[] userAnswer) implements GuessResult {
        @Override
        public char[] state() {
            return userAnswer;
        }

        @Override
        public @NotNull String message() {
            return "You lost!";
        }
    }

    record Win(char[] userAnswer) implements GuessResult {
        @Override
        public char[] state() {
            return userAnswer;
        }

        @Override
        public @NotNull String message() {
            return "You won!";
        }
    }

    record SuccessfulGuess(char[] userAnswer) implements GuessResult {
        @Override
        public char[] state() {
            return userAnswer;
        }

        @Override
        public @NotNull String message() {
            return "Hit!";
        }
    }

    record FailedGuess(char[] userAnswer, int attempts, int maxAttempts) implements GuessResult {
        @Override
        public char[] state() {
            return userAnswer;
        }

        @Override
        public @NotNull String message() {
            return "Missed, mistake " + attempts + " out of " + maxAttempts + ".";
        }
    }
}
