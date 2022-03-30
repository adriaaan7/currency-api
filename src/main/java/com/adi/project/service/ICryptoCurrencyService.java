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

    /*
        Method which returns list of cryptoCurrencies with positive or negative rate of change
        If you want to get in return cryptoCurrencies with negative rate of change, then pass -1 as filter
        Otherwise, to get pass 1 as filter
    */
    List<CryptoCurrency> filterCryptoCurrenciesByRateOfChange(int filter);

}
