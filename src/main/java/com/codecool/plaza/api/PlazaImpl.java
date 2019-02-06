package com.codecool.plaza.api;

import com.codecool.plaza.api.exceptions.NoSuchShopException;
import com.codecool.plaza.api.exceptions.PlazaIsClosedException;
import com.codecool.plaza.api.exceptions.ShopAlreadyExistsException;

import java.util.List;

public class PlazaImpl implements Plaza {

    private List<Shop> shops;

    public PlazaImpl(List<Shop> shops) {
        this.shops = shops;
    }

    @Override
    public List<Shop> getShops() throws PlazaIsClosedException {
        return null;
    }

    @Override
    public void addShop(Shop shop) throws ShopAlreadyExistsException, PlazaIsClosedException {

    }

    @Override
    public void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException {

    }

    @Override
    public Shop findShopByName(String name) throws NoSuchShopException, PlazaIsClosedException {
        return null;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }
}
