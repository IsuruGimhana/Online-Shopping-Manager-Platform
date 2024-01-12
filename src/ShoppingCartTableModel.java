import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ShoppingCartTableModel extends AbstractTableModel {
    private ArrayList<Product> productList;
    private int quantity;
    private String[] columnNames = {"Product", "Quantity", "Price"};
    public ShoppingCartTableModel(ArrayList productList, int quantity) {
        this.productList = productList;
        this.quantity = quantity;
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
    public boolean isCellEditable(int rowIndex, int columnIndex) {//Make the quantity column editable
        return columnIndex == 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;
        if (productList.size() > 0 && rowIndex < productList.size()) {
            switch (columnIndex) {
                case 0:
                    if (productList.get(rowIndex) instanceof Electronics) {//Check the instance type and set the value to be Electronic or Clothing
                        Electronics electronics = (Electronics) productList.get(rowIndex);
                        temp = electronics.getProductId() + ", " + electronics.getProductName() + ", " + electronics.getElectronicBrand() + ", " + electronics.getElectronicWarrantyPeriod() + " months warranty";
                    } else {
                        Clothing clothing = (Clothing) productList.get(rowIndex);
                        temp = clothing.getProductId() + ", " + clothing.getProductName() + ", " + clothing.getClothingSize() + ", " + clothing.getClothingColour();
                    }
                    break;
                case 1:
                    int availableQuantity = productList.get(rowIndex).getNumAvailableItems();
                    if (quantity <= availableQuantity) {
                        temp = quantity;
                    } else {
                        temp = "Not enough items available";
                    }
                    break;
                case 2:
                    temp = String.format("%.2f", productList.get(rowIndex).getProductPrice()) + " LKR";
                    break;
            }
        }
        return temp;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
}
