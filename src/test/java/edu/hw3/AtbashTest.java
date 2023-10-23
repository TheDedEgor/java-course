package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AtbashTest {

    @Test
    @DisplayName("Строка только в верхнем регистре")
    void Test1() {
        var result = Task1.atbash("ABC");
        assertThat(result).isEqualTo("ZYX");
    }

    @Test
    @DisplayName("Строка только в нижнем регистре")
    void Test2() {
        var result = Task1.atbash("abc");
        assertThat(result).isEqualTo("zyx");
    }

    @Test
    @DisplayName("Строка с разным регистром")
    void Test3() {
        var result = Task1.atbash("AbC");
        assertThat(result).isEqualTo("ZyX");
    }

    @Test
    @DisplayName("Строка со спец. символами")
    void Test4() {
        var result = Task1.atbash("Hello world!");
        assertThat(result).isEqualTo("Svool dliow!");
    }

    @Test
    @DisplayName("Строка с русским алфавитом")
    void Test5() {
        var result = Task1.atbash("Hello мир");
        assertThat(result).isEqualTo("Svool мир");
    }

    @Test
    @DisplayName("Большая строка")
    void Test6() {
        var result = Task1.atbash("Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler");
        assertThat(result).isEqualTo("Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi");
    }

}
