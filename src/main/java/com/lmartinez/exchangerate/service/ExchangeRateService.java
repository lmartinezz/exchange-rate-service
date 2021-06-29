package com.lmartinez.exchangerate.service;

import com.lmartinez.exchangerate.model.dto.AmountWithExchangeRateResponse;
import com.lmartinez.exchangerate.model.entity.ExchangeRate;

import io.reactivex.Completable;
import io.reactivex.Maybe;

public interface ExchangeRateService {

    Completable updateExchangeRate(ExchangeRate exchangeRate);

    Maybe<AmountWithExchangeRateResponse> getAmountWithExchangeRate(
        String originCurrency, String destinationCurrency, Float amount);

}
