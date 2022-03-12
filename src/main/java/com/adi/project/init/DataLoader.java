package com.adi.project.init;

import com.adi.project.service.CryptoCurrencyService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final CryptoCurrencyService cryptoCurrencyService;

    public DataLoader(CryptoCurrencyService cryptoCurrencyService) {
        this.cryptoCurrencyService = cryptoCurrencyService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        cryptoCurrencyService.initAllCryptoCurrencies();
    }
}
