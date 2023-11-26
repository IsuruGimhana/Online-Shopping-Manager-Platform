import java.util.Scanner;

public interface ShoppingManager {
    public abstract void addProduct(Scanner scanner);
    public abstract void deleteProduct(Scanner scanner);
    public abstract void getProductList();
    public abstract void saveProductList();
}
