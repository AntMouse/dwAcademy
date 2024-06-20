package com.example.DWShopProject.specification;

import com.example.DWShopProject.entity.Product;
import com.example.DWShopProject.enums.ProductTypeEnum;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductSpecification {

    // 상품명을 통해 검색 조건을 생성하는 메서드
    public static Specification<Product> hasProductName(String productName) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("productName")), "%" + productName.toLowerCase() + "%");
    }

    // 상품 설명을 통해 검색 조건을 생성하는 메서드
    public static Specification<Product> hasProductDescription(String productDescription) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("explanation")), "%" + productDescription.toLowerCase() + "%");
    }

    // 상품명이나 설명을 통해 검색 조건을 생성하는 메서드
    public static Specification<Product> hasProductNameOrDescription(String value) {
        return (root, query, builder) -> builder.or(
                builder.like(builder.lower(root.get("productName")), "%" + value.toLowerCase() + "%"),
                builder.like(builder.lower(root.get("explanation")), "%" + value.toLowerCase() + "%")
        );
    }

    // 카테고리 필터를 처리하는 메서드
    public static Specification<Product> hasCategory(List<ProductTypeEnum> categories) {
        return (root, query, builder) -> root.get("productType").in(categories);
    }
}