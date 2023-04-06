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
@ApiModel(description = "Normalized Value for the Crypto")
public class NormalizedCryptoDTO {
    private double normalizedValue;
    private String symbol;
}
