package com.adi.project.repository;

import com.adi.project.model.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {

    @Query(value = "SELECT * FROM crypto_currencies", nativeQuery = true)
    List<CryptoCurrency> getAllCryptoCurrencies();

    @Query(value = "SELECT * FROM cryptoCurrency WHERE cryptoCurrency.name=:currencyName", nativeQuery = true)
    CryptoCurrency getCryptoCurrencyByName(@Param("currencyName") String currencyName);

    @Transactional
    @Modifying
    @Query(value = "UPDATE crypto_currencies c SET c.name = ?2," +
            "c.priceUsd = ?5, c.rateOfChange = ?5 " +
            "WHERE c.id = ?1", nativeQuery = true)
    void updateCryptoCurrency(Long id, String name,
                              BigDecimal priceUsd, BigDecimal rateOfChange);

    List<CryptoCurrency> findAll();
}
