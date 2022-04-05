package com.adi.project.converter;

import com.adi.project.model.CryptoCurrency;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

public interface IJsonParser {
    List<CryptoCurrency> parseJsonArrayToCryptoCurrencyFromCoinCap(JsonArray jsonArray);
    List<CryptoCurrency> parseJsonArrayToCryptoCurrencyFromCoinMarketCap(JsonArray jsonArray);
    List<CryptoCurrency> parseJsonArrayToCryptoCurrencyFromGemini(JsonArray jsonArray);
    List<CryptoCurrency> parseJsonObjectListToCryptoCurrencyList(List<JsonObject> list, String hostingName);
}
