package com.example.recommendations.repository;

import com.example.recommendations.entity.CryptoRecord;

import java.util.List;
import java.util.Map;


public interface CryptoRepository {
    void addRecord(String cryptoSymbol, CryptoRecord record);

    List<CryptoRecord> getRecordsBySymbol(String symbol);

    Map<String, List<CryptoRecord>> getAllRecordsGroupedBySymbol();
}
