// src/main/java/com/example/DWShopProject/controller/SearchController.java
package com.example.DWShopProject.controller;

import com.example.DWShopProject.dto.SearchDto;
import com.example.DWShopProject.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
//    @Autowired
//    private SearchService searchService;
//
//    @GetMapping("/api/search")
//    public List<SearchDto> searchProducts(@RequestParam("keyword") String keyword, @RequestParam("type") String type) {
//        if ("all".equals(type)) {
//            return searchService.searchProductsAll(keyword);
//        } else if ("category".equals(type)) {
//            return searchService.searchProductsByCategory(keyword);
//        } else {
//            return searchService.searchProductsByName(keyword);
//        }
//    }
}
