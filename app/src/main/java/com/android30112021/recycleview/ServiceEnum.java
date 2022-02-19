package com.android30112021.recycleview;

public enum ServiceEnum {
    RESTAURANT( "Nhà hàng"),
    BIRTHDAY("Sinh nhật"),
    FAMILY("Gia đình"),
    GROUP("Hội nhóm"),
    BUFFET("Bufet"),
    SMALL_RESTAURANT("Quán ăn nhỏ");
    private final String name;

    ServiceEnum(String s)
    {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
