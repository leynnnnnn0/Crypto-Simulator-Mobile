package com.leynnnnnn.cryptosimulatormobile;

import java.util.List;

public class CoinApiResponse {
    private List<Coin> coins;
    public List<Coin> getCoins() {
        return coins;
    }
}
class Coin {
    private Item item;
    public Item getItem() {
        return item;
    }
}

class Item {
    private Data data;
    String name;
    String symbol;
    String small;

    public Data getData() {
        return data;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }
}

class Data {
    float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
