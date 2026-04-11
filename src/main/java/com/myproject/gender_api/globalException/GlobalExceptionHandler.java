package com.myproject.gender_api.globalException;

import com.myproject.gender_api.customException.BadRequestException;
import com.myproject.gender_api.customException.ExternalServiceException;
import com.myproject.gender_api.customException.NoPredictionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequest(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error(ex.getMessage()));
    }

    @ExceptionHandler(NoPredictionException.class)
    public ResponseEntity<?> unprocessable(NoPredictionException ex) {
        return ResponseEntity.status(HttpStatus.valueOf(422))
                .body(error(ex.getMessage()));
    }

    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<?> externalError(ExternalServiceException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(error(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> serverError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error("Something went wrong"));
    }

    private Map<String, Object> error(String message) {
        return Map.of(
                "status", "error",
                "message", message
        );
    }
}