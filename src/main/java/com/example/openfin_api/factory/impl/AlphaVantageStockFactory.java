package com.example.openfin_api.factory.impl;

import com.example.openfin_api.domain.StockData;
import com.example.openfin_api.factory.StockDataFactory;

import java.time.LocalDateTime;

import org.json.JSONObject;

public class AlphaVantageStockFactory implements StockDataFactory {

    @Override
    public StockData createStockData(String json) {

        JSONObject jsonObject = new JSONObject(json);

        JSONObject globalQuote = jsonObject.getJSONObject("Global Quote");

        String symbol = globalQuote.getString("01. symbol");

        Double price = globalQuote.getDouble("05. price");

        Integer volume = globalQuote.getInt("06. volume");

        LocalDateTime timestamp = LocalDateTime.now();

        return new StockData(symbol, price, volume, timestamp);
    }
}
