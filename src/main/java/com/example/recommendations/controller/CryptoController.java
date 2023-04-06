package com.example.recommendations.controller;

import com.example.recommendations.dto.CryptoDataDTO;
import com.example.recommendations.dto.NormalizedCryptoDTO;
import com.example.recommendations.service.CryptoService;
import io.github.bucket4j.Bucket;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/cryptos", produces = MediaType.APPLICATION_JSON_VALUE)
public class CryptoController {
    private final CryptoService service;
    private final Bucket bucket; //Rate limiter bucket


    @ApiOperation(
            value = "Get a descending sorted list of all the cryptos,\n" +
                    "comparing the normalized range (i.e. (max-min)/min)",
            notes = "If nothing is found error is returned <br/> " +
                    "<b> Idempotent operation </b> <br/> ",
            tags = "Crypto")
    @GetMapping("/normalizedvalues")
    public List<NormalizedCryptoDTO> getNormalizedRanges() {
        return service.getNormalizedCryptoDataDesc();
    }

    @ApiOperation(
            value = "Get the crypto with the highest normalized range for a\n" +
                    "specific day",
            notes = "If nothing is found error is returned <br/> " +
                    "<b> Idempotent operation </b> <br/> ",
            tags = "Crypto")
    @GetMapping("/normalizedvalues/{day}")
    public NormalizedCryptoDTO getHighestNormalizedRangeByDay(long day) {
        return service.getHighestNormalizedRangeByDay(day);
    }

    @ApiOperation(
            value = "Get max, min, oldest and newest values for a requested crypto",
            notes = "If nothing is found error is returned <br/> " +
                    "<b> Idempotent operation </b> <br/> ",
            tags = "Crypto")
    @GetMapping("analytics/{cryptoSymbol}")
    public CryptoDataDTO getDataByCrypto(@ApiParam(value = "Crypto symbol", required = true)
                                         @PathVariable("cryptoSymbol") String cryptoSymbol) {
        return service.getAnalyticDataByCrypto(cryptoSymbol);
    }

}
