package com.example.openfin_api.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NonNull;


@Data
public class StockData {
    @NonNull private String symbol;
    @NonNull private Double price;
    @NonNull private LocalDateTime timestamp;

    public StockData(String symbol, Double price, LocalDateTime timestamp) {

        this.symbol = symbol;
        this.price = price;
        this.timestamp = timestamp;
    }

}
