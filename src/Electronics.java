/**
 * This subclass of the Product abstract class represents electronics
 *
 * @author Isuru Gimhana
 */
public class Electronics extends Product{
    private String productId; // product id
    private String productName; // product name
    private int numAvailableItems; // number of available items of the product
    private double productPrice; // the product price
    private String electronicBrand; // the brand of the Electronic product
    private int electronicWarrantyPeriod; // the warranty period of the Electronic product
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
}
