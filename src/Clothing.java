/**
 * This subclass of the Product abstract class represents clothing
 *
 * @author Isuru Gimhana
 */
public class Clothing extends Product{
    // instance variables
    private String productId; // product id
    private String productName; // product name
    private int numAvailableItems; // number of available items of the product
    private double productPrice; // the product price
    private double clothingSize; // the size of the clothing
    private  String clothingColour; // the colour of the clothing

    /**
     * Constructs a new clothing object
     * @param productId - the products id
     * @param productName - the product name
     * @param numAvailableItems - number of available items of the product
     * @param productPrice - the product price
     * @param clothingSize - the size of the clothing
     * @param clothingColour - the colour of the clothing
     */
    public Clothing(String productId, String productName, int numAvailableItems, double productPrice, double clothingSize, String clothingColour) {
        super(productId, productName, numAvailableItems, productPrice);
        this.clothingSize = clothingSize;
        this.clothingColour = clothingColour;
    }

    /**
     * getters and setters
     */
    public double getClothingSize() {
        return clothingSize;
    }
    public String getClothingColour() {
        return clothingColour;
    }
    public void setClothingSize(double clothingSize) {
        this.clothingSize = clothingSize;
    }
    public void setClothingColour(String clothingColour) {
        this.clothingColour = clothingColour;
    }

    @Override
    public String toString() {
        return "Clothing{" +
                "productId='" + productId + "'" +
                ", productName='" + productName + "'" +
                ", numAvailableItems=" + numAvailableItems +
                ", productPrice=" + productPrice +
                ", clothingSize=" + clothingSize +
                ", clothingColour='" + clothingColour + "'" +
                "}";
    }
}
