package com.example.DWShopProject.dao;

import com.example.DWShopProject.enums.ParentTypeEnum;
import com.example.DWShopProject.enums.ProductTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesRequestDto {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Long> productIds;
    private List<ProductTypeEnum> productSubTypes; // 서브 타입 정보 포함
    private List<ParentTypeEnum> productMainTypes; // 메인 타입 정보 포함
    private String sortType;
}
