package com.example.openfin_api.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.Validator;

public class TestStockData {
    
    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

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

        StockData stockData = new StockData(null, 100.0, timestamp);
        Set<jakarta.validation.ConstraintViolation<StockData>> violations = validator.validate(stockData);
        assertFalse(violations.isEmpty());
        violations.forEach(violation -> {
            assertEquals("Symbol is required", violation.getMessage());
        });
        
    }

    @Test
    void TestStockDataConstructorWithNullPriceIsRejected() {

        LocalDateTime timestamp = LocalDateTime.now();

        
        StockData stockData = new StockData("AAPL", null, timestamp);
        Set<jakarta.validation.ConstraintViolation<StockData>> violations = validator.validate(stockData);
        assertFalse(violations.isEmpty());
        violations.forEach(violation -> {
            assertEquals("Price is required", violation.getMessage());
        });
        
    }

    @Test
    void TestStockDataConstructorWithNullTimestampIsRejected() {

        StockData stockData = new StockData("AAPL", 100.0, null);
        Set<jakarta.validation.ConstraintViolation<StockData>> violations = validator.validate(stockData);
        assertFalse(violations.isEmpty());
        violations.forEach(violation -> {
            assertEquals("Timestamp is required", violation.getMessage());
        });
        
    }

    @Nested
    class TestStockValidDataFields {

        private LocalDateTime timestamp;
        private StockData stockData;
        

        @BeforeEach
        void setUp() {
            timestamp = LocalDateTime.now();
            stockData = new StockData("AAPL", 100.0, timestamp);
        }

        @Test
        void TestStockDataGetSymbol() {
            assertEquals("AAPL", stockData.getSymbol());
        }

        @Test
        void TestStockDataGetPrice() {
            assertEquals(100.0, stockData.getPrice());
        }

        @Test
        void TestStockDataGetTimestamp() {
            assertEquals(timestamp, stockData.getTimestamp());
        }

        @Test
        void TestStockDataSetValidSymbol() {
            stockData.setSymbol("GOOGL");
            assertEquals("GOOGL", stockData.getSymbol());
        }    

    }

}
