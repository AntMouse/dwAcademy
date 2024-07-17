package com.example.DWShopProject.productmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product_main_types")
public class ProductMainType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String typeName;

    @OneToMany(mappedBy = "productMainType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductSubType> productSubTypes;
}
