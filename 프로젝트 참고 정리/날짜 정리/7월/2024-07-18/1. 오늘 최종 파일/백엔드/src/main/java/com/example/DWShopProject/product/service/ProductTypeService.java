package com.example.DWShopProject.product.service;

import com.example.DWShopProject.common.exception.ResourceNotFoundException;
import com.example.DWShopProject.product.dto.ProductMainTypeDTO;
import com.example.DWShopProject.product.dto.ProductSubTypeDTO;
import com.example.DWShopProject.product.entity.ProductMainType;
import com.example.DWShopProject.product.entity.ProductSubType;
import com.example.DWShopProject.product.repository.ProductMainTypeRepository;
import com.example.DWShopProject.product.repository.ProductSubTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductTypeService {

    private final ProductMainTypeRepository productMainTypeRepository;
    private final ProductSubTypeRepository productSubTypeRepository;

    @Autowired
    public ProductTypeService(ProductMainTypeRepository productMainTypeRepository, ProductSubTypeRepository productSubTypeRepository) {
        this.productMainTypeRepository = productMainTypeRepository;
        this.productSubTypeRepository = productSubTypeRepository;
    }

    public ProductSubTypeDTO convertToDTO(ProductSubType productSubType) {
        return new ProductSubTypeDTO(productSubType);
    }

    public ProductMainTypeDTO convertToDTO(ProductMainType productMainType) {
        return new ProductMainTypeDTO(productMainType);
    }

    public static ProductSubType convertToEntity(ProductSubTypeDTO productSubTypeDTO, ProductMainType productMainType) {
        return ProductSubType.builder()
                .typeName(productSubTypeDTO.getTypeName())
                .productMainType(productMainType)
                .build();
    }

    public static ProductMainType convertToEntity(ProductMainTypeDTO productMainTypeDTO) {
        return ProductMainType.builder()
                .typeName(productMainTypeDTO.getTypeName())
                .build();
    }

    public ProductSubTypeDTO createProductSubType(ProductSubTypeDTO productSubTypeDTO) {
        ProductMainType productMainType = productMainTypeRepository.findById(productSubTypeDTO.getProductMainTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid product main type ID"));

        ProductSubType productSubType = convertToEntity(productSubTypeDTO, productMainType);
        ProductSubType savedSubType = productSubTypeRepository.save(productSubType);
        return convertToDTO(savedSubType);
    }

    public ProductMainTypeDTO createProductMainType(ProductMainTypeDTO productMainTypeDTO) {
        ProductMainType productMainType = convertToEntity(productMainTypeDTO);
        ProductMainType savedMainType = productMainTypeRepository.save(productMainType);
        return convertToDTO(savedMainType);
    }

    public ProductSubTypeDTO updateProductSubType(Long id, ProductSubTypeDTO productSubTypeDTO) {
        ProductSubType productSubType = productSubTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product sub-type not found with ID: " + id));

        ProductMainType productMainType = productMainTypeRepository.findById(productSubTypeDTO.getProductMainTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid product main type ID"));

        productSubType.updateSubTypeInfo(productSubTypeDTO.getTypeName(), productMainType);

        ProductSubType updatedSubType = productSubTypeRepository.save(productSubType);
        return convertToDTO(updatedSubType);
    }

    public ProductMainTypeDTO updateProductMainType(Long id, ProductMainTypeDTO productMainTypeDTO) {
        ProductMainType productMainType = productMainTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product main type not found with ID: " + id));

        productMainType.updateMainTypeInfo(productMainTypeDTO.getTypeName());

        ProductMainType updatedMainType = productMainTypeRepository.save(productMainType);
        return convertToDTO(updatedMainType);
    }

    @Transactional(readOnly = true)
    public List<ProductSubTypeDTO> getSubTypesByMainTypeId(Long mainTypeId) {
        List<ProductSubType> productSubTypes = productSubTypeRepository.findByProductMainTypeId(mainTypeId);
        return productSubTypes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductSubTypeDTO getProductSubTypeById(Long id) {
        ProductSubType productSubType = productSubTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product sub-type not found with ID: " + id));
        return convertToDTO(productSubType);
    }

    @Transactional(readOnly = true)
    public List<ProductSubTypeDTO> getAllProductSubTypes() {
        List<ProductSubType> productSubTypes = productSubTypeRepository.findAll();
        return productSubTypes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductMainTypeDTO getProductMainTypeById(Long id) {
        ProductMainType productMainType = productMainTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product main type not found with ID: " + id));
        return convertToDTO(productMainType);
    }

    @Transactional(readOnly = true)
    public List<ProductMainTypeDTO> getAllProductMainTypes() {
        List<ProductMainType> productMainTypes = productMainTypeRepository.findAll();
        return productMainTypes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteProductSubType(Long id) {
        ProductSubType productSubType = productSubTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product sub-type not found with ID: " + id));
        productSubTypeRepository.delete(productSubType);
    }

    public void deleteProductMainType(Long id) {
        ProductMainType productMainType = productMainTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product main type not found with ID: " + id));
        productMainTypeRepository.delete(productMainType);
    }
}

