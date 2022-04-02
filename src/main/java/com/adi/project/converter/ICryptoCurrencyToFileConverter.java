package com.adi.project.converter;

import com.adi.project.model.CryptoCurrency;

import java.io.File;
import java.util.List;

public interface ICryptoCurrencyToFileConverter {
    boolean listOfCryptoCurrenciesToTextFile(List<CryptoCurrency> cryptoCurrencies, File file);
    long countNumberOfLinesInTextFile(File file);
    String convertDotToCommaInString(String value);
}
