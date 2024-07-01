package com.example.DWShopProject.service;

import com.example.DWShopProject.entity.ProductTypeMgmt;
import com.example.DWShopProject.enums.ParentTypeEnum;
import com.example.DWShopProject.enums.ProductTypeEnum;
import com.example.DWShopProject.repository.ProductTypeMgmtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductTypeService {
    private final ProductTypeMgmtRepository productTypeMgmtRepository;

    public List<ProductTypeMgmt> getAllProductTypes() {
        return productTypeMgmtRepository.findAll();
    }

    public Optional<ProductTypeMgmt> getProductType(ProductTypeEnum productType) {
        return productTypeMgmtRepository.findByProductType(productType);
    }

    public List<ProductTypeMgmt> getProductTypesByParent(ParentTypeEnum parentType) {
        return productTypeMgmtRepository.findByParentType(parentType);
    }

    // ParentTypeEnum 값에 따라 ProductTypeEnum 값을 반환하는 서비스 메서드
    public List<String> getProductSubTypes(ParentTypeEnum parentType) {
        // 메인 타입이 null이면 모든 서브 타입을 반환
        if (parentType == null) {
            return Arrays.stream(ProductTypeEnum.values())
                    .map(ProductTypeEnum::name)
                    .collect(Collectors.toList());
        }

        // 메인 타입이 있으면 해당 메인 타입에 속하는 서브 타입만 반환
        return Arrays.stream(ProductTypeEnum.values())
                .filter(productTypeEnum -> productTypeEnum.getParentTypeEnum().equals(parentType))
                .map(ProductTypeEnum::name)
                .collect(Collectors.toList());
    }
}
