package edu.hw3;

import java.util.TreeMap;

@SuppressWarnings("MagicNumber")
public class Task4 {

    private Task4() {
    }

    public static String convertToRoman(Integer num) {
        var map = new TreeMap<Integer, String>();
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

        var result = new StringBuilder();
        var number = num;
        while (number > 0) {
            var digit = map.floorKey(number);
            result.append(map.get(digit));
            number -= digit;
        }

        return result.toString();
    }
}
