package edu.hw3.task5;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public final class ContactSorter {

    private ContactSorter() {
    }

    public static List<Contact> sortContacts(List<String> names, SortType sortType) {
        if (names == null || names.isEmpty()) {
            return Collections.emptyList();
        }

        Stream<Contact> contacts = names.stream().map(Contact::fromString);

        return switch (sortType) {
            case ASC -> contacts.sorted(ContactSorter::compare).toList();
            case DESC -> contacts.sorted((s1, s2) -> ContactSorter.compare(s2, s1)).toList();
        };
    }

    private static int compare(Contact o1, Contact o2) {
        if (o1 == null || o2 == null) {
            return 0;
        }
        String contactNameO1 = o1.surName().isEmpty() ? o1.name() : o1.surName();
        String contactNameO2 = o2.surName().isEmpty() ? o2.name() : o2.surName();

        return contactNameO1.compareTo(contactNameO2);
    }

    public enum SortType {
        ASC, DESC
    }

}
