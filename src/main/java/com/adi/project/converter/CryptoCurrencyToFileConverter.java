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
    public boolean listOfCryptoCurrenciesToTextFile(List<CryptoCurrency> cryptoCurrencies, File file) {
        if (file.length() == 100)
            return writeCryptoCurrenciesToTxtFile(cryptoCurrencies, file, true);
        else
            return writeCryptoCurrenciesToTxtFile(cryptoCurrencies, file, false);
    }

    /*
        Writing each CryptoCurrency's symbol and rateOfChange values to a txt.file
    */
    private boolean writeCryptoCurrenciesToTxtFile(List<CryptoCurrency> cryptoCurrencies, File file, boolean override) {
        try {
            if(file.createNewFile())
                System.out.println("Creating new file...");
            else
                System.out.println("File already exists");
            if(override)
                System.out.println("Overriding a file...");
            else
                System.out.println("Writing to a file...");
            FileWriter fileWriter = new FileWriter(file);
            for(CryptoCurrency cryptoCurrency: cryptoCurrencies){
                String symbol = cryptoCurrency.getSymbol();
                BigDecimal rateOfChange = cryptoCurrency.getRateOfChange();
                fileWriter.write(symbol);
                fileWriter.write(" ");
                if(cryptoCurrency.getId() == 100)
                    fileWriter.write(String.valueOf(rateOfChange));
                else
                    fileWriter.write(String.valueOf(rateOfChange) + System.lineSeparator());
            }
            System.out.println("Finished writing to a file");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.length() == cryptoCurrencies.size();
    }
}
