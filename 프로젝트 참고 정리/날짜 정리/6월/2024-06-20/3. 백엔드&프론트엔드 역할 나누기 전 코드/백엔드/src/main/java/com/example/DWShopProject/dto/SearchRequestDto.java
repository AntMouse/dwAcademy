package com.example.DWShopProject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {
    private String searchValue; // 검색 값
    private String searchFilter; // 검색 필터 (productName, category, all 등)
}
