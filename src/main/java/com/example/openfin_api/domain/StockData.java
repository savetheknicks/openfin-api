package com.example.openfin_api.domain;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class StockData {
    
    @NotEmpty(message = "Symbol is required")
    private String symbol;
    
    @NotNull(message = "Price is required")
    private Double price;

    @NotNull(message = "Timestamp is required")
    private LocalDateTime timestamp;
}
