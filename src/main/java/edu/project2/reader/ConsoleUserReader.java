package edu.project2.reader;

import java.util.Scanner;

public class ConsoleUserReader implements UserReader {

    private final Scanner input;

    public ConsoleUserReader() {
        this.input = new Scanner(System.in);
    }

    @Override
    public int readInt() {
        return input.nextInt();
    }
}
