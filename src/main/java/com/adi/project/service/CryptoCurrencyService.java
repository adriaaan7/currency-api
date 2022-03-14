package com.adi.project.service;

import com.adi.project.json.JsonParser;
import com.adi.project.model.CryptoCurrency;
import com.adi.project.repository.CryptoCurrencyRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CryptoCurrencyService implements ICryptoCurrencyService {

    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final JsonParser jsonParser;
    private final CryptoCurrencyApiService cryptoCurrencyApiService;

    public CryptoCurrencyService(CryptoCurrencyRepository cryptoCurrencyRepository, JsonParser jsonParser, CryptoCurrencyApiService cryptoCurrencyApiService) {
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
        return cryptoCurrencyRepository.saveAll(
                jsonParser.parseJsonElementToCryptoCurrency(
                cryptoCurrencyApiService.fetchAllCryptoCurrencies()));
    }

    @Override
    public void updateCryptoCurrency(Long id, String name,
                                     int rank, String symbol,
                                     BigDecimal priceUsd, BigDecimal rateOfChange) {
        cryptoCurrencyRepository.updateCryptoCurrency(id, name, rank, symbol, priceUsd, rateOfChange);
    }

    @Override
    public List<CryptoCurrency> updateAllCryptoCurrencies() {
        List<CryptoCurrency> list = jsonParser.parseJsonElementToCryptoCurrency(
                cryptoCurrencyApiService.fetchAllCryptoCurrencies());
        // checking if whole list was updated
        int count = 0;
        for (int i = 0; i < list.size(); i++, count++){
            CryptoCurrency c = list.get(i);
            updateCryptoCurrency(c.getId(), c.getName(),
                    c.getRank(), c.getSymbol(),
                    c.getPriceUsd(), c.getRateOfChange());
        }
        return list;
        //return (count == list.size()) ? list : null;
    }
}
