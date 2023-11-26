/**
 * This subclass of the Product abstract class represents electronics
 *
 * @author Isuru Gimhana
 */
public class Electronics extends Product{
    // instance variables
    private String productId; // product id
    private String productName; // product name
    private int numAvailableItems; // number of available items of the product
    private double productPrice; // the product price
    private String electronicBrand; // the brand of the Electronic product
    private int electronicWarrantyPeriod; // the warranty period of the Electronic product

    /**
     * Constructs a new electronics object
     * @param productId - the products id
     * @param productName - the product name
     * @param numAvailableItems - number of available items of the product
     * @param productPrice - the product price
     * @param electronicBrand - the brand of the Electronic product
     * @param electronicWarrantyPeriod - the warranty period of the Electronic product
     */
    public Electronics(String productId, String productName, int numAvailableItems, double productPrice, String electronicBrand, int electronicWarrantyPeriod) {
        super(productId, productName, numAvailableItems, productPrice);
        this.electronicBrand = electronicBrand;
        this.electronicWarrantyPeriod = electronicWarrantyPeriod;
    }

    /**
     * getters and setters
     */
    public String getElectronicBrand() {
        return electronicBrand;
    }
    public int getElectronicWarrantyPeriod() {
        return electronicWarrantyPeriod;
    }
    public void setElectronicBrand(String electronicBrand) {
        this.electronicBrand = electronicBrand;
    }
    public void setElectronicWarrantyPeriod(int electronicWarrantyPeriod) {
        this.electronicWarrantyPeriod = electronicWarrantyPeriod;
    }

    @Override
    public String toString() {
        return "Electronics{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", numAvailableItems=" + numAvailableItems +
                ", productPrice=" + productPrice +
                ", electronicBrand='" + electronicBrand + '\'' +
                ", electronicWarrantyPeriod=" + electronicWarrantyPeriod +
                '}';
    }
}
