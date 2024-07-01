package com.example.DWShopProject.service;

import com.example.DWShopProject.exception.ResourceNotFoundException;
import com.example.DWShopProject.dto.ProductDto;
import com.example.DWShopProject.entity.Product;
import com.example.DWShopProject.enums.ParentTypeEnum;
import com.example.DWShopProject.repository.ProductRepository;
import com.example.DWShopProject.repository.ProductTypeMgmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final ProductTypeMgmtRepository productTypeMgmtRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductTypeMgmtRepository productTypeMgmtRepository) {
        this.productRepository = productRepository;
        this.productTypeMgmtRepository = productTypeMgmtRepository;
    }

    // 샐운 상품을 생성하는 메서드.
    public ProductDto createProduct(ProductDto productDTO) {
        logger.info("Creating product with name: {}", productDTO.getProductName());

        Product product = Product.builder()
                .productType(productDTO.getProductType())
                .productName(productDTO.getProductName())
                .price(productDTO.getPrice())
                .explanation(productDTO.getExplanation())
                .imageUrl(productDTO.getImageUrl()) // 이미지 URL 필드 설정
                .createDate(LocalDateTime.now())
                .build();

        Product savedProduct = productRepository.save(product);
        logger.info("Product saved with ID: {}", savedProduct.getId());

        return mapToDTO(savedProduct);
    }

    // 상품 ID로 상품을 조회.
    @Transactional(readOnly = true)
    public Optional<ProductDto> getProductById(Long id) {
        logger.info("Fetching product by ID: {}", id);
        return productRepository.findById(id)
                .map(this::mapToDTO);
    }

    // 상품 이름으로 상품을 조회.
    @Transactional(readOnly = true)
    public Optional<ProductDto> getProductByName(String productName) {
        logger.info("Fetching product by name: {}", productName);
        return productRepository.findByProductName(productName)
                .map(this::mapToDTO);
    }

    // 모든 상품을 조회.
    @Transactional(readOnly = true)
    public List<ProductDto> getAllProducts() {
        logger.info("Fetching all products");
        return productRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // 상품 정보를 업데이트.
    public ProductDto updateProduct(Long id, ProductDto productDTO) {

        logger.info("Updating product with ID: {}", id);

        // ID로 상품 조회, 존재하지 않으면 예외 발생
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("상품을 찾을 수 없습니다."));

        // 상품 정보 업데이트
        product.updateProductInfo(productDTO.getProductType(), productDTO.getProductName(),
                productDTO.getPrice(), productDTO.getExplanation(), productDTO.getImageUrl());

        // 변경된 상품을 저장
        Product updatedProduct = productRepository.save(product);
        logger.info("Product updated with ID: {}", updatedProduct.getId());

        // DTO로 변환하여 반환
        return mapToDTO(updatedProduct);
    }

    // 상품을 삭제하는 메서드.
    public void deleteProduct(Long id) {
        logger.info("Deleting product with ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("상품을 찾을 수 없습니다."));

        productRepository.delete(product);
        logger.info("Product deleted with ID: {}", id);
    }

    // 엔티티를 Dto로 변환하는 메서드.
    private ProductDto mapToDTO(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .productType(product.getProductType())
                .productName(product.getProductName())
                .price(product.getPrice())
                .explanation(product.getExplanation())
                .imageUrl(product.getImageUrl()) // 이미지 URL 필드 매핑
                .build();
    }
}