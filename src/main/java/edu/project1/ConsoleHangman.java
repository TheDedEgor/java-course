package edu.project1;

import edu.project1.Interfaces.Dictionary;
import edu.project1.Interfaces.GuessResult;
import edu.project1.classes.Session;
import edu.project1.classes.WordsDictionary;
import java.util.Scanner;

@SuppressWarnings("RegexpSinglelineJava")
public class ConsoleHangman {

    private final Dictionary dictionary = new WordsDictionary();

    public void run() {
        System.out.println("-----Hangman Game-----\n");
        final int maxAttempts = 5;
        var session = new Session(dictionary.randomWord(), maxAttempts);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Guess a letter: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("/end")) {
                System.out.println(session.giveUp().message());
                break;
            }

            if (input.length() != 1) {
                System.out.println("Please enter a single letter.\n");
                continue;
            }

            var guessResult = session.guess(input.charAt(0));
            printState(guessResult);

            guessResult = session.checkEndGame();
            if (guessResult != null) {
                System.out.println(guessResult.message());
                break;
            }
        }
        scanner.close();
    }

    private void printState(GuessResult guess) {
        System.out.println(guess.message());
        System.out.println();
        System.out.println("The word: " + new String(guess.state()));
        System.out.println();
    }
}
