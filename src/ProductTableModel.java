import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ProductTableModel extends AbstractTableModel {
    private ArrayList<Product> productList;
    private String[] columnNames = {"Product ID", "Name", "Category", "Price", "Info"};
    public ProductTableModel() {}

    public void setProductList() {
        productList = WestminsterShoppingManager.getProductList();
    }

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
            if (columnIndex == 0) {
                temp = productList.get(rowIndex).getProductId();
            }
            else if (columnIndex == 1) {
                temp = productList.get(rowIndex).getProductName();
            }
            else if (columnIndex == 2) {
                if(productList.get(rowIndex) instanceof Electronics)//Check the instance type and set the value to be Electronic or Clothing
                    temp = "Electronics";
                else
                    temp = "Clothing";
            }
            else if (columnIndex == 3) {
                temp = productList.get(rowIndex).getProductPrice();
            }
            else if (columnIndex == 4) {
                if (productList.get(rowIndex) instanceof Electronics) {
                    Electronics electronics = (Electronics) productList.get(rowIndex);
                    temp = electronics.getElectronicBrand() + ", " + electronics.getElectronicWarrantyPeriod() + " months warranty";
                } else {
                    Clothing clothing = (Clothing) productList.get(rowIndex);
                    temp = clothing.getClothingSize() + ", " + clothing.getClothingColour();
                }
            }
        }
        return temp;
    }
    //show the column name
    public String getColumnName(int col) {
        return columnNames[col];
    }

}
