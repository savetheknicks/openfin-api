package com.example.openfin_api.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;


@Data
@AllArgsConstructor
public class StockData {
    @NonNull private String symbol;
    @NonNull private Double price;
    @NonNull private LocalDateTime timestamp;
}
