package edu.project3.model.adoc;

public record HeaderAdoc(String text, int level) implements AdocComponent {

    @Override
    public String convertToString() {
        return "=".repeat(Math.max(1, level)) + " " + text;
    }
}
