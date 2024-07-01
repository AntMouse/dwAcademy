package com.example.DWShopProject.controller;

import com.example.DWShopProject.ResourceNotFoundException;
import com.example.DWShopProject.dto.ProductDto;
import com.example.DWShopProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    // 제품 생성
    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDTO) {
        return productService.createProduct(productDTO);
    }

    // ID로 제품 조회
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        ProductDto productDTO = productService.getProductById(id)
                .orElseThrow(() -> new ResourceNotFoundException("상품을 찾을 수 없습니다."));
        return ResponseEntity.ok(productDTO);
    }

    // 이름으로 제품 조회
    @GetMapping("/name/{productName}")
    public ResponseEntity<ProductDto> getProductByName(@PathVariable String productName) {
        ProductDto productDTO = productService.getProductByName(productName)
                .orElseThrow(() -> new ResourceNotFoundException("상품을 찾을 수 없습니다."));
        return ResponseEntity.ok(productDTO);
    }

    // 모든 제품 조회
    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    // 제품 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDTO) {
        ProductDto updatedProduct = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    // 제품 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
