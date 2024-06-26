package com.example.DWShopProject.repository;

import com.example.DWShopProject.entity.ProductTypeMgmt;
import com.example.DWShopProject.enums.ProductTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductTypeMgmtRepository extends JpaRepository<ProductTypeMgmt, Long> {
    Optional<ProductTypeMgmt> findByProductType(ProductTypeEnum productType);
}
