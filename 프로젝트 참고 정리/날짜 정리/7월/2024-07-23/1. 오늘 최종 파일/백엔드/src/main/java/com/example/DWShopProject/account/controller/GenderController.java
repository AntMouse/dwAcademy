package com.example.DWShopProject.account.controller;

import com.example.DWShopProject.account.dto.GenderDTO;
import com.example.DWShopProject.account.enums.Gender;
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
    public ResponseEntity<List<GenderDTO>> getGenders() {
        List<GenderDTO> genders = Arrays.stream(Gender.values())
                .map(gender -> new GenderDTO(gender.name(), gender.getDisplayName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(genders);
    }
}

