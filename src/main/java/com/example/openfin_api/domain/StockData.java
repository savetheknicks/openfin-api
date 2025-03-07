package com.example.openfin_api.domain;

import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockData {

    @NotBlank(message = "Symbol is required")
    private String symbol;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", message = "Price must be greater than 0", inclusive = false)
    private Double price;

    @NotNull(message = "Volume is required")
    @DecimalMin(value = "0", message = "Volume must be greater than 0", inclusive = false)
    private Integer volume;

    @NotNull(message = "Timestamp is required")
    private LocalDateTime timestamp;
}
