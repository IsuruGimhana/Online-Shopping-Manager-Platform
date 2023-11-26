import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This interface represents the user Westminster Shopping Manager
 *
 * @author Isuru Gimhana
 */
public class WestminsterShoppingManager implements ShoppingManager{
    private ArrayList<Product> productList;
    private static final int MAX_PRODUCT = 50;

    public WestminsterShoppingManager() {
        this.productList = new ArrayList<>();
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
                saveProductList();
                break;
            default:
                System.out.println("Invalid input!");
                break;
        }
    }
    @Override
    public void addProduct(Scanner scanner) {
        if (this.productList.size() >= MAX_PRODUCT) {
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
            String productName = scanner.nextLine();
            System.out.print("Enter number of available items: ");
            int numAvailableItems = scanner.nextInt();
            System.out.print("Enter product price: ");
            double productPrice = scanner.nextDouble();
            if (answer == 1) {
                System.out.print("Enter product brand: ");
                String productBrand = scanner.nextLine();
                System.out.print("Enter product warranty: ");
                int productWarranty = scanner.nextInt();
                Product electronic = new Electronics(productId, productName, numAvailableItems, productPrice, productBrand, productWarranty);
                this.productList.add(electronic);
            } else {
                System.out.print("Enter product size: ");
                double productSize = scanner.nextDouble();
                System.out.print("Enter product colour: ");
                String productColour = scanner.nextLine();
                Product clothing = new Clothing(productId, productName, numAvailableItems, productPrice, productSize, productColour);
                this.productList.add(clothing);
            }
            System.out.print("Product added successfully!\n" +
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
            }
        } else {
            System.out.println("Invalid input!");
        }
    }

    @Override
    public void deleteProduct(Scanner scanner) {
        System.out.print("------------------------------------------------------\n" +
                "Enter product id: ");
        String productId = scanner.next();
        for (Product product : this.productList) {
            if (product.getProductId().equals(productId)) {
                if (product instanceof Electronics) {
                    Electronics electronics = (Electronics) product;
                    System.out.println(electronics.toString());
                } else {
                    Clothing clothing = (Clothing) product;
                    System.out.println(clothing.toString());
                }
                this.productList.remove(product);
                System.out.println("\nProduct deleted successfully!");
                int totalProducts = productList.size();
                System.out.println("\nTotal number of products left in the System: " + totalProducts);
            } else {
                System.out.println("Invalid input!");
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
            System.out.println("Invalid input!");
        }
    }
    @Override
    public void getProductList() {
        Collections.sort(productList);
        for (Product product : this.productList) {
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
    public void saveProductList() {

    }
}
