package com.github.loginapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

@Configuration
public class HttpClientConfiguration {
    @Bean
    public HttpClient httpClient() {
        return HttpClient.newBuilder()
                .connectTimeout(Duration.of(10, SECONDS))
                .build();
    }
}
