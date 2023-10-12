package edu.hw2.task2;

public class Square extends Rectangle {

    public Square setSide(int side) {
        setWidth(side);
        setHeight(side);
        return this;
    }

}
