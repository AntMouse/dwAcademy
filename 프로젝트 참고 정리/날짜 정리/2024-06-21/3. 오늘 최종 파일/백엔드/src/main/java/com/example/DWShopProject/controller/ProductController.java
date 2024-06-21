package com.example.DWShopProject.controller;

import com.example.DWShopProject.dto.ProductDto;
import com.example.DWShopProject.entity.ProductTypeMgmt;
import com.example.DWShopProject.enums.ParentTypeEnum;
import com.example.DWShopProject.enums.ProductTypeEnum;
import com.example.DWShopProject.exception.ResourceNotFoundException;
import com.example.DWShopProject.service.ProductService;
import com.example.DWShopProject.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private final ProductService productService;
    private final ProductTypeService productTypeService;

    // 새로운 상품을 생성.
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDTO) {
        ProductDto createdProduct = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    // 상품 ID로 상품 조회.
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        ProductDto productDTO = productService.getProductById(id)
                .orElseThrow(() -> new ResourceNotFoundException("상품을 찾을 수 없습니다."));
        return ResponseEntity.ok(productDTO);
    }

    // 상품 이름으로 상품 조회.
    @GetMapping("/name/{productName}")
    public ResponseEntity<ProductDto> getProductByName(@PathVariable String productName) {
        ProductDto productDTO = productService.getProductByName(productName)
                .orElseThrow(() -> new ResourceNotFoundException("상품을 찾을 수 없습니다."));
        return ResponseEntity.ok(productDTO);
    }

    // 모든 상품을 조회.
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // 상품 정보를 업데이트.
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDTO) {
        ProductDto updatedProduct = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    // 상품을 삭제.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // 모든 상품 타입을 조회.
    @GetMapping("/types")
    public ResponseEntity<List<ProductTypeMgmt>> getAllProductTypes() {
        List<ProductTypeMgmt> productTypes = productTypeService.getAllProductTypes();
        return ResponseEntity.ok(productTypes);
    }

    // 상품 ID로 메인 타입을 조회.
    @GetMapping("/{id}/main-type")
    public ResponseEntity<ParentTypeEnum> getMainTypeByProductId(@PathVariable Long id) {
        ProductDto product = productService.getProductById(id)
                .orElseThrow(() -> new ResourceNotFoundException("상품을 찾을 수 없습니다."));
        ParentTypeEnum mainType = productTypeService.getMainTypeBySubType(product.getProductType());
        return ResponseEntity.ok(mainType);
    }

    // 메인 타입에 속한 서브 타입들을 조회.
    @GetMapping("/sub-types/{parentType}")
    public ResponseEntity<List<ProductTypeEnum>> getSubTypesByMainType(@PathVariable String parentType) {
        List<ProductTypeEnum> subTypes = productTypeService.getSubTypesByMainType(ParentTypeEnum.valueOf(parentType.toUpperCase()));
        return ResponseEntity.ok(subTypes);
    }
}