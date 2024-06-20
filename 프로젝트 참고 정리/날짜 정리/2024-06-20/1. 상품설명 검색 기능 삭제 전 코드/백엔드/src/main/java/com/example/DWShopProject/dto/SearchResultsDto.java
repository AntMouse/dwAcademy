package com.example.DWShopProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SearchResultsDto {
    private List<SearchResultDto> results;
    private int totalResults;
}
