import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class AddProductTest {

    //create an instance of the WestminsterShoppingManager class
    WestminsterShoppingManager westminsterShoppingManager = new WestminsterShoppingManager();

    @Test
    public void addElectronicProductTest() {
        String userInput = "1\nE001\niPhone 12\n10\n200000.00\nApple\n12\n1\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
//        int answer = 1;
//        //test data
//        String productId = "E001";
//        String productName = "iPhone 12";
//        int numAvailableItems = 10;
//        double productPrice = 200000.00;
//        String electronicBrand = "Apple";
//        int electronicWarrantyPeriod = 12;

//        String userInput = answer + "\n" + productId + "\n" + productName + "\n" + numAvailableItems + "\n" + productPrice + "\n" + electronicBrand + "\n" + electronicWarrantyPeriod + "\n" + answer + "\n";
//        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        //call the addElectronicProduct method
        westminsterShoppingManager.addProduct();

//        assertTrue(WestminsterShoppingManager.getMaxProduct() >= westminsterShoppingManager.getProductList().size());
//        Product addedProduct = westminsterShoppingManager.getProductList().get(0);
//        assertTrue(addedProduct instanceof Electronics);
//        assertEquals(productId, addedProduct.getProductId());
//        assertEquals(productName, addedProduct.getProductName());
//        assertEquals(numAvailableItems, addedProduct.getNumAvailableItems());
//        assertEquals(productPrice, addedProduct.getProductPrice());
//        assertEquals(electronicBrand, ((Electronics) addedProduct).getElectronicBrand());
//        assertEquals(electronicWarrantyPeriod, ((Electronics) addedProduct).getElectronicWarrantyPeriod());
    }
}