package com.example.shoppingmall.service;

import com.example.shoppingmall.ResourceNotFoundException;
import com.example.shoppingmall.dto.ProductDTO;
import com.example.shoppingmall.entity.Product;
import com.example.shoppingmall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = Product.builder()
                .productType(productDTO.getProductType())
                .productName(productDTO.getProductName())
                .price(productDTO.getPrice())
                .explanation(productDTO.getExplanation())
                .createDate(LocalDateTime.now())
                .build();

        Product savedProduct = productRepository.save(product);
        return mapToDTO(savedProduct);
    }

    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id).map(this::mapToDTO);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        product.updateProduct(productDTO.getProductType(), productDTO.getProductName(), productDTO.getPrice(), productDTO.getExplanation());
        Product updatedProduct = productRepository.save(product);
        return mapToDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        try {
            productRepository.delete(product);
        } catch (Exception e) {
            logger.error("Failed to delete product with id {}: {}", id, e.getMessage());
            throw e;
        }
    }

    private ProductDTO mapToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProductType(product.getProductType());
        productDTO.setProductName(product.getProductName());
        productDTO.setPrice(product.getPrice());
        productDTO.setExplanation(product.getExplanation());
        return productDTO;
    }
}