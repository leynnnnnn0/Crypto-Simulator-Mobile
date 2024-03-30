package com.leynnnnnn.cryptosimulatormobile;

public class CoinInfo {
    String name;
    String symbol;
    String image;
    float price;

    public CoinInfo(String name, String symbol, String image, float price) {
        this.name = name;
        this.symbol = symbol;
        this.image = image;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
