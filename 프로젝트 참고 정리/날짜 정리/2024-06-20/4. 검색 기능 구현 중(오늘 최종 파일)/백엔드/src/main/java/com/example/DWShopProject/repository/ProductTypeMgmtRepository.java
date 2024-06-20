// src/main/java/com/example/DWShopProject/repository/ProductTypeMgmtRepository.java
package com.example.DWShopProject.repository;

import com.example.DWShopProject.entity.ProductTypeMgmt;
import com.example.DWShopProject.enums.ParentTypeEnum;
import com.example.DWShopProject.enums.ProductTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductTypeMgmtRepository extends JpaRepository<ProductTypeMgmt, Long> {
    List<ProductTypeMgmt> findByParentType(ParentTypeEnum parentType);
    Optional<ProductTypeMgmt> findByProductType(ProductTypeEnum productType);
}
