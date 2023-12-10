import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

/**
 * This interface represents the user Westminster Shopping Manager
 *
 * @author Isuru Gimhana
 */
public class WestminsterShoppingManager extends JFrame implements ShoppingManager{
    private static ArrayList<Product> productList;
    private static final int MAX_PRODUCT = 50;
    JPanel headerPanel;
    JPanel bodyPanel;
    JPanel footerPanel;
    JPanel sortPanel;
    JButton shoppingCart;
    public WestminsterShoppingManager() {
        productList = new ArrayList<>();
    }

    public  WestminsterShoppingManager(String title) throws HeadlessException {
        super(title);
        headerPanel = new JPanel();
        bodyPanel = new JPanel();
        footerPanel = new JPanel();
        sortPanel = new JPanel();

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(headerPanel, BorderLayout.NORTH);
        this.getContentPane().add(bodyPanel, BorderLayout.CENTER);
        this.getContentPane().add(footerPanel, BorderLayout.SOUTH);
        headerPanel.setLayout(new BorderLayout());
//        panel2.setLayout(new BorderLayout());
//        panel3.setLayout(new BorderLayout());
        headerPanel.setBackground(Color.BLACK);
        bodyPanel.setBackground(Color.GRAY);
        footerPanel.setBackground(Color.darkGray);
        sortPanel.setBackground(Color.pink);

        shoppingCart = new JButton("Shopping Cart");
        headerPanel.add(sortPanel, BorderLayout.WEST);
        headerPanel.add(shoppingCart, BorderLayout.EAST);

        sortPanel.setLayout(new BorderLayout(5,5));
        JPanel sortTypePanel = new JPanel();
        JPanel sortValue = new JPanel();
        sortPanel.add(sortTypePanel, BorderLayout.WEST);
        sortPanel.add(sortValue, BorderLayout.EAST);
        sortTypePanel.setLayout(new GridLayout(2,1));
        sortValue.setLayout(new GridLayout(2,1));

        JLabel category = new JLabel("Category");
        sortTypePanel.add(category);
        String[] productCategory = {"All", "Electronics", "Clothing"};
        JComboBox categoryDropDown = new JComboBox(productCategory);
        categoryDropDown.setBorder(new EmptyBorder(5,0,0,0));
        sortValue.add(categoryDropDown);
        JLabel sort = new JLabel("Sort");
        sortTypePanel.add(sort);
        JCheckBox ascendingOrder = new JCheckBox("a-z");
        sortValue.add(ascendingOrder);
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. GUI\n" +
                "2. Manager Console");
        int menuType = nextIntErrorHandling(scanner, "Select an option: ", "1, 2", "==");
        if (menuType == 1) {
            WestminsterShoppingManager frame = new WestminsterShoppingManager("Westminster Shopping Centre");
//            frame.setTitle("Westminster Shopping Centre");
            frame.setSize(600,400);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        else {
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
            int menuAnswer = nextIntErrorHandling(scanner, "Select an option: ", "1, 2, 3, 4, 5","==");
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
    }

    @Override
    public void addProduct(Scanner scanner) {
        if (productList.size() >= MAX_PRODUCT) {
            System.out.println("The product list is full!");
            menu();
        }
        System.out.println("------------------------------------------------------\n" +
                "1. Add a new Electronic to the system\n" +
                "2. Add a new Clothing to the system\n" +
                "------------------------------------------------------");
        int answer = nextIntErrorHandling(scanner, "Select an option: ", "1, 2", "==");
        System.out.println("------------------------------------------------------");
        String productId;
        if (answer == 1) {
            productId = nextErrorHandling(scanner, "Enter product id (format: E001): ", "4", "E==");
        } else {
            productId = nextErrorHandling(scanner, "Enter product id (format: C001): ", "4", "C==");
        }
//            scanner.nextLine();
        for (Product product : productList) {
            if (product.getProductId().equals(productId)) {
                System.out.println("\nProduct already exists!\n" +
                        "------------------------------------------------------");
                String answer2 = nextErrorHandling(scanner, "Do you want to add a new product? (Y/N): ", "Y, N", "===");
                String answer2Lower = answer2.toLowerCase();
                if (answer2Lower.equals("y")) {
                    addProduct(scanner);
                } else {
                    menu();
                }
            }
        }
        String productName = nextLineErrorHandling(scanner, "Enter product name: ", "3", ">=");
        int numAvailableItems = nextIntErrorHandling(scanner, "Enter number of available items: ", "0",">");
        double productPrice = nextDoubleErrorHandling(scanner, "Enter product price: ", "0",">");
        if (answer == 1) {
//                scanner.nextLine();
            String productBrand = nextLineErrorHandling(scanner, "Enter product brand: ", "3", ">=");
            int productWarranty = nextIntErrorHandling(scanner, "Enter product warranty(months): ", "0",">=");
            Product electronic = new Electronics(productId, productName, numAvailableItems, productPrice, productBrand, productWarranty);
            productList.add(electronic);
        } else {
            String productSize = nextErrorHandling(scanner, "Enter product size: ", "S, M, L, XL","===");
//                scanner.nextLine();
            String productColour = nextLineErrorHandling(scanner, "Enter product colour: ","3", ">=");
            Product clothing = new Clothing(productId, productName, numAvailableItems, productPrice, productSize, productColour);
            productList.add(clothing);
        }
        System.out.println("\nProduct added successfully!\n" +
                "------------------------------------------------------");
        String answer2 = nextErrorHandling(scanner, "Do you want to add another product? (Y/N): ", "Y, N", "===");
        String answer2Lower = answer2.toLowerCase();
        if (answer2Lower.equals("y")) {
            addProduct(scanner);
        } else {
            menu();
        }
    }

    @Override
    public void deleteProduct(Scanner scanner) {
        if (productList.size() == 0) {
            System.out.println("\n" +
                    "The product list is empty!");
            menu();
        }
        System.out.println("------------------------------------------------------\n" +
                "1. Delete an Electronic product from the system\n" +
                "2. Delete a Clothing product from the system\n" +
                "------------------------------------------------------");
        int answer = nextIntErrorHandling(scanner, "Select an option: ", "1, 2", "==");
        System.out.println("------------------------------------------------------");
        String productId;
        if (answer == 1 ) {
            productId = nextErrorHandling(scanner, "Enter product id (format: E001): ", "4", "E==");
        } else {
            productId = nextErrorHandling(scanner, "Enter product id (format: C001): ", "4", "C==");
        }
        Iterator<Product> iterator = productList.iterator();
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
                productList.remove(product);
                System.out.println("\nProduct deleted successfully!");
                int totalProducts = productList.size();
                System.out.println("Total number of products left in the System: " + totalProducts);
                break;
            } else {
                System.out.println("\n" +
                        "Product not found!");
            }
        }
//        for (Product product : productList) {
//        }
        System.out.println("------------------------------------------------------");
        String answer2 = nextErrorHandling(scanner, "Do you want to delete another product? (Y/N): ", "Y, N", "===");
        String answer2Lower = answer2.toLowerCase();
        if (answer2Lower.equals("y")) {
            deleteProduct(scanner);
        } else {
            menu();
        }
//        else {
//            recursiveErrorHandling(scanner, "Delete a product from the system", null);
//        }
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
        menu();
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
        menu();
    }

    public void loadProductList() {
        File file = new File("Existing Products/saveProductList.txt");
        if (!file.exists()) {
//            System.out.println("The file does not exist!");
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
                    productList.add(electronic);
                } else {
                    String[] savedList = line.substring(9, line.length()-1).split(", ");
                    String productId = savedList[0].substring(11, savedList[0].length()-1);
                    String productName = savedList[1].substring(13, savedList[1].length()-1);
                    int numAvailableItems = Integer.parseInt(savedList[2].substring(18));
                    double productPrice = Double.parseDouble(savedList[3].substring(13));
                    String productSize = savedList[4].substring(14, savedList[4].length()-1);
                    String productColour = savedList[5].substring(16, savedList[5].length()-1);
                    Product clothing = new Clothing(productId, productName, numAvailableItems, productPrice, productSize, productColour);
                    productList.add(clothing);
                }
//                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void recursiveErrorHandling(Scanner scanner, String message, String additionalMessage) {
//        if (additionalMessage == null) {
//            additionalMessage = "Please enter a valid input";
//        }
//        System.out.print("Invalid input!\n" +
//                additionalMessage + "\n" +
//                "------------------------------------------------------\n" +
//                "1. " + message + "\n" +
//                "2. Return to main menu\n" +
//                "Select an option: ");
//        variableErrorHandling(scanner, "nextInt", "Select an option: ");
//        int answer3 = scanner.nextInt();
//        if (answer3 == 1) {
//            addProduct(scanner);
//        } else if (answer3 == 2) {
//            menu();
//        } else {
//            System.out.println("Invalid input!");
//            recursiveErrorHandling(scanner, message, additionalMessage);
//        }
//    }

    public int nextIntErrorHandling(Scanner scanner, String message, String condition, String operator) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                String[] newCondition = condition.split(", ");
//                for (int i = 0; i < newCondition.length; i++) {
//                    newCondition[i] = newCondition[i].trim();
//                }
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
//            System.out.println("Please enter a valid input");
//            System.out.println("------------------------------------------------------");
//            scanner.next();
        }
    }

    public String nextLineErrorHandling(Scanner scanner, String message, String condition, String operator) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextLine()) {
                String value = scanner.nextLine();
                String[] newCondition = condition.split(", ");
//                for (int i = 0; i < newCondition.length; i++) {
//                    newCondition[i] = newCondition[i].trim();
//                }
                for (String parameter : newCondition) {
                    if (operator.equals(">=")) {
                        if (value.length() >= Integer.parseInt(parameter)) {
//                            scanner.nextLine();
                            return value;
                        }
                    }
                }
            }
//            scanner.nextLine();
            System.out.println("\n" +
                    "Invalid input!\n" +
                    "Please enter a valid input\n" +
                    "------------------------------------------------------");
//            scanner.nextLine();
        }
    }

    public String nextErrorHandling(Scanner scanner, String message, String condition, String operator) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNext()) {
                String value = scanner.next();
                String[] newCondition = condition.split(", ");
//                for (int i = 0; i < newCondition.length; i++) {
//                    newCondition[i] = newCondition[i].trim();
//                }
                for (String parameter : newCondition) {
                    if (operator.equals("E==") || operator.equals("C==")) {
                        if ((value.length() == Integer.parseInt(parameter)) && (value.substring(0,1).toUpperCase().equals(operator.substring(0,1)))) {
                            scanner.nextLine();
                            return value.toUpperCase();
                        }
                    }
                    else {
                        if (value.toUpperCase().equals(parameter)) {
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
//            scanner.next();
        }
    }

    public double nextDoubleErrorHandling(Scanner scanner, String message, String condition, String operator) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                String[] newCondition = condition.split(", ");
//                for (int i = 0; i < newCondition.length; i++) {
//                    newCondition[i] = newCondition[i].trim();
//                }
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
//    public int variableErrorHandling(Scanner scanner, String type, String message, String condition) {
//        switch (type) {
//            case "nextLine":
////                while (true) {
////                    if (scanner.hasNextLine()) {
////                        return;
////                    } else {
////                        System.out.println("Invalid input!");
////                        System.out.println("Please enter a valid input");
////                        scanner.nextLine();
////                        System.out.println(message);
////                    }
////                }
//            case "nextInt":
////                while (true) {
////                    if (scanner.hasNextInt()) {
////                        int value = scanner.nextInt();
////                        if (condition == null) {
////                            return value;
////                        } else {
////                            String[] newCondition = condition.split(",");
////                            for (String parameter : newCondition) {
////                                if (value == Integer.parseInt(parameter)) {
////                                    return value;
////                                }
////                            }
////                        }
////                    }
////                    System.out.println("Invalid input!");
////                    System.out.println("Please enter a valid input");
////                    scanner.next();
////                    System.out.println(message);
////                }
//            case "nextDouble":
//                while (true) {
//                    if (scanner.hasNextDouble()) {
//                        return;
//                    } else {
//                        System.out.println("Invalid input!");
//                        System.out.println("Please enter a valid input");
//                        scanner.next();
//                        System.out.println(message);
//                    }
//                }
//            case "next":
//                while (true) {
//                    if (scanner.hasNext()) {
//                        return;
//                    } else {
//                        System.out.println("Invalid input!");
//                        System.out.println("Please enter a valid input");
//                        scanner.next();
//                        System.out.println(message);
//                    }
//                }
//        }
//    }
}
