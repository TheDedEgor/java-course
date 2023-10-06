package edu.hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void helloWorldPrint() {
        LOGGER.info("Привет, мир!");
    }

    @SuppressWarnings("MagicNumber")
    public static int minutesToSeconds(String time) {
        var nums = time.split(":");
        var minutes = Integer.parseInt(nums[0]);
        var seconds = Integer.parseInt(nums[1]);
        if (minutes < 0 || seconds < 0 || seconds >= 60) {
            return -1;
        }
        return minutes * 60 + seconds;
    }

    @SuppressWarnings("MagicNumber")
    public static int countDigits(int number) {
        int count = 0;
        int num = number;
        do {
            num /= 10;
            count++;
        } while (num != 0);
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

    @SuppressWarnings("MagicNumber")
    private static boolean isPalindromeNumber(int number) {
        var initial = number;
        var num = initial;
        var rev = 0;
        while (num > 0) {
            var dig = num % 10;
            rev = rev * 10 + dig;
            num = num / 10;
        }
        return initial == rev;
    }

    @SuppressWarnings("MagicNumber")
    public static boolean isPalindromeDescendant(int number) {
        int n = number;
        do {
            if (isPalindromeNumber(n)) {
                return true;
            }
            var num = 0;
            var deg = 1;
            while (n > 0) {
                var digit1 = n % 10;
                var digit2 = n % 100 / 10;
                num = (digit1 + digit2) * deg + num;
                n /= 100;
                deg *= 10;
            }
            n = num;
        } while (String.valueOf(n).length() > 1);

        return false;
    }

    @SuppressWarnings("MagicNumber")
    public static int countK(int number, int count) {
        int n = number;
        if (n == 6174) {
            return count;
        }

        List<Integer> digits = new ArrayList<>();
        while (n > 0) {
            var digit = n % 10;
            digits.add(digit);
            n /= 10;
        }
        digits.sort(Comparator.naturalOrder());
        int deg = 1;
        int num1 = 0;
        int num2 = 0;
        for (var digit : digits) {
            num1 = num1 * 10 + digit;
            num2 = digit * deg + num2;
            deg *= 10;
        }

        return countK(num2 - num1, count + 1);
    }

    public static int rotateLeft(int n, int shift) {
        int sh = shift;
        var bits = Integer.toBinaryString(n).toCharArray();
        int length = bits.length;
        sh %= length;
        var shiftBits = new char[length];
        System.arraycopy(bits, sh, shiftBits, 0, length - sh);
        System.arraycopy(bits, 0, shiftBits, length - sh, sh);

        return Integer.parseInt(new String(shiftBits), 2);
    }

    public static int rotateRight(int n, int shift) {
        int sh = shift;
        var bits = Integer.toBinaryString(n).toCharArray();
        int length = bits.length;
        sh %= length;
        var shiftBits = new char[length];
        System.arraycopy(bits, length - sh, shiftBits, 0, sh);
        System.arraycopy(bits, 0, shiftBits, sh, length - sh);

        return Integer.parseInt(new String(shiftBits), 2);
    }

    private static boolean checkPlace(int i, int j, int[][] board) {
        try {
            return board[i][j] == 1;
        } catch (Exception ex) {
            return false;
        }
    }

    @SuppressWarnings("MagicNumber")
    public static boolean knightBoardCapture(int[][] board) {
        boolean result = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 1) {
                    if (checkPlace(i - 1, j - 2, board)) {
                        result = false;
                        break;
                    }
                    if (checkPlace(i - 1, j + 2, board)) {
                        result = false;
                        break;
                    }
                    if (checkPlace(i + 1, j - 2, board)) {
                        result = false;
                        break;
                    }
                    if (checkPlace(i + 1, j + 2, board)) {
                        result = false;
                        break;
                    }
                    if (checkPlace(i - 2, j - 1, board)) {
                        result = false;
                        break;
                    }
                    if (checkPlace(i - 2, j + 1, board)) {
                        result = false;
                        break;
                    }
                    if (checkPlace(i + 2, j - 1, board)) {
                        result = false;
                        break;
                    }
                    if (checkPlace(i + 2, j + 1, board)) {
                        result = false;
                        break;
                    }
                }
            }
            if (!result) {
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        helloWorldPrint();
    }
}
