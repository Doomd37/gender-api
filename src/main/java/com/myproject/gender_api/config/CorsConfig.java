package com.myproject.gender_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.WebFilter;

@Configuration
public class CorsConfig {

    @Bean
    public WebFilter corsFilter() {
        return (exchange, chain) -> {

            exchange.getResponse().getHeaders()
                    .add("Access-Control-Allow-Origin", "*");

            exchange.getResponse().getHeaders()
                    .add("Access-Control-Allow-Methods", "*");

            exchange.getResponse().getHeaders()
                    .add("Access-Control-Allow-Headers", "*");

            return chain.filter(exchange);
        };
    }
}