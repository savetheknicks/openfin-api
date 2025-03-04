package com.example.openfin_api.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class TestIndicatorData {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    class TestIndicatorDataConstructor {

        @Test
        void TestIndicatorDataValidConstructor() {

            LocalDate date = LocalDate.now();

            BigDecimal value = new BigDecimal("70.0");

            IndicatorData indicatorData = new IndicatorData("RSI", value, date);
            assertEquals("RSI", indicatorData.getIndicatorName());
            assertEquals(value, indicatorData.getValue());
            assertEquals(date, indicatorData.getDate());
        }

        @Test
        void TestIndicatorDataConstructorWithNegativeValueIsAccepted() {

            LocalDate date = LocalDate.now();

            BigDecimal value = new BigDecimal("-70.0");

            IndicatorData indicatorData = new IndicatorData("RSI", value, date);
            assertEquals("RSI", indicatorData.getIndicatorName());
            assertEquals(value, indicatorData.getValue());
            assertEquals(date, indicatorData.getDate());
        }

        @Test
        void TestIndicatorDataConstructorWithZeroValueIsAccepted() {

            LocalDate date = LocalDate.now();

            BigDecimal value = new BigDecimal("0.0");

            IndicatorData indicatorData = new IndicatorData("RSI", value, date);
            assertEquals("RSI", indicatorData.getIndicatorName());
            assertEquals(value, indicatorData.getValue());
            assertEquals(date, indicatorData.getDate());
        }

        @Test
        void TestIndicatorDataConstructorWithFractionPercentValueIsAccepted() {

            LocalDate date = LocalDate.now();

            BigDecimal value = new BigDecimal("0.70");

            IndicatorData indicatorData = new IndicatorData("RSI", value, date);
            assertEquals("RSI", indicatorData.getIndicatorName());
            assertEquals(value, indicatorData.getValue());
            assertEquals(date, indicatorData.getDate());

        }

        @Test
        void TestIndicatorDataConstructorWithNullIndicatorNameIsRejected() {

            LocalDate date = LocalDate.now();

            BigDecimal value = new BigDecimal("70.0");

            IndicatorData indicatorData = new IndicatorData(null, value, date);
            assertEquals("Indicator name is required",
                    validator.validateProperty(indicatorData, "indicatorName").iterator().next().getMessage());
        }

        @Test
        void TestIndicatorDataConstructorWithEmptyIndicatorNameIsRejected() {

            LocalDate date = LocalDate.now();

            BigDecimal value = new BigDecimal("70.0");

            IndicatorData indicatorData = new IndicatorData("", value, date);
            assertEquals("Indicator name is required",
                    validator.validateProperty(indicatorData, "indicatorName").iterator().next().getMessage());
        }

        @Test
        void TestIndicatorDataConstructorWithNullValueIsRejected() {

            LocalDate date = LocalDate.now();

            IndicatorData indicatorData = new IndicatorData("RSI", null, date);
            assertEquals("Value is required",
                    validator.validateProperty(indicatorData, "value").iterator().next().getMessage());
        }

        @Test
        void TestIndicatorDataConstructorWithNullDateIsRejected() {

            BigDecimal value = new BigDecimal("70.0");

            IndicatorData indicatorData = new IndicatorData("RSI", value, null);
            assertEquals("Date is required",
                    validator.validateProperty(indicatorData, "date").iterator().next().getMessage());
        }

    }

    @Nested
    class TestIndicatorFieldGetters {

        @Test
        void TestIndicatorDataGetIndicatorName() {

            LocalDate date = LocalDate.now();

            BigDecimal value = new BigDecimal("70.0");

            IndicatorData indicatorData = new IndicatorData("RSI", value, date);
            assertEquals("RSI", indicatorData.getIndicatorName());
        }

        @Test
        void TestIndicatorDataGetValue() {

            LocalDate date = LocalDate.now();

            BigDecimal value = new BigDecimal("70.0");

            IndicatorData indicatorData = new IndicatorData("RSI", value, date);
            assertEquals(value, indicatorData.getValue());
        }

        @Test
        void TestIndicatorDataGetNegativeValue() {

            LocalDate date = LocalDate.now();

            BigDecimal value = new BigDecimal("-70.0");

            IndicatorData indicatorData = new IndicatorData("RSI", value, date);
            assertEquals(value, indicatorData.getValue());
        }

        @Test
        void TestIndicatorDataGetDate() {

            LocalDate date = LocalDate.now();

            BigDecimal value = new BigDecimal("70.0");

            IndicatorData indicatorData = new IndicatorData("RSI", value, date);
            assertEquals(date, indicatorData.getDate());
        }

    }

    @Nested
    class TestIndicatorFieldSetters {

        @Test
        void TestIndicatorDataSetIndicatorName() {

            LocalDate date = LocalDate.now();

            BigDecimal value = new BigDecimal("70.0");

            IndicatorData indicatorData = new IndicatorData("RSI", value, date);
            indicatorData.setIndicatorName("MACD");
            assertEquals("MACD", indicatorData.getIndicatorName());
        }

        @Test
        void TestIndicatorDataSetValue() {

            LocalDate date = LocalDate.now();

            BigDecimal value = new BigDecimal("70.0");

            IndicatorData indicatorData = new IndicatorData("RSI", value, date);
            BigDecimal newValue = new BigDecimal("80.0");
            indicatorData.setValue(newValue);
            assertEquals(newValue, indicatorData.getValue());
        }

        @Test
        void TestIndicatorDataSetNegativeValue() {

            LocalDate date = LocalDate.now();

            BigDecimal value = new BigDecimal("70.0");

            IndicatorData indicatorData = new IndicatorData("RSI", value, date);
            BigDecimal newValue = new BigDecimal("-80.0");
            indicatorData.setValue(newValue);
            assertEquals(newValue, indicatorData.getValue());
        }

        @Test
        void TestIndicatorDataSetDate() {

            LocalDate date = LocalDate.now();

            BigDecimal value = new BigDecimal("70.0");

            IndicatorData indicatorData = new IndicatorData("RSI", value, date);
            LocalDate newDate = LocalDate.now().minusDays(1);
            indicatorData.setDate(newDate);
            assertEquals(newDate, indicatorData.getDate());
        }
    }

}
