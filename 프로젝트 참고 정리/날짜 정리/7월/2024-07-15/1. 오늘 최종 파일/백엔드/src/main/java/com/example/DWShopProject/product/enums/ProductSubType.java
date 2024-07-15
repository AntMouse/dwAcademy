package com.example.DWShopProject.product.enums;

import lombok.Getter;

@Getter
public enum ProductSubType {


    // 소분류 (상의)
    HOODIES(1L, "후드티셔츠", ProductMainType.TOPS),
    SHORT_SLEEVE_TSHIRTS(2L, "반소매티셔츠", ProductMainType.TOPS),
    LONG_SLEEVE_TSHIRTS(3L, "긴소매티셔츠", ProductMainType.TOPS),
    KNIT_SWEATERS(4L, "니트/스웨터", ProductMainType.TOPS),
    COLLAR_TSHIRTS(5L, "카라티셔츠", ProductMainType.TOPS),
    SWEATSHIRTS(6L, "맨투맨", ProductMainType.TOPS),
    SHIRTS_BLOUSES(7L, "셔츠/블라우스", ProductMainType.TOPS),
    SPORTSWEAR_TOPS(8L, "스포츠웨어", ProductMainType.TOPS),

    // 소분류 (하의)
    COTTON_PANTS(9L, "코튼팬츠", ProductMainType.BOTTOMS),
    DENIM_PANTS(10L, "데님팬츠", ProductMainType.BOTTOMS),
    JOGGER_PANTS(11L, "조거팬츠", ProductMainType.BOTTOMS),
    SUIT_SLACKS(12L, "슈트/슬랙스", ProductMainType.BOTTOMS),
    JUMPSUITS(13L, "점프슈트", ProductMainType.BOTTOMS),
    LEGGINGS(14L, "레깅스", ProductMainType.BOTTOMS),
    SKIRTS(15L, "치마", ProductMainType.BOTTOMS),
    DRESSES(16L, "원피스", ProductMainType.BOTTOMS),
    SPORTSWEAR_BOTTOMS(17L, "스포츠웨어", ProductMainType.BOTTOMS),

    // 소분류 (아우터)
    MOUSTANGS(18L, "무스탕", ProductMainType.OUTERWEAR),
    SUITS(19L, "슈트", ProductMainType.OUTERWEAR),
    ANORAK_JACKETS(20L, "아노락재킷", ProductMainType.OUTERWEAR),
    SHORT_PADDING(21L, "숏패딩", ProductMainType.OUTERWEAR),
    LONG_PADDING(22L, "롱패딩", ProductMainType.OUTERWEAR),
    CARDIGANS(23L, "가디건", ProductMainType.OUTERWEAR),
    COATS(24L, "코트", ProductMainType.OUTERWEAR),

    // 소분류 (신발)
    DRESS_SHOES(25L, "구두", ProductMainType.SHOES),
    CROCS(26L, "크록스", ProductMainType.SHOES),
    RUNNING_SHOES(27L, "런닝화", ProductMainType.SHOES),
    SLIPPERS(28L, "슬리퍼", ProductMainType.SHOES),
    FLATS(29L, "단화", ProductMainType.SHOES),
    BOOTS(30L, "부츠", ProductMainType.SHOES),
    SPORTS_SHOES(31L, "스포츠신발", ProductMainType.SHOES),

    // 소분류 (액세서리)
    HATS(32L, "모자", ProductMainType.ACCESSORIES),
    BAGS(33L, "가방", ProductMainType.ACCESSORIES),
    RINGS_BRACELETS(34L, "반지/팔찌", ProductMainType.ACCESSORIES),
    NECKLACES(35L, "목걸이", ProductMainType.ACCESSORIES),
    EARRINGS(36L, "귀걸이", ProductMainType.ACCESSORIES),
    SUNGLASSES_GLASSES(37L, "선글라스/안경", ProductMainType.ACCESSORIES),
    WATCHES(38L, "시계", ProductMainType.ACCESSORIES);

    private final Long id;
    private final String displayName;
    private final ProductMainType parentTypeEnum;

    ProductSubType(Long id, String displayName, ProductMainType parentTypeEnum) {
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

    public ProductMainType getParentTypeEnum() {
        return parentTypeEnum;
    }

    public static ProductSubType getById(Long id) {
        for (ProductSubType type : values()) {
            if (type.getId().equals(id)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid id: " + id);
    }
}
