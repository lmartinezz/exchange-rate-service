package com.lmartinez.exchangerate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lmartinez.exchangerate.repository.entity.ExchangeRateEntity;

import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, Integer> {

    Optional<ExchangeRateEntity> findByOriginCurrencyAndDestinationCurrencyAndActive(
        String originCurrency, String destinationCurrency, Integer active);
}
