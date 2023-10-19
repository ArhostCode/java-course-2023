package edu.project1.game;

public final class InputData {
    private enum InputStatus {
        AVAILABLE, CLOSED
    }

    private final String input;
    private final InputStatus inputStatus;

    private InputData(String input, InputStatus inputStatus) {
        this.input = input;
        this.inputStatus = inputStatus;
    }

    // Static factory method for closed inputData
    public static InputData closed() {
        return new InputData(null, InputStatus.CLOSED);
    }

    // Static factory method for input string
    public static InputData input(String input) {
        return new InputData(input, InputStatus.AVAILABLE);
    }

    public String getInput() {
        return input;
    }

    public boolean isInputStopped() {
        return inputStatus == InputStatus.CLOSED;
    }

}
