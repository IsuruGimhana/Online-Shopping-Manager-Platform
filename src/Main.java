public class Main {
    public static void main(String[] args) {
        WestminsterShoppingManager westminsterShoppingManager = new WestminsterShoppingManager();
        westminsterShoppingManager.loadProductList();
        System.out.println("1. GUI\n" +
                "2. Manager Console");
        int menuType = westminsterShoppingManager.nextIntErrorHandling("Select an option: ", "1, 2", "==");
        if (menuType == 1) {
            WestminsterShoppingCentre frame = new WestminsterShoppingCentre();
            frame.setVisible(true);
        } else {
            westminsterShoppingManager.menu();
        }
    }
}
