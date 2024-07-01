package com.example.DWShopProject.controller;

import com.example.DWShopProject.enums.ProductTypeEnum;
import com.example.DWShopProject.exception.ResourceNotFoundException;
import com.example.DWShopProject.entity.ProductTypeMgmt;
import com.example.DWShopProject.enums.ParentTypeEnum;
import com.example.DWShopProject.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product-types")
@RequiredArgsConstructor
public class ProductTypeController {
    private final ProductTypeService productTypeService;

    @GetMapping
    public List<ProductTypeMgmt> getAllProductTypes() {
        return productTypeService.getAllProductTypes();
    }

    @GetMapping("/{productType}")
    public List<ProductTypeMgmt> getProductTypesByParent(@PathVariable String productType) {
        try {
            ParentTypeEnum parentTypeEnum = ParentTypeEnum.valueOf(productType.toUpperCase());
            return productTypeService.getProductTypesByParent(parentTypeEnum);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Invalid product type: " + productType);
        }
    }

    // 모든 ParentTypeEnum 값을 반환하는 엔드포인트
    @GetMapping("/product-parent-types")
    public List<String> getProductParentTypes() {
        return Arrays.stream(ParentTypeEnum.values())
                .map(ParentTypeEnum::name)
                .collect(Collectors.toList());
    }

    // 선택된 ParentTypeEnum 값에 따라 해당하는 ProductTypeEnum 값을 반환하는 엔드포인트
    @GetMapping("/product-sub-types")
    public List<String> getProductSubTypes(@RequestParam(required = false) ParentTypeEnum parentType) {
        return productTypeService.getProductSubTypes(parentType);
    }
}
