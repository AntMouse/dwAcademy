package com.example.DWShopProject.product.repository;

import com.example.DWShopProject.product.entity.ProductTypeMgmt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductTypeMgmtRepository extends JpaRepository<ProductTypeMgmt, Long> {
    Optional<ProductTypeMgmt> findByProductType(ProductSubType productType);
    List<ProductTypeMgmt> findByParentType(ProductMainType parentType);
    // 새로운 메서드 추가
    List<ProductTypeMgmt> findByProductTypeIn(List<ProductSubType> productTypes);
}
