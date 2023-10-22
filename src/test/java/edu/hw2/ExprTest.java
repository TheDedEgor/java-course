package edu.hw2;

import edu.hw2.task1.Expr.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExprTest {

    @Test
    @DisplayName("Проверка константы 5")
    void constantTest1() {
        var result = new Constant(5).evaluate();
        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("Проверка константы -3")
    void constantTest2() {
        var result = new Constant(-3).evaluate();
        assertThat(result).isEqualTo(-3);
    }

    @Test
    @DisplayName("Проверка отрицания 10")
    void negateTest1() {
        var result = new Negate(new Constant(10)).evaluate();
        assertThat(result).isEqualTo(-10);
    }

    @Test
    @DisplayName("Проверка отрицания -7")
    void negateTest2() {
        var result = new Negate(new Constant(-7)).evaluate();
        assertThat(result).isEqualTo(7);
    }

    @Test
    @DisplayName("Проверка сложения 11 и 5")
    void additionTest1() {
        var result = new Addition(new Constant(11), new Constant(5)).evaluate();
        assertThat(result).isEqualTo(16);
    }

    @Test
    @DisplayName("Проверка сложения -7 и 10")
    void additionTest2() {
        var result = new Addition(new Constant(-7), new Constant(10)).evaluate();
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("Проверка сложения -2 и -13")
    void additionTest3() {
        var result = new Addition(new Constant(-2), new Constant(-13)).evaluate();
        assertThat(result).isEqualTo(-15);
    }

    @Test
    @DisplayName("Проверка умножения 3 и 4")
    void multiplicationTest1() {
        var result = new Multiplication(new Constant(3), new Constant(4)).evaluate();
        assertThat(result).isEqualTo(12);
    }

    @Test
    @DisplayName("Проверка умножения -2 и 5")
    void multiplicationTest2() {
        var result = new Multiplication(new Constant(-2), new Constant(5)).evaluate();
        assertThat(result).isEqualTo(-10);
    }

    @Test
    @DisplayName("Проверка умножения -7 и -9")
    void multiplicationTest3() {
        var result = new Multiplication(new Constant(-7), new Constant(-9)).evaluate();
        assertThat(result).isEqualTo(63);
    }

    @Test
    @DisplayName("Проверка возведения числа 3 в степень 2")
    void exponentTest1() {
        var result = new Exponent(new Constant(3), 2).evaluate();
        assertThat(result).isEqualTo(9);
    }

    @Test
    @DisplayName("Проверка возведения числа -2 в степень 4")
    void exponentTest2() {
        var result = new Exponent(new Constant(-2), 4).evaluate();
        assertThat(result).isEqualTo(16);
    }

    @Test
    @DisplayName("Проверка примера (((2 + 4) * -1)^2 + 1)")
    void exampleTest1() {
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var result = new Addition(exp, new Constant(1));

        assertThat(result.toString()).isEqualTo("(((2.0 + 4.0) * -1.0)^2.0 + 1.0)");
        assertThat(result.evaluate()).isEqualTo(37.0);
    }

    @Test
    @DisplayName("Проверка примера ((-2 * 5 + 7)^2 + 1)")
    void exampleTest2() {
        var negativeTwo = new Constant(-2);
        var five = new Constant(5);
        var mult = new Multiplication(negativeTwo, five);
        var sum = new Addition(mult, new Constant(7));
        var exp = new Exponent(sum, 2);
        var result = new Addition(exp, new Constant(1));

        assertThat(result.toString()).isEqualTo("(((-2.0 * 5.0) + 7.0)^2.0 + 1.0)");
        assertThat(result.evaluate()).isEqualTo(10.0);
    }
}
