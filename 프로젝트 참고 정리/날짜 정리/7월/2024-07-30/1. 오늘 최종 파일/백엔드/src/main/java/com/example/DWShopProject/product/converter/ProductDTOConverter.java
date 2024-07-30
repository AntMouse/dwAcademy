package com.example.DWShopProject.product.converter;

import com.example.DWShopProject.product.dto.ProductDTO;
import com.example.DWShopProject.product.entity.Product;
import com.example.DWShopProject.product.entity.ProductSubType;
import org.springframework.stereotype.Component;

@Component
public class ProductDTOConverter {

    public ProductDTO convertToProductDto(Product product) {
        return ProductDTO.builder()
                .productSubTypeId(product.getProductSubType().getId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .explanation(product.getExplanation())
                .imageUrl(product.getImageUrl())
                .createDate(product.getCreateDate())
                .build();
    }

    public Product convertToProductEntity(ProductDTO productDto, ProductSubType productSubType) {
        return Product.builder()
                .productSubType(productSubType)
                .productName(productDto.getProductName())
                .price(productDto.getPrice())
                .explanation(productDto.getExplanation())
                .imageUrl(productDto.getImageUrl())
                .createDate(productDto.getCreateDate())
                .build();
    }
}
