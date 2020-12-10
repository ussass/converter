package ru.trofimov.service;

import ru.trofimov.dao.ValuteDao;
import ru.trofimov.dao.ValuteDaoImpl;
import ru.trofimov.models.ExchangeRates;

import java.util.List;

public class ValuteServiceImpl implements ValuteService {

    private ValuteDao valuteDao = new ValuteDaoImpl();

    @Override
    public void save(ExchangeRates rates) {
        valuteDao.save(rates);
    }

    @Override
    public List<ExchangeRates> findAll() {
        return valuteDao.findAll();
    }

    @Override
    public int lastDay() {
        List<ExchangeRates> list = valuteDao.findAll();
        int lastDay = 0;
        for (ExchangeRates water : list) {
            if (water.getDate() > lastDay) lastDay = water.getDate();
        }
        return lastDay;
    }

    @Override
    public ExchangeRates getRatesByDate(int date) {
        return valuteDao.getRatesByDate(date);
    }
}
