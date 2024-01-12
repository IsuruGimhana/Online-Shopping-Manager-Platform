/**
 * This interface represents the user Westminster Shopping Manager
 *
 * @author Isuru Gimhana
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager{

    //product list cannot be modified but it will be accessible for all the instances of this class
    private static final int MAX_PRODUCT = 50;

    //maximum number of products that can be added to the system
    private static final ArrayList<Product> PRODUCT_LIST = new ArrayList<>();

    // constructor
    public WestminsterShoppingManager() {}

    //getter for the product list
    public static ArrayList<Product> getProductList() {
        return PRODUCT_LIST;
    }

    public static int getMaxProduct() {
        return MAX_PRODUCT;
    }

    // main menu
    @Override
    public void menu() {
        System.out.println("------------------------------------------------------\n" +
                "------------------------------------------------------\n" +
                "Welcome!\n" +
                "------------------------------------------------------\n" +
                "------------------------------------------------------\n" +
                "1. Add a new product to the system\n" +
                "2. Delete a product from the system\n" +
                "3. Print all the products in the system\n" +
                "4. Save the list of products that have been added to the system\n" +
                "5. Exit the program\n" +
                "------------------------------------------------------");
        int menuAnswer = nextIntErrorHandling("Select an option: ", "1, 2, 3, 4, 5","==");
        switch (menuAnswer) {
            case 1:
                addProduct();
                break;
            case 2:
                deleteProduct();
                break;
            case 3:
                printProductList();
                break;
            case 4:
                try {
                    saveProductList();
                } catch (IOException e) {
                    System.out.println("An error occurred! while saving the product list");
                    e.printStackTrace();
                }
                break;
            case 5:
                System.out.println("Thank you!");
                System.exit(0);
                break;
        }
    }

    // add products to the product list
    @Override
    public void addProduct() {
        if (PRODUCT_LIST.size() >= MAX_PRODUCT) {
            System.out.println("The product list is full!");
            menu();
        }
        System.out.println("------------------------------------------------------\n" +
                "1. Add a new Electronic to the system\n" +
                "2. Add a new Clothing to the system\n" +
                "------------------------------------------------------");
        int answer = nextIntErrorHandling("Select an option: ", "1, 2", "==");
        System.out.println("------------------------------------------------------");
        String productId;
        if (answer == 1) {
            productId = nextErrorHandling("Enter product id (format: E001): ", "4", "E==");
        } else {
            productId = nextErrorHandling("Enter product id (format: C001): ", "4", "C==");
        }
        for (Product product : PRODUCT_LIST) {
            if (product.getProductId().equals(productId)) {
                System.out.println("\nProduct already exists!\n" +
                        "------------------------------------------------------");
                String answer2 = nextErrorHandling("Do you want to add a new product? (Y/N): ", "Y, N", "===");
                String answer2Lower = answer2.toLowerCase();
                if (answer2Lower.equals("y")) {
                    addProduct();
                } else {
                    menu();
                }
            }
        }
        String productName = nextLineErrorHandling("Enter product name: ", "3", ">=");
        int numAvailableItems = nextIntErrorHandling("Enter number of available items: ", "0",">");
        double productPrice = nextDoubleErrorHandling("Enter product price: ", "0",">");
        if (answer == 1) {
            String productBrand = nextLineErrorHandling("Enter product brand: ", "3", ">=");
            int productWarranty = nextIntErrorHandling("Enter product warranty(months): ", "0",">=");
            Product electronic = new Electronics(productId, productName, numAvailableItems, productPrice, productBrand, productWarranty);
            PRODUCT_LIST.add(electronic);
        } else {
            String productSize = nextErrorHandling("Enter product size (S, M, L, XL): ", "S, M, L, XL","===");
            String productColour = nextLineErrorHandling("Enter product colour: ","3", ">=");
            Product clothing = new Clothing(productId, productName, numAvailableItems, productPrice, productSize, productColour);
            PRODUCT_LIST.add(clothing);
        }
        System.out.println("\nProduct added successfully!\n" +
                "------------------------------------------------------");
        String answer2 = nextErrorHandling("Do you want to add another product? (Y/N): ", "Y, N", "===");
        String answer2Lower = answer2.toLowerCase();
        if (answer2Lower.equals("y")) {
            addProduct();
        } else {
            menu();
        }
    }

    // delete products from the product list
    @Override
    public void deleteProduct() {
        Scanner scanner1 = new Scanner(System.in);
        if (PRODUCT_LIST.size() == 0) {
            System.out.println("\n" +
                    "The product list is empty!");
            menu();
        }
        System.out.println("------------------------------------------------------\n" +
                "1. Delete an Electronic product from the system\n" +
                "2. Delete a Clothing product from the system\n" +
                "------------------------------------------------------");
        int answer = nextIntErrorHandling("Select an option: ", "1, 2", "==");
        System.out.println("------------------------------------------------------");
        String productId;
        if (answer == 1 ) {
            productId = nextErrorHandling("Enter product id (format: E001): ", "4", "E==");
        } else {
            productId = nextErrorHandling("Enter product id (format: C001): ", "4", "C==");
        }
        Iterator<Product> iterator = PRODUCT_LIST.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductId().equals(productId)) {
//                System.out.println(product);
                if (product instanceof Electronics) {
                    Electronics electronics = (Electronics) product;
                    System.out.println(electronics.toString());
                } else {
                    Clothing clothing = (Clothing) product;
                    System.out.println(clothing.toString());
                }
                PRODUCT_LIST.remove(product);
                System.out.println("\nProduct deleted successfully!");
                int totalProducts = PRODUCT_LIST.size();
                System.out.println("Total number of products left in the System: " + totalProducts);
                break;
            } else {
                System.out.println("\n" +
                        "Product not found!");
            }
        }
        System.out.println("------------------------------------------------------");
        String answer2 = nextErrorHandling("Do you want to delete another product? (Y/N): ", "Y, N", "===");
        String answer2Lower = answer2.toLowerCase();
        if (answer2Lower.equals("y")) {
            deleteProduct();
        } else {
            menu();
        }
    }

    // print the product list
    @Override
    public void printProductList() {
        Collections.sort(PRODUCT_LIST);
        if (PRODUCT_LIST.size() == 0) {
            System.out.println("The product list is empty!");
            menu();
        }
        for (Product product : PRODUCT_LIST) {
            if (product instanceof Electronics) {
                Electronics electronics = (Electronics) product;
                System.out.println(electronics.toString());
            } else {
                Clothing clothing = (Clothing) product;
                System.out.println(clothing.toString());
            }
        }
        System.out.println("\nProduct List ordered by product id successfully!");
        menu();
    }

    // save the product list to a file
    @Override
    public void saveProductList() throws IOException {
        File file = new File("Existing Products/saveProductList.txt");
        if (file.exists()) {
            file.delete();
        }
        System.out.println(PRODUCT_LIST.size());
        try(FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                for (Product product : PRODUCT_LIST) {
                    bufferedWriter.write(product.toString());
                    bufferedWriter.newLine();
                    }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nProduct List saved successfully!");
        menu();
    }

    // load the product list from a file
    @Override
    public void loadProductList() {
        File file = new File("Existing Products/saveProductList.txt");
        if (!file.exists()) {
            return;
        }
        try(Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("Electronics")) {
                    String[] savedList = line.substring(12, line.length()-1).split(", ");
                    String productId = savedList[0].substring(11, savedList[0].length()-1);
                    String productName = savedList[1].substring(13, savedList[1].length()-1);
                    int numAvailableItems = Integer.parseInt(savedList[2].substring(18));
                    double productPrice = Double.parseDouble(savedList[3].substring(13));
                    String productBrand = savedList[4].substring(17, savedList[4].length()-1);
                    int productWarranty = Integer.parseInt(savedList[5].substring(25));
                    Product electronic = new Electronics(productId, productName, numAvailableItems, productPrice, productBrand, productWarranty);
                    PRODUCT_LIST.add(electronic);
                } else {
                    String[] savedList = line.substring(9, line.length()-1).split(", ");
                    String productId = savedList[0].substring(11, savedList[0].length()-1);
                    String productName = savedList[1].substring(13, savedList[1].length()-1);
                    int numAvailableItems = Integer.parseInt(savedList[2].substring(18));
                    double productPrice = Double.parseDouble(savedList[3].substring(13));
                    String productSize = savedList[4].substring(14, savedList[4].length()-1);
                    String productColour = savedList[5].substring(16, savedList[5].length()-1);
                    Product clothing = new Clothing(productId, productName, numAvailableItems, productPrice, productSize, productColour);
                    PRODUCT_LIST.add(clothing);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // error handling methods for integer inputs
    @Override
    public int nextIntErrorHandling(String message, String condition, String operator) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                String[] newCondition = condition.split(", ");
                for (String parameter : newCondition) {
                    if (operator.equals("==")) {
                        if (value == Integer.parseInt(parameter)) {
                            scanner.nextLine();
                            return value;
                        }
                    } else if (operator.equals(">")) {
                        if (value > Integer.parseInt(parameter)) {
                            scanner.nextLine();
                            return value;
                        }
                    } else {
                        if (value >= Integer.parseInt(parameter)) {
                            scanner.nextLine();
                            return value;
                        }
                    }
                }
            }
            scanner.nextLine();
            System.out.println("\n" +
                    "Invalid input!\n" +
                    "Please enter a valid input\n" +
                    "------------------------------------------------------");
        }
    }

    // error handling methods for string inputs
    @Override
    public String nextLineErrorHandling(String message, String condition, String operator) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            if (scanner.hasNextLine()) {
                String value = scanner.nextLine();
                String[] newCondition = condition.split(", ");
                for (String parameter : newCondition) {
                    if (operator.equals(">=")) {
                        if (value.length() >= Integer.parseInt(parameter)) {
                            return value;
                        }
                    }
                }
            }
            System.out.println("\n" +
                    "Invalid input!\n" +
                    "Please enter a valid input\n" +
                    "------------------------------------------------------");
        }
    }

    // error handling methods for string inputs
    @Override
    public String nextErrorHandling(String message, String condition, String operator) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            if (scanner.hasNext()) {
                String value = scanner.next();
                String[] newCondition = condition.split(", ");
                for (String parameter : newCondition) {
                    if (operator.equals("E==") || operator.equals("C==")) {
                        if ((value.length() == Integer.parseInt(parameter)) && (value.substring(0,1).toUpperCase().equals(operator.substring(0,1))) && (value.substring(1).matches("[0-9]+"))) {//checks if the string matches regular expression (list of numbers)
                            scanner.nextLine();
                            return value.toUpperCase();
                        }
                    } else {
                        if (value.toUpperCase().equals(parameter)) {
                            scanner.nextLine();
                            return value.toUpperCase();
                        }
                    }
                }
            }
            scanner.nextLine();
            System.out.println("\n" +
                    "Invalid input!\n" +
                    "Please enter a valid input\n" +
                    "------------------------------------------------------");
        }
    }

    // error handling methods for double inputs
    @Override
    public double nextDoubleErrorHandling(String message, String condition, String operator) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                String[] newCondition = condition.split(", ");
                for (String parameter : newCondition) {
                    if (operator.equals(">")) {
                        if (value > Double.parseDouble(parameter)) {
                            scanner.nextLine();
                            return value;
                        }
                    }
                }
            }
            scanner.nextLine();
            System.out.println("\n" +
                    "Invalid input!\n" +
                    "Please enter a valid input\n" +
                    "------------------------------------------------------");
        }
    }
}
