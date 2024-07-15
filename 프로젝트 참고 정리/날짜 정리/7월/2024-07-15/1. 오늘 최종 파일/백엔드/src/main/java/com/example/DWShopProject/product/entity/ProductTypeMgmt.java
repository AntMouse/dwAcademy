package com.example.DWShopProject.product.entity;

import com.example.DWShopProject.product.enums.ProductMainType;
import com.example.DWShopProject.product.enums.ProductSubType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductTypeMgmt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductSubType productType;

    @Enumerated(EnumType.STRING)
    private ProductMainType parentType;

    public ProductTypeMgmt(ProductSubType productType, ProductMainType parentType) {
        this.productType = productType;
        this.parentType = parentType;
    }
}
