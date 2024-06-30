package com.example.DWShopProject.dao;

import com.example.DWShopProject.enums.ParentTypeEnum;
import com.example.DWShopProject.enums.ProductTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleDto {
    private Long id;
    private LocalDateTime createDate;
    private Long productId;
    private int price;
    private int quantity;
    private ProductTypeEnum productSubType; // 서브 타입 정보 포함
    private ParentTypeEnum productMainType; // 메인 타입 정보 포함
}
