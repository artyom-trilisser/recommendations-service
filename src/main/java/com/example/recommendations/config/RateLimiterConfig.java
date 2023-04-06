package com.example.recommendations.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@RequiredArgsConstructor
@Configuration
public class RateLimiterConfig {

    @Bean
    public Bucket getRateLimiterBean() { //TODO inject the hardcoded values from application.yml,
                                         // consider about feature flag
        Bandwidth limit = Bandwidth.classic(20, Refill.greedy(20, Duration.ofMinutes(1)));
        return Bucket.builder()
                .addLimit(limit)
                .build();
    }
}
