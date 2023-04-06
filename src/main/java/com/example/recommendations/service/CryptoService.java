package com.example.recommendations.service;

import com.example.recommendations.dto.CryptoDTO;
import com.example.recommendations.dto.CryptoDataDTO;
import com.example.recommendations.dto.NormalizedCryptoDTO;
import com.example.recommendations.entity.CryptoRecord;
import com.example.recommendations.mapper.CryptoMapper;
import com.example.recommendations.repository.CryptoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CryptoService {
    private final CryptoRepository repository;
    private final CryptoMapper mapper;


    public CryptoDataDTO getAnalyticDataByCrypto(String cryptoSymbol) {
        CryptoDTO max = null;
        CryptoDTO min = null;
        CryptoDTO oldest = null;
        CryptoDTO newest = null;

        for (var record : repository.getRecordsBySymbol(cryptoSymbol)) {
            if (max == null) {
                max = mapper.cryptoRecordToDTO(record);
            }

            if (min == null) {
                min = mapper.cryptoRecordToDTO(record);
            }

            if (oldest == null) {
                oldest = mapper.cryptoRecordToDTO(record);
            }

            if (newest == null) {
                newest = mapper.cryptoRecordToDTO(record);
            }

            if (max.getPrice() < record.price()) {
                max = mapper.cryptoRecordToDTO(record);
            }

            if (min.getPrice() > record.price()) {
                min = mapper.cryptoRecordToDTO(record);
            }

            if (oldest.getTimestamp() < record.timestamp().getTime()) {
                oldest = mapper.cryptoRecordToDTO(record);
            }

            if (newest.getTimestamp() > record.timestamp().getTime()) {
                newest = mapper.cryptoRecordToDTO(record);
            }

        }
        return CryptoDataDTO.builder()
                .max(max)
                .min(min)
                .newest(newest)
                .oldest(oldest)
                .build();
    }

    public List<NormalizedCryptoDTO> getNormalizedCryptoDataDesc() {

        List<NormalizedCryptoDTO> result = new ArrayList<>();

        Set<String> keys = repository.getAllRecordsGroupedBySymbol().keySet();
        keys.forEach(
                key -> {
                    var normalized = calculateNormalizedValue(repository.getRecordsBySymbol(key));

                    result.add(NormalizedCryptoDTO.builder()
                            .normalizedValue(normalized)
                            .symbol(key)
                            .build());
                }
        );

        result.sort(Comparator.comparingDouble(NormalizedCryptoDTO::getNormalizedValue).reversed());

        return result;
    }

    public NormalizedCryptoDTO getHighestNormalizedRangeByDay(long day) {
        return null; //TODO
    }

    private double calculateNormalizedValue(List<CryptoRecord> records) {
        double min = 0;
        double max = 0;

        for (CryptoRecord record: records) {
            if (min > record.price()) {
                min = record.price();
            }
            if (max < record.price()) {
                max = record.price();
            }
        }

        return (max - min)/min;
    }
}
