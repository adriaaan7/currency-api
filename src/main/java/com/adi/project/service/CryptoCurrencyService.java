package com.adi.project.service;

import com.adi.project.model.CryptoCurrency;
import com.adi.project.repository.CryptoCurrencyRepository;
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
}
