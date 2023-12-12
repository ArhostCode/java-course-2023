package edu.hw8.task1;

import java.util.HashMap;
import java.util.Map;

public class QuotesStorage {
    private final Map<String, String> quotesMap = new HashMap<>();

    public static QuotesStorage createDefault() {
        QuotesStorage quotesStorage = new QuotesStorage();
        quotesStorage.appendQuote("личности", "Не переходи на личности там, где их нет");
        quotesStorage.appendQuote(
            "оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами"
        );
        quotesStorage.appendQuote(
            "глупый",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма"
        );
        quotesStorage.appendQuote(
            "интеллект",
            "Чем ниже интеллект, тем громче оскорбления"
        );
        return quotesStorage;
    }

    public String getQuote(String id) {
        return quotesMap.getOrDefault(id, "Неизвестная цитата");
    }

    public void appendQuote(String id, String quote) {
        quotesMap.put(id, quote);
    }
}
