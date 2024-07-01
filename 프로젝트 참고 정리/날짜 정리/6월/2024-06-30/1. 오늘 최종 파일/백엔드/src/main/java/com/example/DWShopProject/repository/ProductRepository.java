package com.example.DWShopProject.repository;

import com.example.DWShopProject.entity.Product;
import com.example.DWShopProject.enums.ParentTypeEnum;
import com.example.DWShopProject.enums.ProductTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductName(String productName);  // 상품 이름으로 검색하는 메서드 추가

    // 상품명을 포함한 검색 (메서드 쿼리 방식)
    List<Product> findByProductNameContaining(String keyword);

    // 설명을 포함한 검색 (메서드 쿼리 방식)
    List<Product> findByExplanationContaining(String keyword);

    // 서브 타입으로 검색
    List<Product> findByProductType(ProductTypeEnum productType);

    // 여러 서브 타입으로 검색
    List<Product> findByProductTypeIn(List<ProductTypeEnum> productTypes);
}
