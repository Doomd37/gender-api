package com.myproject.gender_api.controller;

import com.myproject.gender_api.dtos.ApiResponse;
import com.myproject.gender_api.dtos.ClassifyResponse;
import com.myproject.gender_api.service.GenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ClassifyController {

    private final GenderService genderService;

    public ClassifyController(GenderService genderService) {
        this.genderService = genderService;
    }

    @GetMapping("/classify")
    public ResponseEntity<?> classify(@RequestParam(required = false) String name) {

        // 400
        if (name == null) {
            return ResponseEntity.status(400).body(
                    Map.of(
                            "status", "error",
                            "message", "Missing or empty name parameter"
                    )
            );
        }

        // Trim input
        name = name.trim();

        // Check empty AFTER trimming
        if (name.isEmpty()) {
            return ResponseEntity.status(400).body(
                    Map.of(
                            "status", "error",
                            "message", "Missing or empty name parameter"
                    )
            );
        }

        try {
            ClassifyResponse result = genderService.classifyName(name.trim());

            // No prediction
            if (result == null) {
                return ResponseEntity.ok(
                        Map.of(
                                "status", "error",
                                "message", "No prediction available for the provided name"
                        )
                );
            }

            // SUCCESS
            return ResponseEntity.ok(
                    new ApiResponse<>("success", result)
            );

        } catch (RuntimeException e) {

            // 502 upstream failure
            return ResponseEntity.status(502).body(
                    Map.of(
                            "status", "error",
                            "message", "Upstream or server failure"
                    )
            );
        }
    }
}