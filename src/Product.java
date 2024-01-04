/**
 * This abstract class represents a product
 *
 * @author Isuru Gimhana
 */
public abstract class Product implements Comparable<Product> {
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

    public Product(Product cartProduct) {
        this.productId = cartProduct.getProductId();
        this.productName = cartProduct.getProductName();
        this.numAvailableItems = cartProduct.getNumAvailableItems();
        this.productPrice = cartProduct.getProductPrice();
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
    public void setNumAvailableItems(int numAvailableItems) {
        this.numAvailableItems = numAvailableItems;
    }
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "productId='" + productId + "'" +
                ", productName='" + productName + "'" +
                ", numAvailableItems=" + numAvailableItems +
                ", productPrice=" + productPrice;
    }
    public int compareTo(Product product) {
        // compare  the product id's alphabetically first
        int comparedStringValue = this.productId.compareToIgnoreCase(product.getProductId());
        // if the product id's are not the same, return the comparison
        if (comparedStringValue != 0) {
            return comparedStringValue;
        }
        // if the product id's are the same, compare the product id's numerically
        int comparedThisIntValue = Integer.parseInt(this.productId.substring(1));
        int comparedProductIntValue = Integer.parseInt(product.getProductId().substring(1));
        return comparedThisIntValue - comparedProductIntValue;
    }
}