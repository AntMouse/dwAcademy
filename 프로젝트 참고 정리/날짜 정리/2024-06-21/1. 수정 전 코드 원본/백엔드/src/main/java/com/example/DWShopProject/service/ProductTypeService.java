package com.example.DWShopProject.service;

import com.example.DWShopProject.entity.ProductTypeMgmt;
import com.example.DWShopProject.enums.ParentTypeEnum;
import com.example.DWShopProject.enums.ProductTypeEnum;
import com.example.DWShopProject.repository.ProductTypeMgmtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductTypeService {
    private final ProductTypeMgmtRepository productTypeMgmtRepository;

    // 모든 상품 타입 가져오기
    public List<ProductTypeMgmt> getAllProductTypes() {
        return productTypeMgmtRepository.findAll();
    }

    // 특정 상품 타입 가져오기
    public Optional<ProductTypeMgmt> getProductType(ProductTypeEnum productType) {
        return productTypeMgmtRepository.findByProductType(productType);
    }

    // 부모 타입으로 상품 타입들 가져오기
    public List<ProductTypeMgmt> getProductTypesByParent(ParentTypeEnum parentType) {
        return productTypeMgmtRepository.findByParentType(parentType);
    }
}
