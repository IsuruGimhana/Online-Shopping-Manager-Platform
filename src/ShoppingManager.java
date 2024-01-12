/**
 * This interface represents the shopping manager
 *
 * @author Isuru Gimhana
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public interface ShoppingManager {
    public abstract void menu();
    public abstract void addProduct();
    public abstract void deleteProduct();
    public abstract void printProductList();
    public abstract void saveProductList() throws IOException;
    public abstract void loadProductList();
    public abstract int nextIntErrorHandling(String message, String regex, String operator);
    public abstract String nextLineErrorHandling(String message, String regex, String operator);
    public abstract String nextErrorHandling(String message, String regex, String operator);
    public abstract double nextDoubleErrorHandling(String message, String regex, String operator);
}
