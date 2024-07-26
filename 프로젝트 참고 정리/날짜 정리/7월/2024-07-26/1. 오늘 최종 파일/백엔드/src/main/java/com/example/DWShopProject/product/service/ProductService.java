package com.example.DWShopProject.product.service;

import com.example.DWShopProject.common.exception.ResourceNotFoundException;
import com.example.DWShopProject.product.converter.ProductDTOConverter;
import com.example.DWShopProject.product.dto.ProductDTO;
import com.example.DWShopProject.product.entity.Product;
import com.example.DWShopProject.product.entity.ProductSubType;
import com.example.DWShopProject.product.repository.ProductRepository;
import com.example.DWShopProject.product.repository.ProductSubTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductSubTypeRepository productSubTypeRepository;
    private final ProductDTOConverter productDtoConverter;

    public ProductDTO createProduct(ProductDTO productDto) {
        log.info("Creating product with name: {}", productDto.getProductName());

        ProductSubType productSubType = productSubTypeRepository.findById(productDto.getProductSubTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product sub-type ID"));

        Product product = productDtoConverter.convertToProductEntity(productDto, productSubType);

        Product savedProduct = productRepository.save(product);

        log.info("Product saved with ID: {}", savedProduct.getId());

        return productDtoConverter.convertToProductDto(savedProduct);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDto) {
        log.info("Updating product with ID: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        ProductSubType productSubType = productSubTypeRepository.findById(productDto.getProductSubTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product sub-type ID"));

        validateImageUrl(productDto.getImageUrl());

        product.updateProductInfo(
                productSubType,
                productDto.getProductName(),
                productDto.getPrice(),
                productDto.getExplanation(),
                productDto.getImageUrl()
        );

        Product updatedProduct = productRepository.save(product);

        log.info("Product updated with ID: {}", updatedProduct.getId());

        return productDtoConverter.convertToProductDto(updatedProduct);
    }

    private void validateImageUrl(String imageUrl) {
        if (imageUrl == null || !imageUrl.startsWith("https://")) {
            throw new IllegalArgumentException("Invalid image URL");
        }
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts() {
        log.info("Fetching all products");

        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(productDtoConverter::convertToProductDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductDTO getProductById(Long id) {
        log.info("Fetching product with ID: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        return productDtoConverter.convertToProductDto(product);
    }

    @Transactional(readOnly = true)
    public ProductDTO getProductByName(String name) {
        log.info("Fetching product with name: {}", name);

        Product product = productRepository.findByProductName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with name: " + name));

        return productDtoConverter.convertToProductDto(product);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getProductsBySubTypeId(Long subTypeId) {
        log.info("Fetching products by sub-type ID: {}", subTypeId);

        List<Product> products = productRepository.findByProductSubTypeId(subTypeId);

        return products.stream()
                .map(productDtoConverter::convertToProductDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getProductsByMainTypeId(Long mainTypeId) {
        log.info("Fetching products by main type ID: {}", mainTypeId);

        List<ProductSubType> subTypes = productSubTypeRepository.findByProductMainTypeId(mainTypeId);

        List<Product> products = subTypes.stream()
                .flatMap(subType -> productRepository.findByProductSubTypeId(subType.getId()).stream())
                .collect(Collectors.toList());

        return products.stream()
                .map(productDtoConverter::convertToProductDto)
                .collect(Collectors.toList());
    }

    public void deleteProduct(Long id) {
        log.info("Deleting product with ID: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        productRepository.delete(product);

        log.info("Product deleted with ID: {}", id);
    }
}
