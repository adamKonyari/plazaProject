package com.codecool.plaza.api;

import com.codecool.plaza.api.enums.Gender;

public class ShoeProduct extends Product {

    private float size;

    public ShoeProduct(long barcode, String name, String manufacturer, Gender gender, float size) {
        super(barcode, name, manufacturer, gender);
        this.size = size;
    }

    public float getSize() {
        return size;
    }
}

