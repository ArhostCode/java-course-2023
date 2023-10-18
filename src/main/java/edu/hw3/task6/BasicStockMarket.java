package edu.hw3.task6;

import java.util.PriorityQueue;
import java.util.Queue;

public class BasicStockMarket implements StockMarket {

    private final Queue<Stock> stocks = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.price(), o1.price()));

    @Override
    public void add(Stock stock) {
        stocks.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stocks.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stocks.peek();
    }
}
