package com.adi.project.repository;

import com.adi.project.model.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {

    @Query(value = "SELECT * FROM cryptoCurrency", nativeQuery = true)
    List<CryptoCurrency> getAllCryptoCurrencies();

    @Query(value = "SELECT * FROM cryptoCurrency WHERE cryptoCurrency.name=:currencyName", nativeQuery = true)
    CryptoCurrency getCryptoCurrencyByName(@Param("currencyName") String currencyName);

}
