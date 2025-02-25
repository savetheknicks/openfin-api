package com.example.openfin_api.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TestStockData {
    
    @Test
    void TestStockDataConstructor() {

        LocalDateTime timestamp = LocalDateTime.now();

        StockData stockData = new StockData("AAPL", 100.0, timestamp);
        assertEquals("AAPL", stockData.getSymbol());
        assertEquals(100.0, stockData.getPrice());
        assertEquals(timestamp, stockData.getTimestamp());
    }

}
