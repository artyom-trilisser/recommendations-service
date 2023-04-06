package com.example.recommendations.repository;

import com.example.recommendations.entity.CryptoRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Component
public class InMemoryCryptoRepository implements CryptoRepository {
    private final Map<String, List<CryptoRecord>> records = new ConcurrentHashMap<>();

    @Override
    public void addRecord(String cryptoSymbol, CryptoRecord record) {
        List<CryptoRecord> cryptoRecords = this.records.get(cryptoSymbol);

        if (cryptoRecords == null) {
            cryptoRecords = new ArrayList<>();
        }

        cryptoRecords.add(record);
        records.put(cryptoSymbol, cryptoRecords);
    }

    @Override
    public List<CryptoRecord> getRecordsBySymbol(String symbol) {
        return records.getOrDefault(symbol, new ArrayList<>());
    }

    @Override
    public Map<String, List<CryptoRecord>> getAllRecordsGroupedBySymbol() {
        return records;
    }
}
