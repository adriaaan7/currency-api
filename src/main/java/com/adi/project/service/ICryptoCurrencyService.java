package com.adi.project.service;

import com.adi.project.model.CryptoCurrency;
import com.google.gson.JsonArray;

import java.math.BigDecimal;
import java.util.List;

public interface ICryptoCurrencyService {

    List<CryptoCurrency> getAllCryptoCurrencies();

    CryptoCurrency getCryptoCurrencyByName(String currencyName);

    CryptoCurrency saveCryptoCurrency(CryptoCurrency cryptoCurrency);

    List<CryptoCurrency> initAllCryptoCurrencies();

    void updateCryptoCurrency(Long id, String name,
                              int rank, String symbol,
                              BigDecimal priceUsd, BigDecimal rateOfChange);

    List<CryptoCurrency> updateAllCryptoCurrencies();

}
