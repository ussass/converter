package ru.trofimov.dao;

import ru.trofimov.models.ExchangeRates;

import java.util.List;

public interface ValuteDao {

    void save(ExchangeRates rates);

    List<ExchangeRates> findAll();

    ExchangeRates getRatesByDate(int date);
}
