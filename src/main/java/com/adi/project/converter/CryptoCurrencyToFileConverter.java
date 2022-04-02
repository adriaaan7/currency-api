package com.adi.project.converter;

import com.adi.project.model.CryptoCurrency;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;

@Component
public class CryptoCurrencyToFileConverter implements ICryptoCurrencyToFileConverter {

    @Override
    public boolean listOfCryptoCurrenciesToTextFile(List<CryptoCurrency> cryptoCurrencies, File file) {
        return writeCryptoCurrenciesSymbolAndRateOfChangeToTxtFile(cryptoCurrencies, file, true);
    }

    /*
        Writing each CryptoCurrency's symbol and rateOfChange values to a txt.file
    */
    private boolean writeCryptoCurrenciesSymbolAndRateOfChangeToTxtFile(List<CryptoCurrency> cryptoCurrencies,
                                                                        File file, boolean override) {
        try {
            if (file.createNewFile())
                System.out.println("Creating new file...");
            else
                System.out.println("File already exists");
            if (override)
                System.out.println("Overriding a file...");
            else {
                System.out.println("Not overriding - exiting...");
                return false;
            }
            FileWriter fileWriter = new FileWriter(file);
            for (CryptoCurrency cryptoCurrency : cryptoCurrencies) {
                String symbol = cryptoCurrency.getSymbol();
                BigDecimal rateOfChange = cryptoCurrency.getRateOfChange();
                fileWriter.write(symbol);
                fileWriter.write(" ");
                if (cryptoCurrency.getId() == 100)
                    fileWriter.write(String.valueOf(rateOfChange));
                else
                    fileWriter.write(String.valueOf(rateOfChange) + System.lineSeparator());
            }
            System.out.println("Finished writing to a file");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long linesInFile = countNumberOfLinesInTextFile(file);
        System.out.println("LENGTH OF FILE: " + linesInFile);

        return linesInFile == Long.valueOf(cryptoCurrencies.size());
    }

    public boolean writeCryptoCurrenciesIDAndRateOfChangeToTxtFile(List<CryptoCurrency> cryptoCurrencies, File file) {
        try {
            if (file.createNewFile())
                System.out.println("Creating new file...");
            else
                System.out.println("File already exists");

            FileWriter fileWriter = new FileWriter(file);
            for (CryptoCurrency cryptoCurrency : cryptoCurrencies) {
                long id = cryptoCurrency.getId();
                BigDecimal rateOfChange = cryptoCurrency.getRateOfChange();
                fileWriter.write(String.valueOf(id));
                fileWriter.write(" ");
                String convertedRateOfChange = convertDotToCommaInString(String.valueOf(rateOfChange));
                if (cryptoCurrency.getId() == 100)
                    fileWriter.write(convertedRateOfChange);
                else
                    fileWriter.write(convertedRateOfChange + System.lineSeparator());
            }
            System.out.println("Finished writing to a file");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long linesInFile = countNumberOfLinesInTextFile(file);
        System.out.println("LENGTH OF FILE: " + linesInFile);

        return linesInFile == Long.valueOf(cryptoCurrencies.size());
    }

    @Override
    public long countNumberOfLinesInTextFile(File file) {
        long lines = 0;

        try (LineNumberReader lnr = new LineNumberReader(new FileReader(file))) {

            while (lnr.readLine() != null) ;

            lines = lnr.getLineNumber();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    @Override
    public String convertDotToCommaInString(String value) {
        value = value.replaceAll("\\.", ",");
        return value;
    }
}
