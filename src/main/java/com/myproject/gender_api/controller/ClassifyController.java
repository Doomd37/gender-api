package com.myproject.gender_api.controller;

import com.myproject.gender_api.customException.BadRequestException;
import com.myproject.gender_api.dtos.ApiResponse;
import com.myproject.gender_api.dtos.ClassifyResponse;
import com.myproject.gender_api.service.GenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ClassifyController {

    private final GenderService genderService;

    public ClassifyController(GenderService genderService) {
        this.genderService = genderService;
    }

    @GetMapping("/classify")
    public ResponseEntity<ApiResponse<ClassifyResponse>> classify(
            @RequestParam(required = false) String name) {

        // Validate input
        if (name == null || name.trim().isEmpty()) {
            throw new BadRequestException("Missing or empty name parameter");
        }

        // Call service (business logic handled there)
        ApiResponse<ClassifyResponse> response =
                genderService.classifyName(name.trim());

        return ResponseEntity.ok(response);
    }
}