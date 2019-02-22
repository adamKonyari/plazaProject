package com.codecool.plaza.api;

import com.codecool.plaza.api.enums.Gender;
import com.codecool.plaza.api.enums.Movement;

public class WatchProduct extends Product {

    Movement movement;

    public WatchProduct(long barcode, String name, String manufacturer, Gender gender, Movement movement) {
        super(barcode, name, manufacturer, gender);
        this.movement = movement;
    }

    public Movement getMovement() {
        return movement;
    }
}
