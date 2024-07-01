package com.example.DWShopProject.controller;

import com.example.DWShopProject.dto.SearchRequestDto;
import com.example.DWShopProject.dto.SearchResponseDto;
import com.example.DWShopProject.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @PostMapping
    public ResponseEntity<List<SearchResponseDto>> searchProducts(@RequestBody SearchRequestDto searchRequestDto) {
        LocalDateTime startDate = searchService.calculateStartDate(searchRequestDto.getDateFilter());
        List<SearchResponseDto> products = searchService.searchProducts(startDate, searchRequestDto);
        return ResponseEntity.ok(products);
    }
}
