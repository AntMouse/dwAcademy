package com.example.DWShopProject.user.controller;

import com.example.DWShopProject.user.dto.GenderDto;
import com.example.DWShopProject.user.enums.Gender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/genders")
public class GenderController {

    @GetMapping
    public ResponseEntity<List<GenderDto>> getGenders() {
        List<GenderDto> genders = Arrays.stream(Gender.values())
                .map(gender -> new GenderDto(gender.name(), gender.getDisplayName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(genders);
    }
}

