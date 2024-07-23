package com.example.DWShopProject.product.service;

import com.example.DWShopProject.common.exception.ResourceNotFoundException;
import com.example.DWShopProject.product.dto.ProductDTO;
import com.example.DWShopProject.product.entity.Product;
import com.example.DWShopProject.product.entity.ProductSubType;
import com.example.DWShopProject.product.repository.ProductRepository;
import com.example.DWShopProject.product.repository.ProductSubTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final ProductSubTypeRepository productSubTypeRepository;

    public static ProductDTO convertToDTO(Product product) {
        return new ProductDTO(product);
    }

    public static Product convertToEntity(ProductDTO productDTO, ProductSubType productSubType) {
        return Product.builder()
                .id(productDTO.getId())
                .productSubType(productSubType)
                .productName(productDTO.getProductName())
                .price(productDTO.getPrice())
                .explanation(productDTO.getExplanation())
                .imageUrl(productDTO.getImageUrl())
                .createDate(productDTO.getCreateDate())
                .build();
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        logger.info("Creating product with name: {}", productDTO.getProductName());

        ProductSubType productSubType = productSubTypeRepository.findById(productDTO.getProductSubTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product sub-type ID"));

        Product product = convertToEntity(productDTO, productSubType);

        Product savedProduct = productRepository.save(product);

        logger.info("Product saved with ID: {}", savedProduct.getId());

        return convertToDTO(savedProduct);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        logger.info("Updating product with ID: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        ProductSubType productSubType = productSubTypeRepository.findById(productDTO.getProductSubTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product sub-type ID"));

        validateImageUrl(productDTO.getImageUrl());

        product.updateProductInfo(
                productSubType,
                productDTO.getProductName(),
                productDTO.getPrice(),
                productDTO.getExplanation(),
                productDTO.getImageUrl()
        );

        Product updatedProduct = productRepository.save(product);

        logger.info("Product updated with ID: {}", updatedProduct.getId());

        return convertToDTO(updatedProduct);
    }

    private void validateImageUrl(String imageUrl) {
        if (imageUrl == null || !imageUrl.startsWith("https://")) {
            throw new IllegalArgumentException("Invalid image URL");
        }
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts() {
        logger.info("Fetching all products");

        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(ProductService::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductDTO getProductById(Long id) {
        logger.info("Fetching product with ID: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        return convertToDTO(product);
    }

    @Transactional(readOnly = true)
    public ProductDTO getProductByName(String name) {
        logger.info("Fetching product with name: {}", name);

        Product product = productRepository.findByProductName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with name: " + name));

        return convertToDTO(product);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getProductsBySubTypeId(Long subTypeId) {
        logger.info("Fetching products by sub-type ID: {}", subTypeId);

        List<Product> products = productRepository.findByProductSubTypeId(subTypeId);

        return products.stream()
                .map(ProductService::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getProductsByMainTypeId(Long mainTypeId) {
        logger.info("Fetching products by main type ID: {}", mainTypeId);

        List<ProductSubType> subTypes = productSubTypeRepository.findByProductMainTypeId(mainTypeId);

        List<Product> products = subTypes.stream()
                .flatMap(subType -> productRepository.findByProductSubTypeId(subType.getId()).stream())
                .collect(Collectors.toList());

        return products.stream()
                .map(ProductService::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteProduct(Long id) {
        logger.info("Deleting product with ID: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        productRepository.delete(product);

        logger.info("Product deleted with ID: {}", id);
    }
}
