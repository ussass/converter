package ru.trofimov.models;

import javax.persistence.*;

@Entity
@Table(name = "foreign_currency_market")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "valute_id")
    private String valuteId ;

    @Column (name = "num_code")
    private Short numCode;

    @Column (name = "char_code")
    private String charCode;

    private short nominal;
    private String name;
    private double value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ex_rates_id")
    private ExchangeRates exchangeRates;

    public Currency() {
    }

    public Currency(String valuteId, Short numCode, String charCode, short nominal, String name, double value) {
        this.valuteId = valuteId;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValuteId() {
        return valuteId;
    }

    public void setValuteId(String valuteId) {
        this.valuteId = valuteId;
    }

    public Short getNumCode() {
        return numCode;
    }

    public void setNumCode(Short numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public short getNominal() {
        return nominal;
    }

    public void setNominal(short nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public ExchangeRates getExchangeRates() {
        return exchangeRates;
    }

    public void setExchangeRates(ExchangeRates exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    @Override
    public String toString() {
        return name;
    }
}
