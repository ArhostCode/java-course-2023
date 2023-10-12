package edu.hw2.task2;

public class Square extends Rectangle {

    public Square() {
    }

    public Square(int side) {
        super(side, side);
    }

    // Immutable class return new object with given side
    public final Square withSide(int side) {
        return new Square(side);
    }

}
