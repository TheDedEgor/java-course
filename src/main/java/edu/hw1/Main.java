package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    public static void helloWorldPrint() {
        LOGGER.info("Привет, мир!");
    }

    public static int minutesToSeconds(String time) {
        var nums = time.split(":");
        var minutes = Integer.parseInt(nums[0]);
        var seconds = Integer.parseInt(nums[1]);
        if (minutes < 0 || seconds < 0 || seconds >= 60) {
            return -1;
        }
        return minutes * 60 + seconds;
    }

    public static int countDigits(int number) {
        int count = 0;
        do {
            number /= 10;
            count++;
        }
        while (number != 0);
        return count;
    }

    public static boolean isNestable(int[] arr1, int[] arr2) {
        var minArr1 = Arrays.stream(arr1).min().orElse(-1);
        var maxArr1 = Arrays.stream(arr1).max().orElse(-1);
        var minArr2 = Arrays.stream(arr2).min().orElse(-1);
        var maxArr2 = Arrays.stream(arr2).max().orElse(-1);
        return (minArr1 > minArr2 && maxArr1 < maxArr2);
    }

    public static String fixString(String input) {
        var result = new StringBuilder();
        for (int i = 1; i < input.length(); i += 2) {
            result.append(input.charAt(i));
            result.append(input.charAt(i - 1));
        }
        if (input.length() % 2 != 0) {
            result.append(input.charAt(input.length() - 1));
        }
        return result.toString();
    }

    private static boolean isPalindromeNumber(int number) {
        var n = number;
        var rev = 0;
        while (number > 0) {
            var dig = number % 10;
            rev = rev * 10 + dig;
            number = number / 10;
        }
        return n == rev;
    }

    public static boolean isPalindromeDescendant(int number) {
        do {
            if (isPalindromeNumber(number)) {
                return true;
            }
            var num = 0;
            var deg = 1;
            while (number > 0) {
                var digit1 = number % 10;
                var digit2 = number % 100 / 10;
                num = (digit1 + digit2) * deg + num;
                number /= 100;
                deg *= 10;
            }
            number = num;
        }
        while (String.valueOf(number).length() > 1);

        return false;
    }

    public static int countK(int number, int count) {
        if (number == 6174) {
            return count;
        }

        List<Integer> digits = new ArrayList<>();
        while (number > 0) {
            var digit = number % 10;
            digits.add(digit);
            number /= 10;
        }
        digits.sort(Comparator.naturalOrder());
        int num1 = 0, num2 = 0, deg = 1;
        for (var digit : digits) {
            num1 = num1 * 10 + digit;
            num2 = digit * deg + num2;
            deg *= 10;
        }

        return countK(num2 - num1, count + 1);
    }

    public static int rotateLeft(int n, int shift) {
        var bits = Integer.toBinaryString(n).toCharArray();
        int length = bits.length;
        shift %= length;
        var shiftBits = new char[length];
        System.arraycopy(bits, shift, shiftBits, 0, length - shift);
        System.arraycopy(bits, 0, shiftBits, length - shift, shift);

        return Integer.parseInt(new String(shiftBits), 2);
    }

    public static int rotateRight(int n, int shift) {
        var bits = Integer.toBinaryString(n).toCharArray();
        int length = bits.length;
        shift %= length;
        var shiftBits = new char[length];
        System.arraycopy(bits, length - shift, shiftBits, 0, shift);
        System.arraycopy(bits, 0, shiftBits, shift, length - shift);

        return Integer.parseInt(new String(shiftBits), 2);
    }

    private static boolean checkPlace(int i, int j, int[][] board) {
        try {
            return board[i][j] == 1;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean knightBoardCapture(int[][] board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 1) {
                    if (checkPlace(i - 1, j - 2, board)) {
                        return false;
                    }
                    if (checkPlace(i - 1, j + 2, board)) {
                        return false;
                    }
                    if (checkPlace(i + 1, j - 2, board)) {
                        return false;
                    }
                    if (checkPlace(i + 1, j + 2, board)) {
                        return false;
                    }
                    if (checkPlace(i - 2, j - 1, board)) {
                        return false;
                    }
                    if (checkPlace(i - 2, j + 1, board)) {
                        return false;
                    }
                    if (checkPlace(i + 2, j - 1, board)) {
                        return false;
                    }
                    if (checkPlace(i + 2, j + 1, board)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        helloWorldPrint();
    }
}
