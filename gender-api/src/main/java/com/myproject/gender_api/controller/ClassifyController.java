package com.myproject.gender_api.controller;

import com.myproject.gender_api.service.GenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClassifyController {

    private final GenderService genderService;

    public ClassifyController(GenderService genderService) {
        this.genderService = genderService;
    }

    @GetMapping("/classify")
    public ResponseEntity<?> classify(@RequestParam(required = false) String name) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }

        return ResponseEntity.ok(genderService.classifyName(name));
    }
}