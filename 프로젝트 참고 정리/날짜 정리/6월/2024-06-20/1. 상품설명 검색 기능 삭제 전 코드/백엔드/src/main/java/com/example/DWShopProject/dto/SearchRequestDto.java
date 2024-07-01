package com.example.DWShopProject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {
    private String searchValue;
    private String searchFilter;
}
