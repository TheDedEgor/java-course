package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BytecodeGenerationTest {

    static class ArithmeticUtils {
        public int sum(int a, int b) {
            return a + b;
        }
    }

    static class MyInterceptor {
        public static int sum(int a, int b) {
            return a * b;
        }
    }

    @Test
    void helloByteBuddyTest() throws Exception {
        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .name("MyClass")
            .method(named("toString")).intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();

        assertThat(dynamicType.getDeclaredConstructor().newInstance().toString()).isEqualTo("Hello, ByteBuddy!");
    }

    @Test
    void replaceMethodTest() throws Exception {
        Class<?> modifiedClass = new ByteBuddy()
            .subclass(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(MyInterceptor.class))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
            .getLoaded();

        ArithmeticUtils modifiedInstance = (ArithmeticUtils) modifiedClass.newInstance();

        int result = modifiedInstance.sum(3, 4);
        assertThat(result).isEqualTo(12);
    }
}
