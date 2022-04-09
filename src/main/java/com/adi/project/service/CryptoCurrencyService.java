package com.adi.project.service;

import com.adi.project.converter.JsonParser;
import com.adi.project.model.ApiHosting;
import com.adi.project.model.CryptoCurrency;
import com.adi.project.repository.CryptoCurrencyRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CryptoCurrencyService implements ICryptoCurrencyService {

    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final JsonParser jsonParser;
    private final CryptoCurrencyApiService cryptoCurrencyApiService;

    public CryptoCurrencyService(CryptoCurrencyRepository cryptoCurrencyRepository,
                                 JsonParser jsonParser,
                                 CryptoCurrencyApiService cryptoCurrencyApiService) {
        this.cryptoCurrencyRepository = cryptoCurrencyRepository;
        this.jsonParser = jsonParser;
        this.cryptoCurrencyApiService = cryptoCurrencyApiService;
    }

    @Override
    public List<CryptoCurrency> getAllCryptoCurrencies() {
        return cryptoCurrencyRepository.findAll();
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
    public List<CryptoCurrency> initAllCryptoCurrencies() {
        List<CryptoCurrency> coinCapList = jsonParser
                .parseJsonArrayToCryptoCurrencyFromCoinCap(
                cryptoCurrencyApiService.fetchAllCryptoCurrenciesFromCoinCap());
        List<CryptoCurrency> coinMarketCapList = jsonParser
                .parseJsonArrayToCryptoCurrencyFromCoinMarketCap(
                cryptoCurrencyApiService.fetchAllCryptoCurrenciesFromCoinMarketCap());
        List<JsonObject> allCryptoCurrenciesWithUsdPrices = getAllCryptoCurrenciesWithUsdPrices(
                cryptoCurrencyApiService.fetchAllCryptoCurrenciesFromGemini());
        List<CryptoCurrency> geminiList = jsonParser
                .parseJsonObjectListToCryptoCurrencyList(allCryptoCurrenciesWithUsdPrices, "Gemini");
        cryptoCurrencyRepository.saveAll(coinCapList);
        cryptoCurrencyRepository.saveAll(coinMarketCapList);
        cryptoCurrencyRepository.saveAll(geminiList);
        return cryptoCurrencyRepository.getAllCryptoCurrencies();
    }

    @Override
    public void updateCryptoCurrency(Long id, String name, String symbol,
                                     BigDecimal priceUsd, BigDecimal rateOfChange) {
        cryptoCurrencyRepository.updateCryptoCurrency(id, name, symbol, priceUsd, rateOfChange);
    }

    @Override
    public List<CryptoCurrency> updateAllCryptoCurrencies() {
        List<CryptoCurrency> list = jsonParser.parseJsonArrayToCryptoCurrencyFromCoinCap(
                cryptoCurrencyApiService.fetchAllCryptoCurrenciesFromCoinCap());
        // checking if whole list was updated
        int count = 0;
        for (int i = 0; i < list.size(); i++, count++){
            CryptoCurrency c = list.get(i);
            updateCryptoCurrency(c.getId(), c.getName(), c.getSymbol(),
                    c.getPriceUsd(), c.getRateOfChange());
        }
        return list;
        //return (count == list.size()) ? list : null;
    }

    @Override
    public List<CryptoCurrency> filterCryptoCurrenciesByRateOfChange(int filter) {
        List<CryptoCurrency> list = getAllCryptoCurrencies();
        List<CryptoCurrency> newList = new ArrayList<>();
        if(filter == 1) {
            list.stream()
                .filter(c -> c.getRateOfChange().doubleValue() > 0)
                .forEach(newList::add);}

        else if (filter == -1){
            list.stream()
                .filter(c -> c.getRateOfChange().doubleValue() < 0)
                .forEach(newList::add);
        }
        return newList;
    }

    /*
        Method which retrieves all the crypto currencies with usd prices
        from Gemini API and
        \@return list of JsonObjects containing USD prices
     */
    @Override
    public List<JsonObject> getAllCryptoCurrenciesWithUsdPrices(JsonArray jsonArray) {
        List<JsonObject> list = new ArrayList<>();
        for(int i = 0; i < jsonArray.size(); i++){
            JsonObject obj = jsonArray.get(i).getAsJsonObject();
            if(obj.get("pair").getAsString().toLowerCase().contains("usd"))
                list.add(obj);
        }
        return list;
    }
}
