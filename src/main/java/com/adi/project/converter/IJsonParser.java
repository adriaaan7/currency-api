package com.adi.project.converter;

import com.adi.project.model.CryptoCurrency;
import com.google.gson.JsonArray;

import java.util.List;

public interface IJsonParser {
    List<CryptoCurrency> parseJsonElementToCryptoCurrency(JsonArray jsonArray);
}
