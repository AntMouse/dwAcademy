package com.example.DWShopProject.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class GenderDTO {
    private String genderCode;
    private String displayName;
}