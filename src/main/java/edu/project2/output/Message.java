package edu.project2.output;

public enum Message {

    HELLO_MESSAGE("Добро пожаловать в генератор лабиринтов!"),
    WRITE_SIZES("Введите размеры лабиринта через пробел. Минимальный размер лабиринта 5x5"),
    WRONG_INPUT("Неверные входные данные!"),
    WRITE_SOLVER_ALGORITHM("Введите алгоритм для решения лабиринта. (1) - DFS, (2) - BFS"),
    WRITE_ALGORITHM(
        "Введите алгоритм для генерации лабиринта. (1) - DFS, (2) - Итеративный DFS, (3) - Краскала, (4) - Прима"),
    WRITE_COORDINATES("Введите начальную и конечную точку. Все координаты через пробел");

    private final String text;

    Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
