package com.lmartinez.exchangerate.dao;

import com.lmartinez.exchangerate.model.entity.ExchangeRate;

import io.reactivex.Completable;
import io.reactivex.Maybe;

public interface ExchangeRateDao {

    Maybe<ExchangeRate> findByOriginCurrencyAndDestinationCurrency(String originCurrency, String destinationCurrency);

    Completable update(ExchangeRate exchangeRate);

    Completable save(ExchangeRate exchangeRate);
}
