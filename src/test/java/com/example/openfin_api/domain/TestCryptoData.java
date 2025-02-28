package com.example.openfin_api.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class TestCryptoData {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    class TestCryptoDataConstructor {

        @Test
        void TestCryptoDataValidConstructor() {

            LocalDateTime timestamp = LocalDateTime.now();

            CryptoData cryptoData = new CryptoData("BTC", 100.0, 30000.0, timestamp);
            assertEquals("BTC", cryptoData.getCoinId());
            assertEquals(100.0, cryptoData.getPrice());
            assertEquals(30000.0, cryptoData.getMarketCap());
            assertEquals(timestamp, cryptoData.getTimestamp());
        }

        @Test
        void TestCryptoDataConstructorWithNullCoinIdIsRejected() {

            LocalDateTime timestamp = LocalDateTime.now();

            CryptoData cryptoData = new CryptoData(null, 100.0, 30000.0, timestamp);
            Set<jakarta.validation.ConstraintViolation<CryptoData>> violations = validator.validate(cryptoData);
            assertFalse(violations.isEmpty());
            violations.forEach(violation -> {
                assertEquals("Coin ID is required", violation.getMessage());
            });
        }

        @Test
        void TestCryptoDataConstructorWithEmptyCoinIdIsRejected() {

            LocalDateTime timestamp = LocalDateTime.now();

            CryptoData cryptoData = new CryptoData("", 100.0, 30000.0, timestamp);
            Set<jakarta.validation.ConstraintViolation<CryptoData>> violations = validator.validate(cryptoData);
            assertFalse(violations.isEmpty());
            violations.forEach(violation -> {
                assertEquals("Coin ID is required", violation.getMessage());
            });
        }

        @Test
        void TestCryptoDataConstructorWithNullPriceIsRejected() {

            LocalDateTime timestamp = LocalDateTime.now();

            CryptoData cryptoData = new CryptoData("BTC", null, 30000.0, timestamp);
            Set<jakarta.validation.ConstraintViolation<CryptoData>> violations = validator.validate(cryptoData);
            assertFalse(violations.isEmpty());
            violations.forEach(violation -> {
                assertEquals("Price is required", violation.getMessage());
            });
        }

        @Test
        void TestCryptoDataConstructorWithNegativePriceIsRejected() {

            LocalDateTime timestamp = LocalDateTime.now();

            CryptoData cryptoData = new CryptoData("BTC", -100.0, 30000.0, timestamp);
            Set<jakarta.validation.ConstraintViolation<CryptoData>> violations = validator.validate(cryptoData);
            assertFalse(violations.isEmpty());
            violations.forEach(violation -> {
                assertEquals("Price must be greater than 0", violation.getMessage());
            });
        }

        @Test
        void TestCryptoDataConstructorWithNullMarketCapIsRejected() {

            LocalDateTime timestamp = LocalDateTime.now();

            CryptoData cryptoData = new CryptoData("BTC", 100.0, null, timestamp);
            Set<jakarta.validation.ConstraintViolation<CryptoData>> violations = validator.validate(cryptoData);
            assertFalse(violations.isEmpty());
            violations.forEach(violation -> {
                assertEquals("Market Cap is required", violation.getMessage());
            });
        }

        @Test
        void TestCryptoDataConstructorWithNegativeMarketCapIsRejected() {

            LocalDateTime timestamp = LocalDateTime.now();

            CryptoData cryptoData = new CryptoData("BTC", 100.0, -30000.0, timestamp);
            Set<jakarta.validation.ConstraintViolation<CryptoData>> violations = validator.validate(cryptoData);
            assertFalse(violations.isEmpty());
            violations.forEach(violation -> {
                assertEquals("Market Cap must be greater than 0", violation.getMessage());
            });
        }

        @Test
        void TestCryptoDataConstructorWithNullTimestampIsRejected() {

            CryptoData cryptoData = new CryptoData("BTC", 100.0, 30000.0, null);
            Set<jakarta.validation.ConstraintViolation<CryptoData>> violations = validator.validate(cryptoData);
            assertFalse(violations.isEmpty());
            violations.forEach(violation -> {
                assertEquals("Timestamp is required", violation.getMessage());
            });
        }
    }

    @Nested
    class TestCryptoDataGetters {

        LocalDateTime timestamp = LocalDateTime.now();
        CryptoData cryptoData = new CryptoData("BTC", 100.0, 30000.0, timestamp);

        @Test
        void TestGetCoinId() {
            assertEquals("BTC", cryptoData.getCoinId());
        }

        @Test
        void TestGetPrice() {
            assertEquals(100.0, cryptoData.getPrice());
        }

        @Test
        void TestGetMarketCap() {
            assertEquals(30000.0, cryptoData.getMarketCap());
        }

        @Test
        void TestGetTimestamp() {
            assertEquals(timestamp, cryptoData.getTimestamp());
        }
    }

    @Nested
    class TestCryptoDataSetters {

        LocalDateTime timestamp = LocalDateTime.now();
        CryptoData cryptoData = new CryptoData("BTC", 100.0, 30000.0, timestamp);

        @Test
        void TestSetCoinId() {
            cryptoData.setCoinId("ETH");
            assertEquals("ETH", cryptoData.getCoinId());
        }

        @Test
        void TestSetPrice() {
            cryptoData.setPrice(200.0);
            assertEquals(200.0, cryptoData.getPrice());
        }

        @Test
        void TestSetMarketCap() {
            cryptoData.setMarketCap(60000.0);
            assertEquals(60000.0, cryptoData.getMarketCap());
        }

        @Test
        void TestSetTimestamp() {
            LocalDateTime newTimestamp = LocalDateTime.now();
            cryptoData.setTimestamp(newTimestamp);
            assertEquals(newTimestamp, cryptoData.getTimestamp());
        }
    }

}
