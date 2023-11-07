package edu.hw3;

import org.jetbrains.annotations.NotNull;

public class Stock implements Comparable<Stock> {

    private String name;
    private Integer price;

    public Stock(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public int compareTo(@NotNull Stock o) {
        return o.price.compareTo(price);
    }
}
