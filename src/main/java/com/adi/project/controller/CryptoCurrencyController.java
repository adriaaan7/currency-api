package com.adi.project.controller;

import com.adi.project.model.CryptoCurrency;
import com.adi.project.service.CryptoCurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v2")
public class CryptoCurrencyController {

    private final CryptoCurrencyService cryptoCurrencyService;

    public CryptoCurrencyController(CryptoCurrencyService cryptoCurrencyService) {
        this.cryptoCurrencyService = cryptoCurrencyService;
    }

    @PostMapping("/add")
    @ResponseBody
    public CryptoCurrency addCryptoCurrency(@RequestBody CryptoCurrency cryptoCurrency){
        return cryptoCurrencyService.saveCryptoCurrency(cryptoCurrency);
    }

    @GetMapping("/")
    public String getMainPage(){
        return "index";
    }
}
