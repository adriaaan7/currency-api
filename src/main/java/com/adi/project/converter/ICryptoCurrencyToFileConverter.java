package com.adi.project.converter;

import com.adi.project.model.CryptoCurrency;

import java.util.List;

public interface ICryptoCurrencyToFileConverter {
    boolean listOfCryptoCurrenciesToTextFile(List<CryptoCurrency> cryptoCurrencies);
}
