package edu.hw8.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuotesTest {

    @SneakyThrows
    @Test
    @DisplayName("Тестирование работы QuotesServer и QuotesClient")
    public void quotes_shouldCorrectlyWork() {
        List<String> userRequests = new ArrayList<>();
        List<String> serverResponses = new ArrayList<>();
        QuotesStorage quotesStorage = QuotesStorage.createDefault();
        QuotesServer quotesServer = new QuotesServer(12345, quotesStorage, 2);
        QuotesClient quotesClient = new QuotesClient("localhost", 12345);

        quotesServer.setMessageConsumer(userRequests::add);
        Executors.newSingleThreadExecutor().execute(quotesServer::start);
        Thread.sleep(1000);

        // Imitate multiconnnection
        quotesClient.start();
        quotesClient.close();
        quotesClient.start();
        quotesClient.close();
        quotesClient.start();

        serverResponses.add(quotesClient.requestQuote("личности"));
        serverResponses.add(quotesClient.requestQuote("оскорбления"));

        Thread.sleep(1000);
        quotesServer.stop();
        quotesClient.close();

        Assertions.assertThat(userRequests).containsExactly("личности", "оскорбления");
        Assertions.assertThat(serverResponses).containsExactly(
            "Не переходи на личности там, где их нет",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами"
        );
    }

}
