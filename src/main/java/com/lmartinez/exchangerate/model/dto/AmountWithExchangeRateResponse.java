package com.lmartinez.exchangerate.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AmountWithExchangeRateResponse {
    private String originCurrency;
    private String destinationCurrency;
    private Float exchangeRateValue;
    private Float originalAmount;
    private Float convertedAmount;
}
