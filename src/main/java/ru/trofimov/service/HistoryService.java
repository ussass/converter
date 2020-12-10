package ru.trofimov.service;

import ru.trofimov.models.ConversionHistory;

import java.util.List;

public interface HistoryService {

    void save(ConversionHistory history);

    List<ConversionHistory> findAll();
}
