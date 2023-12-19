package edu.project5;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@SuppressWarnings({"MagicNumber", "UncommentedMain"})
@State(Scope.Thread)
public class ReflectionBenchmark {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(5))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(5))
            .build();

        new Runner(options).run();
    }

    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    private NameGetter lambdaMetafactory;

    @Setup
    public void setup() throws Throwable {
        var name = "name";
        student = new Student("Alexander", "Biryukov");
        method = student.getClass().getMethod(name);

        var lookup = MethodHandles.lookup();
        methodHandle = lookup.findVirtual(Student.class, name, MethodType.methodType(String.class));

        var factoryType = MethodType.methodType(NameGetter.class);
        var methodType = MethodType.methodType(String.class, Student.class);
        lambdaMetafactory = (NameGetter) LambdaMetafactory.metafactory(
            lookup,
            "get",
            factoryType,
            methodType,
            methodHandle,
            methodType
        ).getTarget().invokeExact();
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        bh.consume(name);
    }

    @Benchmark
    public void reflection(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        String name = (String) method.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void methodHandles(Blackhole bh) throws Throwable {
        String name = (String) methodHandle.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void lambdaMetafactory(Blackhole bh) {
        String name = lambdaMetafactory.get(student);
        bh.consume(name);
    }

    record Student(String name, String surname) {
    }

    interface NameGetter {
        String get(Student student);
    }
}
