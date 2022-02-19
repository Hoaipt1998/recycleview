package com.android30112021.recycleview;

public enum DiscountSessionEnum {
    MORNING("Ăn Sáng"), LAUNCH("Ăn trưa"), DINNER("Ăn tối"), ALLDAY("Cả ngày");

    private final String name;

    DiscountSessionEnum(String s)
    {
        name = s;
    }

    public String toString() {
        return this.name;
    }

}
