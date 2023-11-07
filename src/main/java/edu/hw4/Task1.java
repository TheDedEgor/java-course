package edu.hw4;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuppressWarnings("MagicNumber")
public class Task1 {

    private final List<Animal> animals;

    public Task1(List<Animal> animals) {
        this.animals = animals;
    }

    public List<Animal> task1() {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::height))
            .toList();
    }

    public List<Animal> task2(int k) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    public Map<Animal.Type, Long> task3() {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    public Animal task4() {
        return animals.stream()
            .sorted(Comparator.comparing(x -> -x.name().length()))
            .findFirst().get();
    }

    public Animal.Sex task5() {
        var map = animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()));
        if (map.get(Animal.Sex.F) > map.get(Animal.Sex.M)) {
            return Animal.Sex.F;
        }
        return Animal.Sex.M;
    }

    public Map<Animal.Type, Animal> task6() {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::type,
                Function.identity(),
                BinaryOperator.maxBy(Comparator.comparing(Animal::weight))
            ));
    }

    public Animal task7(int k) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::age).reversed()).toList().get(k - 1);
    }

    public Optional<Animal> task8(int k) {
        return animals.stream()
            .filter(x -> x.height() < k)
            .max(Comparator.comparing(Animal::weight));
    }

    public Integer task9() {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public List<Animal> task10() {
        return animals.stream()
            .filter(x -> x.age() != x.paws())
            .toList();
    }

    public List<Animal> task11() {
        return animals.stream()
            .filter(x -> x.bites() && x.height() > 100)
            .toList();
    }

    public Long task12() {
        return animals.stream()
            .filter(x -> x.weight() > x.height())
            .count();
    }

    public List<Animal> task13() {
        return animals.stream()
            .filter(x -> x.name().split(" ").length > 2)
            .toList();
    }

    public Boolean task14(int k) {
        return animals.stream()
            .anyMatch(x -> x.type() == Animal.Type.DOG && x.height() > k);
    }

    public Map<Animal.Type, Integer> task15(int k, int l) {
        return animals.stream()
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.filtering(x -> k < x.age() && x.age() < l, Collectors.summingInt(Animal::weight))
            ));
    }

    public List<Animal> task16() {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type).thenComparing(Animal::sex).thenComparing(Animal::name))
            .toList();
    }

    public Boolean task17() {
        var spidersCount = animals.stream()
            .filter(x -> x.type() == Animal.Type.SPIDER && x.bites())
            .count();

        var dogsCount = animals.stream()
            .filter(x -> x.type() == Animal.Type.DOG && x.bites())
            .count();

        return spidersCount > dogsCount;
    }

    public Animal task18(List<List<Animal>> list) {
        return list.stream()
            .flatMap(Collection::stream)
            .filter(x -> x.type() == Animal.Type.FISH)
            .sorted(Comparator.comparing(Animal::weight).reversed())
            .findFirst().orElseThrow();
    }

    public Map<String, Set<ValidationError>> task19() {
        return animals.stream()
            .collect(Collectors.toMap(Animal::name, this::validateAnimal));
    }

    public Map<String, String> task20() {
        var map = task19();
        return map.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, x -> x.getValue().stream()
                .map(ValidationError::fieldName)
                .collect(Collectors.joining(", "))));
    }

    private Set<ValidationError> validateAnimal(Animal animal) {
        var result = new HashSet<ValidationError>();
        if (animal.age() < 0) {
            result.add(new ValidationError("age", "Возраст меньше 0"));
        }
        if (animal.height() < 0) {
            result.add(new ValidationError("height", "Рост меньше 0"));
        }
        if (animal.weight() < 0) {
            result.add(new ValidationError("weight", "Вес меньше 0"));
        }
        return result;
    }
}
