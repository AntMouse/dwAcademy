package com.example.DWShopProject.product.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSubTypeDTO {
    private String typeName;
    private Long productMainTypeId;
}
