package com.adi.project.controller;

import com.adi.project.service.CryptoCurrencyApiService;
import com.google.gson.JsonArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
public class CryptoCurrencyApiController {

    private final CryptoCurrencyApiService cryptoCurrencyApiService;

    public CryptoCurrencyApiController(CryptoCurrencyApiService cryptoCurrencyApiService) {
        this.cryptoCurrencyApiService = cryptoCurrencyApiService;
    }

    @GetMapping("/cryptoCurrencies")
    public String getCryptoCurrenciesApiResponse(){
        JsonArray test = cryptoCurrencyApiService.fetchAllCryptoCurrenciesFromCoinCap();
        System.out.println(test);
        return "index";
    }


}
