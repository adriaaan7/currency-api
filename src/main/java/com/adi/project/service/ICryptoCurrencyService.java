package com.adi.project.service;

import com.adi.project.model.CryptoCurrency;

import java.util.List;

public interface ICryptoCurrencyService {

    List<CryptoCurrency> getAllCryptoCurrencies();

    CryptoCurrency getCryptoCurrencyByName(String currencyName);
}
