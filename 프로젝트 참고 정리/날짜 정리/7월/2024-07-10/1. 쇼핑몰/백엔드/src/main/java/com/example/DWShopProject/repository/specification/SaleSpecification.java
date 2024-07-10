package com.example.DWShopProject.repository.specification;

import com.example.DWShopProject.entity.Sale;
import com.example.DWShopProject.enums.ParentTypeEnum;
import com.example.DWShopProject.enums.ProductTypeEnum;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

public class SaleSpecification {

    public static Specification<Sale> isWithinDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return (root, query, builder) -> builder.between(root.get("createDate"), startDate, endDate);
    }

    public static Specification<Sale> hasProductIdIn(List<Long> productIds) {
        return (root, query, builder) -> root.get("productId").in(productIds);
    }

    public static Specification<Sale> hasProductTypeIn(List<ProductTypeEnum> productSubTypes) {
        return (root, query, builder) -> root.get("productType").in(productSubTypes);
    }

    public static Specification<Sale> hasParentTypeIn(List<ParentTypeEnum> productMainTypes) {
        return (root, query, builder) -> root.join("product").get("productType").get("parentTypeEnum").in(productMainTypes);
    }
}
