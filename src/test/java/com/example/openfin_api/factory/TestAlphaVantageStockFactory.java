package com.example.openfin_api.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.example.openfin_api.domain.StockData;
import com.example.openfin_api.factory.impl.AlphaVantageStockFactory;

public class TestAlphaVantageStockFactory {

    @Test
    void TestAlphaVantageStockFactoryConstructor() {
        AlphaVantageStockFactory alphaVantageStockFactory = new AlphaVantageStockFactory();
        assertNotNull(alphaVantageStockFactory);
    }

    @Test
    void TestAlphaVantageStockFactoryCreateStockData() {
        AlphaVantageStockFactory alphaVantageStockFactory = new AlphaVantageStockFactory();

        String json = "{\n" + "    \"Global Quote\": {\n" + "        \"01. symbol\": \"AAPL\",\n"
                + "        \"05. price\": \"100.0\",\n" + "        \"06. volume\": \"500000\",\n"
                + "        \"07. latest trading day\": \"2021-03-26\",\n"
                + "        \"08. previous close\": \"99.0\",\n" + "        \"09. change\": \"1.0\",\n"
                + "        \"10. change percent\": \"1.0%\"\n" + "    }\n" + "}";
        StockData stockData = alphaVantageStockFactory.createStockData(json);
        assertNotNull(stockData);
        assertEquals("AAPL", stockData.getSymbol());
        assertEquals(100.0, stockData.getPrice());
        assertEquals(500000, stockData.getVolume());
    }
}
