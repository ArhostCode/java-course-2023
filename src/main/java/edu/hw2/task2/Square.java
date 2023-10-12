package edu.hw2.task2;

public class Square extends Rectangle {

    public Square(int side) {
        super(side, side);
    }

    public Square setSide(int side) {
        return new Square(side);
    }

}
