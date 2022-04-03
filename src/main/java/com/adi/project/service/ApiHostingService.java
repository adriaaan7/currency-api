package com.adi.project.service;

import com.adi.project.model.ApiHosting;
import com.adi.project.repository.ApiHostingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiHostingService implements IApiHostingService {

    private final ApiHostingRepository apiHostingRepository;

    public ApiHostingService(ApiHostingRepository apiHostingRepository) {
        this.apiHostingRepository = apiHostingRepository;
    }

    @Override
    public List<ApiHosting> saveAll(List<ApiHosting> list) {
        return apiHostingRepository.saveAll(list);
    }

    @Override
    public List<String> findAllNames() {
        List<ApiHosting> cryptoCurrencies = apiHostingRepository.findAll();
        return cryptoCurrencies.stream()
                .map(ApiHosting::getName)
                .collect(Collectors.toList());
    }

    @Override
    public ApiHosting save(ApiHosting apiHosting) {
        return apiHostingRepository.save(apiHosting);
    }

    @Override
    public boolean existsByName(String name) {
        return apiHostingRepository.existsByName(name);
    }
}
