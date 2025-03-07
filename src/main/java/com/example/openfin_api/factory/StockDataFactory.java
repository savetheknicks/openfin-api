package com.example.openfin_api.factory;

import com.example.openfin_api.domain.StockData;

public interface StockDataFactory {

    StockData createStockData(String json);
}
