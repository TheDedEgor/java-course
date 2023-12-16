package edu.hw10;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.Random;

public class RandomObjectGenerator {

    private static final String EXCEPTION_MSG = "Не удалось создать объект";

    public <T> T nextObject(Class<T> clazz, String factoryMethod) throws MyException {
        try {
            var method = getFactoryMethod(clazz, factoryMethod);
            if (method.isPresent()) {
                return createObject(method.get());
            } else {
                return nextObject(clazz);
            }
        } catch (Exception ex) {
            throw new MyException(EXCEPTION_MSG);
        }
    }

    public <T> T nextObject(Class<T> clazz) throws MyException {
        try {
            var constructors = clazz.getDeclaredConstructors();
            var constructor =
                Arrays.stream(constructors).sorted(Comparator.comparing(c -> c.getParameters().length)).toList()
                    .getLast();
            return createObject(constructor);
        } catch (Exception ex) {
            throw new MyException(EXCEPTION_MSG);
        }
    }

    private <T> Optional<Method> getFactoryMethod(Class<T> clazz, String factoryMethod) {
        return Arrays.stream(clazz.getDeclaredMethods())
            .filter(m -> m.getName().equals(factoryMethod))
            .findFirst();
    }

    private static <T> T createObject(Constructor<?> constructor) throws Exception {
        var parameters = constructor.getParameters();
        var args = getArgs(parameters);
        return (T) constructor.newInstance(args);
    }

    private static <T> T createObject(Method method) throws Exception {
        var parameters = method.getParameters();
        var args = getArgs(parameters);
        return (T) method.invoke(null, args);
    }

    private static Object[] getArgs(Parameter[] parameters) {
        var args = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            args[i] = RandomValueGenerator.getRandomValue(parameters[i]);
        }
        return args;
    }

    @SuppressWarnings("MagicNumber")
    static final class RandomValueGenerator {
        private static final Random RANDOM = new Random();

        public static Object getRandomValue(Parameter parameter) {
            var type = parameter.getType();
            if (type.equals(int.class) || type.equals(Integer.class)) {
                return randomInt(parameter);
            } else if (type.equals(String.class)) {
                return randomString(parameter);
            }
            return null;
        }

        private static Object randomInt(Parameter parameter) {
            int minValue = Integer.MIN_VALUE;
            int maxValue = Integer.MAX_VALUE;

            Max maxAnnotation = parameter.getAnnotation(Max.class);
            if (maxAnnotation != null) {
                maxValue = maxAnnotation.value();
            }
            Min minAnnotation = parameter.getAnnotation(Min.class);
            if (minAnnotation != null) {
                minValue = minAnnotation.value();
            }

            return RANDOM.nextInt(minValue, maxValue);
        }

        private static boolean isRandomNull() {
            var number = RANDOM.nextInt(100);
            return number > 50;
        }

        private static String randomString(Parameter parameter) {
            int leftLimit = 97;
            int rightLimit = 122;
            int targetStringLength = 10;
            boolean isNull = false;

            NotNull notNullAnnotation = parameter.getAnnotation(NotNull.class);
            if (notNullAnnotation == null) {
                isNull = isRandomNull();
            }

            return isNull ? null : RANDOM.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        }
    }
}
