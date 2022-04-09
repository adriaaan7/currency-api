package com.adi.project.controller;

import com.adi.project.model.CryptoCurrency;
import com.adi.project.service.CryptoCurrencyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CryptoCurrencyController.class)
class CryptoCurrencyControllerTest {

    @MockBean
    private CryptoCurrencyService cryptoCurrencyService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void it_should_return_created_crypto_currency() throws Exception {
        CryptoCurrency cryptoCurrency = new CryptoCurrency();
        cryptoCurrency.setId(1L);
        cryptoCurrency.setName("Polish Coin");
        cryptoCurrency.setSymbol("PL");
        cryptoCurrency.setPriceUsd(BigDecimal.valueOf(55));
        cryptoCurrency.setRateOfChange(BigDecimal.valueOf(10));

        when(cryptoCurrencyService.saveCryptoCurrency(any(CryptoCurrency.class)))
                .thenReturn(cryptoCurrency);

        mockMvc.perform(post("/api/v2/add")
                .param("id", "1")
                .param("name", "Polish Coin")
                .param("rank", "69")
                .param("symbol", "PL")
                .param("priceUsd", "55")
                .param("rateOfChange", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cryptoCurrency.getId()))
                .andExpect(jsonPath("$.name").value(cryptoCurrency.getName()))
                .andExpect(jsonPath("$.symbol").value(cryptoCurrency.getSymbol()))
                .andExpect(jsonPath("$.priceUsd").value(cryptoCurrency.getPriceUsd()))
                .andExpect(jsonPath("$.rateOfChange").value(cryptoCurrency.getRateOfChange()));
    }

    @Test
    void it_should_return_list_of_all_crypto_currencies() throws Exception {
        CryptoCurrency c1 = new CryptoCurrency();
        c1.setId(1L);
        c1.setName("First Coin");
        c1.setSymbol("FC");
        c1.setPriceUsd(BigDecimal.valueOf(111));
        c1.setRateOfChange(BigDecimal.valueOf(11));

        CryptoCurrency c2 = new CryptoCurrency();
        c2.setId(2L);
        c2.setName("Second Coin");
        c2.setSymbol("SC");
        c2.setPriceUsd(BigDecimal.valueOf(222));
        c2.setRateOfChange(BigDecimal.valueOf(22));

        List<CryptoCurrency> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);

        when(cryptoCurrencyService.initAllCryptoCurrencies())
                .thenReturn(list);

        mockMvc.perform(put("/api/v2/update/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(c1.getId()))
                .andExpect(jsonPath("$[0].name").value(c1.getName()))
                .andExpect(jsonPath("$[0].symbol").value(c1.getSymbol()))
                .andExpect(jsonPath("$[0].priceUsd").value(c1.getPriceUsd()))
                .andExpect(jsonPath("$[0].rateOfChange").value(c1.getRateOfChange()))
                .andExpect(jsonPath("$[1].id").value(c2.getId()))
                .andExpect(jsonPath("$[1].name").value(c2.getName()))
                .andExpect(jsonPath("$[1].symbol").value(c2.getSymbol()))
                .andExpect(jsonPath("$[1].priceUsd").value(c2.getPriceUsd()))
                .andExpect(jsonPath("$[1].rateOfChange").value(c2.getRateOfChange()));

    }
}