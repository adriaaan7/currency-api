package com.adi.project.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "crypto_currencies", schema = "public")
public class CryptoCurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "api_hosting_id")
    private ApiHosting hosting;

    private String name;
    private String symbol;
    private BigDecimal priceUsd;
    private BigDecimal rateOfChange;

    public CryptoCurrency() { }

    public CryptoCurrency(Long id, ApiHosting hosting, String name, String symbol, BigDecimal priceUsd, BigDecimal rateOfChange) {
        this.id = id;
        this.hosting = hosting;
        this.name = name;
        this.symbol = symbol;
        this.priceUsd = priceUsd;
        this.rateOfChange = rateOfChange;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApiHosting getHosting() {
        return hosting;
    }

    public void setHosting(ApiHosting hosting) {
        this.hosting = hosting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(BigDecimal priceUsd) {
        this.priceUsd = priceUsd;
    }

    public BigDecimal getRateOfChange() {
        return rateOfChange;
    }

    public void setRateOfChange(BigDecimal rateOfChange) {
        this.rateOfChange = rateOfChange;
    }
}

