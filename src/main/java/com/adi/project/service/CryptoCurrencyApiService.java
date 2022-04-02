package com.adi.project.service;

import com.google.gson.JsonArray;
import org.springframework.stereotype.Service;

@Service
public class CryptoCurrencyApiService implements ICryptoCurrencyApiService {
    //@Value("${coinCapApiKey}")
    //private String apiKey;

    private final ApiRequestService apiRequestService;

    public CryptoCurrencyApiService(ApiRequestService apiRequestService) {
        this.apiRequestService = apiRequestService;
    }

    @Override
    public JsonArray fetchAllCryptoCurrenciesFromCoinCap( ) {
        JsonArray cryptoCurrencies;
        cryptoCurrencies = apiRequestService.responseFromCoinCapApi().getAsJsonArray("data");
        return cryptoCurrencies;
    }

    @Override
    public JsonArray fetchAllCryptoCurrenciesFromCoinMarketCap() {
        JsonArray cryptoCurrencies;
        cryptoCurrencies = apiRequestService.responseFromCoinMarketCapApi().getAsJsonArray("data");
        return cryptoCurrencies;
    }

}
