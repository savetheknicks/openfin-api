package com.example.openfin_api.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IndicatorData {

    @NotBlank(message = "Indicator name is required")
    private String indicatorName;

    @NotNull(message = "Value is required")
    private BigDecimal value;

    @NotNull(message = "Date is required")
    private LocalDate date;
}
