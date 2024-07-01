package com.example.DWShopProject.controller;

import com.example.DWShopProject.ResourceNotFoundException;
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

    @PostMapping
    public com.example.DWShopProject.dto.ProductDto createProduct(@RequestBody com.example.DWShopProject.dto.ProductDto productDTO) {
        return productService.createProduct(productDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.example.DWShopProject.dto.ProductDto> getProductById(@PathVariable Long id) {
        com.example.DWShopProject.dto.ProductDto productDTO = productService.getProductById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/name/{productName}")
    public ResponseEntity<com.example.DWShopProject.dto.ProductDto> getProductByName(@PathVariable String productName) {
        com.example.DWShopProject.dto.ProductDto productDTO = productService.getProductByName(productName).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping
    public List<com.example.DWShopProject.dto.ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public ResponseEntity<com.example.DWShopProject.dto.ProductDto> updateProduct(@PathVariable Long id, @RequestBody com.example.DWShopProject.dto.ProductDto productDTO) {
        com.example.DWShopProject.dto.ProductDto updatedProduct = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
