package com.example.DWShopProject.controller;

import com.example.DWShopProject.entity.ProductTypeMgmt;
import com.example.DWShopProject.enums.ParentTypeEnum;
import com.example.DWShopProject.enums.ProductTypeEnum;
import com.example.DWShopProject.exception.ResourceNotFoundException;
import com.example.DWShopProject.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product-types")
@RequiredArgsConstructor
public class ProductTypeController {
    private final ProductTypeService productTypeService;

    // 모든 상품 타입을 조회.
    @GetMapping
    public List<ProductTypeMgmt> getAllProductTypes() {
        return productTypeService.getAllProductTypes();
    }

    // 메인 타입으로 서브 타입을 조회.
    @GetMapping("/{parentType}")
    public List<ProductTypeMgmt> getProductTypesByParent(@PathVariable String parentType) {
        try {
            ParentTypeEnum parentTypeEnum = ParentTypeEnum.valueOf(parentType.toUpperCase());
            return productTypeService.getProductTypesByParent(parentTypeEnum);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Invalid product type: " + parentType);
        }
    }

    // 모든 메인 타입을 조회.
    @GetMapping("/main-types")
    public List<ParentTypeEnum> getAllMainTypes() {
        return productTypeService.getAllMainTypes();
    }

    // 메인 타입으로 서브 타입을 조회.
    @GetMapping("/sub-types/{parentType}")
    public List<ProductTypeEnum> getSubTypesByMainType(@PathVariable String parentType) {
        try {
            ParentTypeEnum parentTypeEnum = ParentTypeEnum.valueOf(parentType.toUpperCase());
            return productTypeService.getSubTypesByMainType(parentTypeEnum);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Invalid parent type: " + parentType);
        }
    }
}