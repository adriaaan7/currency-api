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

    public CryptoCurrency(Long id, String name, int rank, String symbol, BigDecimal priceUsd, BigDecimal rateOfChange) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.symbol = symbol;
        this.priceUsd = priceUsd;
        this.rateOfChange = rateOfChange;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "api_hosting_id")
    private ApiHosting hosting;

    private String name;
    private int rank;
    private String symbol;
    private BigDecimal priceUsd;
    private LocalDate date;
    private BigDecimal rateOfChange;

    public CryptoCurrency() {
    }

    public CryptoCurrency(String name, int rank, String symbol, BigDecimal priceUsd, LocalDate date, BigDecimal rateOfChange) {
        this.name = name;
        this.rank = rank;
        this.symbol = symbol;
        this.priceUsd = priceUsd;
        this.date = date;
        this.rateOfChange = rateOfChange;
    }

    public CryptoCurrency(String name, int rank, String symbol, BigDecimal priceUsd, BigDecimal rateOfChange) {
        this.name = name;
        this.rank = rank;
        this.symbol = symbol;
        this.priceUsd = priceUsd;
        this.rateOfChange = rateOfChange;
    }

    public CryptoCurrency(Long id, ApiHosting hosting, String name, int rank, String symbol, BigDecimal priceUsd, BigDecimal rateOfChange) {
        this.id = id;
        this.hosting = hosting;
        this.name = name;
        this.rank = rank;
        this.symbol = symbol;
        this.priceUsd = priceUsd;
        this.rateOfChange = rateOfChange;
    }

    @Override
    public String toString() {
        return "CryptoCurrency{" +
                "id=" + id +
                ", hosting=" + hosting +
                ", name='" + name + '\'' +
                ", rank=" + rank +
                ", symbol='" + symbol + '\'' +
                ", priceUsd=" + priceUsd +
                ", date=" + date +
                ", rateOfChange=" + rateOfChange +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getRateOfChange() {
        return rateOfChange;
    }

    public void setRateOfChange(BigDecimal rateOfChange) {
        this.rateOfChange = rateOfChange;
    }

    public ApiHosting getHosting() {
        return hosting;
    }

    public void setHosting(ApiHosting apiHosting) {
        this.hosting = apiHosting;
    }
}

