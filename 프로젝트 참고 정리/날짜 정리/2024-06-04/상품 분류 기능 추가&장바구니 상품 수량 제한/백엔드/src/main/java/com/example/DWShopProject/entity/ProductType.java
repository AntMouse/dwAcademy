package com.example.DWShopProject.entity;

import com.example.DWShopProject.enums.MajorTypeEnum;
import com.example.DWShopProject.enums.ProductTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductTypeEnum typeName;

    @Enumerated(EnumType.STRING)
    private MajorTypeEnum parentTypeName;

    public ProductType(ProductTypeEnum typeName, MajorTypeEnum parentTypeName) {
        this.typeName = typeName;
        this.parentTypeName = parentTypeName;
    }
}

