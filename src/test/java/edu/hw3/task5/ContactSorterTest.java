package edu.hw3.task5;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ContactSorterTest {

    private static Stream<Arguments> contactArguments() {
        return Stream.of(
            Arguments.of(
                List.of("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"),
                "ASC",
                List.of(
                    Contact.fromString("Thomas Aquinas"),
                    Contact.fromString("Rene Descartes"),
                    Contact.fromString("David Hume"),
                    Contact.fromString("John Locke")
                )
            ),
            Arguments.of(
                List.of("Paul Erdos", "Leonhard Euler", "Carl Gauss"),
                "DESC",
                List.of(
                    Contact.fromString("Carl Gauss"),
                    Contact.fromString("Leonhard Euler"),
                    Contact.fromString("Paul Erdos")
                )
            ),
            Arguments.of(
                List.of("Paul", "Leonhard Euler", "Carl Gauss"),
                "DESC",
                List.of(
                    Contact.fromString("Paul"),
                    Contact.fromString("Carl Gauss"),
                    Contact.fromString("Leonhard Euler")
                )
            ),
            Arguments.of(
                null,
                "DESC",
                Collections.emptyList()
            ),
            Arguments.of(
                Collections.emptyList(),
                "DESC",
                Collections.emptyList()
            )
        );
    }

    @DisplayName("Тест ContactSorter#sortContacts")
    @ParameterizedTest(name = "{0} - правильно сортируется")
    @MethodSource("contactArguments")
    public void sortContacts_shouldReturnCorrectValue(List<String> given, String sortType, List<Contact> expected) {
        Assertions.assertThat(ContactSorter.sortContacts(given, sortType)).containsAll(expected);
    }

    @DisplayName("Тест ContactSorter#sortContacts с неверным типом сортировки")
    @Test
    public void sortContacts_shouldThrowException_whenSortTypeInvalid() {
        Assertions.assertThatThrownBy(() -> {
            ContactSorter.sortContacts(List.of("hello"), "D");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
