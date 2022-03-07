package com.adi.project.controller;

import com.adi.project.service.CryptoCurrencyApiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
public class CryptoCurrencyApiController {

    private final CryptoCurrencyApiService cryptoCurrencyApiService;
    private final String allCryptoCurrenciesURL = "https://api.coincap.io/v2/assets";

    public CryptoCurrencyApiController(CryptoCurrencyApiService cryptoCurrencyApiService) {
        this.cryptoCurrencyApiService = cryptoCurrencyApiService;
    }

    @GetMapping("/cryptoCurrencies")
    public String getCryptoCurrenciesApiResponse(){
        String test = cryptoCurrencyApiService.fetchAllCryptoCurrencies(allCryptoCurrenciesURL);
        System.out.println(test);
        return "index";
    }


}
