package com.example.DWShopProject.productmanagement.dto;

import com.example.DWShopProject.productmanagement.entity.ProductSubType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSubTypeDto {
    private Long id;
    private String typeName;
    private Long productMainTypeId;

    public ProductSubTypeDto(ProductSubType productSubType) {
        this.id = productSubType.getId();
        this.typeName = productSubType.getTypeName();
        this.productMainTypeId = productSubType.getProductMainType().getId();
    }
}
