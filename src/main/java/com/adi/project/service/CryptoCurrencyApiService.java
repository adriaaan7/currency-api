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
        return apiRequestService.responseFromCoinCapApi();
    }

    @Override
    public JsonArray fetchAllCryptoCurrenciesFromCoinMarketCap() {
        return apiRequestService.responseFromCoinMarketCapApi();
    }

    @Override
    public JsonArray fetchAllCryptoCurrenciesFromGemini() {
        return apiRequestService.responseFromGeminiApi();
    }

}
