package edu.project1.source;

import java.util.ArrayList;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class InMemoryWordSource implements WordSource {

    private final ArrayList<String> words;

    public InMemoryWordSource() {
        words = new ArrayList<>();
        words.add("hangman");
        words.add("cosmos");
        words.add("drive");
        words.add("crowd");
        words.add("darkness");
    }

    @Override
    public @NotNull String randomWord() {
        Random random = new Random();
        int listSize = words.size();
        int randomIndex = random.nextInt(listSize);
        return words.get(randomIndex);
    }
}
