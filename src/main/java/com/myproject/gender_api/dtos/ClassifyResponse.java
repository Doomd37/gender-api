package com.myproject.gender_api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClassifyResponse {

    private String name;
    private String gender;
    private double probability;

    @JsonProperty("sample_size")
    private int sampleSize;

    @JsonProperty("is_confident")
    private boolean isConfident;

    @JsonProperty("processed_at")
    private String processedAt;

    public ClassifyResponse(String name, String gender, double probability,
                            int sampleSize, boolean isConfident, String processedAt) {
        this.name = name;
        this.gender = gender;
        this.probability = probability;
        this.sampleSize = sampleSize;
        this.isConfident = isConfident;
        this.processedAt = processedAt;
    }

    public String getName() { return name; }
    public String getGender() { return gender; }
    public double getProbability() { return probability; }
    public int getSampleSize() { return sampleSize; }
    @JsonProperty("is_confident")
    public boolean getIsConfident() {
        return isConfident;
    }
    public String getProcessedAt() { return processedAt; }
}