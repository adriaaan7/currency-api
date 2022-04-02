package com.adi.project.init;

import com.adi.project.converter.CryptoCurrencyToFileConverter;
import com.adi.project.model.CryptoCurrency;
import com.adi.project.service.CryptoCurrencyService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
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
        List<CryptoCurrency> listOfCryptoCurrencies = cryptoCurrencyService.getAllCryptoCurrencies();
        List<CryptoCurrency> negativeRatesOfChange = cryptoCurrencyService.filterCryptoCurrenciesByRateOfChange(-1);
        List<CryptoCurrency> positiveRatesOfChange = cryptoCurrencyService.filterCryptoCurrenciesByRateOfChange(1);

        //cryptoCurrencyToFileConverter.listOfCryptoCurrenciesToTextFile(listOfCryptoCurrencies, new File("cryptoCurrencies.txt"));
        //cryptoCurrencyToFileConverter.writeCryptoCurrenciesIDAndRateOfChangeToTxtFile(negativeRatesOfChange, new File("cryptoCurrenciesNegativeRatesOfChange.txt"));
        //cryptoCurrencyToFileConverter.writeCryptoCurrenciesIDAndRateOfChangeToTxtFile(positiveRatesOfChange, new File("cryptoCurrenciesPositiveRatesOfChange.txt"));
    }
}
