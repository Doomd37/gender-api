package com.myproject.gender_api.controller;

import com.myproject.gender_api.dtos.GenderizeResponse;
import com.myproject.gender_api.dtos.CustomResponse;
import com.myproject.gender_api.service.GenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.myproject.gender_api.util.AppConstant.classifyEndpoint;
import static com.myproject.gender_api.util.AppConstant.controllerMapping;

@RestController
@RequestMapping(controllerMapping)
@RequiredArgsConstructor
public class ClassifyController {

    private final GenderService service;

    @GetMapping(classifyEndpoint)
    public ResponseEntity<?> fetchGenderizer(@RequestParam(required = false) String name) {

        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.status(400).body(
                    GenderizeResponse.builder()
                            .status("error")
                            .message("Missing or empty name parameter")
                            .build()
            );
        } else if (!name.matches("[a-zA-Z]+")) {
            return ResponseEntity.status(422).body(
                    GenderizeResponse.builder()
                            .status("error")
                            .message("Name must be a string")
                            .build()
            );
        }

        return service.classifyName(name);
    }

}