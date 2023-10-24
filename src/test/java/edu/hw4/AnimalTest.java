package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnimalTest {

    static List<Animal> getAnimals () {
        var list = new ArrayList<Animal>();
        list.add(new Animal("Кошка", Animal.Type.CAT, Animal.Sex.F, 2, 30, 15, true));
        list.add(new Animal("Кот", Animal.Type.CAT, Animal.Sex.M, 5, 25, 20, true));
        list.add(new Animal("Собака", Animal.Type.DOG, Animal.Sex.M, 4, 50, 25, true));
        list.add(new Animal("Птица", Animal.Type.BIRD, Animal.Sex.M, 1, 20, 7, false));
        list.add(new Animal("Рыба", Animal.Type.FISH, Animal.Sex.M, 3, 5, 3, false));
        return list;
    }

    @Test
    @DisplayName("Задача 1")
    void Test1() {
        var task1 = new Task1(getAnimals());
        var list = task1.task1();
        for (int i = 0; i < list.size() - 1; i++) {
            var result = list.get(i).height() <= list.get(i + 1).height();
            assertThat(result).isTrue();
        }
    }

    @Test
    @DisplayName("Задача 2")
    void Test2() {
        var task1 = new Task1(getAnimals());
        var list = task1.task2(2);
        assertThat(list.size()).isEqualTo(2);
        for (int i = 0; i < list.size() - 1; i++) {
            var result = list.get(i).weight() >= list.get(i + 1).weight();
            assertThat(result).isTrue();
        }
    }

    @Test
    @DisplayName("Задача 3")
    void Test3() {
        var task1 = new Task1(getAnimals());
        var map = task1.task3();
        for (var result: map.entrySet()) {
            if (result.getKey() == Animal.Type.CAT) {
                assertThat(result.getValue()).isEqualTo(2);
                continue;
            }
            assertThat(result.getValue()).isEqualTo(1);
        }
    }

    @Test
    @DisplayName("Задача 4")
    void Test4() {
        var task1 = new Task1(getAnimals());
        var animal = task1.task4();
        var result = animal.name();
        assertThat(result).isEqualTo("Собака");
    }

    @Test
    @DisplayName("Задача 5")
    void Test5() {
        var task1 = new Task1(getAnimals());
        var result = task1.task5();
        assertThat(result).isEqualTo(Animal.Sex.M);
    }

    @Test
    @DisplayName("Задача 6")
    void Test6() {
        var task1 = new Task1(getAnimals());
        var map = task1.task6();
        for (var result: map.entrySet()) {
            if (result.getKey() == Animal.Type.CAT) {
                assertThat(result.getValue().age()).isEqualTo(5);
            } else if (result.getKey() == Animal.Type.DOG) {
                assertThat(result.getValue().age()).isEqualTo(4);
            }
            else if (result.getKey() == Animal.Type.BIRD) {
                assertThat(result.getValue().age()).isEqualTo(1);
            }
            else {
                assertThat(result.getValue().age()).isEqualTo(3);
            }
        }
    }

    @Test
    @DisplayName("Задача 7")
    void Test7() {
        var task1 = new Task1(getAnimals());
        var result = task1.task7(2);
        assertThat(result.type()).isEqualTo(Animal.Type.DOG);
    }

    @Test
    @DisplayName("Задача 8")
    void Test8() {
        var task1 = new Task1(getAnimals());
        var result = task1.task8(25);
        assertThat(result.get().type()).isEqualTo(Animal.Type.BIRD);
    }

    @Test
    @DisplayName("Задача 9")
    void Test9() {
        var task1 = new Task1(getAnimals());
        var result = task1.task9();
        assertThat(result).isEqualTo(14);
    }

    @Test
    @DisplayName("Задача 10")
    void Test10() {
        var task1 = new Task1(getAnimals());
        var result = task1.task10();
        var expected = getAnimals();
        expected.remove(2);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Задача 11")
    void Test11() {
        var task1 = new Task1(getAnimals());
        var result = task1.task11();
        assertThat(result).isEqualTo(List.of());
    }

    @Test
    @DisplayName("Задача 12")
    void Test12() {
        var task1 = new Task1(getAnimals());
        var result = task1.task12();
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("Задача 13")
    void Test13() {
        var task1 = new Task1(getAnimals());
        var result = task1.task13();
        assertThat(result).isEqualTo(List.of());
    }

    @Test
    @DisplayName("Задача 14")
    void Test14() {
        var task1 = new Task1(getAnimals());
        var result = task1.task14(30);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Задача 15")
    void Test15() {
        var task1 = new Task1(getAnimals());
        var result = task1.task15(0, 3);
        assertThat(result).isEqualTo(22);
    }

    @Test
    @DisplayName("Задача 16")
    void Test16() {
        var task1 = new Task1(getAnimals());
        var result = task1.task16();
        assertThat(result.get(0).name()).isEqualTo("Кот");
        assertThat(result.get(1).name()).isEqualTo("Кошка");
        assertThat(result.get(2).name()).isEqualTo("Собака");
        assertThat(result.get(3).name()).isEqualTo("Птица");
        assertThat(result.get(4).name()).isEqualTo("Рыба");
    }

    @Test
    @DisplayName("Задача 17")
    void Test17() {
        var task1 = new Task1(getAnimals());
        var result = task1.task17();
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Задача 18")
    void Test18() {
        List<List<Animal>> list = new ArrayList<>();
        var list1 = new ArrayList<Animal>();
        list1.add(new Animal("Кошка", Animal.Type.CAT, Animal.Sex.F, 2, 30, 15, true));
        list1.add(new Animal("Рыба", Animal.Type.FISH, Animal.Sex.M, 5, 25, 20, true));
        list1.add(new Animal("Собака", Animal.Type.DOG, Animal.Sex.M, 4, 50, 25, true));
        var list2 = new ArrayList<Animal>();
        list2.add(new Animal("Кот", Animal.Type.CAT, Animal.Sex.M, 3, 70, 25, true));
        list2.add(new Animal("Рыбка", Animal.Type.FISH, Animal.Sex.M, 2, 30, 15, false));
        list.add(list1);
        list.add(list2);
        var task1 = new Task1(null);
        var result = task1.task18(list);
        assertThat(result.weight()).isEqualTo(20);
    }

    @Test
    @DisplayName("Задача 19")
    void Test19() {
        var list = new ArrayList<Animal>();
        list.add(new Animal("Кошка", Animal.Type.CAT, Animal.Sex.F, -5, -30, 15, true));
        list.add(new Animal("Рыба", Animal.Type.FISH, Animal.Sex.M, 5, 25, -100, true));
        var task1 = new Task1(list);
        var result = task1.task19();
        assertThat(result.get("Кошка").size()).isEqualTo(2);
        assertThat(result.get("Рыба").size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Задача 20")
    void Test20() {
        var list = new ArrayList<Animal>();
        list.add(new Animal("Кошка", Animal.Type.CAT, Animal.Sex.F, -5, -30, 15, true));
        list.add(new Animal("Рыба", Animal.Type.FISH, Animal.Sex.M, 5, 25, -100, true));
        var task1 = new Task1(list);
        var result = task1.task20();
        System.out.println(result);
        assertThat(result.get("Кошка")).isEqualTo("age, height");
        assertThat(result.get("Рыба")).isEqualTo("weight");
    }
}
