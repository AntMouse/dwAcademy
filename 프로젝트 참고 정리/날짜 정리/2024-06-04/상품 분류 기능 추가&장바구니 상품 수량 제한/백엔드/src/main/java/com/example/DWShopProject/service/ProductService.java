package com.example.DWShopProject.service;

import com.example.DWShopProject.ResourceNotFoundException;
import com.example.DWShopProject.dto.ProductDto;
import com.example.DWShopProject.entity.Product;
import com.example.DWShopProject.entity.ProductType;
import com.example.DWShopProject.repository.ProductRepository;
import com.example.DWShopProject.repository.ProductTypeRepository;
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

    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductTypeRepository productTypeRepository) {
        this.productRepository = productRepository;
        this.productTypeRepository = productTypeRepository;
    }

    public ProductDto createProduct(ProductDto productDTO) {
        ProductType productType = productTypeRepository.findById(productDTO.getProductType().getId())
                .orElseThrow(() -> new ResourceNotFoundException("상품 유형을 찾을 수 없습니다."));
        Product product = Product.builder()
                .productType(productType)
                .productName(productDTO.getProductName())
                .price(productDTO.getPrice())
                .explanation(productDTO.getExplanation())
                .createDate(LocalDateTime.now())
                .build();

        Product savedProduct = productRepository.save(product);
        return mapToDTO(savedProduct);
    }

    public Optional<ProductDto> getProductById(Long id) {
        return productRepository.findById(id)
                .map(this::mapToDTO);
    }

    public Optional<ProductDto> getProductByName(String productName) {
        return productRepository.findByProductName(productName)
                .map(this::mapToDTO);
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ProductDto updateProduct(Long id, ProductDto productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("상품을 찾을 수 없습니다."));
        ProductType productType = productTypeRepository.findById(productDTO.getProductType().getId())
                .orElseThrow(() -> new ResourceNotFoundException("상품 유형을 찾을 수 없습니다."));
        product.updateProductInfo(productType, productDTO.getProductName(), productDTO.getPrice(), productDTO.getExplanation());
        Product updatedProduct = productRepository.save(product);
        return mapToDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("상품을 찾을 수 없습니다."));
        try {
            productRepository.delete(product);
        } catch (Exception e) {
            logger.error("ID가 {}인 제품 삭제 실패: {}", id, e.getMessage());
            throw e;
        }
    }

    private ProductDto mapToDTO(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .productType(product.getProductType())
                .productName(product.getProductName())
                .price(product.getPrice())
                .explanation(product.getExplanation())
                .build();
    }
}
