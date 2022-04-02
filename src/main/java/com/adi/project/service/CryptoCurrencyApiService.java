package com.adi.project.service;

import com.adi.project.configuration.ApiRequestBeanConfiguration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class CryptoCurrencyApiService implements ICryptoCurrencyApiService {
    //@Value("${coinCapApiKey}")
    //private String apiKey;

    private final ApiRequestBeanConfiguration apiRequestBeanConfiguration;

    public CryptoCurrencyApiService(ApiRequestBeanConfiguration apiRequestBeanConfiguration) {
        this.apiRequestBeanConfiguration = apiRequestBeanConfiguration;
    }

    @Override
    public JsonArray fetchAllCryptoCurrenciesFromCoinCap( ) {
        JsonArray cryptoCurrencies;
        cryptoCurrencies = apiRequestBeanConfiguration.responseFromCoinCapApi().getAsJsonArray("data");
        return cryptoCurrencies;
    }

}
