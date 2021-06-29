package com.lmartinez.exchangerate.web;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.lmartinez.exchangerate.model.dto.AmountWithExchangeRateResponse;
import com.lmartinez.exchangerate.model.dto.UpdateExchangeRateWebRequest;
import com.lmartinez.exchangerate.model.entity.ExchangeRate;
import com.lmartinez.exchangerate.service.ExchangeRateService;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/exchangerate")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping(
        value = "/{originCurrency}-{destinationCurrency}/{amount}",
        produces = APPLICATION_JSON_VALUE
    )
    public Maybe<ResponseEntity<AmountWithExchangeRateResponse>> getAmountWithExchangeRate(
            @PathVariable(value = "originCurrency") String originCurrency,
            @PathVariable(value = "destinationCurrency") String destinationCurrency,
            @PathVariable(value = "amount") Float amount) {
        return exchangeRateService.getAmountWithExchangeRate(originCurrency, destinationCurrency, amount)
                .subscribeOn(Schedulers.io())
                .map(ResponseEntity::ok);
    }

    @PutMapping(
            value = "/{originCurrency}-{destinationCurrency}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public Completable updateExchangeRate(
            @PathVariable(value = "originCurrency") String originCurrency,
            @PathVariable(value = "destinationCurrency") String destinationCurrency,
            @RequestBody UpdateExchangeRateWebRequest updateExchangeRateWebRequest) {
        return exchangeRateService.updateExchangeRate(
                new ExchangeRate(null, originCurrency, destinationCurrency, updateExchangeRateWebRequest.getValue()))
                .subscribeOn(Schedulers.io());
    }


}
