package com.adi.project.converter;

import com.adi.project.model.CryptoCurrency;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Component
public class CryptoCurrencyToFileConverter implements ICryptoCurrencyToFileConverter {

    @Override
    public boolean listOfCryptoCurrenciesToTextFile(List<CryptoCurrency> cryptoCurrencies) {
        try {
            FileWriter fileWriter = new FileWriter("cryptoCurrencies.txt");
            for(CryptoCurrency cryptoCurrency: cryptoCurrencies){
                String symbol = cryptoCurrency.getSymbol();
                BigDecimal rateOfChange = cryptoCurrency.getRateOfChange();
                if(symbol.length() != 0)
                    fileWriter.write(symbol);
                fileWriter.write(" ");
                if(rateOfChange != null)
                    fileWriter.write(String.valueOf(rateOfChange) + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File("cryptoCurrencies.txt");

        return file.length() == cryptoCurrencies.size();
    }
}
