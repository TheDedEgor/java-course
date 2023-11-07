package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StockMarketTest {

    @Test
    @DisplayName("Добавление акции")
    void addStockTest() {
        var stock = new Stock("Тинькоф", 500);
        var stockMarket = new Task6();
        stockMarket.add(stock);
        assertThat(stockMarket.getStocksCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("Удаление акции")
    void deleteStockTest() {
        var stock1 = new Stock("Газпром", 100);
        var stock2 = new Stock("Сбербанк", 200);
        var stockMarket = new Task6();
        stockMarket.add(stock1);
        stockMarket.add(stock2);
        assertThat(stockMarket.getStocksCount()).isEqualTo(2);
        stockMarket.remove(stock1);
        assertThat(stockMarket.getStocksCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("Самая дорогая акция")
    void expensiveStockTest() {
        var stock1 = new Stock("Газпром", 100);
        var stock2 = new Stock("Сбербанк", 200);
        var stock3 = new Stock("Тинькоф", 500);
        var stockMarket = new Task6();
        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.add(stock3);
        var stock = stockMarket.mostValuableStock();
        assertThat(stock).isEqualTo(stock3);
    }

    @Test
    @DisplayName("Самая дорогая акция после удаления")
    void expensiveStockAfterRemoveTest() {
        var stock1 = new Stock("Газпром", 100);
        var stock2 = new Stock("Сбербанк", 200);
        var stock3 = new Stock("Тинькоф", 500);
        var stockMarket = new Task6();
        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.add(stock3);
        var stock = stockMarket.mostValuableStock();
        assertThat(stock).isEqualTo(stock3);
        stockMarket.remove(stock3);
        stock = stockMarket.mostValuableStock();
        assertThat(stock).isEqualTo(stock2);
    }
}
