package com.adi.project.controller;

import com.adi.project.model.CryptoCurrency;
import com.adi.project.service.CryptoCurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/api/v2")
@CrossOrigin
public class CryptoCurrencyController {

    private final CryptoCurrencyService cryptoCurrencyService;

    public CryptoCurrencyController(CryptoCurrencyService cryptoCurrencyService) {
        this.cryptoCurrencyService = cryptoCurrencyService;
    }

    @PostMapping("/add")
    @ResponseBody
    public CryptoCurrency addCryptoCurrency(@RequestParam(value = "name", required = true) String name,
                                            @RequestParam(value = "priceUsd", required = true) BigDecimal priceUsd,
                                            @RequestParam(value = "rateOfChange", required = true) BigDecimal rateOfChange){
        CryptoCurrency cryptoCurrency = new CryptoCurrency();
        cryptoCurrency.setPriceUsd(priceUsd);
        cryptoCurrency.setRateOfChange(rateOfChange);
        return cryptoCurrencyService.saveCryptoCurrency(cryptoCurrency);
    }

    @PutMapping("/update/all")
    @ResponseBody
    public List<CryptoCurrency> updateAllCryptoCurrencies(){
        return cryptoCurrencyService.initAllCryptoCurrencies();
    }

    @GetMapping("/all")
    @ResponseBody
    public List<CryptoCurrency> getMainPage(){
        return cryptoCurrencyService.getAllCryptoCurrencies();
    }
}
