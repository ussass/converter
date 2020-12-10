package ru.trofimov.service;

import ru.trofimov.dao.HistoryDaoImpl;
import ru.trofimov.dao.HistoryDao;
import ru.trofimov.models.ConversionHistory;

import java.util.List;

public class HistoryServiceImpl implements HistoryService {
    private HistoryDao dao = new HistoryDaoImpl();

    @Override
    public void save(ConversionHistory history) {
        dao.save(history);
    }

    @Override
    public List<ConversionHistory> findAll() {
        return dao.findAll();
    }
}
