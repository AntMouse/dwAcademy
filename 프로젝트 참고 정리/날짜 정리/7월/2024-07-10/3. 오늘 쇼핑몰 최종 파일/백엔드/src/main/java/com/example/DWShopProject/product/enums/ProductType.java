package com.example.DWShopProject.product.enums;

import lombok.Getter;

@Getter
public enum ProductType {


    // 소분류 (상의)
    HOODIES(1L, "후드티셔츠", ParentType.TOPS),
    SHORT_SLEEVE_TSHIRTS(2L, "반소매티셔츠", ParentType.TOPS),
    LONG_SLEEVE_TSHIRTS(3L, "긴소매티셔츠", ParentType.TOPS),
    KNIT_SWEATERS(4L, "니트/스웨터", ParentType.TOPS),
    COLLAR_TSHIRTS(5L, "카라티셔츠", ParentType.TOPS),
    SWEATSHIRTS(6L, "맨투맨", ParentType.TOPS),
    SHIRTS_BLOUSES(7L, "셔츠/블라우스", ParentType.TOPS),
    SPORTSWEAR_TOPS(8L, "스포츠웨어", ParentType.TOPS),

    // 소분류 (하의)
    COTTON_PANTS(9L, "코튼팬츠", ParentType.BOTTOMS),
    DENIM_PANTS(10L, "데님팬츠", ParentType.BOTTOMS),
    JOGGER_PANTS(11L, "조거팬츠", ParentType.BOTTOMS),
    SUIT_SLACKS(12L, "슈트/슬랙스", ParentType.BOTTOMS),
    JUMPSUITS(13L, "점프슈트", ParentType.BOTTOMS),
    LEGGINGS(14L, "레깅스", ParentType.BOTTOMS),
    SKIRTS(15L, "치마", ParentType.BOTTOMS),
    DRESSES(16L, "원피스", ParentType.BOTTOMS),
    SPORTSWEAR_BOTTOMS(17L, "스포츠웨어", ParentType.BOTTOMS),

    // 소분류 (아우터)
    MOUSTANGS(18L, "무스탕", ParentType.OUTERWEAR),
    SUITS(19L, "슈트", ParentType.OUTERWEAR),
    ANORAK_JACKETS(20L, "아노락재킷", ParentType.OUTERWEAR),
    SHORT_PADDING(21L, "숏패딩", ParentType.OUTERWEAR),
    LONG_PADDING(22L, "롱패딩", ParentType.OUTERWEAR),
    CARDIGANS(23L, "가디건", ParentType.OUTERWEAR),
    COATS(24L, "코트", ParentType.OUTERWEAR),

    // 소분류 (신발)
    DRESS_SHOES(25L, "구두", ParentType.SHOES),
    CROCS(26L, "크록스", ParentType.SHOES),
    RUNNING_SHOES(27L, "런닝화", ParentType.SHOES),
    SLIPPERS(28L, "슬리퍼", ParentType.SHOES),
    FLATS(29L, "단화", ParentType.SHOES),
    BOOTS(30L, "부츠", ParentType.SHOES),
    SPORTS_SHOES(31L, "스포츠신발", ParentType.SHOES),

    // 소분류 (액세서리)
    HATS(32L, "모자", ParentType.ACCESSORIES),
    BAGS(33L, "가방", ParentType.ACCESSORIES),
    RINGS_BRACELETS(34L, "반지/팔찌", ParentType.ACCESSORIES),
    NECKLACES(35L, "목걸이", ParentType.ACCESSORIES),
    EARRINGS(36L, "귀걸이", ParentType.ACCESSORIES),
    SUNGLASSES_GLASSES(37L, "선글라스/안경", ParentType.ACCESSORIES),
    WATCHES(38L, "시계", ParentType.ACCESSORIES);

    private final Long id;
    private final String displayName;
    private final ParentType parentTypeEnum;

    ProductType(Long id, String displayName, ParentType parentTypeEnum) {
        this.id = id;
        this.displayName = displayName;
        this.parentTypeEnum = parentTypeEnum;
    }

    public Long getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ParentType getParentTypeEnum() {
        return parentTypeEnum;
    }

    public static ProductType getById(Long id) {
        for (ProductType type : values()) {
            if (type.getId().equals(id)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid id: " + id);
    }
}
