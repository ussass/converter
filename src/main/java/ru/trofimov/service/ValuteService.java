package ru.trofimov.service;

import ru.trofimov.models.ExchangeRates;

import java.util.List;

public interface ValuteService {

    void save(ExchangeRates rates);

    List<ExchangeRates> findAll();

    int lastDay();

    ExchangeRates getRatesByDate(int date);
}
