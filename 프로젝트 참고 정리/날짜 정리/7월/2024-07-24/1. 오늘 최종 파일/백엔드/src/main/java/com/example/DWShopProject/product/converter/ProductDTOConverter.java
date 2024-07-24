package com.example.DWShopProject.product.converter;

import com.example.DWShopProject.product.dto.ProductDTO;
import com.example.DWShopProject.product.entity.Product;
import com.example.DWShopProject.product.entity.ProductSubType;
import org.springframework.stereotype.Component;

@Component
public class ProductDTOConverter {

    public ProductDTO convertToDTO(Product product) {
        return new ProductDTO(product);
    }

    public Product convertToEntity(ProductDTO productDTO, ProductSubType productSubType) {
        return Product.builder()
                .id(productDTO.getId())
                .productSubType(productSubType)
                .productName(productDTO.getProductName())
                .price(productDTO.getPrice())
                .explanation(productDTO.getExplanation())
                .imageUrl(productDTO.getImageUrl())
                .createDate(productDTO.getCreateDate())
                .build();
    }
}
