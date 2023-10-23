package edu.hw3;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Task6 implements StockMarket {

    private final PriorityQueue<Stock> stocks = new PriorityQueue<>(Comparator.reverseOrder());

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

    public Integer getStocksCount() {
        return stocks.size();
    }
}
