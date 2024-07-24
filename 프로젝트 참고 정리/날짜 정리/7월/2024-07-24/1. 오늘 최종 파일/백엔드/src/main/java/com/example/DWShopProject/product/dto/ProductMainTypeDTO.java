package com.example.DWShopProject.product.dto;

import com.example.DWShopProject.product.entity.ProductMainType;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductMainTypeDTO {
    private Long id;
    private String typeName;
    private List<ProductSubTypeDTO> productSubTypes;

    public ProductMainTypeDTO(ProductMainType productMainType) {
        this.id = productMainType.getId();
        this.typeName = productMainType.getTypeName();
        this.productSubTypes = productMainType.getProductSubTypes().stream()
                .map(ProductSubTypeDTO::new)
                .collect(Collectors.toList());
    }
}
