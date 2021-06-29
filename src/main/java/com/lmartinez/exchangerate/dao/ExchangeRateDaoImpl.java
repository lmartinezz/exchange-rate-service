package com.lmartinez.exchangerate.dao;

import com.lmartinez.exchangerate.model.entity.ExchangeRate;
import com.lmartinez.exchangerate.repository.ExchangeRateRepository;
import com.lmartinez.exchangerate.repository.entity.ExchangeRateEntity;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExchangeRateDaoImpl implements ExchangeRateDao {

    @Autowired
    private ExchangeRateRepository repository;

    private static final Integer ACTIVE_STATE_EXCHANGE_RATE = 1;

    @Override
    public Maybe<ExchangeRate> findByOriginCurrencyAndDestinationCurrency(
            String originCurrency, String destinationCurrency) {
        return Single.fromCallable(() -> repository
            .findByOriginCurrencyAndDestinationCurrencyAndActive(
                originCurrency,
                destinationCurrency,
                ACTIVE_STATE_EXCHANGE_RATE))
            .subscribeOn(Schedulers.io())
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(exchangeRateEntity -> new ExchangeRate(
                exchangeRateEntity.getId(),
                    originCurrency,
                    destinationCurrency,
                exchangeRateEntity.getValue()));
    }

    @Override
    public Completable update(ExchangeRate exchangeRate) {
        return Completable.fromRunnable(() ->
            repository.findByOriginCurrencyAndDestinationCurrencyAndActive(
                exchangeRate.getOriginCurrency(),
                exchangeRate.getDestinationCurrency(),
                ACTIVE_STATE_EXCHANGE_RATE)
            .map(entity -> {
                entity.setValue(exchangeRate.getValue());
                repository.save(entity);
                return entity;
            }));
    }

    @Override
    public Completable save(ExchangeRate exchangeRate) {
        return Completable.fromRunnable(() ->
            repository.save(new ExchangeRateEntity(null,
                exchangeRate.getOriginCurrency(),
                exchangeRate.getDestinationCurrency(),
                1,
                exchangeRate.getValue())));
    }
}
