/**
 * This abstract class represents a product
 *
 * @author Isuru Gimhana
 */
public abstract class Product {
    // instance variables
    private String productId; // product id
    private String productName; // product name
    private int numAvailableItems; // number of available items of the product
    private double productPrice; // the product price
    /**
     * Constructs a new product object
     * @param productId - the products id
     * @param productName - the product name
     * @param numAvailableItems - number of available items of the product
     * @param productPrice - the product price
    */
    public Product(String productId, String productName, int numAvailableItems, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.numAvailableItems = numAvailableItems;
        this.productPrice = productPrice;
    }
/**
 * getters and setters
*/
    public String getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }
    public int getNumAvailableItems() {
        return numAvailableItems;
    }
    public double getProductPrice() {
        return productPrice;
    }

    public void setProductId() {
        this.productId = productId;
    }
    public void setProductName() {
        this.productName = productName;
    }
    public void setNumAvailableItems() {
        this.numAvailableItems = numAvailableItems;
    }
    public void setProductPrice() {
        this.productPrice = productPrice;
    }
}