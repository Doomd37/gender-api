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
            @RequestParam String name) {

        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>("error", null));
        }

        return ResponseEntity.ok(
                genderService.classifyName(name.trim())
        );
    }
}