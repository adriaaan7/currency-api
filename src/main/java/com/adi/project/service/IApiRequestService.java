package com.adi.project.service;

import com.adi.project.model.ApiHosting;
import com.google.gson.JsonObject;

import java.util.List;

public interface IApiRequestService {

    JsonObject responseFromCoinCapApi();
    JsonObject responseFromCoinMarketCapApi();
    JsonObject responseFromGeminiApi();
    List<ApiHosting> saveAllApiHostings(List<ApiHosting> list);
}
