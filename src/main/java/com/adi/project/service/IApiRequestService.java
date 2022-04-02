package com.adi.project.service;

import com.google.gson.JsonObject;

public interface IApiRequestService {

    JsonObject responseFromCoinCapApi();
    JsonObject responseFromCoinMarketCapApi();
}
