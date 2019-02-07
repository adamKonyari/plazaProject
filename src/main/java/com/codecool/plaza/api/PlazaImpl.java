package com.codecool.plaza.api;

import com.codecool.plaza.api.exceptions.NoSuchShopException;
import com.codecool.plaza.api.exceptions.PlazaIsClosedException;
import com.codecool.plaza.api.exceptions.ShopAlreadyExistsException;

import java.util.List;

public class PlazaImpl implements Plaza {

    private List<Shop> shops;
    private boolean open = true;
    private String name;

    public PlazaImpl(String name, List<Shop> shops) {
        this.name = name;
        this.shops = shops;
    }

    public String getName() {
        return name;
    }

    @Override
    public List<Shop> getShops() throws PlazaIsClosedException {
        if(!isOpen()) {
            throw new PlazaIsClosedException("Plaza is closed.");
        }
        return shops;
    }

    @Override
    public void addShop(Shop shop) throws ShopAlreadyExistsException, PlazaIsClosedException {
        if(!isOpen()) {
            throw new PlazaIsClosedException("Plaza is closed.");
        }
        if(shops.contains(shop)) {
            throw new ShopAlreadyExistsException("The shop is already exist.");
        }
        shops.add(shop);
    }

    @Override
    public void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException {
        if(!shops.contains(shop)) {
            throw new NoSuchShopException("There is no such shop in the plaza.");
        }
        if(!isOpen()) {
            throw new PlazaIsClosedException("Plaza is closed.");
        }
        shops.remove(shop);
    }

    @Override
    public Shop findShopByName(String name) throws NoSuchShopException, PlazaIsClosedException {

        if(!isOpen()) {
            throw new PlazaIsClosedException("Plaza is closed.");
        }

        for(Shop shop: shops) {
            if(name.equals(shop.getName())) {
                return shop;
            }
        }
            throw new NoSuchShopException("There is no shop by the given name.");
    }

    @Override
    public boolean isOpen() {
        return open;
    }

    @Override
    public void open() {
        open = true;
    }

    @Override
    public void close() {
        open = false;
    }
}
