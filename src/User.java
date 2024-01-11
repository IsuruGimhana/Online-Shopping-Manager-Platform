import java.util.ArrayList;

/**
 * This class represents the user account
 *
 * @author Isuru Gimhana
 */
public class User {
    // instance variables
    private static ArrayList<Product> purchasedProductList; // the list of purchased products
    private String userName; // user name
    private String userPassword; // user password

    /**
     * Constructs a new user object
     * @param userName - the user name
     * @param userPassword - the user password
     */
    public User(String userName, String userPassword){
        this.purchasedProductList = new ArrayList<>();
        this.userName = userName;
        this.userPassword = userPassword;
    }

    /**
     * getters and setters
     */
    public String getUserName(){
        return userName;
    }
    public String getUserPassword(){
        return userPassword;
    }


    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setUserPassword(String userPassword){
        this.userPassword = userPassword;
    }

    public ArrayList<Product> getPurchasedProductList() {
        return purchasedProductList;
    }
    public void setPurchasedProductList(ArrayList<Product> purchasedProductList) {
        this.purchasedProductList = purchasedProductList;
    }
}
