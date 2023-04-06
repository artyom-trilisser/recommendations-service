package com.example.recommendations.entity;

import lombok.Builder;

import java.sql.Timestamp;

@Builder
public record CryptoRecord(double price, Timestamp timestamp) {
}
