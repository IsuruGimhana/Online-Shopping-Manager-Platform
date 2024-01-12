/**
 * This class represents the main class
 *
 * @author Isuru Gimhana
 */

public class Main {
    public static void main(String[] args) {
        // create a new instance of WestminsterShoppingManager
        WestminsterShoppingManager westminsterShoppingManager = new WestminsterShoppingManager();

        // load the product list from the file
        westminsterShoppingManager.loadProductList();

        System.out.println("1. GUI\n" +
                "2. Manager Console");

        // get the menu type
        int menuType = westminsterShoppingManager.nextIntErrorHandling("Select an option: ", "1, 2", "==");

        if (menuType == 1) {
            // create a new instance of WestminsterShoppingCentre
            WestminsterShoppingCentre frame = new WestminsterShoppingCentre();
            frame.setVisible(true);
        } else {
            // call the menu method from the WestminsterShoppingManager class
            westminsterShoppingManager.menu();
        }
    }
}
