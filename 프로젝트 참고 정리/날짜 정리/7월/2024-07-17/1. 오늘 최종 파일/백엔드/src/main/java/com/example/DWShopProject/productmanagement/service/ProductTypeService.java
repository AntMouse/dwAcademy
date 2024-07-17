package com.example.DWShopProject.productmanagement.service;

import com.example.DWShopProject.productmanagement.dto.ProductMainTypeDto;
import com.example.DWShopProject.productmanagement.dto.ProductSubTypeDto;
import com.example.DWShopProject.productmanagement.repository.ProductMainTypeRepository;
import com.example.DWShopProject.productmanagement.repository.ProductSubTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductTypeService {

    @Autowired
    private ProductMainTypeRepository productMainTypeRepository;

    @Autowired
    private ProductSubTypeRepository productSubTypeRepository;

    public List<ProductMainTypeDto> getAllMainTypes() {
        return productMainTypeRepository.findAll().stream()
                .map(ProductMainTypeDto::new)
                .collect(Collectors.toList());
    }

    public List<ProductSubTypeDto> getAllSubTypes() {
        return productSubTypeRepository.findAll().stream()
                .map(ProductSubTypeDto::new)
                .collect(Collectors.toList());
    }
}
