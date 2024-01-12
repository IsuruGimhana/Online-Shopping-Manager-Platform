/**
 * This class represents the product table model
 *
 * @author Isuru Gimhana
 */

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ProductTableModel extends AbstractTableModel {
    //ArrayList to store the products
    private ArrayList<Product> productList;

    //Column names for the table
    private String[] columnNames = {"Product ID", "Name", "Category", "Price", "Info"};

    //Constructor
    public ProductTableModel(ArrayList<Product> productList) {
        this.productList = productList;
    }

    //Setter for the productList
    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    //Getter for the productList
    public ArrayList<Product> getProductList() {
        return productList;
    }


    //Overridden methods
    @Override
    public int getRowCount() {
        return productList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;
        if (productList.size() > 0 && rowIndex < productList.size()) {
            switch (columnIndex) {
                case 0:
                    temp = productList.get(rowIndex).getProductId();
                    break;
                case 1:
                    temp = productList.get(rowIndex).getProductName();
                    break;
                case 2:
                    if (productList.get(rowIndex) instanceof Electronics) {//Check the instance type and set the value to be Electronic or Clothing
                        temp = "Electronics";
                    } else {
                        temp = "Clothing";
                    }
                    break;
                case 3:
                    temp = String.format("%.2f", productList.get(rowIndex).getProductPrice()) + " LKR";
                    break;
                case 4: {
                    if (productList.get(rowIndex) instanceof Electronics) {
                        Electronics electronics = (Electronics) productList.get(rowIndex);
                        temp = electronics.getElectronicBrand() + ", " + electronics.getElectronicWarrantyPeriod() + " months warranty";
                    } else {
                        Clothing clothing = (Clothing) productList.get(rowIndex);
                        temp = clothing.getClothingSize() + ", " + clothing.getClothingColour();
                    }
                }
            }
        }
        return temp;
    }
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
}
