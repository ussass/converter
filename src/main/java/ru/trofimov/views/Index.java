package ru.trofimov.views;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import ru.trofimov.models.ConversionHistory;
import ru.trofimov.models.Currency;
import ru.trofimov.models.ExchangeRates;
import ru.trofimov.service.HistoryService;
import ru.trofimov.service.HistoryServiceImpl;
import ru.trofimov.service.ValuteService;
import ru.trofimov.service.ValuteServiceImpl;
import ru.trofimov.utils.ParsXml;

import java.util.*;

public class Index extends WebPage {

    private TextField leftField;
    private TextField rightField;
    DropDownChoice leftSelect;
    DropDownChoice rightSelect;

    private List<Currency> currency;

    public Index() {

        ExchangeRates rates = getTodayRates();

        currency = rates.getCurrencyList();
        addRuble(currency);

        Form form = new Form("form");

        leftField = new TextField("leftField", new Model());
        form.add(leftField);

        rightField = new TextField("rightField", new Model());
        form.add(rightField);

        leftSelect = new DropDownChoice("leftSelect", new PropertyModel(this, ""), currency);
        form.add(leftSelect);

        rightSelect = new DropDownChoice("rightSelect", new PropertyModel(this, ""), currency);
        form.add(rightSelect);

        form.add(new Button("button") {
            @Override
            public void onSubmit() {
                double leftValue;
                try {
                    leftValue = leftField.getModelObject() == null ? 0 : Double.parseDouble(leftField.getModelObject().toString().replaceAll(",", "."));
                } catch (NumberFormatException e) {
                    leftValue = 0;
                }
                double rightValue;
                try {
                    rightValue = rightField.getModelObject() == null ? 0 : Double.parseDouble(rightField.getModelObject().toString().replaceAll(",", "."));
                } catch (NumberFormatException e) {
                    rightValue = 0;
                }
                if (rightValue == 0 && leftValue == 0) return;
                String leftCurrency = leftSelect.getModelObject().toString();
                String rightCurrency = rightSelect.getModelObject().toString();

                ExchangeRates ratesOnSubmit = getTodayRates();
                List<Currency> currencyList = addRuble(ratesOnSubmit.getCurrencyList());

                if (leftValue == 0) {
                    Currency currency1 = getCurrencyByName(currencyList, rightCurrency);
                    Currency currency2 = getCurrencyByName(currencyList, leftCurrency);
                    leftValue = (currency1.getValue() / currency1.getNominal()) / (currency2.getValue() / currency2.getNominal()) * rightValue;
                } else {
                    Currency currency1 = getCurrencyByName(currencyList, leftCurrency);
                    Currency currency2 = getCurrencyByName(currencyList, rightCurrency);
                    rightValue = (currency1.getValue() / currency1.getNominal()) / (currency2.getValue() / currency2.getNominal()) * leftValue;
                }

                ConversionHistory history = new ConversionHistory(ratesOnSubmit.getDate(), leftValue, rightValue, leftCurrency, rightCurrency,
                        getCurrencyByName(currencyList, leftCurrency).getCharCode(), getCurrencyByName(currencyList, rightCurrency).getCharCode());

                leftField.setModelObject(history.getMoneyFormat(true));
                rightField.setModelObject(history.getMoneyFormat(false));
                leftSelect.setModelObject(leftSelect.getModelObject());
                rightSelect.setModelObject(rightSelect.getModelObject());

                HistoryService historyService = new HistoryServiceImpl();
                historyService.save(history);
            }
        });
        add(form);
    }

    private List<Currency> addRuble(List<Currency> currency) {
        currency.add(0, new Currency("R01010", (short) 643, "RUB", (short) 1, "Российский рубль", 1));
        return currency;
    }

    private Currency getCurrencyByName(List currency, String name) {
        for (Object o : currency) {
            Currency cur = (Currency) o;
            if (cur.getName().equals(name))
                return cur;
        }
        return new Currency();
    }

    private ExchangeRates getTodayRates() {
        ValuteService service = new ValuteServiceImpl();
        ExchangeRates rates;

        Calendar calendar = new GregorianCalendar();
        int today = (calendar.get(Calendar.YEAR) % 100) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DATE);

        if (service.lastDay() != today) {
            rates = new ExchangeRates();
            rates.setDate(today);
            try {
                ParsXml parsXml = new ParsXml("http://www.cbr.ru/scripts/XML_daily.asp", rates);
                rates.setCurrencyList(parsXml.getCurrencyList());
                service.save(rates);
            } catch (Exception e) {
                e.printStackTrace();
                rates.setCurrencyList(new ArrayList<>(2));
            }
        } else {
            rates = service.getRatesByDate(today);
        }
        return rates;
    }
}
