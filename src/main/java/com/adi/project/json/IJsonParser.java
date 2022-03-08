package com.adi.project.json;

import com.adi.project.model.CryptoCurrency;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.List;

public interface IJsonParser {
    List<CryptoCurrency> parseJsonElementToCryptoCurrency(JsonArray jsonArray);
}
