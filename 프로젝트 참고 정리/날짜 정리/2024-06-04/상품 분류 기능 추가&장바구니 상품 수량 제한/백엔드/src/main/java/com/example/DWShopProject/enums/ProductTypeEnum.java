package com.example.DWShopProject.enums;

public enum ProductTypeEnum {
    // 소분류 (상의)
    HOODIES("후드티셔츠", MajorTypeEnum.TOPS),
    SHORT_SLEEVE_TSHIRTS("반소매티셔츠", MajorTypeEnum.TOPS),
    LONG_SLEEVE_TSHIRTS("긴소매티셔츠", MajorTypeEnum.TOPS),
    KNIT_SWEATERS("니트/스웨터", MajorTypeEnum.TOPS),
    COLLAR_TSHIRTS("카라티셔츠", MajorTypeEnum.TOPS),
    SWEATSHIRTS("맨투맨", MajorTypeEnum.TOPS),
    SHIRTS_BLOUSES("셔츠/블라우스", MajorTypeEnum.TOPS),
    SPORTSWEAR_TOPS("스포츠웨어", MajorTypeEnum.TOPS),

    // 소분류 (하의)
    COTTON_PANTS("코튼팬츠", MajorTypeEnum.BOTTOMS),
    DENIM_PANTS("데님팬츠", MajorTypeEnum.BOTTOMS),
    JOGGER_PANTS("조거팬츠", MajorTypeEnum.BOTTOMS),
    SUIT_SLACKS("슈트/슬랙스", MajorTypeEnum.BOTTOMS),
    JUMPSUITS("점프슈트", MajorTypeEnum.BOTTOMS),
    LEGGINGS("레깅스", MajorTypeEnum.BOTTOMS),
    SKIRTS("치마", MajorTypeEnum.BOTTOMS),
    DRESSES("원피스", MajorTypeEnum.BOTTOMS),
    SPORTSWEAR_BOTTOMS("스포츠웨어", MajorTypeEnum.BOTTOMS),

    // 소분류 (아우터)
    MOUSTANGS("무스탕", MajorTypeEnum.OUTERWEAR),
    SUITS("슈트", MajorTypeEnum.OUTERWEAR),
    ANORAK_JACKETS("아노락재킷", MajorTypeEnum.OUTERWEAR),
    SHORT_PADDING("숏패딩", MajorTypeEnum.OUTERWEAR),
    LONG_PADDING("롱패딩", MajorTypeEnum.OUTERWEAR),
    CARDIGANS("가디건", MajorTypeEnum.OUTERWEAR),
    COATS("코트", MajorTypeEnum.OUTERWEAR),

    // 소분류 (신발)
    DRESS_SHOES("구두", MajorTypeEnum.SHOES),
    CROCS("크록스", MajorTypeEnum.SHOES),
    RUNNING_SHOES("런닝화", MajorTypeEnum.SHOES),
    SLIPPERS("슬리퍼", MajorTypeEnum.SHOES),
    FLATS("단화", MajorTypeEnum.SHOES),
    BOOTS("부츠", MajorTypeEnum.SHOES),
    SPORTS_SHOES("스포츠신발", MajorTypeEnum.SHOES),

    // 소분류 (액세서리)
    HATS("모자", MajorTypeEnum.ACCESSORIES),
    BAGS("가방", MajorTypeEnum.ACCESSORIES),
    RINGS_BRACELETS("반지/팔찌", MajorTypeEnum.ACCESSORIES),
    NECKLACES("목걸이", MajorTypeEnum.ACCESSORIES),
    EARRINGS("귀걸이", MajorTypeEnum.ACCESSORIES),
    SUNGLASSES_GLASSES("선글라스/안경", MajorTypeEnum.ACCESSORIES),
    WATCHES("시계", MajorTypeEnum.ACCESSORIES);

    private final String displayName;
    private final MajorTypeEnum majorTypeEnum;

    ProductTypeEnum(String displayName, MajorTypeEnum majorTypeEnum) {
        this.displayName = displayName;
        this.majorTypeEnum = majorTypeEnum;
    }

    public String getDisplayName() {
        return displayName;
    }

    public MajorTypeEnum getMajorType() {
        return majorTypeEnum;
    }
}
