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
                                            @RequestParam(value = "rank", required = true) int rank,
                                            @RequestParam(value = "symbol", required = true) String symbol,
                                            @RequestParam(value = "priceUsd", required = true) BigDecimal priceUsd,
                                            @RequestParam(value = "date", required = false) LocalDate date,
                                            @RequestParam(value = "rateOfChange", required = true) BigDecimal rateOfChange){
        CryptoCurrency cryptoCurrency = new CryptoCurrency();
        cryptoCurrency.setName(name);
        cryptoCurrency.setRank(rank);
        cryptoCurrency.setSymbol(symbol);
        cryptoCurrency.setPriceUsd(priceUsd);
        cryptoCurrency.setDate(date);
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
