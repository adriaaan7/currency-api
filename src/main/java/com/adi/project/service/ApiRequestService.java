package com.adi.project.service;

import com.adi.project.model.ApiHosting;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

@Service
public class ApiRequestService implements IApiRequestService {

    @Value("${coinCapApiKey:0}")
    private String coinCapApiKey; //= "e92b7d3f-c194-4cbb-bb25-708710e1a14a";
    private final String geminiURL = "https://api.gemini.com/v1/pricefeed";
    private final String coinCapURL = "https://api.coincap.io/v2/assets";
    private final String coinMarketCapURL =
            "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";

    @Value("${coinMarketCapApiKey:0}")
    private String coinMarketCapApiKey; // = "e41224c3-0abc-4369-83ae-0ecd1092be37";

    private final ApiHostingService apiHostingService;

    public ApiRequestService(ApiHostingService apiHostingService) {
        this.apiHostingService = apiHostingService;
    }

    @Override
    public JsonObject responseFromGeminiApi() {
        JsonObject responseJson = new JsonObject();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(geminiURL)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String responseContent = Objects.requireNonNull(response.body()).string();
            Gson gson = new GsonBuilder().create();
            // @TODO
            // \@return responseJson is not a json object now
            ResponseBody body = response.body();
            return responseJson;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("XD");
        return responseJson;
    }

    /*
            Send get request to CoinCap api and
            \@return response as JsonObject containing crypto currency data
        */
    @Override
    public JsonObject responseFromCoinCapApi() {
        JsonObject responseJson = new JsonObject();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(coinCapURL)
                .header("Accept-Encoding", "deflate")
                .header("Authorization", coinCapApiKey)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String responseContent = Objects.requireNonNull(response.body()).string();
            Gson gson = new GsonBuilder().create();
            responseJson = gson.fromJson(responseContent, JsonObject.class);
            return responseJson;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseJson;
    }


    /*
        Send get request to CoinMarketCap api and
        \@return response as JsonObject containing crypto currency data
    */
    @Override
    public JsonObject responseFromCoinMarketCapApi() {
        JsonObject responseJson = new JsonObject();
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(coinMarketCapURL)
                .header("X-CMC_PRO_API_KEY", coinMarketCapApiKey)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String responseContent = Objects.requireNonNull(response.body()).string();
            Gson gson = new GsonBuilder().create();
            responseJson = gson.fromJson(responseContent, JsonObject.class);
            return responseJson;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseJson;
    }

    @Override
    public List<ApiHosting> saveAllApiHostings(List<ApiHosting> list) {
        return apiHostingService.saveAll(list);
    }
}
