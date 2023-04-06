package com.example.recommendations.mapper;

import com.example.recommendations.dto.CryptoDTO;
import com.example.recommendations.entity.CryptoRecord;
import org.mapstruct.Mapper;

@Mapper
public interface CryptoMapper {
    public CryptoDTO cryptoRecordToDTO(CryptoRecord record);
}
