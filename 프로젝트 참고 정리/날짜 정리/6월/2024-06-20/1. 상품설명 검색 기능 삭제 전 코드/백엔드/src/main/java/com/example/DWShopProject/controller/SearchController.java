package com.example.DWShopProject.controller;

import com.example.DWShopProject.dto.SearchRequestDto;
import com.example.DWShopProject.dto.SearchResultsDto;
import com.example.DWShopProject.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("/products")
    public ResponseEntity<SearchResultsDto> searchProducts(@RequestBody SearchRequestDto searchRequestDto) {
        SearchResultsDto searchResults = searchService.searchProducts(searchRequestDto);
        return ResponseEntity.ok(searchResults);
    }
}
