package edu.hw3.task5;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class ContactSorter {

    private ContactSorter() {
    }

    public static List<Contact> sortContacts(List<String> names, String sortType) {
        if (names == null || names.isEmpty()) {
            return Collections.emptyList();
        }

        List<Contact> contacts = names.stream().map(Contact::fromString).collect(Collectors.toList());

        return switch (sortType) {
            case "ASC" -> internalSort(contacts, ContactSorter::compare);
            case "DESC" -> internalSort(contacts, (s1, s2) -> ContactSorter.compare(s2, s1));
            default -> throw new IllegalArgumentException("SortType must be DESC or ASC");
        };
    }

    private static List<Contact> internalSort(List<Contact> contacts, Comparator<Contact> comparator) {
        contacts.sort(comparator);
        return contacts;
    }

    private static int compare(Contact o1, Contact o2) {
        if (o1 == null || o2 == null) {
            return 0;
        }
        String contactNameO1 = o1.surName().isEmpty() ? o1.name() : o1.surName();
        String contactNameO2 = o2.surName().isEmpty() ? o2.name() : o2.surName();

        return contactNameO1.compareTo(contactNameO2);
    }

}
