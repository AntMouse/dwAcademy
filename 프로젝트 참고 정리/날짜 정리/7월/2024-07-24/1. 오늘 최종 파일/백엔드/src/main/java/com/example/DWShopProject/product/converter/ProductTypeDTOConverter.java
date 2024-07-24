package com.example.DWShopProject.product.converter;

import com.example.DWShopProject.product.dto.ProductMainTypeDTO;
import com.example.DWShopProject.product.dto.ProductSubTypeDTO;
import com.example.DWShopProject.product.entity.ProductMainType;
import com.example.DWShopProject.product.entity.ProductSubType;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeDTOConverter {

    public ProductSubTypeDTO convertToDTO(ProductSubType productSubType) {
        return new ProductSubTypeDTO(productSubType);
    }

    public ProductMainTypeDTO convertToDTO(ProductMainType productMainType) {
        return new ProductMainTypeDTO(productMainType);
    }

    public ProductSubType convertToEntity(ProductSubTypeDTO productSubTypeDTO, ProductMainType productMainType) {
        return ProductSubType.builder()
                .typeName(productSubTypeDTO.getTypeName())
                .productMainType(productMainType)
                .build();
    }

    public ProductMainType convertToEntity(ProductMainTypeDTO productMainTypeDTO) {
        return ProductMainType.builder()
                .typeName(productMainTypeDTO.getTypeName())
                .build();
    }
}
