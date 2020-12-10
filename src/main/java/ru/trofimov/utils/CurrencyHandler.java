package ru.trofimov.utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.trofimov.models.Currency;
import ru.trofimov.models.ExchangeRates;

import java.util.List;

public class CurrencyHandler extends DefaultHandler {
    private List<Currency> currencyList;
    private Currency currency;
    private ExchangeRates exchangeRates;

    private final StringBuilder data = new StringBuilder();

    private boolean isNumCodeFieldUsed;
    private boolean isCharCodeFieldUsed;
    private boolean isNominalFieldUsed;
    private boolean isNameFieldUsed;
    private boolean isValueFieldUsed;

    public CurrencyHandler(List<Currency> currencyList, ExchangeRates exchangeRates) {
        this.currencyList = currencyList;
        this.exchangeRates = exchangeRates;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case "Valute":
                currency = new Currency();
                currency.setValuteId(attributes.getValue("ID"));
                break;
            case "NumCode":
                isNumCodeFieldUsed = true;
                break;
            case "CharCode":
                isCharCodeFieldUsed = true;
                currency.setCharCode(attributes.getValue("CharCode"));
                break;
            case "Nominal":
                isNominalFieldUsed = true;
                break;
            case "Name":
                isNameFieldUsed = true;
                currency.setName(attributes.getValue("Name"));
                break;
            case "Value":
                isValueFieldUsed = true;
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("Valute")) {
            currencyList.add(currency);
        }

        if (isNumCodeFieldUsed) {
            currency.setNumCode(Short.parseShort(data.toString()));
            isNumCodeFieldUsed = false;
        }
        if (isCharCodeFieldUsed) {
            currency.setCharCode(data.toString());
            isCharCodeFieldUsed = false;
        }
        if (isNominalFieldUsed) {
            currency.setNominal(Short.parseShort(data.toString()));
            isNominalFieldUsed = false;
        }
        if (isNameFieldUsed) {
            currency.setName(data.toString());
            isNameFieldUsed = false;
        }
        if (isValueFieldUsed) {
            int charIndex = data.indexOf(",");
            data.replace(charIndex, charIndex + 1, ".");
            currency.setValue(Double.parseDouble(data.toString()));

            isValueFieldUsed = false;
        }

        currency.setExchangeRates(exchangeRates);

        data.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        data.append(new String(ch, start, length));
    }
}