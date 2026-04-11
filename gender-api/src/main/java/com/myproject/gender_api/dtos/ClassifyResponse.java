package com.myproject.gender_api.dtos;

public class ClassifyResponse {

    private String name;
    private String gender;
    private double probability;
    private int sampleSize;
    private boolean isConfident;
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
    public boolean isConfident() { return isConfident; }
    public String getProcessedAt() { return processedAt; }
}
