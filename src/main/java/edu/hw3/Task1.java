package edu.hw3;

public class Task1 {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private Task1() {
    }

    public static String atbash(String input) {
        var lastIdx = ALPHABET.length() - 1;
        var result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            var symbol = input.charAt(i);
            var idx = ALPHABET.indexOf(Character.toUpperCase(symbol));
            if (idx != -1) {
                var newSymbol = ALPHABET.charAt(lastIdx - idx);
                if (!Character.isUpperCase(symbol)) {
                    newSymbol = Character.toLowerCase(newSymbol);
                }
                result.append(newSymbol);
            } else {
                result.append(symbol);
            }
        }
        return result.toString();
    }
}
