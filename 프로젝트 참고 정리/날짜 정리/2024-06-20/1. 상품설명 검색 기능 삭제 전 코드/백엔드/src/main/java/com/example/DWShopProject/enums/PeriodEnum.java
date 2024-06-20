package com.example.DWShopProject.enums;

public enum PeriodEnum {
    ALL("전체기간"),
    LAST_DAY("1일 이내"),
    LAST_WEEK("1주 이내"),
    LAST_MONTH("1개월 이내"),
    LAST_SIX_MONTHS("6개월 이내"),
    LAST_YEAR("1년 이내");

    private final String displayName;

    PeriodEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
