package com.example.DWShopProject.product.repository;

import com.example.DWShopProject.product.entity.ProductSubType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductSubTypeRepository extends JpaRepository<ProductSubType, Long> {
    List<ProductSubType> findByProductMainTypeId(Long mainTypeId);
}
