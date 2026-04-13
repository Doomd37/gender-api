package com.myproject.gender_api.service;

import com.myproject.gender_api.customException.ExternalServiceException;
import com.myproject.gender_api.customException.NoPredictionException;
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

    public ApiResponse<ClassifyResponse> classifyName(String name) {

        String url = "https://api.genderize.io?name=" + name;

        GenderizeResponse response;

        try {
            response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(GenderizeResponse.class)
                    .block();

        } catch (Exception e) {
            throw new ExternalServiceException("Upstream service failure");
        }

        if (response == null || response.getGender() == null || response.getCount() == 0) {
            throw new NoPredictionException("No prediction available for the provided name");
        }

        double probability = response.getProbability();
        int sampleSize = response.getCount();

        boolean isConfident = probability >= 0.7 && sampleSize >= 100;

        ClassifyResponse data = new ClassifyResponse(
                name,
                response.getGender(),
                probability,
                sampleSize,
                isConfident,
                Instant.now().toString()
        );

        return new ApiResponse<>("success", data);
    }
}