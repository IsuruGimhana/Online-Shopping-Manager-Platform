import java.io.IOException;
import java.util.Scanner;

public interface ShoppingManager {
    public abstract void addProduct();
    public abstract void deleteProduct();
    public abstract void getProductList();
    public abstract void saveProductList() throws IOException;
}
