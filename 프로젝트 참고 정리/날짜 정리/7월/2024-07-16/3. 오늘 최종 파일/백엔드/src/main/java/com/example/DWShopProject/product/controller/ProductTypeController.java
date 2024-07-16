package com.example.DWShopProject.product.controller;

import com.example.DWShopProject.product.dto.ProductMainTypeDto;
import com.example.DWShopProject.product.dto.ProductSubTypeDto;
import com.example.DWShopProject.product.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-types")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("/main")
    public List<ProductMainTypeDto> getAllMainTypes() {
        return productTypeService.getAllMainTypes();
    }

    @GetMapping("/sub")
    public List<ProductSubTypeDto> getAllSubTypes() {
        return productTypeService.getAllSubTypes();
    }
}
