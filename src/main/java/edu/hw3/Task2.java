package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Task2 {

    private Task2() {
    }

    public static List<String> clusterize(String input) {
        var result = new ArrayList<String>();
        var number = 0;
        var cluster = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            var symbol = input.charAt(i);
            if (symbol == '(') {
                number++;
            } else if (symbol == ')') {
                number--;
            }
            cluster.append(symbol);
            if (number == 0) {
                result.add(cluster.toString());
                cluster = new StringBuilder();
            }
        }
        return result;
    }
}
