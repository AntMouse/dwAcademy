package com.example.DWShopProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SearchResultsDto {
    private List<SearchResultDto> results; // 검색 결과 목록
    private int totalResults; // 총 검색 결과 수
}
