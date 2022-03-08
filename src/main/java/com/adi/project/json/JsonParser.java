package com.adi.project.json;

import com.adi.project.model.CryptoCurrency;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JsonParser implements IJsonParser {

    @Override
    public List<CryptoCurrency> parseJsonElementToCryptoCurrency(JsonArray jsonArray) {
        List<CryptoCurrency> cryptoCurrencyList = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++){
            CryptoCurrency cryptoCurrency = new CryptoCurrency();
            JsonObject obj = jsonArray.get(i).getAsJsonObject();
            cryptoCurrency.setName(obj.get("name").getAsString());
            cryptoCurrency.setRank(obj.get("rank").getAsInt());
            cryptoCurrency.setSymbol(obj.get("symbol").getAsString());
            cryptoCurrency.setPriceUsd(obj.get("priceUsd").getAsBigDecimal());
            cryptoCurrency.setRateOfChange(obj.get("changePercent24Hr").getAsBigDecimal());
            cryptoCurrencyList.add(cryptoCurrency);
        }
        return cryptoCurrencyList;
    }
}
