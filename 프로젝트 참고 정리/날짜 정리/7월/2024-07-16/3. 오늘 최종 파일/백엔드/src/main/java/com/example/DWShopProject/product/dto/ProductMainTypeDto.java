package com.example.DWShopProject.product.dto;

import com.example.DWShopProject.product.entity.ProductMainType;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductMainTypeDto {
    private Long id;
    private String typeName;
    private List<ProductSubTypeDto> productSubTypes;

    public ProductMainTypeDto(ProductMainType productMainType) {
        this.id = productMainType.getId();
        this.typeName = productMainType.getTypeName();
        this.productSubTypes = productMainType.getProductSubTypes().stream()
                .map(ProductSubTypeDto::new)
                .collect(Collectors.toList());
    }
}
