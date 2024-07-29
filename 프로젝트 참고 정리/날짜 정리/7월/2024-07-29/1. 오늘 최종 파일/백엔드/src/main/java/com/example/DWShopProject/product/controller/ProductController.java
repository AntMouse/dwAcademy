package com.example.DWShopProject.product.controller;

import com.example.DWShopProject.common.exception.ResourceNotFoundException;
import com.example.DWShopProject.product.dto.ProductDTO;
import com.example.DWShopProject.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        log.info("Creating product with details: {}", productDTO);
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        log.info("Updating product with ID: {}", id);
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        log.info("Fetching all products");
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        log.info("Fetching product with ID: {}", id);
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductDTO> getProductByName(@PathVariable String name) {
        log.info("Fetching product with name: {}", name);
        ProductDTO product = productService.getProductByName(name);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/sub-type/{subTypeId}")
    public ResponseEntity<List<ProductDTO>> getProductsBySubTypeId(@PathVariable Long subTypeId) {
        log.info("Fetching products by sub-type ID: {}", subTypeId);
        List<ProductDTO> products = productService.getProductsBySubTypeId(subTypeId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/main-type/{mainTypeId}")
    public ResponseEntity<List<ProductDTO>> getProductsByMainTypeId(@PathVariable Long mainTypeId) {
        log.info("Fetching products by main type ID: {}", mainTypeId);
        List<ProductDTO> products = productService.getProductsByMainTypeId(mainTypeId);
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.info("Deleting product with ID: {}", id);
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
