package com.adi.project.service;

import com.adi.project.model.CryptoCurrency;
import com.adi.project.repository.CryptoCurrencyRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoCurrencyService implements ICryptoCurrencyService {

    private final CryptoCurrencyRepository cryptoCurrencyRepository;

    public CryptoCurrencyService(CryptoCurrencyRepository cryptoCurrencyRepository) {
        this.cryptoCurrencyRepository = cryptoCurrencyRepository;
    }

    @Override
    public List<CryptoCurrency> getAllCryptoCurrencies() {
        return cryptoCurrencyRepository.getAllCryptoCurrencies();
    }

    @Override
    public CryptoCurrency getCryptoCurrencyByName(String currencyName) {
        return cryptoCurrencyRepository.getCryptoCurrencyByName(currencyName);
    }

    @Override
    public void saveCryptoCurrency(CryptoCurrency cryptoCurrency) {
        cryptoCurrencyRepository.save(cryptoCurrency);
    }

    @Override
    public void updateAllCryptoCurrencies(JsonArray cryptoCurrencyArray) {
        for(int i = 0; i < cryptoCurrencyArray.size(); i++){
            Gson gson = new Gson();
            CryptoCurrency obj = gson.fromJson(cryptoCurrencyArray.get(i), CryptoCurrency.class);
            if(cryptoCurrencyRepository.existsCryptoCurrenciesByName(obj.getName()))
                continue;
            saveCryptoCurrency(obj);
        }
    }
}
