package com.adi.project.service;

import com.adi.project.model.CryptoCurrency;
import com.google.gson.JsonArray;

import java.util.List;

public interface ICryptoCurrencyService {

    List<CryptoCurrency> getAllCryptoCurrencies();

    CryptoCurrency getCryptoCurrencyByName(String currencyName);

    CryptoCurrency saveCryptoCurrency(CryptoCurrency cryptoCurrency);

    void updateAllCryptoCurrencies(JsonArray cryptoCurrencyArray);

}
