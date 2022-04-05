package com.adi.project.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "api_hosting", schema = "public")
public class ApiHosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "hosting",
            targetEntity = CryptoCurrency.class,
            cascade = CascadeType.ALL)
    private List<CryptoCurrency> cryptoCurrencyList;

    private String name;

    public ApiHosting(){

    }

    public ApiHosting(String name) {
        this.name = name;
    }

    public void add(CryptoCurrency cryptoCurrency){
        if (cryptoCurrencyList == null)
            cryptoCurrencyList = new ArrayList<>();

        cryptoCurrencyList.add(cryptoCurrency);

        cryptoCurrency.setHosting(this);
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

    public List<CryptoCurrency> getCryptoCurrencyList() {
        return cryptoCurrencyList;
    }

    public void setCryptoCurrencyList(List<CryptoCurrency> cryptoCurrencyList) {
        this.cryptoCurrencyList = cryptoCurrencyList;
    }
}
