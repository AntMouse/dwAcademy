package com.example.DWShopProject.controller;

import com.example.DWShopProject.entity.ProductTypeMgmt;
import com.example.DWShopProject.enums.ProductTypeEnum;
import com.example.DWShopProject.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

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
    public RedirectView redirectToProductType(@PathVariable ProductTypeEnum productType) {
        Optional<ProductTypeMgmt> productTypeMgmt = productTypeService.getProductType(productType);
        if (productTypeMgmt.isPresent()) {
            // 리다이렉션할 URL 설정
            String redirectUrl = "/api/product-details/" + productType;
            return new RedirectView(redirectUrl);
        } else {
            // 상품 타입이 없는 경우 기본 URL로 리다이렉션
            return new RedirectView("/api/product-types");
        }
    }
}
