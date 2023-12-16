package edu.hw10;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RandomObjectGeneratorTest {

    @Test
    void randomObjectGeneratorTest1() throws MyException {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        var myClass = rog.nextObject(MyClass.class);
        assertThat(myClass).isNotNull();
        assertThat(myClass.getName()).isNotNull();
        assertThat(myClass.getNumber()).isBetween(1, 10);
    }

    @Test
    void randomObjectGeneratorTest2() throws MyException {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        var myRecord = rog.nextObject(MyRecord.class);
        assertThat(myRecord).isNotNull();
    }

    @Test
    void randomObjectGeneratorTest3() throws MyException {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        var myClass = rog.nextObject(MyClass.class, "create");
        assertThat(myClass).isNotNull();
    }
}

class MyClass {
    private String name;

    private int number;

    public MyClass(String name) {
        this.name = name;
    }

    public MyClass(@NotNull String name, @Min(value = 1) @Max(value = 10) Integer number) {
        this.name = name;
        this.number = number;
    }

    public static MyClass create(String name) {
        return new MyClass(name);
    }

    public String getName() {
        return this.name;
    }

    public Integer getNumber() {
        return this.number;
    }
}

record MyRecord(String value) {
}
