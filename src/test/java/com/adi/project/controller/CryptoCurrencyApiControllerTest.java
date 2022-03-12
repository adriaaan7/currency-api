package com.adi.project.controller;

import com.adi.project.service.CryptoCurrencyApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CryptoCurrencyApiControllerTest {

    @Autowired
    private CryptoCurrencyApiService cryptoCurrencyApiService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void should_return_100_crypto_currencies() throws Exception {
        mockMvc.perform(get(cryptoCurrencyApiService.getCryptoCurrenciesURL())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(100)))
                .andExpect(jsonPath("$[0].name").value(String.class));
    }
}