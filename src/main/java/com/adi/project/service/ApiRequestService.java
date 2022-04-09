package com.adi.project.service;

import com.adi.project.model.ApiHosting;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class ApiRequestService implements IApiRequestService {

    @Value("${coinCapApiKey:0}")
    private String coinCapApiKey; //= "e92b7d3f-c194-4cbb-bb25-708710e1a14a";
    @Value("${coinMarketCapApiKey:0}")
    private String coinMarketCapApiKey; // = "e41224c3-0abc-4369-83ae-0ecd1092be37";

    @Value("${api.url.gemini}")
    private String geminiURL;
    @Value("${api.url.coinCap}")
    private String coinCapURL;
    @Value("${api.url.coinMarketCap}")
    private String coinMarketCapURL;


    private final ApiHostingService apiHostingService;

    public ApiRequestService(ApiHostingService apiHostingService) {
        this.apiHostingService = apiHostingService;
    }

    @Override
    public JsonArray responseFromGeminiApi() {
        JsonArray responseArray = new JsonArray();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(geminiURL)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String responseContent = Objects.requireNonNull(response.body()).string();
            Gson gson = new GsonBuilder().create();
            responseArray = gson.fromJson(responseContent, JsonArray.class);
            return responseArray;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseArray;
    }

    /*
            Send get request to CoinCap api and
            \@return response as JsonObject containing crypto currency data
        */
    @Override
    public JsonArray responseFromCoinCapApi() {
        JsonArray array = new JsonArray();
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
            array =  gson.fromJson(responseContent, JsonObject.class)
                         .getAsJsonArray("data");
            return array;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }


    /*
        Send get request to CoinMarketCap api and
        \@return response as JsonObject containing crypto currency data
    */
    @Override
    public JsonArray responseFromCoinMarketCapApi() {
        JsonArray array = new JsonArray();
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(coinMarketCapURL)
                .header("X-CMC_PRO_API_KEY", coinMarketCapApiKey)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String responseContent = Objects.requireNonNull(response.body()).string();
            Gson gson = new GsonBuilder().create();
            array = gson.fromJson(responseContent, JsonObject.class)
                        .getAsJsonArray("data");
            return array;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }

    @Override
    public List<ApiHosting> saveAllApiHostings(List<ApiHosting> list) {
        return apiHostingService.saveAll(list);
    }
}
