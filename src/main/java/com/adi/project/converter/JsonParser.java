package com.adi.project.converter;

import com.adi.project.model.ApiHosting;
import com.adi.project.model.CryptoCurrency;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class JsonParser implements IJsonParser {

    @Override
    public List<CryptoCurrency> parseJsonArrayToCryptoCurrencyFromCoinCap(JsonArray jsonArray) {
        List<CryptoCurrency> cryptoCurrencyList = new ArrayList<>();
        ApiHosting coinCapHosting = new ApiHosting();
        coinCapHosting.setName("Coin Cap");
        for (int i = 0; i < jsonArray.size(); i++){
            CryptoCurrency cryptoCurrency = new CryptoCurrency();
            JsonObject obj = jsonArray.get(i).getAsJsonObject();
            cryptoCurrency.setName(obj.get("name").getAsString());
            cryptoCurrency.setRank(obj.get("rank").getAsInt());
            cryptoCurrency.setSymbol(obj.get("symbol").getAsString());
            cryptoCurrency.setPriceUsd(obj.get("priceUsd").getAsBigDecimal());
            cryptoCurrency.setRateOfChange(obj.get("changePercent24Hr").getAsBigDecimal());
            cryptoCurrency.setHosting(coinCapHosting);
            cryptoCurrencyList.add(cryptoCurrency);
        }
        return cryptoCurrencyList;
    }

    public List<CryptoCurrency> parseJsonArrayToCryptoCurrencyFromCoinMarketCap(JsonArray jsonArray){
        List<CryptoCurrency> cryptoCurrencyList = new ArrayList<>();
        ApiHosting coinMarketCapHosting = new ApiHosting();
        coinMarketCapHosting.setName("Coin Market Cap");
        for (int i = 0; i < jsonArray.size(); i++){
            CryptoCurrency cryptoCurrency = new CryptoCurrency();
            JsonObject obj = jsonArray.get(i).getAsJsonObject();
            cryptoCurrency.setName(obj.get("name").getAsString());
            cryptoCurrency.setRank(++i);
            cryptoCurrency.setSymbol(obj.get("symbol").getAsString());
            cryptoCurrency.setPriceUsd(obj.getAsJsonObject("quote").getAsJsonObject("USD").get("price").getAsBigDecimal());
            cryptoCurrency.setRateOfChange(obj.getAsJsonObject("quote").getAsJsonObject("USD").get("percent_change_24h").getAsBigDecimal());
            cryptoCurrency.setHosting(coinMarketCapHosting);
            cryptoCurrencyList.add(cryptoCurrency);
            System.out.println(obj);
        }
        return cryptoCurrencyList;
    }

}
