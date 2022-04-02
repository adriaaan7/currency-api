package com.adi.project.service;

import com.google.gson.JsonArray;

import java.util.Map;

public interface ICryptoCurrencyApiService {
    JsonArray fetchAllCryptoCurrenciesFromCoinCap();
    JsonArray fetchAllCryptoCurrenciesFromCoinMarketCap();
}
