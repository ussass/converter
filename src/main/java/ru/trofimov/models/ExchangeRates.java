package ru.trofimov.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exchange_rates")
public class ExchangeRates {

    @Id
    @Column(name = "rates_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "rates_date")
    private int date;

    @OneToMany(mappedBy = "exchangeRates", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Currency> currencyList = new ArrayList<>();

    public ExchangeRates() {
    }

    public void addReading(Currency currency) {
        currency.setExchangeRates(this);
        currencyList.add(currency);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }
}
