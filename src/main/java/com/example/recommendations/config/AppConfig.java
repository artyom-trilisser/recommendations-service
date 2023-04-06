package com.example.recommendations.config;

import com.example.recommendations.config.properties.AppProps;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@RequiredArgsConstructor
@Configuration
//@EnableConfigurationProperties(AppProps.class)
public class AppConfig {

    private final AppProps properties;

    @Bean(name = "Currencies")
    public List<String> getCryptoCurrenciesNames() {
        return properties.getCurrencies();
    }

    @Bean(name = "CurrencyFileTemplate")
    public String getFileTemplate() {
        return properties.getCurrenciesFileTemplate();
    }

    @Bean(name = "CurrencyDirPath")
    public String getDirPath() {
        return properties.getCurrenciesDirPath();
    }

}
