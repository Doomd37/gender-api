package com.myproject.gender_api.service;


import com.myproject.gender_api.dtos.ApiResponse;
import com.myproject.gender_api.dtos.ClassifyResponse;
import com.myproject.gender_api.dtos.GenderizeResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;


@Service
public class GenderService {

    private final WebClient webClient;

    public GenderService(WebClient webClient) {
        this.webClient = webClient;
    }

    public ClassifyResponse classifyName(String name) {

        try {
            GenderizeResponse response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("https")
                            .host("api.genderize.io")
                            .queryParam("name", name)
                            .build())
                    .retrieve()
                    .bodyToMono(GenderizeResponse.class)
                    .block();

            // EDGE CASE: no prediction
            if (response == null ||
                    response.getGender() == null ||
                    response.getCount() == 0) {
                return null;
            }

            boolean isConfident =
                    response.getProbability() >= 0.7 &&
                            response.getCount() >= 100;

            return new ClassifyResponse(
                    name,
                    response.getGender(),
                    response.getProbability(),
                    response.getCount(),
                    isConfident,
                    Instant.now().toString()
            );

        } catch (Exception e) {
            // upstream failure signal
            throw new RuntimeException("UPSTREAM_ERROR");
        }
    }
}