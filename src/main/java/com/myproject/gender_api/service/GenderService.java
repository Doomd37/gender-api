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

    public ApiResponse<ClassifyResponse> classifyName(String name) {

        GenderizeResponse response;

        try {
            response = webClient.get()
                    .uri("https://api.genderize.io?name=" + name)
                    .retrieve()
                    .bodyToMono(GenderizeResponse.class)
                    .block();

        } catch (Exception e) {
            return new ApiResponse<>("error", null);
        }

        // ONLY true failure case
        if (response == null) {
            return new ApiResponse<>("error", null);
        }

        String gender = response.getGender();
        double probability = response.getProbability();
        int sampleSize = response.getCount();

        // IMPORTANT: DO NOT THROW EXCEPTIONS HERE
        if (gender == null || sampleSize == 0) {
            return new ApiResponse<>("error", null);
        }

        boolean isConfident = probability >= 0.7 && sampleSize >= 100;

        ClassifyResponse data = new ClassifyResponse(
                name,
                gender,
                probability,
                sampleSize,
                isConfident,
                Instant.now().toString()
        );

        return new ApiResponse<>("success", data);
    }
}