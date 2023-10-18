package edu.hw3.task5;

public record Contact(String name, String surName) {

    public static Contact fromString(String fullName) {
        String[] name = fullName.split(" ");
        if (name.length < 2) {
            return new Contact(name[0], "");
        }
        return new Contact(name[0], name[1]);
    }
}
