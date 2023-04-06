package com.example.recommendations.bootstrap;


import com.example.recommendations.entity.CryptoRecord;
import com.example.recommendations.repository.CryptoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Component
@Slf4j
public class FilesCryptoLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final List<String> cryptoCurrenciesNames;
    private final String dirPath;
    private final String fileTemplate;
    private final CryptoRepository repository;

    public FilesCryptoLoader(@Qualifier("Currencies")List<String> cryptoCurrenciesNames,
                             @Qualifier("CurrencyDirPath") String dirPath,
                             @Qualifier("CurrencyFileTemplate") String fileTemplate,
                             CryptoRepository repository) {
        this.cryptoCurrenciesNames = cryptoCurrenciesNames;
        this.dirPath = dirPath;
        this.fileTemplate = fileTemplate;
        this.repository = repository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        cryptoCurrenciesNames.parallelStream().forEach(
                currencyName -> {
                    try (FileInputStream inputStream =
                                 new FileInputStream(dirPath + File.separator + String.format(fileTemplate, currencyName));) {
                        var scanner = new Scanner(inputStream, StandardCharsets.UTF_8);

                        //skipping 1st line which represents headers.
                        scanner.tokens().skip(1)
                                .map(
                                        s -> {
                                            String[] splitted = s.split(",");

                                            if (splitted.length == 3) {
                                                return CryptoRecord.builder()
                                                        .price(Double.parseDouble(splitted[2]))
                                                        .timestamp(new Timestamp(Long.parseLong(splitted[0])))
                                                        .build();

                                            } else return null;
                                        }
                                ).filter(Objects::nonNull)
                                .forEach(record -> repository.addRecord(currencyName, record));

                    } catch (FileNotFoundException ex) {
                        log.info("File for {} not found. Proceed with the next one", currencyName);
                    } catch (IOException e) {
                        log.info(e.getMessage());
                    }
                }
        );
    }
}
