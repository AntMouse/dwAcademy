package com.example.DWShopProject.product.entity;

import com.example.DWShopProject.product.enums.ParentType;
import com.example.DWShopProject.product.enums.ProductType;
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
    private ProductType productType;

    @Enumerated(EnumType.STRING)
    private ParentType parentType;

    public ProductTypeMgmt(ProductType productType, ParentType parentType) {
        this.productType = productType;
        this.parentType = parentType;
    }
}
