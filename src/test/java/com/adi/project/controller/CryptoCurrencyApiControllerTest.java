package com.adi.project.controller;

import com.adi.project.service.CryptoCurrencyApiService;
import com.adi.project.service.CryptoCurrencyService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CryptoCurrencyApiController.class)
class CryptoCurrencyApiControllerTest {

    @MockBean
    private CryptoCurrencyApiService cryptoCurrencyApiService;

    @Autowired
    private MockMvc mockMvc;

    private final String allCryptoCurrenciesURL = "https://api.coincap.io/v2/assets";


    @Test
    void should_return_100_crypto_currencies() throws Exception {
        mockMvc.perform(get(allCryptoCurrenciesURL)
                .header("Accept-Encoding", "deflate")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(100)))
                .andExpect(jsonPath("$[0].name").value(String.class));
    }
}