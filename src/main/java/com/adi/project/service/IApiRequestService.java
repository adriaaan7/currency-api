package com.adi.project.service;

import com.adi.project.model.ApiHosting;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

public interface IApiRequestService {

    JsonArray responseFromCoinCapApi();
    JsonArray responseFromCoinMarketCapApi();
    JsonArray responseFromGeminiApi();
    List<ApiHosting> saveAllApiHostings(List<ApiHosting> list);
}
