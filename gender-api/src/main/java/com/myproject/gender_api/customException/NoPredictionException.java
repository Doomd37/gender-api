package com.myproject.gender_api.customException;

public class NoPredictionException extends RuntimeException {
    public NoPredictionException(String message) {
        super(message);
    }
}
