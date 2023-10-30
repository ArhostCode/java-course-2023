package edu.hw4;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public final class AnimalUtils {

    private AnimalUtils() {
    }

    public static List<Animal> sortAnimalsByHeightMinToMax(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    public static List<Animal> sortAnimalsByWeightMaxToMinAndTakeK(List<Animal> animals, int k) {
        if (k <= 0 || k > animals.size()) {
            throw new IllegalArgumentException("k must be positive and <= list size");
        }
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    public static Map<Animal.Type, Integer> countAllTypes(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(e -> 1)));
    }

    public static Animal getAnimalWithMaxName(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt((a) -> a.name().length()))
            .orElseThrow();
    }

    public static Animal.Sex getMostFrequentSex(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Comparator.comparingLong(Map.Entry::getValue))
            .orElseThrow()
            .getKey();
    }

    public static Map<Animal.Type, Animal> getHeaviestAnimalsMap(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparingInt(Animal::weight)),
                Optional::orElseThrow
            )));
    }

    public static Animal getKOldestAnimal(List<Animal> animals, int k) {
        if (k <= 0 || k > animals.size()) {
            throw new IllegalArgumentException("K must be positive and <= list size");
        }
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .skip(k - 1)
            .findFirst()
            .orElseThrow();
    }

    public static Optional<Animal> getHeaviestAnimalBelowHeightK(List<Animal> animals, int k) {
        return animals.stream()
            .filter(a -> a.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer getPawsSum(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> getAnimalsWithAgeNotEqualsPawsCount(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.paws() != animal.age())
            .toList();
    }

    public static List<Animal> getAnimalsWithBitesAndBigHeight(List<Animal> animals) {
        final int height = 100;
        return animals.stream()
            .filter(a -> a.bites() && a.height() > height)
            .toList();
    }

    public static Integer countAnimalsWithWeightMoreThanHeight(List<Animal> animals) {
        return (int) animals.stream()
            .filter(a -> a.weight() > a.height())
            .count();
    }

    public static List<Animal> getAnimalsWithNamesMoreTwoWords(List<Animal> animals) {
        return animals.stream()
            .filter(a -> a.name().split(" ").length > 2)
            .toList();
    }

    public static boolean containsDogHigherK(List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(a -> a.type() == Animal.Type.DOG && a.height() > k);
    }

    public static Map<Animal.Type, Integer> getWeightSumAnimalsWithAge(List<Animal> animals, int lower, int higher) {
        return animals.stream()
            .filter(a -> a.age() >= lower && a.age() <= higher)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    public static List<Animal> getSortedByTypeAndSexAndName(List<Animal> animals) {
        return animals.stream()
            .sorted(
                Comparator.comparing(Animal::type)
                    .thenComparing(Animal::sex)
                    .thenComparing(Animal::name))
            .toList();
    }

    public static boolean isSpidersBitesMoreDogs(List<Animal> animals) {
        return animals.stream()
            .filter(a -> a.type() == Animal.Type.SPIDER || a.type() == Animal.Type.DOG)
            .collect(Collectors.collectingAndThen(Collectors.groupingBy(
                Animal::type,
                Collectors.summingInt(a -> a.bites() ? 1 : 0)
            ), (map) -> {
                if (!map.containsKey(Animal.Type.SPIDER) || !map.containsKey(Animal.Type.DOG)) {
                    return false;
                }
                return map.get(Animal.Type.SPIDER) > map.get(Animal.Type.DOG);
            }));
    }

    public static Animal getHeaviestFish(List<List<Animal>> animals) {
        return animals.stream()
            .flatMap(Collection::stream)
            .filter(a -> a.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElseThrow();
    }

    public static Map<String, Set<ValidationError>> validate(List<Animal> animals) {
        final AnimalValidator validator = new AnimalValidator();
        return animals.stream()
            .collect(
                Collectors.collectingAndThen(
                    Collectors.toMap(
                        Animal::name, validator::validateAll
                    ), map -> {
                        map.values().removeIf(Set::isEmpty);
                        return map;
                    }
                )
            );
    }

    public static Map<String, String> validateToHumanReadable(List<Animal> animals) {
        final AnimalValidator validator = new AnimalValidator();
        return animals.stream()
            .collect(
                Collectors.collectingAndThen(
                    Collectors.toMap(Animal::name, animal -> validator
                        .validateAll(animal)
                        .stream()
                        .map(ValidationError::field)
                        .collect(Collectors.joining(","))
                    ), map -> {
                        map.values().removeIf(String::isEmpty);
                        return map;
                    }
                )
            );
    }
}
