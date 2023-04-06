package com.example.recommendations.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@Builder
@Data
public record CryptoRecord(double price, Timestamp timestamp) {
}
