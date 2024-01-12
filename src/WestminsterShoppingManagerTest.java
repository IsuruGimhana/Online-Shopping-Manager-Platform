import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.*;
public class WestminsterShoppingManagerTest {
    WestminsterShoppingManager westminsterShoppingManager = new WestminsterShoppingManager();

    @Test
    public void addElectronicProductTest() {
        String userInput = "1\n\nE010\niPhone 12\n10\n200000.00\nApple\n12\nn\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        westminsterShoppingManager.addProduct();

//        assertTrue(WestminsterShoppingManager.getMaxProduct() >= westminsterShoppingManager.getProductList().size());
//        Product addedProduct = westminsterShoppingManager.getProductList().get(0);
//        assertTrue(addedProduct instanceof Electronics);
//        assertEquals(1, addedProduct.getProductId());
//        assertEquals("E001", addedProduct.getProductId());
//        assertEquals("iPhone 12", addedProduct.getProductName());
//        assertEquals(10, addedProduct.getNumAvailableItems());
//        assertEquals(200000.00, addedProduct.getProductPrice());
//        assertEquals("Apple", ((Electronics) addedProduct).getElectronicBrand());
//        assertEquals(12, ((Electronics) addedProduct).getElectronicWarrantyPeriod());
    }

}