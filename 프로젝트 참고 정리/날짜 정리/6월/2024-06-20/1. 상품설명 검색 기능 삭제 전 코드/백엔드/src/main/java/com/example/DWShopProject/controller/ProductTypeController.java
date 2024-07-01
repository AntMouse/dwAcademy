package com.example.DWShopProject.controller;

import com.example.DWShopProject.dto.ProductTypeDto;
import com.example.DWShopProject.dto.ParentTypeDto;
import com.example.DWShopProject.enums.ProductTypeEnum;
import com.example.DWShopProject.exception.ResourceNotFoundException;
import com.example.DWShopProject.entity.ProductTypeMgmt;
import com.example.DWShopProject.enums.ParentTypeEnum;
import com.example.DWShopProject.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // ProductTypeEnum 정보를 제공하는 새로운 API 추가
    @GetMapping("/enums")
    public List<ProductTypeDto> getProductTypeEnums() {
        return Arrays.stream(ProductTypeEnum.values())
                .map(type -> new ProductTypeDto(type.name(), type.getDisplayName()))
                .collect(Collectors.toList());
    }

    // ParentTypeEnum 정보를 제공하는 새로운 API 추가
    @GetMapping("/parents")
    public List<ParentTypeDto> getParentTypeEnums() {
        return Arrays.stream(ParentTypeEnum.values())
                .map(type -> new ParentTypeDto(type.name(), type.getDisplayName()))
                .collect(Collectors.toList());
    }
}
