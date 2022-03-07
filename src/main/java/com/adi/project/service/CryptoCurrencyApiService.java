package com.adi.project.service;

import com.adi.project.configuration.BeanConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class CryptoCurrencyApiService implements ICryptoCurrencyApiService {

    private final BeanConfiguration beanConfiguration;

    public CryptoCurrencyApiService(BeanConfiguration beanConfiguration) {
        this.beanConfiguration = beanConfiguration;
    }

    /*
        Returns whole content of the response from the provided url
        #TODO
        SELECT ALL KEYS AND MATCH WITH THEIR VALUES
    */
    @Override
    public String fetchAllCryptoCurrencies(String url) {
        ObjectMapper objectMapper = beanConfiguration.objectMapper();
        OkHttpClient okHttpClient = beanConfiguration.okHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .header("Accept-Encoding", "deflate")
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String responseContent = response.body().string();
            return responseContent;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "Failed to fetch data from API";
    }
}
