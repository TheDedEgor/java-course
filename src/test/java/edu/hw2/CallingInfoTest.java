package edu.hw2;

import edu.hw2.task4.Task4;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CallingInfoTest {

    void throwingException1() {
        throw new RuntimeException();
    }

    void throwingException2() {
        new ThrowingExceptionClass().run();
    }

    @Test
    @DisplayName("Проверка метода вызывающего ошибку 1")
    void validTest1() {
        try {
            throwingException1();
        }
        catch (Throwable ex) {
            var callingInfo = Task4.callingInfo(ex);
            var className = callingInfo.className();
            var methodName = callingInfo.methodName();
            assertThat(className).isEqualTo("edu.hw2.CallingInfoTest");
            assertThat(methodName).isEqualTo("throwingException1");
        }
    }

    @Test
    @DisplayName("Проверка метода вызывающего ошибку 2")
    void validTest2() {
        try {
            throwingException2();
        }
        catch (Throwable ex) {
            var callingInfo = Task4.callingInfo(ex);
            var className = callingInfo.className();
            var methodName = callingInfo.methodName();
            assertThat(className).isEqualTo("edu.hw2.ThrowingExceptionClass");
            assertThat(methodName).isEqualTo("run");
        }
    }
}
