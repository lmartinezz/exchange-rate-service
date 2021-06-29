package com.lmartinez.exchangerate.service;

import com.lmartinez.exchangerate.dao.ExchangeRateDao;
import com.lmartinez.exchangerate.exception.ExchangeRateException;
import com.lmartinez.exchangerate.model.dto.AmountWithExchangeRateResponse;
import com.lmartinez.exchangerate.model.entity.ExchangeRate;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    private ExchangeRateDao exchangeRateDao;

    private static final String DEFAULT_ERROR_CODE = "9999";

    @Override
    public Completable updateExchangeRate(ExchangeRate exchangeRate) {
        return exchangeRateDao.update(exchangeRate);
    }

    @Override
    public Maybe<AmountWithExchangeRateResponse> getAmountWithExchangeRate(String originCurrency,
            String destinationCurrency, Float amount) {
        return exchangeRateDao.findByOriginCurrencyAndDestinationCurrency(originCurrency, destinationCurrency)
            .map(exchangeRate -> computeAmountWithExchangeRate(
                originCurrency,
                destinationCurrency,
                exchangeRate.getValue(),
                amount))
            .switchIfEmpty(Maybe.error(new ExchangeRateException(DEFAULT_ERROR_CODE, "No exchange rate found")));
    }

    private AmountWithExchangeRateResponse computeAmountWithExchangeRate(String originCurrency,
            String destinationCurrency, Float exchangeRateValue, Float amount) {
        return new AmountWithExchangeRateResponse(
            originCurrency,
            destinationCurrency,
            exchangeRateValue,
            amount,
            BigDecimal.valueOf(exchangeRateValue * amount)
                .setScale(2, RoundingMode.HALF_UP).floatValue());
    }
}
