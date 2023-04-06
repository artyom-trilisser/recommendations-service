package com.example.recommendations.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties("application")
public class AppProps {
    private List<String> currencies;

    private String currenciesDirPath;

    private String currenciesFileTemplate;
}
