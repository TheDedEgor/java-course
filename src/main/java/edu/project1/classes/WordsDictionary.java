package edu.project1.classes;

import edu.project1.Interfaces.Dictionary;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class WordsDictionary implements Dictionary {

    private final String[] words = new String[] {
        "banana",
        "brother",
        "mistake",
        "apple",
        "dictionary"
    };

    @Override
    public @NotNull String randomWord() {
        var rand = new Random();
        var idx = rand.nextInt(words.length);
        return words[idx];
    }
}
