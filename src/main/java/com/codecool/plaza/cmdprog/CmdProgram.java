package com.codecool.plaza.cmdprog;

import com.codecool.plaza.api.*;
import com.codecool.plaza.api.enums.Gender;
import com.codecool.plaza.api.enums.Movement;
import com.codecool.plaza.api.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CmdProgram {

    private List<Product> cart;
    private List<Float> prices;

    private Scanner scanner = new Scanner(System.in);
    private List<Shop> shops = new ArrayList<>();
    private PlazaImpl myPlaza;

    public void run() throws PlazaIsClosedException, ShopAlreadyExistsException, NoSuchShopException, ShopIsClosedException, ProductAlreadyExistsException, NoSuchProductException, OutOfStockException {

        clearScreen();
        System.out.println("There is no plaza created yet!\n");
        menuPrinter(new String[]{"Create a new plaza."});
        System.out.print("Please select an option: ");

        int option = Integer.parseInt(scanner.nextLine());
        if (option == 1) {
            clearScreen();
            System.out.println("Welcome to the plaza builder!\n");
            System.out.print("Please define the name of the plaza: ");
            String name = scanner.nextLine();
            myPlaza = new PlazaImpl(name, shops);

            do {
                clearScreen();
                System.out.println("Welcome to the " + myPlaza.getName() + " plaza!\n");

                menuPrinter(new String[]{
                        "List all shops.",
                        "Add new shop.",
                        "Remove an existing shop.",
                        "Enter a shop by name.",
                        "Open the plaza.",
                        "Close the plaza",
                        "Check if the plaza is open."});

                System.out.print("Please select an option: ");
                option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        clearScreen();
                        shopLister();
                        promptEnterKey();
                        break;

                    case 2:
                        clearScreen();
                        System.out.println("Welcome to the shop builder!\n");
                        System.out.print("Name of the shop: ");
                        String shopName = scanner.nextLine();
                        System.out.print("Owner of the shop: ");
                        String shopOwner = scanner.nextLine();
                        myPlaza.addShop(new ShopImpl(shopName, shopOwner));
                        System.out.println(shopName + " has been created!");
                        promptEnterKey();
                        break;

                    case 3:
                        clearScreen();
                        shopLister();
                        System.out.print("\nEnter the shop's name that you want to remove: ");
                        String shopToRemove = scanner.nextLine();
                        for (Shop shop : myPlaza.getShops()) {
                            if (shop.getName().equals(shopToRemove)) {
                                myPlaza.removeShop(shop);
                                System.out.println(shop.getName() + " has been removed!");
                                break;
                            }
                        }
                        promptEnterKey();
                        break;

                    case 4:
                        clearScreen();
                        Shop currentShop = null;
                        if (!myPlaza.getShops().isEmpty()) {
                            shopLister();
                            System.out.print("\nPlease type in the name of the shop that you want to enter: ");
                            String shopToEnter = scanner.nextLine();

                            for (Shop shop : myPlaza.getShops()) {
                                if (shop.getName().equals(shopToEnter)) {
                                    currentShop = shop;
                                }
                            }

                            do {
                                String productType;
                                Long barcode;
                                String productName;
                                String manufacturer;
                                Gender gender;
                                String getGender;
                                Movement movement;
                                String getMovement;
                                float size;
                                int price;
                                int quantity;

                                clearScreen();
                                System.out.println("You have entered: " + shopToEnter + "!\n");
                                menuPrinter(new String[]{
                                        "List available products.",
                                        "Find a product by name.",
                                        "Display the shop's owner.",
                                        "Open the shop.",
                                        "Close the shop.",
                                        "Add new product to the shop.",
                                        "Add an existing product to the shop.",
                                        "Buy products by barcode.",
                                        "Check the price by barcode"
                                });
                                System.out.print("Please select an option: ");
                                option = Integer.parseInt(scanner.nextLine());

                                switch (option) {
                                    case 1:
                                        clearScreen();
                                        if (currentShop != null) {
                                            if (!currentShop.getProducts().isEmpty()) {
                                                System.out.println("All available products:\n");
                                                int counter = 1;
                                                for (Product product : currentShop.getProducts()) {
                                                    System.out.println(counter + ". " + product.getName());
                                                    counter++;
                                                }
                                                promptEnterKey();
                                            } else {
                                                System.out.println("The shop is empty.");
                                                promptEnterKey();
                                            }
                                        }
                                        break;

                                    case 2:
                                        clearScreen();
                                        if (currentShop != null) {
                                            System.out.print("Please enter the name of the product you are looking for: ");
                                            productName = scanner.nextLine();
                                            for (Product product : currentShop.getProducts()) {
                                                if (productName.equals(product.getName())) {
                                                    clearScreen();
                                                    productDetails(product);
                                                    promptEnterKey();
                                                }
                                            }
                                        }
                                        break;

                                    case 3:
                                        clearScreen();
                                        System.out.println("The shop's owner is: " + currentShop.getOwner());
                                        promptEnterKey();
                                        break;

                                    case 4:
                                        clearScreen();
                                        currentShop.open();
                                        System.out.println("The shop is now open!");
                                        promptEnterKey();
                                        break;

                                    case 5:
                                        clearScreen();
                                        currentShop.close();
                                        System.out.println("The shop is now closed.");
                                        promptEnterKey();
                                        break;

                                    case 6:
                                        clearScreen();
                                        System.out.print("Product type: ");
                                        productType = scanner.nextLine();
                                        System.out.print("Barcode: ");
                                        barcode = Long.parseLong(scanner.nextLine());
                                        System.out.print("Name: ");
                                        productName = scanner.nextLine();
                                        System.out.print("Manufacturer: ");
                                        manufacturer = scanner.nextLine();
                                        System.out.print("Gender: ");
                                        getGender = scanner.nextLine();
                                        if (getGender.equalsIgnoreCase("Male")) {
                                            gender = Gender.MALE;
                                        } else if (getGender.equalsIgnoreCase("Female")) {
                                            gender = Gender.FEMALE;
                                        } else {
                                            gender = Gender.UNISEX;
                                        }
                                        if (productType.equalsIgnoreCase("shoe")) {
                                            System.out.print("Size: ");
                                            size = Float.parseFloat(scanner.nextLine());
                                            System.out.print("Price: ");
                                            price = Integer.parseInt(scanner.nextLine());
                                            System.out.print("Quantity: ");
                                            quantity = Integer.parseInt(scanner.nextLine());
                                            currentShop.addNewProduct(new ShoeProduct(barcode, productName, manufacturer, gender, size), quantity, price);
                                        } else if (productType.equalsIgnoreCase("watch")) {
                                            System.out.print("Movement: ");
                                            getMovement = scanner.nextLine();
                                            if (getMovement.equalsIgnoreCase("Quartz")) {
                                                movement = Movement.QUARTZ;
                                            } else {
                                                movement = Movement.MECHANICAL;
                                            }
                                            System.out.print("Price: ");
                                            price = Integer.parseInt(scanner.nextLine());
                                            System.out.print("Quantity: ");
                                            quantity = Integer.parseInt(scanner.nextLine());
                                            currentShop.addNewProduct(new WatchProduct(barcode, productName, manufacturer, gender, movement), quantity, price);
                                        }
                                        System.out.println(productName + " successfully added!");
                                        promptEnterKey();
                                        break;

                                    case 7:
                                        clearScreen();
                                        System.out.print("Barcode: ");
                                        barcode = Long.parseLong(scanner.nextLine());
                                        System.out.print("Quantity: ");
                                        quantity = Integer.parseInt(scanner.nextLine());
                                        currentShop.addProduct(barcode, quantity);
                                        System.out.println("Product(s) added!");
                                        promptEnterKey();
                                        break;

                                    case 8:
                                        clearScreen();
                                        System.out.print("Barcode: ");
                                        barcode = Long.parseLong(scanner.nextLine());
                                        System.out.print("Quantity: ");
                                        quantity = Integer.parseInt(scanner.nextLine());
                                        if(quantity == 1) {
                                            cart.add(currentShop.buyProduct(barcode));
                                            System.out.println("Product has been added to your cart!");
                                        } else if(quantity > 1) {
                                            cart.addAll(currentShop.buyProducts(barcode, quantity));
                                            System.out.println("Products have been added to your cart!");
                                        }
                                        promptEnterKey();
                                        break;
                                    case 9:
                                        clearScreen();
                                        System.out.print("Barcode: ");
                                        barcode = Long.parseLong(scanner.nextLine());
                                        System.out.println("The price of the product is: " + currentShop.getPrice(barcode));
                                        promptEnterKey();
                                        break;

                        }

                } while (option != 0) ;

            } else{
                System.out.println("\nThere are no shops in the plaza.");
                promptEnterKey();
            }

            break;

            case 5:
                clearScreen();
                myPlaza.open();
                System.out.println("The plaza is open!");
                promptEnterKey();
                break;

            case 6:
                clearScreen();
                myPlaza.close();
                System.out.println("The plaza is closed.");
                promptEnterKey();
                break;

            case 7:
                clearScreen();
                String isOpen;
                if (myPlaza.isOpen()) {
                    isOpen = "open!";
                } else {
                    isOpen = "closed.";
                }
                System.out.println("\nThe plaza " + isOpen);
                promptEnterKey();
                break;
        }
    } while(option !=0);
}
    }

private void productDetails(Product product){
        System.out.println("Name: "+product.getName());
        System.out.println("Manufacturer: "+product.getManufacturer());
        System.out.println("Gender: "+product.getGender());
        if(product instanceof ShoeProduct){
        System.out.println("Size: "+((ShoeProduct)product).getSize());
        }else if(product instanceof WatchProduct){
        System.out.println("Movement: "+((WatchProduct)product).getMovement());
        }
        }

private void menuPrinter(String[]options){
        int counter=1;
        for(String option:options){
        System.out.println(counter+". "+option);
        counter++;
        }
        System.out.println("0. Exit\n");
        }

private void shopLister()throws PlazaIsClosedException{
        int counter=1;
        for(Shop shop:myPlaza.getShops()){
        System.out.println(counter+". "+shop.getName());
        counter++;
        }
        }

private void promptEnterKey(){
        System.out.println("\n Press \"ENTER\" to continue...");
        scanner.nextLine();
        }

private void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        }

        }
