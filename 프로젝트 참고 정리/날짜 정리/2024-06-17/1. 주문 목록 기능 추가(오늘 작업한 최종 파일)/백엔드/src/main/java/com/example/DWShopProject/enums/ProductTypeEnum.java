package com.example.DWShopProject.enums;

public enum ProductTypeEnum {
    // 소분류 (상의)
    HOODIES("후드티셔츠", ParentTypeEnum.TOPS),
    SHORT_SLEEVE_TSHIRTS("반소매티셔츠", ParentTypeEnum.TOPS),
    LONG_SLEEVE_TSHIRTS("긴소매티셔츠", ParentTypeEnum.TOPS),
    KNIT_SWEATERS("니트_스웨터", ParentTypeEnum.TOPS),
    COLLAR_TSHIRTS("카라티셔츠", ParentTypeEnum.TOPS),
    SWEATSHIRTS("맨투맨", ParentTypeEnum.TOPS),
    SHIRTS_BLOUSES("셔츠_블라우스", ParentTypeEnum.TOPS),
    SPORTSWEAR_TOPS("스포츠웨어", ParentTypeEnum.TOPS),

    // 소분류 (하의)
    COTTON_PANTS("코튼팬츠", ParentTypeEnum.BOTTOMS),
    DENIM_PANTS("데님팬츠", ParentTypeEnum.BOTTOMS),
    JOGGER_PANTS("조거팬츠", ParentTypeEnum.BOTTOMS),
    SUIT_SLACKS("슈트_슬랙스", ParentTypeEnum.BOTTOMS),
    JUMPSUITS("점프슈트", ParentTypeEnum.BOTTOMS),
    LEGGINGS("레깅스", ParentTypeEnum.BOTTOMS),
    SKIRTS("치마", ParentTypeEnum.BOTTOMS),
    DRESSES("원피스", ParentTypeEnum.BOTTOMS),
    SPORTSWEAR_BOTTOMS("스포츠웨어", ParentTypeEnum.BOTTOMS),

    // 소분류 (아우터)
    MOUSTANGS("무스탕", ParentTypeEnum.OUTERWEAR),
    SUITS("슈트", ParentTypeEnum.OUTERWEAR),
    ANORAK_JACKETS("아노락재킷", ParentTypeEnum.OUTERWEAR),
    SHORT_PADDING("숏패딩", ParentTypeEnum.OUTERWEAR),
    LONG_PADDING("롱패딩", ParentTypeEnum.OUTERWEAR),
    CARDIGANS("가디건", ParentTypeEnum.OUTERWEAR),
    COATS("코트", ParentTypeEnum.OUTERWEAR),

    // 소분류 (신발)
    DRESS_SHOES("구두", ParentTypeEnum.SHOES),
    CROCS("크록스", ParentTypeEnum.SHOES),
    RUNNING_SHOES("런닝화", ParentTypeEnum.SHOES),
    SLIPPERS("슬리퍼", ParentTypeEnum.SHOES),
    FLATS("단화", ParentTypeEnum.SHOES),
    BOOTS("부츠", ParentTypeEnum.SHOES),
    SPORTS_SHOES("스포츠신발", ParentTypeEnum.SHOES),

    // 소분류 (액세서리)
    HATS("모자", ParentTypeEnum.ACCESSORIES),
    BAGS("가방", ParentTypeEnum.ACCESSORIES),
    RINGS_BRACELETS("반지_팔찌", ParentTypeEnum.ACCESSORIES),
    NECKLACES("목걸이", ParentTypeEnum.ACCESSORIES),
    EARRINGS("귀걸이", ParentTypeEnum.ACCESSORIES),
    SUNGLASSES_GLASSES("선글라스_안경", ParentTypeEnum.ACCESSORIES),
    WATCHES("시계", ParentTypeEnum.ACCESSORIES);

    private final String displayName;
    private final ParentTypeEnum parentTypeEnum;

    ProductTypeEnum(String displayName, ParentTypeEnum parentTypeEnum) {
        this.displayName = displayName;
        this.parentTypeEnum = parentTypeEnum;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ParentTypeEnum getParentTypeEnum() {
        return parentTypeEnum;
    }
}
