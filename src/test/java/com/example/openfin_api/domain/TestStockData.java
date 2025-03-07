package com.example.openfin_api.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

        StockData stockData = new StockData("AAPL", 100.0, 500000, timestamp);
        assertEquals("AAPL", stockData.getSymbol());
        assertEquals(100.0, stockData.getPrice());
        assertEquals(timestamp, stockData.getTimestamp());
    }

    @Test
    void TestStockDataConstructorWithNullSymbolIsRejected() {

        LocalDateTime timestamp = LocalDateTime.now();

        StockData stockData = new StockData(null, 100.0, 500000, timestamp);
        Set<jakarta.validation.ConstraintViolation<StockData>> violations = validator.validate(stockData);
        assertFalse(violations.isEmpty());
        violations.forEach(violation -> {
            assertEquals("Symbol is required", violation.getMessage());
        });

    }

    @Test
    void TestStockDataConstructorWithNullPriceIsRejected() {

        LocalDateTime timestamp = LocalDateTime.now();

        StockData stockData = new StockData("AAPL", null, 500000, timestamp);
        Set<jakarta.validation.ConstraintViolation<StockData>> violations = validator.validate(stockData);
        assertFalse(violations.isEmpty());
        violations.forEach(violation -> {
            assertEquals("Price is required", violation.getMessage());
        });

    }

    @Test
    void TestStockDataConstructorWithNullTimestampIsRejected() {

        StockData stockData = new StockData("AAPL", 100.0, 500000, null);
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
            stockData = new StockData("AAPL", 100.0, 500000, timestamp);
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

    @Nested
    class TestStockDataFieldsInvalidInput {

        private LocalDateTime timestamp;
        private StockData stockData;

        @BeforeEach
        void setUp() {
            timestamp = LocalDateTime.now();
            stockData = new StockData("AAPL", 100.0, 500000, timestamp);
        }

        @Test
        void TestStockDataSetNullSymbol() {
            stockData.setSymbol(null);
            Set<jakarta.validation.ConstraintViolation<StockData>> violations = validator.validate(stockData);
            assertFalse(violations.isEmpty());
            violations.forEach(violation -> {
                assertEquals("Symbol is required", violation.getMessage());
            });
        }

        @Test
        void TestStockDataSetEmptySymbol() {
            stockData.setSymbol("");
            Set<jakarta.validation.ConstraintViolation<StockData>> violations = validator.validate(stockData);
            assertFalse(violations.isEmpty());
            violations.forEach(violation -> {
                assertEquals("Symbol is required", violation.getMessage());
            });
        }

        @Test
        void TestStockDataSetBlankSymbol() {
            stockData.setSymbol(" ");
            Set<jakarta.validation.ConstraintViolation<StockData>> violations = validator.validate(stockData);
            assertFalse(violations.isEmpty());
            violations.forEach(violation -> {
                assertEquals("Symbol is required", violation.getMessage());
            });
        }

        @Test
        void TestStockDataSetNullPrice() {
            stockData.setPrice(null);
            Set<jakarta.validation.ConstraintViolation<StockData>> violations = validator.validate(stockData);
            assertFalse(violations.isEmpty());
            violations.forEach(violation -> {
                assertEquals("Price is required", violation.getMessage());
            });
        }

        @Test
        void TestStockDataSetNullPriceZero() {
            stockData.setPrice(0.0);
            Set<jakarta.validation.ConstraintViolation<StockData>> violations = validator.validate(stockData);
            assertFalse(violations.isEmpty());
            violations.forEach(violation -> {
                assertEquals("Price must be greater than 0", violation.getMessage());
            });
        }

        @Test
        void TestStockDataSetPriceNegative() {
            stockData.setPrice(-100.0);
            Set<jakarta.validation.ConstraintViolation<StockData>> violations = validator.validate(stockData);
            assertFalse(violations.isEmpty());
            violations.forEach(violation -> {
                assertEquals("Price must be greater than 0", violation.getMessage());
            });
        }

        @Test
        void TestStockDataSetNullTimestamp() {
            stockData.setTimestamp(null);
            Set<jakarta.validation.ConstraintViolation<StockData>> violations = validator.validate(stockData);
            assertFalse(violations.isEmpty());
            violations.forEach(violation -> {
                assertEquals("Timestamp is required", violation.getMessage());
            });
        }
    }

}
