import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This interface represents the user Westminster Shopping Manager
 *
 * @author Isuru Gimhana
 */
public class WestminsterShoppingManager implements ShoppingManager{
    private static ArrayList<Product> productList;
    private static final int MAX_PRODUCT = 50;

    public WestminsterShoppingManager() {
        productList = new ArrayList<>();
    }
    public void menu() {
        System.out.print("------------------------------------------------------\n" +
                "------------------------------------------------------\n" +
                "Welcome!\n" +
                "------------------------------------------------------\n" +
                "------------------------------------------------------\n" +
                "1. Add a new product to the system\n" +
                "2. Delete a product from the system\n" +
                "3. Print all the products in the system\n" +
                "4. Save the list of products that have been added to the system\n" +
                "5. Exit the program\n" +
                "------------------------------------------------------\n" +
                "Select an option: ");
        Scanner scanner = new Scanner(System.in);
        int menuAnswer = 0;
        while (true) {
            if (scanner.hasNextInt()) {
                menuAnswer = scanner.nextInt();
                if (menuAnswer > 0 && menuAnswer < 6) {
                    break;
                } else {
                    System.out.println("Invalid input!");
                    System.out.println("Please enter a valid input");
                    scanner.next();
                }
            } else {
                System.out.println("Invalid input!");
                System.out.println("Please enter a valid input");
                scanner.next();
            }
        }
        switch (menuAnswer) {
            case 1:
                addProduct(scanner);
                break;
            case 2:
                deleteProduct(scanner);
                break;
            case 3:
                getProductList();
                break;
            case 4:
                try {
                    saveProductList(scanner);
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

    @Override
    public void addProduct(Scanner scanner) {
        if (productList.size() >= MAX_PRODUCT) {
            System.out.println("The product list is full!");
            menu();
        }
        System.out.print("------------------------------------------------------\n" +
                "1. Add a new Electronic to the system\n" +
                "2. Add a new Clothing to the system\n" +
                "------------------------------------------------------\n" +
                "Select an option: ");
        variableErrorHandling(scanner, "nextInt", "Select an option: ");
        int answer = scanner.nextInt();
        if (answer == 1 || answer == 2) {
            System.out.print("------------------------------------------------------\n" +
                    "Enter product id: ");
            variableErrorHandling(scanner, "next", "Enter product id: ");
            String productId = scanner.next();
            scanner.nextLine();
            System.out.print("Enter product name: ");
            variableErrorHandling(scanner, "nextLine", "Enter product name: ");
            String productName = scanner.nextLine();
            System.out.print("Enter number of available items: ");
            variableErrorHandling(scanner, "nextInt", "Enter number of available items: ");
            int numAvailableItems = scanner.nextInt();
            System.out.print("Enter product price: ");
            variableErrorHandling(scanner, "nextDouble", "Enter product price: ");
            double productPrice = scanner.nextDouble();
            if (answer == 1) {
                scanner.nextLine();
                System.out.print("Enter product brand: ");
                variableErrorHandling(scanner, "nextLine", "Enter product brand: ");
                String productBrand = scanner.nextLine();
                System.out.print("Enter product warranty: ");
                variableErrorHandling(scanner, "nextInt", "Enter product warranty(months): ");
                int productWarranty = scanner.nextInt();
                Product electronic = new Electronics(productId, productName, numAvailableItems, productPrice, productBrand, productWarranty);
                productList.add(electronic);
            } else {
                System.out.print("Enter product size: ");
                variableErrorHandling(scanner, "nextDouble", "Enter product size: ");
                double productSize = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Enter product colour: ");
                variableErrorHandling(scanner, "nextLine", "Enter product colour: ");
                String productColour = scanner.nextLine();
                Product clothing = new Clothing(productId, productName, numAvailableItems, productPrice, productSize, productColour);
                productList.add(clothing);
            }
            System.out.print("\nProduct added successfully!\n" +
                    "------------------------------------------------------\n" +
                    "Do you want to add another product? (Y/N): ");
            variableErrorHandling(scanner, "next", "Do you want to add another product? (Y/N): ");
            String answer2 = scanner.next();
            String answer2Lower = answer2.toLowerCase();
            if (answer2Lower.equals("y")) {
                addProduct(scanner);
            } else if (answer2Lower.equals("n")) {
                menu();
            } else {
                recursiveErrorHandling(scanner, "Add a new product to the system", null);
            }
        } else {
            recursiveErrorHandling(scanner, "Add a new product to the system", null);
        }
    }

    @Override
    public void deleteProduct(Scanner scanner) {
        if (productList.size() == 0) {
            System.out.println("The product list is empty!");
            menu();
        }
        System.out.print("------------------------------------------------------\n" +
                "Enter product id: ");
        variableErrorHandling(scanner, "next", "Enter product id: ");
        String productId = scanner.next();
        for (Product product : productList) {
            if (product.getProductId().equals(productId)) {
                if (product instanceof Electronics) {
                    Electronics electronics = (Electronics) product;
                    System.out.println(electronics.toString());
                } else {
                    Clothing clothing = (Clothing) product;
                    System.out.println(clothing.toString());
                }
                productList.remove(product);
                System.out.println("Product deleted successfully!");
                int totalProducts = productList.size();
                System.out.println("Total number of products left in the System: " + totalProducts);
            } else {
//                System.out.println("Invalid input!");
//                System.out.println("The product id does not exist in the system!");
                recursiveErrorHandling(scanner, "Delete a product from the system", "The product id does not exist in the system!");
            }
        }
        System.out.print("------------------------------------------------------\n" +
                "Do you want to delete another product? (Y/N): ");
        variableErrorHandling(scanner, "next", "Do you want to delete another product? (Y/N): ");
        String answer2 = scanner.next();
        String answer2Lower = answer2.toLowerCase();
        if (answer2Lower.equals("y")) {
            deleteProduct(scanner);
        } else if (answer2Lower.equals("n")) {
            menu();
        } else {
            recursiveErrorHandling(scanner, "Delete a product from the system", null);
        }
    }
    @Override
    public void getProductList() {
        Collections.sort(productList);
        if (productList.size() == 0) {
            System.out.println("The product list is empty!");
            menu();
        }
        for (Product product : productList) {
            if (product instanceof Electronics) {
                Electronics electronics = (Electronics) product;
                System.out.println(electronics.toString());
            } else {
                Clothing clothing = (Clothing) product;
                System.out.println(clothing.toString());
            }
        }
        System.out.println("\nProduct List ordered by product id successfully!");
    }
    @Override
    public void saveProductList(Scanner scanner) throws IOException {
//        System.out.println("Enter file name: ");
//        String newFile = scanner.next();
//        File file = new File("Existing Products/" + newFile + ".txt");
        File file = new File("Existing Products/saveProductList.txt");
        try(FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Product product : productList) {
                bufferedWriter.write(product.toString());
                bufferedWriter.newLine();
            }
            System.out.println("\nProduct List saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadProductList() {
        File file = new File("Existing Products/saveProductList.txt");
        if (!file.exists()) {
            System.out.println("The file does not exist!");
            menu();
        }
        try(Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] savedList = line.substring(8, line.length()-1).split(",");
                String productId = savedList[0].substring(11, savedList[0].length()-1);
                String productName = savedList[1].substring(13, savedList[1].length()-1);
                int numAvailableItems = Integer.parseInt(savedList[2].substring(18));
                double productPrice = Double.parseDouble(savedList[3].substring(13));
                if (savedList[4].startsWith("clothingSize")){
                    double productSize = Double.parseDouble(savedList[4].substring(13));
                    String productColour = savedList[5].substring(16, savedList[5].length()-1);
                    Product clothing = new Clothing(productId, productName, numAvailableItems, productPrice, productSize, productColour);
                } else {
                    String productBrand = savedList[4].substring(17, savedList[4].length()-1);
                    int productWarranty = Integer.parseInt(savedList[5].substring(25));
                    Product electronic = new Electronics(productId, productName, numAvailableItems, productPrice, productBrand, productWarranty);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recursiveErrorHandling(Scanner scanner, String message, String additionalMessage) {
        if (additionalMessage == null) {
            additionalMessage = "Please enter a valid input";
        }
        System.out.print("Invalid input!\n" +
                additionalMessage + "\n" +
                "------------------------------------------------------\n" +
                "1. " + message + "\n" +
                "2. Return to main menu\n" +
                "Select an option: ");
        variableErrorHandling(scanner, "nextInt", "Select an option: ");
        int answer3 = scanner.nextInt();
        if (answer3 == 1) {
            addProduct(scanner);
        } else if (answer3 == 2) {
            menu();
        } else {
            System.out.println("Invalid input!");
            recursiveErrorHandling(scanner, message, additionalMessage);
        }
    }

    public void variableErrorHandling(Scanner scanner, String type, String message) {
        switch (type) {
            case "nextLine":
                while (true) {
                    if (scanner.hasNextLine()) {
                        return;
                    } else {
                        System.out.println("Invalid input!");
                        System.out.println("Please enter a valid input");
                        scanner.nextLine();
                        System.out.println(message);
                    }
                }
            case "nextInt":
                while (true) {
                    if (scanner.hasNextInt()) {
                        return;
                    } else {
                        System.out.println("Invalid input!");
                        System.out.println("Please enter a valid input");
                        scanner.next();
                        System.out.println(message);
                    }
                }
            case "nextDouble":
                while (true) {
                    if (scanner.hasNextDouble()) {
                        return;
                    } else {
                        System.out.println("Invalid input!");
                        System.out.println("Please enter a valid input");
                        scanner.next();
                        System.out.println(message);
                    }
                }
            case "next":
                while (true) {
                    if (scanner.hasNext()) {
                        return;
                    } else {
                        System.out.println("Invalid input!");
                        System.out.println("Please enter a valid input");
                        scanner.next();
                        System.out.println(message);
                    }
                }
        }
    }
}
