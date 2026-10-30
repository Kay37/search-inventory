package com.pluralsight;

//! Importing Scanner for receiving & storing user input

import java.util.Scanner;

//! Importing ArrayList for our inventory of products
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;

public class SearchInventoryMain {

    public static void main(String[] args) {

        //TODO: Will be an app to manage and search the product inventory of a store using java collections.
        //*Creating a scanner so we can ask the user some questions
        Scanner userInput =
                new Scanner(System.in);

        //TODO: Create an initial inventory of at least 5 products.
        //*I completed this within the getInventory method

        //*Using getInventory method to load hardwareInventory
        ArrayList<Product> hardwareInventory = getInventory(userInput);

        //*Creating a variable to keep the menu looping
        boolean menuRunning = true;

        //*Looping the menu
        while (menuRunning) {

            //*Display the main menu and store the user's command
            System.out.println("Welcome to the Inventory Management System!\n");
            int userCommand = displayMainMenu(userInput);
            switch (userCommand) {
                case 1:
                    listInventory(hardwareInventory);
                    break;
                case 2:
                    System.out.println("Enter product name: ");
                    String nameSearch = userInput.nextLine();
                    searchName(hardwareInventory, nameSearch);
                    break;
                case 3:
                    System.out.println("Enter product description: ");
                    String descriptionSearch = userInput.nextLine();
                    searchDescription(hardwareInventory, descriptionSearch);
                    break;
                case 4:
                    System.out.println("Minimum quantity in stock to search for: ");
                    int quantitySearch = userInput.nextInt();
                    searchMinQuantity(hardwareInventory, quantitySearch);
                    break;
                case 5:
                    System.out.println("Enter product minimum price: ");
                    double minProductPrice = userInput.nextDouble();
                    System.out.println("Enter product maximum price: ");
                    double maxProductPrice = userInput.nextDouble();
                    if (minProductPrice > maxProductPrice) {
                        System.out.println("Minimum price is greater than maximum price. Try again");
                    } else {
                        searchPrice(hardwareInventory, minProductPrice, maxProductPrice);
                    }

                    break;
                case 6:
                    addProduct(userInput, hardwareInventory);
                    break;
                case 7:
                    menuRunning = false;
                    break;
                default:
                    System.out.println("=== Invalid command entered. Try again ===");
            }
        }

    }

    //TODO: listInventory method - list the inventory, alphabetically sorted.
    public static void listInventory(ArrayList<Product> hardwareInventory) {
        Collections.sort(hardwareInventory, Comparator.comparing(Product::getProductName));
        for (Product product : hardwareInventory) {
            System.out.printf("%s - $%.2f%n", product.getProductName(), product.getProductPrice());
        }

    }

    //TODO: searchName method - search the inventory for a name
    public static void searchName(ArrayList<Product> hardwareInventory, String productName) {
        boolean found = false;
        for (Product product : hardwareInventory) {
            if (product.getProductName().equalsIgnoreCase(productName)) {
                found = true;
                System.out.printf("%s - $%.2f%n", product.getProductName(), product.getProductPrice());
            }
        }
        if (!found) {
            System.out.println("No product with that name found.");
        }
    }

    //TODO: searchDescription method - search the inventory for a description
    public static void searchDescription(ArrayList<Product> hardwareInventory, String productDescription) {
        boolean found = false;
        for (Product product : hardwareInventory) {
            if (product.getProductDescription().contains(productDescription)) {
                found = true;
                System.out.printf("%s - $%.2f%n", product.getProductName(), product.getProductPrice());
            }
        }
        if (!found) {
            System.out.println("No product with that description found.");
        }
    }

    //TODO: searchQuantity method - search the inventory for products with at least this quantity
    public static void searchMinQuantity(ArrayList<Product> hardwareInventory, int minQuantity) {
        boolean found = false;
        for (Product product : hardwareInventory) {
            if (product.getProductQuantity() >= minQuantity) {
                found = true;
                System.out.printf("%s - $%.2f%n", product.getProductName(), product.getProductPrice());
            }
        }
        if (!found) {
            System.out.println("No product with that quantity or greater found.");
        }
    }

    //TODO: searchPrice method - search the inventory at or under a price
    public static void searchPrice(ArrayList<Product> hardwareInventory, double minProductPrice, double maxProductPrice) {
        boolean found = false;
        for (Product product : hardwareInventory) {
            if (product.getProductPrice() <= maxProductPrice && product.getProductPrice() >= minProductPrice) {
                found = true;
                System.out.printf("%s - $%.2f%n", product.getProductName(), product.getProductPrice());
            }
        }
        if (!found) {
            System.out.println("No products in that price range found.");
        }
    }

    //TODO: addProduct method - add product to the inventory
    public static void addProduct(Scanner userInput, ArrayList<Product> hardwareInventory) {
        System.out.println("Enter product name: ");
        String productName = userInput.nextLine();
        System.out.println("Enter product description: ");
        String productDescription = userInput.nextLine();
        System.out.println("Enter product quantity: ");
        int productQuantity = userInput.nextInt();
        userInput.nextLine();
        System.out.println("Enter product price: ");
        double productPrice = userInput.nextDouble();
        userInput.nextLine();

        hardwareInventory.add(new Product(productName, productDescription, productQuantity, productPrice));
    }

    //TODO: displayMenu method - display user options for managing and searching inventory
    public static int displayMainMenu(Scanner userInput) {

        //? ask the user some questions
        System.out.println("""
                What would you like to do?
                    1. List the inventory
                    2. Search by name
                    3. Search by description
                    4. Search for items with at least X in stock
                    5. Search for items within a price range
                    6. Add to the inventory
                    7. Quit
                    Enter your command:""");
        //?store the user's answer / command
        int menuSelection = userInput.nextInt();
        userInput.nextLine();
        return menuSelection;
    }

    //TODO: getInventory() method - retrieving our inventory
    public static ArrayList<Product> getInventory(Scanner userInput) {

        //! Using an array list to store our hardware inventory
        ArrayList<Product> hardwareInventory = new ArrayList<Product>();

        //!Creating some hardware/tools
        //!Exercise says at least 5 initial products
        Product product1 =
                new Product("Hammer", "A hand tool with a heavy metal head", 25, 5.97);
        Product product2 =
                new Product("Hand Saw", "A hand tool with a toothed metal blade", 15, 8.62);
        Product product3 =
                new Product("3/8 in. Zinc-Plated Steel USS Nylon Lock Nut 100 pk", "Lock nut for securely fastening a bolt", 50, 9.99);
        Product product4 =
                new Product("20V Cordless Brushed Compact Drill", "Handheld power tool that spins bits to bore holes and drive screws.", 50, 115);
        Product product5 =
                new Product("20V Cordless Brushed Impact Driver", "Handheld power tool for driving long screws and bolts", 50, 115);

        hardwareInventory.add(product1);
        hardwareInventory.add(product2);
        hardwareInventory.add(product3);
        hardwareInventory.add(product4);
        hardwareInventory.add(product5);
        return hardwareInventory;
    }
}
