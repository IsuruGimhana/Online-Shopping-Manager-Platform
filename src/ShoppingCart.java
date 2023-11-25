/**
 * This class represents the user shopping cart
 *
 * @author Isuru Gimhana
 */
import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Product> cartList;

    public ShoppingCart() {
        this.cartList = new ArrayList<>();
    }
    public void addProduct(Product product) {
        this.cartList.add(product);
    }

    public void removeProduct(Product product) {
        this.cartList.remove(product);
    }

    public double getTotalCost() {
        double totalCost = 0;
        for (Product product : cartList) {
            totalCost += product.getProductPrice();
        }
        return totalCost;
    }
}
