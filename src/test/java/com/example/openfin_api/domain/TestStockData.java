package com.example.openfin_api.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void TestStockDataConstructorWithNullSymbolIsRejected() {

        LocalDateTime timestamp = LocalDateTime.now();

        assertThrows(NullPointerException.class, () -> {
            new StockData(null, 100.0, timestamp);
        });
        
    }

    @Test
    void TestStockDataConstructorWithNullPriceIsRejected() {

        LocalDateTime timestamp = LocalDateTime.now();

        assertThrows(NullPointerException.class, () -> {
            new StockData("AAPL", null, timestamp);
        });
        
    }

    @Test
    void TestStockDataConstructorWithNullTimestampIsRejected() {

        assertThrows(NullPointerException.class, () -> {
            new StockData("AAPL", 100.0, null);
        });
        
    }

    @Nested
    class TestStockDataFields {

    }

}
