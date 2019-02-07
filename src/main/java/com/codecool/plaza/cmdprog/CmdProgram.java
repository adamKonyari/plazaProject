package com.codecool.plaza.cmdprog;

import com.codecool.plaza.api.PlazaImpl;
import com.codecool.plaza.api.Product;
import com.codecool.plaza.api.Shop;
import com.codecool.plaza.api.ShopImpl;
import com.codecool.plaza.api.exceptions.NoSuchShopException;
import com.codecool.plaza.api.exceptions.PlazaIsClosedException;
import com.codecool.plaza.api.exceptions.ShopAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CmdProgram {

    private List<Product> cart;
    private List<Float> prices;

    private Scanner scanner = new Scanner(System.in);
    private List<Shop> shops = new ArrayList<>();
    private PlazaImpl myPlaza;

    public void run() throws PlazaIsClosedException, ShopAlreadyExistsException, NoSuchShopException {

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
                        if (!myPlaza.getShops().isEmpty()) {
                            shopLister();
                            System.out.print("\nPlease type in the name of the shop that you want to enter: ");
                            String shopToEnter = scanner.nextLine();

                            for (Shop shop : myPlaza.getShops()) {
                                if (shop.getName().equals(shopToEnter)) {
                                    Shop currentShop = shop;
                                }
                            }
                            clearScreen();
                            System.out.println("You have entered: " + shopToEnter + "!");
                            menuPrinter(new String[]{
                                    "List available products.",
                                    "Find a product by name.",
                                    "Display the shop's owner.",
                                    "Open the shop.",
                                    "Close the shop.",
                                    "Add new product to the shop.",
                                    "Add an existing product to the shop.",
                                    "Buy a product by barcode.",
                                    "Check the price by barcode"
                            });

                        } else {
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

            } while (option != 0);
        }
    }

    private void menuPrinter(String[] options) {
        int counter = 1;
        for (String option : options) {
            System.out.println(counter + ". " + option);
            counter++;
        }
        System.out.println("0. Exit\n");
    }

    private void shopLister() throws PlazaIsClosedException {
        int counter = 1;
        for (Shop shop : myPlaza.getShops()) {
            System.out.println(counter + ". " + shop.getName());
            counter++;
        }
    }

    private void promptEnterKey() {
        System.out.println("\n Press \"ENTER\" to continue...");
        scanner.nextLine();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
