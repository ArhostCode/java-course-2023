package edu.project2.model;

public record Cell(int x, int y, Type type) {
    public enum Type { WALL, PASSAGE }
}
