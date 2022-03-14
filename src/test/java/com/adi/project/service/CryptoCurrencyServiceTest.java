package com.adi.project.service;

import com.adi.project.model.CryptoCurrency;
import com.adi.project.repository.CryptoCurrencyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CryptoCurrencyServiceTest {

    @Mock
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    @InjectMocks
    private CryptoCurrencyService cryptoCurrencyService;

    @Test
    void getAllCryptoCurrencies() {
        // given
        List<CryptoCurrency> list = new ArrayList<>();
        CryptoCurrency c1 = new CryptoCurrency();
        c1.setName("Polish Coin");
        list.add(c1);

        List<CryptoCurrency> newList = new ArrayList<>();

        // when
        when(cryptoCurrencyRepository.getAllCryptoCurrencies()).thenReturn(list);
        Iterable<CryptoCurrency> all = cryptoCurrencyService.getAllCryptoCurrencies();
        all.forEach((c) -> newList.add(c));

        // then
        assertEquals(1, newList.size());
    }

    @Test
    void getCryptoCurrencyByName() {
    }

    @Test
    void saveCryptoCurrency() {
    }

    @Test
    void initAllCryptoCurrencies() {
    }

    @Test
    void updateCryptoCurrency() {
    }

    @Test
    void updateAllCryptoCurrencies() {
    }
}