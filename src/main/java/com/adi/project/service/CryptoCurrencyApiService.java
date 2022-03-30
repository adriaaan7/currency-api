package com.adi.project.service;

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

    // ONLY PUBLIC FOR NOW
    // WILL BE EXPORTED AS ENV VAR
    private final String coinCapApiKey = "e92b7d3f-c194-4cbb-bb25-708710e1a14a";
    //@Value("${coinCapApiKey}")
    //private String apiKey;

    private final String coinCapURL = "https://api.coincap.io/v2/assets";

    /*
        Returns content of data field of the response from the provided url
        in form of JsonArray
    */
    @Override
    public JsonArray fetchAllCryptoCurrencies( ) {
        OkHttpClient okHttpClient = new OkHttpClient();
        JsonArray cryptoCurrencies = new JsonArray();
        Request request = new Request.Builder()
                .url(coinCapURL)
                .header("Accept-Encoding", "deflate")
                .header("Authorization", coinCapApiKey)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String responseContent = Objects.requireNonNull(response.body()).string();

            Gson gson = new GsonBuilder().create();
            JsonObject responseJson = gson.fromJson(responseContent, JsonObject.class);
            cryptoCurrencies = responseJson.getAsJsonArray("data");

            return cryptoCurrencies;

        } catch (IOException e) {
            e.printStackTrace();
        }


        return cryptoCurrencies;
    }

    public String getCoinCapURL() {
        return coinCapURL;
    }
}
