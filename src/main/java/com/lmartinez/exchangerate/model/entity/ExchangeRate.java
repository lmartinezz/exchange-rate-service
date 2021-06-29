package com.lmartinez.exchangerate.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRate {
    private Integer id;
    private String originCurrency;
    private String destinationCurrency;
    private Float value;
}
