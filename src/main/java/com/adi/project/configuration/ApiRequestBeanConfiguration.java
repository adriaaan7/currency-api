package com.adi.project.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

@Configuration
public class ApiRequestBeanConfiguration {

    private final String coinCapApiKey = "e92b7d3f-c194-4cbb-bb25-708710e1a14a";
    private final String coinCapURL = "https://api.coincap.io/v2/assets";

    @Bean
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
}
