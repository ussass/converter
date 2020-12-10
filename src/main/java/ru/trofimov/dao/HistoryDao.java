package ru.trofimov.dao;

import ru.trofimov.models.ConversionHistory;

import java.util.List;

public interface HistoryDao {

    void save(ConversionHistory history);

    List<ConversionHistory> findAll();
}
