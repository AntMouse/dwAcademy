package com.example.DWShopProject.product.converter;

import com.example.DWShopProject.product.dto.ProductMainTypeDTO;
import com.example.DWShopProject.product.dto.ProductSubTypeDTO;
import com.example.DWShopProject.product.entity.ProductMainType;
import com.example.DWShopProject.product.entity.ProductSubType;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductTypeDTOConverter {

    public ProductSubTypeDTO convertToProductSubTypeDto(ProductSubType productSubType) {
        return ProductSubTypeDTO.builder()
                .typeName(productSubType.getTypeName())
                .productMainTypeId(productSubType.getProductMainType().getId())
                .build();
    }

    public ProductMainTypeDTO convertToProductMainTypeDto(ProductMainType productMainType) {
        return ProductMainTypeDTO.builder()
                .typeName(productMainType.getTypeName())
                .productSubTypes(productMainType.getProductSubTypes().stream()
                        .map(this::convertToProductSubTypeDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public ProductSubType convertToProductSubTypeEntity(ProductSubTypeDTO productSubTypeDTO, ProductMainType productMainType) {
        return ProductSubType.builder()
                .typeName(productSubTypeDTO.getTypeName())
                .productMainType(productMainType)
                .build();
    }

    public ProductMainType convertToProductMainTypeEntity(ProductMainTypeDTO productMainTypeDTO) {
        return ProductMainType.builder()
                .typeName(productMainTypeDTO.getTypeName())
                .build();
    }
}
