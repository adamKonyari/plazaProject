package com.codecool.plaza.api;

import com.codecool.plaza.api.exceptions.NoSuchProductException;
import com.codecool.plaza.api.exceptions.OutOfStockException;
import com.codecool.plaza.api.exceptions.ProductAlreadyExistsException;
import com.codecool.plaza.api.exceptions.ShopIsClosedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShopImpl implements Shop {

    private String name;
    private boolean open;
    private String owner;
    private Map<Long, ShopEntryImpl> products;

    public ShopImpl(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOwner() {
        return owner;
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

    @Override
    public List<Product> getProducts() throws ShopIsClosedException {

        if (!isOpen()) {
            throw new ShopIsClosedException("The shop is closed.");
        }

        List<Product> allProducts = new ArrayList<>();

        for (Long key : products.keySet()) {
            if (products.get(key).getQuantity() > 1) {
                for (int i = 0; i < products.get(key).getQuantity(); i++) {
                    allProducts.add(products.get(key).getProduct());
                }
            } else if (products.get(key).getQuantity() == 1) {
                allProducts.add(products.get(key).getProduct());
            }
        }
        return allProducts;
    }

    @Override
    public Product findByName(String name) throws NoSuchProductException, ShopIsClosedException {

        if (!isOpen()) {
            throw new ShopIsClosedException("The shop is closed.");
        }

        for (Long key : products.keySet()) {
            if(products.get(key).getProduct().getName().equals(name)) {
                return products.get(key).getProduct();
            }
        }

        throw new NoSuchProductException("There is no such product in stock.");
    }

    @Override
    public float getPrice(long barcode) throws NoSuchProductException, ShopIsClosedException {

        if (!isOpen()) {
            throw new ShopIsClosedException("The shop is closed.");
        }

        for (Long key : products.keySet()) {
            if(key == barcode) {
                return products.get(key).getPrice();
            }
        }

        throw new NoSuchProductException("There is no such product in stock.");
    }

    @Override
    public boolean hasProduct(long barcode) throws ShopIsClosedException {

        if (!isOpen()) {
            throw new ShopIsClosedException("The shop is closed.");
        }

        return products.get(barcode).getQuantity() > 0;
    }

    @Override
    public void addNewProduct(Product product, int quantity, float price) throws ProductAlreadyExistsException, ShopIsClosedException {

        if (products.containsKey(product.getBarcode())) {
            throw new ProductAlreadyExistsException("Product is already in stock.");
        }
        if (!isOpen()) {
            throw new ShopIsClosedException("The shop is closed.");
        }

        products.put(product.getBarcode(), new ShopEntryImpl(product, quantity, price));
    }

    @Override
    public void addProduct(long barcode, int quantity) throws NoSuchProductException, ShopIsClosedException {

        if (!products.containsKey(barcode)) {
            throw new NoSuchProductException("There is no such product in stock.");
        }
        if (!isOpen()) {
            throw new ShopIsClosedException("The shop is closed.");
        }

        products.get(barcode).increaseQuantity(quantity);
    }

    @Override
    public Product buyProduct(long barcode) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {

        if (!products.containsKey(barcode)) {
            throw new NoSuchProductException("There is no such product.");
        }

        if (products.get(barcode).getQuantity() < 1) {
            throw new OutOfStockException("The product is out of stock.");
        }

        if (!isOpen()) {
            throw new ShopIsClosedException("The shop is closed.");
        }

        products.get(barcode).decreaseQuantity(1);
        return products.get(barcode).getProduct();
    }

    @Override
    public List<Product> buyProducts(long barcode, int quantity) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {

        if (!products.containsKey(barcode)) {
            throw new NoSuchProductException("There is no such product in stock.");
        }

        if (products.get(barcode).getQuantity() < quantity) {
            throw new OutOfStockException("Not enough or zero products in stock.");
        } else {
            products.get(barcode).decreaseQuantity(quantity);
        }

        if (!isOpen()) {
            throw new ShopIsClosedException("The shop is closed.");
        }

        List<Product> productList = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            productList.add(products.get(barcode).getProduct());
        }

        return productList;
    }

    private class ShopEntryImpl {

        private Product product;
        private int quantity;
        private float price;

        public ShopEntryImpl(Product product, int quantity, float price) {
            this.product = product;
            this.quantity = quantity;
            this.price = price;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void increaseQuantity(int amount) {
            quantity += amount;
        }

        public void decreaseQuantity(int amount) {
            quantity -= amount;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
