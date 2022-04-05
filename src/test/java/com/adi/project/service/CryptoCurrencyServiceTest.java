package com.adi.project.service;

import com.adi.project.converter.IJsonParser;
import com.adi.project.converter.JsonParser;
import com.adi.project.model.CryptoCurrency;
import com.adi.project.repository.CryptoCurrencyRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CryptoCurrencyServiceTest {

    @Mock
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    @InjectMocks
    private CryptoCurrencyService cryptoCurrencyService;

    @InjectMocks
    private JsonParser jsonParser;

    @InjectMocks
    private CryptoCurrencyApiService cryptoCurrencyApiService;


    @Test
    void getAllCryptoCurrencies() {
       // given
        List<CryptoCurrency> list = new ArrayList<>();
        CryptoCurrency c1 = new CryptoCurrency();
        c1.setName("Polish Coin");
        list.add(c1);

        // when
        when(cryptoCurrencyRepository.findAll()).thenReturn(list);
        System.out.println(list);
        List<CryptoCurrency> all = cryptoCurrencyService.getAllCryptoCurrencies();
        List<CryptoCurrency> newList = new ArrayList<>(all);

        // then
        assertEquals(1, newList.size());

    }

    @Test
    void getCryptoCurrencyByName() {
        // given
        CryptoCurrency cryptoCurrency = new CryptoCurrency();
        cryptoCurrency.setName("Polish Coin");

        // when
        when(cryptoCurrencyRepository
                .getCryptoCurrencyByName("Polish Coin"))
                .thenReturn(cryptoCurrency);
        CryptoCurrency cryptoCurrency2 = cryptoCurrencyService
                .getCryptoCurrencyByName("Polish Coin");

        // then
        assertEquals(cryptoCurrency2, cryptoCurrency);
    }

    @Test
    void saveCryptoCurrency() {
        // given
        CryptoCurrency currency1 = new CryptoCurrency();
        currency1.setId(1L);
        currency1.setName("PLC");
        currency1.setPriceUsd(BigDecimal.valueOf(1000L));

        // when
        when(cryptoCurrencyRepository.save(ArgumentMatchers
                                     .any(CryptoCurrency.class)))
                                     .thenReturn(currency1);
        CryptoCurrency currency2 = cryptoCurrencyService.saveCryptoCurrency(currency1);

        // then
        ArgumentCaptor<CryptoCurrency> captor = ArgumentCaptor.forClass(CryptoCurrency.class);
        verify(cryptoCurrencyRepository).save(captor.capture());
        assertEquals(captor.getValue().getPriceUsd(), currency2.getPriceUsd());

    }

    @Test
    void initAllCryptoCurrencies() {
        // given
        List<CryptoCurrency> coinCapList = new ArrayList<>();
        JsonArray coinCapJsonArray = new JsonArray();
        CryptoCurrency c = new CryptoCurrency();
        c.setName("PLC");
        // when
        when(cryptoCurrencyApiService
                .fetchAllCryptoCurrenciesFromCoinCap())
                .thenReturn(coinCapJsonArray);

        // then
    }

    @Test
    void updateCryptoCurrency() {
        // given
        Long id = 1L;
        String name = "Polish Coin";
        int rank = 1;
        String symbol = "PLC";
        BigDecimal priceUsd = BigDecimal.valueOf(1000L);
        BigDecimal rateOfChange = BigDecimal.valueOf(10L);


        // when
        //verify(cryptoCurrencyRepository.updateCryptoCurrency(id, name, rank, symbol, priceUsd, rateOfChange));

        // then
    }

    @Test
    void updateAllCryptoCurrencies() {
    }

    @Test
    void should_Return_List_With_Negative_Rates_Of_Change() {
        // given
        List<CryptoCurrency> list = cryptoCurrencyService.getAllCryptoCurrencies();
        int filter = -1;
        CryptoCurrency c1 = new CryptoCurrency();
        CryptoCurrency c2 = new CryptoCurrency();
        c1.setRateOfChange(BigDecimal.valueOf(-5L));
        c2.setRateOfChange(BigDecimal.valueOf(100L));
        list.add(c1);
        list.add(c2);

        // when
        when(cryptoCurrencyService
                .filterCryptoCurrenciesByRateOfChange(filter))
                .thenReturn(list);

        List<CryptoCurrency> newList = cryptoCurrencyService.filterCryptoCurrenciesByRateOfChange(filter);

        // then
        assertEquals(newList.get(0).getRateOfChange(), c1.getRateOfChange());
        assertEquals(newList.size(), 1);
    }
}