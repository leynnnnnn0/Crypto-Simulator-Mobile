package com.leynnnnnn.cryptosimulatormobile;

import java.util.List;

public class SearchCoinResponse {
    private List<CoinsSearched> coins;

    public List<CoinsSearched> getCoins() {
        return coins;
    }
}

class CoinsSearched {
    private String name;
    private String symbol;
    private String thumb;

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

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
