package com.example.DWShopProject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequestDto {
    private String dateFilter; // "1day", "1week", "1month", "6months", "1year", "all" 중 하나
    private String searchType; // 검색 유형 ("productName", "productDescription", "productNameAndDescription", "category")
    private String keyword;    // 검색어
    private String category;   // 카테고리 (검색 유형이 "category"일 때 사용)
}
