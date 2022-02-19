package com.android30112021.recycleview;

public class Discount {
    private DiscountSessionEnum discountSessionEnum;
    private String name;

    public Discount(DiscountSessionEnum discountSessionEnum, String name) {
        this.discountSessionEnum = discountSessionEnum;
        this.name = name;
    }

    public DiscountSessionEnum getDiscountSessionEnum() {
        return discountSessionEnum;
    }

    public void setDiscountSessionEnum(DiscountSessionEnum discountSessionEnum) {
        this.discountSessionEnum = discountSessionEnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "discountSessionEnum=" + discountSessionEnum +
                ", name='" + name + '\'' +
                '}';
    }
}
