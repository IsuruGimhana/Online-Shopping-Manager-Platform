import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WestminsterShoppingCentre extends JFrame{
    private ArrayList<Product> productList;
    private JPanel headerPanel;
    private JPanel bodyPanel;
    private JPanel footerPanel;
    private JPanel sortPanel;
    private JPanel infoPanel;
    private JButton shoppingCart;

    public WestminsterShoppingCentre() throws HeadlessException {
        ArrayList<Product> productList = WestminsterShoppingManager.getProductList();
        setTitle("Westminster Shopping Centre");
        setSize(1280,720);
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
        footerPanel.setBackground(Color.LIGHT_GRAY);
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
//        String[] columnNames = {"Product ID", "Name", "Category", "Price", "Info"};
//        Object[][] tableData = new Object[productList.size()][5];
//        for (int i = 0; i < productList.size(); i++) {
//            Product product = productList.get(i);
//            tableData[i][0] = product.getProductId();
//            tableData[i][1] = product.getProductName();
//            String info = "";
//            if (product instanceof Electronics) {
//                tableData[i][2] = "Electronics";
//                Electronics electronics = (Electronics) product;
//                info = electronics.getElectronicBrand() + ", " + electronics.getElectronicWarrantyPeriod() + " months warranty";
//            } else {
//                tableData[i][2] = "Clothing";
//                Clothing clothing = (Clothing) product;
//                info = clothing.getClothingSize() + ", " + clothing.getClothingColour();
//            }
//            tableData[i][3] = product.getProductPrice();
//            tableData[i][4] = info;
//        }
//        TableModel tableModel = new DefaultTableModel(tableData, columnNames);
//        JTable table = new JTable(tableModel);
        ProductTableModel tableModel = new ProductTableModel();
        tableModel.setProductList();
        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        bodyPanel.add(tableScrollPane);

        //footer
        footerPanel.setLayout(new BorderLayout());
        JLabel detailsHeader = new JLabel("Selected Product Details");
        footerPanel.add(detailsHeader, BorderLayout.NORTH);
        TableListener tableListener = new TableListener(table);
        table.getSelectionModel().addListSelectionListener(tableListener);

        //Add to cart button
        JButton addToCart = new JButton("Add to Cart");
        footerPanel.add(addToCart, BorderLayout.SOUTH);

        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(6,1));
        footerPanel.add(infoPanel, BorderLayout.CENTER);


    }
    private class TableListener implements ListSelectionListener {
        private JTable table;
        public TableListener(JTable table) {
            this.table = table;
        }
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (table.getSelectedRow() != -1) {
                int selectedRow = table.getSelectedRow();
                productList = WestminsterShoppingManager.getProductList();
                Product product = productList.get(selectedRow);

                infoPanel.removeAll();

                String productId = product.getProductId();
                String productName = product.getProductName();
                int numAvailableItems = product.getNumAvailableItems();
                double productPrice = product.getProductPrice();
                JLabel productIdLabel = new JLabel("Product ID: " + productId);
                infoPanel.add(productIdLabel);
                JLabel productNameLabel = new JLabel("Product Name: " + productName);
                infoPanel.add(productNameLabel);
                JLabel availableItemsLabel = new JLabel("Number of Available Items: " + numAvailableItems);
                infoPanel.add(availableItemsLabel);
                JLabel productPriceLabel = new JLabel("Product Price: " + productPrice);
                infoPanel.add(productPriceLabel);
                if (product instanceof Electronics) {
                    Electronics electronics = (Electronics) product;
                    String electronicBrand = electronics.getElectronicBrand();
                    int electronicWarrantyPeriod = electronics.getElectronicWarrantyPeriod();
                    JLabel electronicBrandLabel = new JLabel("Electronic Brand: " + electronicBrand);
                    infoPanel.add(electronicBrandLabel);
                    JLabel electronicWarrantyPeriodLabel = new JLabel("Electronic Warranty Period: " + electronicWarrantyPeriod);
                    infoPanel.add(electronicWarrantyPeriodLabel);
                } else if (product instanceof Clothing) {
                    Clothing clothing = (Clothing) product;
                    String clothingSize = clothing.getClothingSize();
                    String clothingColour = clothing.getClothingColour();
                    JLabel clothingSizeLabel = new JLabel("Clothing Size: " + clothingSize);
                    infoPanel.add(clothingSizeLabel);
                    JLabel clothingColourLabel = new JLabel("Clothing Colour: " + clothingColour);
                    infoPanel.add(clothingColourLabel);
                }
                infoPanel.revalidate();
            }
        }
    }
}
