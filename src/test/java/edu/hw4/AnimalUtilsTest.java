package edu.hw4;

import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnimalUtilsTest {

    @Test
    @DisplayName("Тестирование AnimalUtils#sortAnimalsByHeightMinToMax")
    public void sortAnimalsByHeightMinToMax_shouldReturnSortedAnimals() {
        List<Animal> given = List.of(
            Animal.builder().height(10).build(),
            Animal.builder().height(2).build(),
            Animal.builder().height(55).build(),
            Animal.builder().height(5).build(),
            Animal.builder().height(9).build()
        );

        Assertions.assertThat(AnimalUtils.sortAnimalsByHeightMinToMax(given)).containsExactly(
            given.get(1),
            given.get(3),
            given.get(4),
            given.get(0),
            given.get(2)
        );
    }

    @Test
    @DisplayName("Тестирование AnimalUtils#sortAnimalsByWeightMaxToMinAndTakeK")
    public void sortAnimalsByWeightMaxToMinAndTakeK_shouldReturnSortedAnimals() {
        List<Animal> given = List.of(
            Animal.builder().weight(10).build(),
            Animal.builder().weight(200).build(),
            Animal.builder().weight(40).build(),
            Animal.builder().weight(70).build(),
            Animal.builder().weight(76).build()
        );

        Assertions.assertThat(AnimalUtils.sortAnimalsByWeightMaxToMinAndTakeK(given, 3)).containsExactly(
            given.get(1),
            given.get(4),
            given.get(3)
        );
    }

    @Test
    @DisplayName("Тестирование AnimalUtils#countAllTypes")
    public void countAllTypes_shouldReturnMapTypesAnimals() {
        List<Animal> given = List.of(
            Animal.builder().type(Animal.Type.CAT).build(),
            Animal.builder().type(Animal.Type.CAT).build(),
            Animal.builder().type(Animal.Type.BIRD).build(),
            Animal.builder().type(Animal.Type.FISH).build(),
            Animal.builder().type(Animal.Type.FISH).build(),
            Animal.builder().type(Animal.Type.SPIDER).build(),
            Animal.builder().type(Animal.Type.DOG).build()
        );
        Assertions.assertThat(AnimalUtils.countAllTypes(given))
            .containsEntry(Animal.Type.CAT, 2)
            .containsEntry(Animal.Type.DOG, 1)
            .containsEntry(Animal.Type.BIRD, 1)
            .containsEntry(Animal.Type.FISH, 2)
            .containsEntry(Animal.Type.SPIDER, 1);
    }

    @Test
    @DisplayName("Тестирование AnimalUtils#getAnimalWithMaxName")
    public void getAnimalWithMaxName_shouldReturnAnimalWithMaxName() {
        List<Animal> given = List.of(
            Animal.builder().name("Korzhik").build(),
            Animal.builder().name("Plavalkas").build(),
            Animal.builder().name("Polzalka").build()
        );
        Assertions.assertThat(AnimalUtils.getAnimalWithMaxName(given)).isEqualTo(given.get(1));
    }

    @Test
    @DisplayName("Тестирование AnimalUtils#getMostFrequentSex")
    public void getMostFrequentSex_shouldReturnSexThatMostFrequent() {
        List<Animal> given = List.of(
            Animal.builder().sex(Animal.Sex.M).build(),
            Animal.builder().sex(Animal.Sex.M).build(),
            Animal.builder().sex(Animal.Sex.F).build(),
            Animal.builder().sex(Animal.Sex.F).build(),
            Animal.builder().sex(Animal.Sex.M).build()
        );
        Assertions.assertThat(AnimalUtils.getMostFrequentSex(given)).isEqualTo(Animal.Sex.M);
    }

    @Test
    @DisplayName("Тестирование AnimalUtils#getHeaviestAnimalsMap")
    public void getHeaviestAnimalsMap_shouldReturnCorrectHeavyMap() {
        List<Animal> given = List.of(
            Animal.builder().type(Animal.Type.CAT).weight(10).build(),
            Animal.builder().type(Animal.Type.CAT).weight(200).build(),
            Animal.builder().type(Animal.Type.DOG).weight(40).build(),
            Animal.builder().type(Animal.Type.FISH).weight(70).build(),
            Animal.builder().type(Animal.Type.SPIDER).weight(76).build()
        );
        Assertions.assertThat(AnimalUtils.getHeaviestAnimalsMap(given))
            .containsEntry(Animal.Type.CAT, given.get(1))
            .containsEntry(Animal.Type.DOG, given.get(2))
            .containsEntry(Animal.Type.FISH, given.get(3))
            .containsEntry(Animal.Type.SPIDER, given.get(4));
    }

    @Test
    @DisplayName("Тестирование AnimalUtils#getKOldestAnimal")
    public void getKOldestAnimal_shouldReturnCorrectKOldestAnimal() {
        List<Animal> given = List.of(
            Animal.builder().age(20).build(),
            Animal.builder().age(2).build(),
            Animal.builder().age(6).build(),
            Animal.builder().age(5).build()
        );
        Assertions.assertThat(AnimalUtils.getKOldestAnimal(given, 2)).isEqualTo(given.get(2));
    }

    @Test
    @DisplayName("Тестирование AnimalUtils#getHeaviestAnimalBelowHeightK")
    public void getHeaviestAnimalBelowHeightK_shouldReturnCorrectOldestAnimalBelowK() {
        List<Animal> given = List.of(
            Animal.builder().height(100).weight(100).build(),
            Animal.builder().height(50).weight(20).build(),
            Animal.builder().height(56).weight(24).build()
        );
        Assertions.assertThat(AnimalUtils.getHeaviestAnimalBelowHeightK(given, 70))
            .isEqualTo(Optional.of(given.get(2)));
    }

    @Test
    @DisplayName("Тестирование AnimalUtils#getPawsSum")
    public void getPawsSum_shouldReturnCorrectPawsCount() {
        List<Animal> given = List.of(
            Animal.builder().type(Animal.Type.DOG).build(),
            Animal.builder().type(Animal.Type.SPIDER).build(),
            Animal.builder().type(Animal.Type.CAT).build()
        );
        Assertions.assertThat(AnimalUtils.getPawsSum(given))
            .isEqualTo(16);
    }

    @Test
    @DisplayName("Тестирование AnimalUtils#getAnimalsWithAgeNotEqualsPawsCount")
    public void getAnimalsWithAgeNotEqualsPawsCount_shouldReturnCorrectAnswer() {
        List<Animal> given = List.of(
            Animal.builder().type(Animal.Type.DOG).age(4).build(),
            Animal.builder().type(Animal.Type.CAT).age(2).build(),
            Animal.builder().type(Animal.Type.BIRD).age(2).build(),
            Animal.builder().type(Animal.Type.FISH).age(2).build(),
            Animal.builder().type(Animal.Type.SPIDER).age(8).build()
        );
        Assertions.assertThat(AnimalUtils.getAnimalsWithAgeNotEqualsPawsCount(given))
            .containsExactly(
                given.get(1),
                given.get(3)
            );
    }

    @Test
    @DisplayName("Тестирование AnimalUtils#getAnimalsWithBitesAndBigHeight")
    public void getAnimalsWithBitesAndBigHeight_shouldReturnCorrectAnswer() {
        List<Animal> given = List.of(
            Animal.builder().type(Animal.Type.DOG).bites(true).height(200).build(),
            Animal.builder().type(Animal.Type.CAT).bites(true).height(400).build(),
            Animal.builder().type(Animal.Type.FISH).bites(false).height(400).build(),
            Animal.builder().type(Animal.Type.DOG).bites(true).height(90).build()
        );
        Assertions.assertThat(AnimalUtils.getAnimalsWithBitesAndBigHeight(given))
            .containsExactly(
                given.get(0),
                given.get(1)
            );
    }

    @Test
    @DisplayName("Тестирование AnimalUtils#countAnimalsWithWeightMoreThanHeight")
    public void countAnimalsWithWeightMoreThanHeight_shouldReturnCorrectAnswer() {
        List<Animal> given = List.of(
            Animal.builder().weight(100).height(20).build(),
            Animal.builder().weight(100).height(200).build(),
            Animal.builder().weight(100).height(50).build()
        );
        Assertions.assertThat(AnimalUtils.countAnimalsWithWeightMoreThanHeight(given))
            .isEqualTo(2);
    }

    @Test
    @DisplayName("Тестирование AnimalUtils#getAnimalsWithNamesMoreTwoWords")
    public void getAnimalsWithNamesMoreTwoWords_shouldReturnCorrectAnswer() {
        List<Animal> given = List.of(
            Animal.builder().name("Polz kolx hi").build(),
            Animal.builder().name("Ji Wan Guk").build(),
            Animal.builder().name("Nihau tau").build()
        );
        Assertions.assertThat(AnimalUtils.getAnimalsWithNamesMoreTwoWords(given))
            .containsExactly(given.get(0), given.get(1));
    }

    @Test
    @DisplayName("Тестирование AnimalUtils#containsDogHigherK")
    public void containsDogHigherK_shouldReturnCorrectAnswer() {
        List<Animal> given = List.of(
            Animal.builder().type(Animal.Type.DOG).height(105).build(),
            Animal.builder().type(Animal.Type.CAT).height(10).build()
        );
        Assertions.assertThat(AnimalUtils.containsDogHigherK(given, 100))
            .isTrue();
    }

    @Test
    @DisplayName("Тестирование AnimalUtils#getWeightSumAnimalsWithAge")
    public void getWeightSumAnimalsWithAge_shouldReturnCorrectAnswer() {
        List<Animal> given = List.of(
            Animal.builder().type(Animal.Type.DOG).age(2).weight(100).build(),
            Animal.builder().type(Animal.Type.DOG).age(7).weight(100).build(),
            Animal.builder().type(Animal.Type.CAT).age(10).weight(20).build(),
            Animal.builder().type(Animal.Type.CAT).age(7).weight(25).build(),
            Animal.builder().type(Animal.Type.CAT).age(8).weight(5).build(),
            Animal.builder().type(Animal.Type.FISH).age(8).weight(3).build(),
            Animal.builder().type(Animal.Type.FISH).age(6).weight(5).build()
        );
        Assertions.assertThat(AnimalUtils.getWeightSumAnimalsWithAge(given, 5, 10))
            .containsEntry(Animal.Type.DOG, 100)
            .containsEntry(Animal.Type.FISH, 8)
            .containsEntry(Animal.Type.CAT, 50);
    }

    @Test
    @DisplayName("Тестирование AnimalUtils#getSortedByTypeAndSexAndName")
    public void getSortedByTypeAndSexAndName_shouldReturnCorrectAnswer() {
        List<Animal> given = List.of(
            Animal.builder().type(Animal.Type.DOG).sex(Animal.Sex.M).name("aoboa").build(),
            Animal.builder().type(Animal.Type.CAT).sex(Animal.Sex.M).name("korzh").build(),
            Animal.builder().type(Animal.Type.FISH).sex(Animal.Sex.M).name("rum").build(),
            Animal.builder().type(Animal.Type.DOG).sex(Animal.Sex.F).name("pikus").build(),
            Animal.builder().type(Animal.Type.DOG).sex(Animal.Sex.M).name("boboa").build()
        );
        Assertions.assertThat(AnimalUtils.getSortedByTypeAndSexAndName(given))
            .containsExactly(
                given.get(1),
                given.get(0),
                given.get(4),
                given.get(3),
                given.get(2)
            );
    }

    @Test
    @DisplayName("Тестирование AnimalUtils#isSpidersBitesMoreDogs")
    public void isSpidersBitesMoreDogs_shouldReturnCorrectAnswer() {
        List<Animal> given = List.of(
            Animal.builder().type(Animal.Type.DOG).bites(false).build(),
            Animal.builder().type(Animal.Type.CAT).bites(true).build(),
            Animal.builder().type(Animal.Type.SPIDER).bites(true).build(),
            Animal.builder().type(Animal.Type.DOG).bites(true).build(),
            Animal.builder().type(Animal.Type.DOG).bites(true).build()
        );
        Assertions.assertThat(AnimalUtils.isSpidersBitesMoreDogs(given)).isFalse();
    }

    @Test
    @DisplayName("Тестирование AnimalUtils#getHeaviestFish")
    public void getHeaviestFish_shouldReturnCorrectAnswer() {
        List<List<Animal>> given = List.of(
            List.of(
                Animal.builder().type(Animal.Type.FISH).weight(100).build(),
                Animal.builder().type(Animal.Type.DOG).weight(500).build()
            ),
            List.of(
                Animal.builder().type(Animal.Type.FISH).weight(103).build(),
                Animal.builder().type(Animal.Type.CAT).weight(550).build()
            ),
            List.of(
                Animal.builder().type(Animal.Type.FISH).weight(99).build(),
                Animal.builder().type(Animal.Type.FISH).weight(150).build()
            ),
            List.of(
                Animal.builder().type(Animal.Type.FISH).weight(50).build()
            )
        );
        Assertions.assertThat(AnimalUtils.getHeaviestFish(given)).isEqualTo(given.get(2).get(1));
    }

    @Test
    @DisplayName("Тестирование AnimalUtils#validate")
    public void validate_shouldReturnCorrectAnswer() {
        List<Animal> given = createAnimalsForValidation();
        Assertions.assertThat(AnimalUtils.validate(given))
            .hasEntrySatisfying("hello", (errors) -> Assertions.assertThat(errors)
                .extracting("field")
                .containsExactly("weight")
            )
            .hasEntrySatisfying("j", (errors) -> Assertions.assertThat(errors)
                .extracting("field")
                .containsExactly("weight", "name", "age")
            )
            .hasEntrySatisfying("jeck", (errors) -> Assertions.assertThat(errors)
                .extracting("field")
                .containsExactly("sex")
            )
            .hasEntrySatisfying("nulltype", (errors) -> Assertions.assertThat(errors)
                .extracting("field")
                .containsExactly("type")
            )
            .doesNotContainKey("normalChel");
    }

    @Test
    @DisplayName("Тестирование AnimalUtils#validateToHumanReadable")
    public void validateToHumanReadable_shouldReturnCorrectAnswer() {
        List<Animal> given = createAnimalsForValidation();
        Assertions.assertThat(AnimalUtils.validateToHumanReadable(given))
            .containsEntry("hello", "weight")
            .containsEntry("j", "weight,name,age")
            .containsEntry("jeck", "sex")
            .containsEntry("nulltype", "type")
            .doesNotContainKey("normalChel");

    }

    private List<Animal> createAnimalsForValidation() {
        return List.of(
            Animal.builder().name("hello").type(Animal.Type.DOG).sex(Animal.Sex.M).weight(-2).age(2).height(100)
                .build(),
            Animal.builder().name("j").type(Animal.Type.DOG).sex(Animal.Sex.M).weight(-5).age(-2).height(100)
                .build(),
            Animal.builder().name("jeck").type(Animal.Type.DOG).sex(null).weight(2).age(2).height(100)
                .build(),
            Animal.builder().name("nulltype").type(null).sex(Animal.Sex.M).weight(2).age(2).height(100)
                .build(),
            Animal.builder().name("normalChel").type(Animal.Type.DOG).sex(Animal.Sex.M).weight(2).age(2).height(100)
                .build()
        );
    }
}
