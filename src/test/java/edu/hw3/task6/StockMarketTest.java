package edu.hw3.task6;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StockMarketTest {

    @Test
    @DisplayName("Тест BasicStockMarket#mostValuableStock")
    public void mostValuableStock_shouldReturnCorrectValue() {
        StockMarket stockMarket = new BasicStockMarket();
        stockMarket.add(new Stock("AU", 1));
        stockMarket.add(new Stock("BE", 19));
        stockMarket.add(new Stock("GG", 9));
        stockMarket.add(new Stock("RR", 6));

        Assertions.assertThat(stockMarket.mostValuableStock())
            .extracting("name", "price")
            .containsExactly("BE", 19);
    }

    @Test
    @DisplayName("Тест BasicStockMarket#mostValuableStock и remove")
    public void mostValuableStock_shouldReturnCorrectValue_whenRemoved() {
        StockMarket stockMarket = new BasicStockMarket();
        stockMarket.add(new Stock("AU", 1));
        stockMarket.add(new Stock("BE", 19));
        stockMarket.add(new Stock("GG", 9));
        stockMarket.add(new Stock("RR", 6));
        stockMarket.remove(new Stock("BE", 19));

        Assertions.assertThat(stockMarket.mostValuableStock())
            .extracting("name", "price")
            .containsExactly("GG", 9);
    }

}
