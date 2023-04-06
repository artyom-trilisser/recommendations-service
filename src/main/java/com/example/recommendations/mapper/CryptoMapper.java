package com.example.recommendations.mapper;

import com.example.recommendations.dto.CryptoDTO;
import com.example.recommendations.entity.CryptoRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;

@Mapper(componentModel = "spring")
public interface CryptoMapper {
    @Mapping(source = "timestamp", target = "timestamp", qualifiedByName = "timestampToLong")
    CryptoDTO cryptoRecordToDTO(CryptoRecord record);

    @Named("timestampToLong")
    static long timestampToLong(Timestamp timestamp) {
        return timestamp.getTime();
    }
}
