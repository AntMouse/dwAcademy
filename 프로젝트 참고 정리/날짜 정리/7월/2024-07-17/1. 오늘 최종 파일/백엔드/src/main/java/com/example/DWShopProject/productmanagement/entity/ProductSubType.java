package com.example.DWShopProject.productmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product_sub_types")
public class ProductSubType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String typeName;

    @ManyToOne
    @JoinColumn(name = "product_main_type_id", nullable = false)
    private ProductMainType productMainType;
}
