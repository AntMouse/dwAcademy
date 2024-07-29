package com.example.DWShopProject.product.controller;

import com.example.DWShopProject.common.exception.ResourceNotFoundException;
import com.example.DWShopProject.product.dto.ProductMainTypeDTO;
import com.example.DWShopProject.product.dto.ProductSubTypeDTO;
import com.example.DWShopProject.product.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-types")
@Slf4j
@RequiredArgsConstructor
public class ProductTypeController {
    private final ProductTypeService productTypeService;

    @PostMapping("/sub-type")
    public ResponseEntity<ProductSubTypeDTO> createProductSubType(@RequestBody ProductSubTypeDTO productSubTypeDTO) {
        log.info("Creating product sub-type with details: {}", productSubTypeDTO);
        ProductSubTypeDTO createdProductSubType = productTypeService.createProductSubType(productSubTypeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductSubType);
    }

    @PostMapping("/main-type")
    public ResponseEntity<ProductMainTypeDTO> createProductMainType(@RequestBody ProductMainTypeDTO productMainTypeDTO) {
        log.info("Creating product main-type with details: {}", productMainTypeDTO);
        ProductMainTypeDTO createdProductMainType = productTypeService.createProductMainType(productMainTypeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductMainType);
    }

    @PutMapping("/sub-type/{id}")
    public ResponseEntity<ProductSubTypeDTO> updateProductSubType(@PathVariable Long id, @RequestBody ProductSubTypeDTO productSubTypeDTO) {
        log.info("Updating product sub-type with ID: {}", id);
        ProductSubTypeDTO updatedProductSubType = productTypeService.updateProductSubType(id, productSubTypeDTO);
        return ResponseEntity.ok(updatedProductSubType);
    }

    @PutMapping("/main-type/{id}")
    public ResponseEntity<ProductMainTypeDTO> updateProductMainType(@PathVariable Long id, @RequestBody ProductMainTypeDTO productMainTypeDTO) {
        log.info("Updating product main-type with ID: {}", id);
        ProductMainTypeDTO updatedProductMainType = productTypeService.updateProductMainType(id, productMainTypeDTO);
        return ResponseEntity.ok(updatedProductMainType);
    }

    @GetMapping("/sub-types/main-type/{mainTypeId}")
    public ResponseEntity<List<ProductSubTypeDTO>> getSubTypesByMainTypeId(@PathVariable Long mainTypeId) {
        log.info("Fetching sub-types for main type ID: {}", mainTypeId);
        List<ProductSubTypeDTO> subTypes = productTypeService.getSubTypesByMainTypeId(mainTypeId);
        return ResponseEntity.ok(subTypes);
    }

    @GetMapping("/sub-type/{id}")
    public ResponseEntity<ProductSubTypeDTO> getProductSubTypeById(@PathVariable Long id) {
        log.info("Fetching product sub-type with ID: {}", id);
        ProductSubTypeDTO subType = productTypeService.getProductSubTypeById(id);
        return ResponseEntity.ok(subType);
    }

    @GetMapping("/sub-types")
    public ResponseEntity<List<ProductSubTypeDTO>> getAllProductSubTypes() {
        log.info("Fetching all product sub-types");
        List<ProductSubTypeDTO> subTypes = productTypeService.getAllProductSubTypes();
        return ResponseEntity.ok(subTypes);
    }

    @GetMapping("/main-type/{id}")
    public ResponseEntity<ProductMainTypeDTO> getProductMainTypeById(@PathVariable Long id) {
        log.info("Fetching product main type with ID: {}", id);
        ProductMainTypeDTO mainType = productTypeService.getProductMainTypeById(id);
        return ResponseEntity.ok(mainType);
    }

    @GetMapping("/main-types")
    public ResponseEntity<List<ProductMainTypeDTO>> getAllProductMainTypes() {
        log.info("Fetching all product main types");
        List<ProductMainTypeDTO> mainTypes = productTypeService.getAllProductMainTypes();
        return ResponseEntity.ok(mainTypes);
    }

    @DeleteMapping("/sub-type/{id}")
    public ResponseEntity<Void> deleteProductSubType(@PathVariable Long id) {
        log.info("Deleting product sub-type with ID: {}", id);
        productTypeService.deleteProductSubType(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/main-type/{id}")
    public ResponseEntity<Void> deleteProductMainType(@PathVariable Long id) {
        log.info("Deleting product main type with ID: {}", id);
        productTypeService.deleteProductMainType(id);
        return ResponseEntity.noContent().build();
    }
}


