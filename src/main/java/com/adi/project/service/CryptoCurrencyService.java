package com.adi.project.service;

import com.adi.project.json.JsonParser;
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
    private final JsonParser jsonParser;
    private final CryptoCurrencyApiService cryptoCurrencyApiService;
    private final String url = "https://api.coincap.io/v2/assets";

    public CryptoCurrencyService(CryptoCurrencyRepository cryptoCurrencyRepository, JsonParser jsonParser, CryptoCurrencyApiService cryptoCurrencyApiService) {
        this.cryptoCurrencyRepository = cryptoCurrencyRepository;
        this.jsonParser = jsonParser;
        this.cryptoCurrencyApiService = cryptoCurrencyApiService;
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
    public CryptoCurrency saveCryptoCurrency(CryptoCurrency cryptoCurrency) {
//        cryptoCurrencyRepository.findById(cryptoCurrency.getId()).orElseThrow();
        return cryptoCurrencyRepository.save(cryptoCurrency);
    }

    @Override
    public void updateAllCryptoCurrencies(JsonArray cryptoCurrencyArray) {
        cryptoCurrencyRepository.saveAll(
                jsonParser.parseJsonElementToCryptoCurrency(
                cryptoCurrencyApiService.fetchAllCryptoCurrencies(url)));
    }

}
