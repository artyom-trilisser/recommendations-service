package com.example.recommendations.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Min, max, oldest, newest data for particular crypto")
public class CryptoDataDTO {
    private String symbol;
    private CryptoDTO newest;
    private CryptoDTO oldest;
    private CryptoDTO min;
    private CryptoDTO max;
}
