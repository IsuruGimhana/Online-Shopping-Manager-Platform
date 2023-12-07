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
                "4. Exit the program\n" +
                "------------------------------------------------------\n" +
                "Select an option: ");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt()) {
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
            default:
                System.out.println("Invalid input!");
                break;
        }
    }
    @Override
    public void addProduct(Scanner scanner) {
        if (productList.size() >= MAX_PRODUCT) {
            System.out.println("The product list is full!");
            return;
        }
        System.out.print("------------------------------------------------------\n" +
                "1. Add a new Electronic to the system\n" +
                "2. Add a new Clothing to the system\n" +
                "------------------------------------------------------\n" +
                "Select an option: ");
        int answer = scanner.nextInt();
        if (answer == 1 || answer == 2) {
            System.out.print("------------------------------------------------------\n" +
                    "Enter product id: ");
            String productId = scanner.next();
            System.out.print("Enter product name: ");
            scanner.nextLine();
            String productName = scanner.nextLine();
            System.out.print("Enter number of available items: ");
            int numAvailableItems = scanner.nextInt();
            System.out.print("Enter product price: ");
            double productPrice = scanner.nextDouble();
            if (answer == 1) {
                System.out.print("Enter product brand: ");
                scanner.nextLine();
                String productBrand = scanner.nextLine();
                System.out.print("Enter product warranty: ");
                int productWarranty = scanner.nextInt();
                Product electronic = new Electronics(productId, productName, numAvailableItems, productPrice, productBrand, productWarranty);
                productList.add(electronic);
            } else {
                System.out.print("Enter product size: ");
                double productSize = scanner.nextDouble();
                System.out.print("Enter product colour: ");
                scanner.nextLine();
                String productColour = scanner.nextLine();
                Product clothing = new Clothing(productId, productName, numAvailableItems, productPrice, productSize, productColour);
                productList.add(clothing);
            }
            System.out.print("\nProduct added successfully!\n" +
                    "------------------------------------------------------\n" +
                    "Do you want to add another product? (Y/N): ");
            String answer2 = scanner.next();
            String answer2Lower = answer2.toLowerCase();
            if (answer2Lower.equals("y")) {
                addProduct(scanner);
            } else if (answer2Lower.equals("n")) {
                return;
            } else {
                System.out.println("Invalid input!");
                inputErrorHandling(scanner, "Add a new product to the system");
            }
        } else {
            inputErrorHandling(scanner, "Add a new product to the system");
//            System.out.print("Invalid input!\n" +
//                    "Please enter a valid input\n" +
//                    "------------------------------------------------------\n" +
//                    "1. Add a new Electronic to the system\n" +
//                    "2. Return to main menu\n" +
//                    "Select an option: ");
//            int answer3 = scanner.nextInt();
//            if (answer3 == 1) {
//                addProduct(scanner);
//            } else if (answer3 == 2) {
//                menu();
//            } else {
//                System.out.println("Invalid input!");
//            }

        }
    }

    @Override
    public void deleteProduct(Scanner scanner) {
        System.out.print("------------------------------------------------------\n" +
                "Enter product id: ");
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
                System.out.println("\nProduct deleted successfully!");
                int totalProducts = productList.size();
                System.out.println("\nTotal number of products left in the System: " + totalProducts);
            } else {
                System.out.println("\nInvalid input!");
            }
        }
        System.out.print("------------------------------------------------------\n" +
                "Do you want to delete another product? (Y/N): ");
        String answer2 = scanner.next();
        String answer2Lower = answer2.toLowerCase();
        if (answer2Lower.equals("y")) {
            deleteProduct(scanner);
        } else if (answer2Lower.equals("n")) {
            return;
        } else {
            System.out.println("\nInvalid input!");
        }
    }
    @Override
    public void getProductList() {
        Collections.sort(productList);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadProductList() {
        File file = new File("Existing Products/saveProductList.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred! while creating the file");
                e.printStackTrace();
            }
            return;
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

    public void inputErrorHandling(Scanner scanner, String message) {
        System.out.print("Invalid input!\n" +
                "Please enter a valid input\n" +
                "------------------------------------------------------\n" +
                "1. " + message + "\n" +
                "2. Return to main menu\n" +
                "Select an option: ");
        int answer3 = scanner.nextInt();
        if (answer3 == 1) {
            addProduct(scanner);
        } else if (answer3 == 2) {
            menu();
        } else {
            System.out.println("Invalid input!");
        }
    }
}
