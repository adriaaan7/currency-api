package com.adi.project.service;

import com.adi.project.configuration.BeanConfiguration;
import com.adi.project.model.CryptoCurrency;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class CryptoCurrencyApiService implements ICryptoCurrencyApiService {

    private final BeanConfiguration beanConfiguration;

    // ONLY PUBLIC FOR NOW
    // WILL BE EXPORTED AS ENV VAR
    private final String apiKey = "e92b7d3f-c194-4cbb-bb25-708710e1a14a";

    public CryptoCurrencyApiService(BeanConfiguration beanConfiguration) {
        this.beanConfiguration = beanConfiguration;
    }

    /*
        Returns whole content of the response from the provided url in form of JsonArray
    */
    @Override
    public JsonArray fetchAllCryptoCurrencies(String url) {
        OkHttpClient okHttpClient = beanConfiguration.okHttpClient();
        JsonArray cryptoCurrencies = new JsonArray();
        Request request = new Request.Builder()
                .url(url)
                .header("Accept-Encoding", "deflate")
                .header("Authorization", apiKey)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String responseContent = response.body().string();

            Gson gson = new GsonBuilder().create();
            JsonObject responseJson = gson.fromJson(responseContent, JsonObject.class);
            cryptoCurrencies = responseJson.getAsJsonArray("data");

            return cryptoCurrencies;

        } catch (IOException e) {
            e.printStackTrace();
        }


        return cryptoCurrencies;
    }
}
