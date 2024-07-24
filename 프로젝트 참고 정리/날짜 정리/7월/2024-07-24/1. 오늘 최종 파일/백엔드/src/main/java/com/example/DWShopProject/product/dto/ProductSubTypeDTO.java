package com.example.DWShopProject.product.dto;

import com.example.DWShopProject.product.entity.ProductSubType;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSubTypeDTO {
    private Long id;
    private String typeName;
    private Long productMainTypeId;

    public ProductSubTypeDTO(ProductSubType productSubType) {
        this.id = productSubType.getId();
        this.typeName = productSubType.getTypeName();
        this.productMainTypeId = productSubType.getProductMainType().getId();
    }
}
