package com.example.DWShopProject.service;

import com.example.DWShopProject.entity.ProductTypeMgmt;
import com.example.DWShopProject.enums.ParentTypeEnum;
import com.example.DWShopProject.enums.ProductTypeEnum;
import com.example.DWShopProject.exception.ResourceNotFoundException;
import com.example.DWShopProject.repository.ProductTypeMgmtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductTypeService {
    private final ProductTypeMgmtRepository productTypeMgmtRepository;

    // 모든 상품 타입 가져오기
    public List<ProductTypeMgmt> getAllProductTypes() {
        return productTypeMgmtRepository.findAll();
    }

    // 특정 상품 타입 가져오기
    public ProductTypeMgmt getProductType(ProductTypeEnum productType) {
        return productTypeMgmtRepository.findByProductType(productType)
                .orElseThrow(() -> new ResourceNotFoundException("상품 타입을 찾을 수 없습니다."));
    }

    // 부모 타입으로 상품 타입들 가져오기
    public List<ProductTypeMgmt> getProductTypesByParent(ParentTypeEnum parentType) {
        return productTypeMgmtRepository.findByParentType(parentType);
    }

    // 서브 타입으로 메인 타입 가져오기
    public ParentTypeEnum getMainTypeBySubType(ProductTypeEnum productType) {
        return getProductType(productType).getParentType();
    }

    // 모든 메인 타입 가져오기
    public List<ParentTypeEnum> getAllMainTypes() {
        return Stream.of(ParentTypeEnum.values()).collect(Collectors.toList());
    }

    // 메인 타입에 속한 서브 타입들 가져오기
    public List<ProductTypeEnum> getSubTypesByMainType(ParentTypeEnum parentType) {
        return productTypeMgmtRepository.findByParentType(parentType).stream()
                .map(ProductTypeMgmt::getProductType)
                .collect(Collectors.toList());
    }
}