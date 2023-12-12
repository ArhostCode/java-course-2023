package edu.project3.model.markdown;

public record HeaderMarkdown(String text, int level) implements MarkdownComponent {

    @Override
    public String convertToString() {
        return "#".repeat(Math.max(1, level)) + " " + text;
    }
}
