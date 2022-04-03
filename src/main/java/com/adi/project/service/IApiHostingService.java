package com.adi.project.service;

import com.adi.project.model.ApiHosting;

import java.util.List;

public interface IApiHostingService {
    ApiHosting save(ApiHosting apiHosting);
    boolean existsByName(String name);
    List<ApiHosting> saveAll(List<ApiHosting> list);
    List<String> findAllNames();

}
