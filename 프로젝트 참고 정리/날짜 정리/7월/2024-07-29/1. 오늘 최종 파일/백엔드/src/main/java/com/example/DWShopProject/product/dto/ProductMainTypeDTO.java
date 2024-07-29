package com.example.DWShopProject.product.dto;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductMainTypeDTO {
    private String typeName;
    private List<ProductSubTypeDTO> productSubTypes;
}
