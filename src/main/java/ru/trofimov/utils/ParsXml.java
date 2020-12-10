package ru.trofimov.utils;

import ru.trofimov.models.Currency;
import ru.trofimov.models.ExchangeRates;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ParsXml {
    private String url;
    private List<Currency> currencyList;
    private ExchangeRates exchangeRates;

    public ParsXml(String url, ExchangeRates exchangeRates) {
        this.url = url;
        this.exchangeRates = exchangeRates;
    }

    public List<Currency> getCurrencyList() throws Exception {
        List<Currency> currencyList = new ArrayList<>();
        InputStream inputStream = new URL(url).openStream();

        SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
        saxParser.parse(inputStream, new CurrencyHandler(currencyList, exchangeRates));

        return currencyList;
    }
}
