package com.myproject.gender_api.dtos;



public class GenderizeResponse {

    private String name;
    private String gender;
    private double probability;
    private int count;

    public String getName() { return name; }
    public String getGender() { return gender; }
    public double getProbability() { return probability; }
    public int getCount() { return count; }

    public void setName(String name) { this.name = name; }
    public void setGender(String gender) { this.gender = gender; }
    public void setProbability(double probability) { this.probability = probability; }
    public void setCount(int count) { this.count = count; }
}