import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

public class WestminsterShoppingCentre extends JFrame{
    JPanel headerPanel;
    JPanel bodyPanel;
    JPanel footerPanel;
    JPanel sortPanel;
    JButton shoppingCart;

    public WestminsterShoppingCentre() throws HeadlessException {
        ArrayList<Product> productList = WestminsterShoppingManager.getProductList();
        setTitle("Westminster Shopping Centre");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        headerPanel = new JPanel();
        bodyPanel = new JPanel();
        footerPanel = new JPanel();
        sortPanel = new JPanel();

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(headerPanel, BorderLayout.NORTH);
        this.getContentPane().add(bodyPanel, BorderLayout.CENTER);
        this.getContentPane().add(footerPanel, BorderLayout.SOUTH);
        headerPanel.setLayout(new BorderLayout());

        headerPanel.setBackground(Color.BLACK);
        bodyPanel.setBackground(Color.GRAY);
        footerPanel.setBackground(Color.darkGray);
        sortPanel.setBackground(Color.pink);

        shoppingCart = new JButton("Shopping Cart");

        headerPanel.add(sortPanel, BorderLayout.WEST);
        headerPanel.add(shoppingCart, BorderLayout.EAST);

        sortPanel.setLayout(new BorderLayout(5,5));
        sortPanel.setBorder(BorderFactory.createEmptyBorder(2,15,2,5));
        JPanel sortTypePanel = new JPanel();
        JPanel sortValue = new JPanel();
        sortPanel.add(sortTypePanel, BorderLayout.WEST);
        sortPanel.add(sortValue, BorderLayout.EAST);
        sortTypePanel.setLayout(new GridLayout(2,1));
        sortValue.setLayout(new GridLayout(2,1));

        JLabel category = new JLabel("Category");
        sortTypePanel.add(category);
        String[] productCategory = {"All", "Electronics", "Clothing"};
        JComboBox categoryDropDown = new JComboBox(productCategory);
        categoryDropDown.setBorder(new EmptyBorder(5,0,0,0));
        sortValue.add(categoryDropDown);
        JLabel sort = new JLabel("Sort");
        sortTypePanel.add(sort);
        JCheckBox ascendingOrder = new JCheckBox("a-z");
        sortValue.add(ascendingOrder);

        //table
        String[] columnNames = {"Product ID", "Name", "Category", "Price", "Info"};
        Object[][] tableData = new Object[productList.size()][5];
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            tableData[i][0] = product.getProductId();
            tableData[i][1] = product.getProductName();
            String info = "";
            if (product instanceof Electronics) {
                tableData[i][2] = "Electronics";
                Electronics electronics = (Electronics) product;
                info = electronics.getElectronicBrand() + ", " + electronics.getElectronicWarrantyPeriod() + " months warranty";
            } else {
                tableData[i][2] = "Clothing";
                Clothing clothing = (Clothing) product;
                info = clothing.getClothingSize() + ", " + clothing.getClothingColour();
            }
            tableData[i][3] = product.getProductPrice();
            tableData[i][4] = info;
        }
        TableModel tableModel = new DefaultTableModel(tableData, columnNames);
        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        bodyPanel.add(tableScrollPane);
    }
}
