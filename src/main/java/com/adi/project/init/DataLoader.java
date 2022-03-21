package com.adi.project.init;

import com.adi.project.converter.CryptoCurrencyToFileConverter;
import com.adi.project.model.CryptoCurrency;
import com.adi.project.service.CryptoCurrencyService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private final CryptoCurrencyService cryptoCurrencyService;
    private final CryptoCurrencyToFileConverter cryptoCurrencyToFileConverter;

    public DataLoader(CryptoCurrencyService cryptoCurrencyService, CryptoCurrencyToFileConverter cryptoCurrencyToFileConverter) {
        this.cryptoCurrencyService = cryptoCurrencyService;
        this.cryptoCurrencyToFileConverter = cryptoCurrencyToFileConverter;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        cryptoCurrencyService.initAllCryptoCurrencies();
        System.out.println("WRITING TO TEXT START");
        List<CryptoCurrency> listOfCryptoCurrencies = cryptoCurrencyService.getAllCryptoCurrencies();
        if(listOfCryptoCurrencies.size() > 0)
            cryptoCurrencyToFileConverter.listOfCryptoCurrenciesToTextFile(listOfCryptoCurrencies);
        System.out.println("WRITING TO TEXT END");

    }
}
