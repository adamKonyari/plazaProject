package com.codecool.plaza.api;

import com.codecool.plaza.api.enums.Gender;

public abstract class Product {

    protected long barcode;
    protected String name;
    protected String manufacturer;
    protected Gender gender;

    protected Product(long barcode, String name, String manufacturer, Gender gender) {
        this.barcode = barcode;
        this.name = name;
        this.manufacturer = manufacturer;
    }

    public long getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
