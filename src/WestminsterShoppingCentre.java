import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WestminsterShoppingCentre extends JFrame{
    private ArrayList<Product> productList;
    private ArrayList<Product> filteredProductList;
    private ProductTableModel tableModel;
    private JPanel headerPanel;
    private JPanel bodyPanel;
    private JPanel footerPanel;
    private JPanel sortPanel;
    private JPanel infoPanel;
    private JPanel tablePanel;
    private JComboBox categoryDropDown;
    private JButton shoppingCartButton;
    private JButton addToCart;

    public WestminsterShoppingCentre() throws HeadlessException {
        productList = WestminsterShoppingManager.getProductList();
        filteredProductList = new ArrayList<>();
        setTitle("Westminster Shopping Centre");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        headerPanel = new JPanel();
        bodyPanel = new JPanel();
        footerPanel = new JPanel();
        sortPanel = new JPanel();
        tablePanel = new JPanel();

        headerPanel.setBackground(Color.BLACK);
        bodyPanel.setBackground(Color.GRAY);
        footerPanel.setBackground(Color.LIGHT_GRAY);
        sortPanel.setBackground(Color.pink);

//        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().setLayout(new GridLayout(2,1));
        this.getContentPane().add(tablePanel);
        this.getContentPane().add(footerPanel);
//        this.getContentPane().add(headerPanel, BorderLayout.NORTH);
//        this.getContentPane().add(bodyPanel, BorderLayout.CENTER);
//        this.getContentPane().add(footerPanel, BorderLayout.SOUTH);

        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(headerPanel, BorderLayout.NORTH);
        tablePanel.add(bodyPanel, BorderLayout.CENTER);
        headerPanel.setLayout(new BorderLayout());
        bodyPanel.setLayout(new GridLayout(1,1));

//header
        shoppingCartButton = new JButton("Shopping Cart");
        ShoppingCart shoppingCart = new ShoppingCart();
        EventListener shoppingCartEventListener = new EventListener(shoppingCart);
        shoppingCartButton.addActionListener(shoppingCartEventListener);

        headerPanel.add(sortPanel, BorderLayout.WEST);
        headerPanel.add(shoppingCartButton, BorderLayout.EAST);

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
        categoryDropDown = new JComboBox(productCategory);
        categoryDropDown.setBorder(new EmptyBorder(5,0,0,0));
        sortValue.add(categoryDropDown);
        JLabel sort = new JLabel("Sort");
        sortTypePanel.add(sort);
        JCheckBox ascendingOrder = new JCheckBox("a-z");
        sortValue.add(ascendingOrder);

        EventListener categoryDropDownEventListener = new EventListener();
        categoryDropDown.addActionListener(categoryDropDownEventListener);

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
        tableModel = new ProductTableModel();
        filteredProductList.addAll(productList);
        tableModel.setProductList(filteredProductList);
        JTable table = new JTable(tableModel);

        table.getTableHeader().setPreferredSize(new Dimension(200, 30));
        table.setRowHeight(30);
        table.getTableHeader().setBackground(Color.LIGHT_GRAY);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        table.setShowVerticalLines(false);

//        table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.blue));
//        table.setGridColor(Color.blue);
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(500);
        JScrollPane tableScrollPane = new JScrollPane(table);
        bodyPanel.add(tableScrollPane);

        //footer
        footerPanel.setLayout(new BorderLayout());
        JLabel detailsHeader = new JLabel("Selected Product Details");
        footerPanel.add(detailsHeader, BorderLayout.NORTH);
        EventListener tableEventListener = new EventListener(table);
        table.getSelectionModel().addListSelectionListener(tableEventListener);

        //Add to cart button
        JPanel addToCartPanel = new JPanel();
        addToCartPanel.setLayout(new FlowLayout());
        addToCart = new JButton("Add to Cart");
        addToCart.setPreferredSize(new Dimension(150, 50));
        footerPanel.add(addToCartPanel, BorderLayout.SOUTH);
        addToCartPanel.add(addToCart);
//        ShoppingCart shoppingCart = new ShoppingCart();
        EventListener shoppingCartEventListener2 = new EventListener(table, shoppingCart);
        addToCart.addActionListener(shoppingCartEventListener2);


        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(6,1));
        footerPanel.add(infoPanel, BorderLayout.CENTER);


    }
    private class EventListener implements ListSelectionListener, ActionListener {
        private JTable table;
        private ShoppingCart shoppingCart;
        public EventListener() {}
        public EventListener(ShoppingCart shoppingCart) {
            this.shoppingCart = shoppingCart;
        }
        public EventListener(JTable table) {
            this.table = table;
        }
        public EventListener(JTable table, ShoppingCart shoppingCart) {
            this.table = table;
            this.shoppingCart = shoppingCart;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (table.getSelectedRow() != -1) {//if a row is selected
                int selectedRow = table.getSelectedRow();//get the selected row
//                productList = WestminsterShoppingManager.getProductList();
                Product product = productList.get(selectedRow);//get the product object from the productList using the selected row

                infoPanel.removeAll();

                String productId = product.getProductId();
                String productName = product.getProductName();
                int numAvailableItems = product.getNumAvailableItems();
                double productPrice = product.getProductPrice();
                String formattedProductPrice = String.format("%.2f", productPrice);
                JLabel productIdLabel = new JLabel("Product ID: " + productId);
                infoPanel.add(productIdLabel);
                JLabel productNameLabel = new JLabel("Product Name: " + productName);
                infoPanel.add(productNameLabel);
                JLabel availableItemsLabel = new JLabel("Number of Available Items: " + numAvailableItems);
                infoPanel.add(availableItemsLabel);
                JLabel productPriceLabel = new JLabel("Product Price: " + formattedProductPrice + " LKR");
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

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == categoryDropDown) {
                filteredProductList.clear();
                String selectedCategory = (String) categoryDropDown.getSelectedItem();
                if (selectedCategory.equals("All")) {
                    for (Product product : productList) {
                        filteredProductList.add(product);
                    }
                } else if (selectedCategory.equals("Electronics")) {
                    for (Product product : productList) {
                        if (product instanceof Electronics) {
                            filteredProductList.add(product);
                        }
                    }
                } else if (selectedCategory.equals("Clothing")) {
                    for (Product product : productList) {
                        if (product instanceof Clothing) {
                            filteredProductList.add(product);
                        }
                    }
                }
                tableModel.setProductList(filteredProductList);
                tableModel.fireTableDataChanged();
            } else if (source == shoppingCartButton) {
                shoppingCart.setVisible(true);
            } else if (source == addToCart) {
                if (table.getSelectedRow() != -1) {
                    int selectedRow = table.getSelectedRow();
                    Product product = productList.get(selectedRow);
//                    ShoppingCart shoppingCart = new ShoppingCart();
                    if (shoppingCart.getCartList().size() == 0) {
                        shoppingCart.addProduct(product);
                    } else {
                        boolean productExists = false;
                        for (int i = 0; i < shoppingCart.getCartList().size(); i++) {
                            if (shoppingCart.getCartList().get(i).getProductId().equals(product.getProductId())) {
                                productExists = true;
                                break;
                            }
                        }
                        if (!productExists) {
                            shoppingCart.addProduct(product);
                        }
                    }
                }
            }
//            JButton button = (JButton) e.getSource();
//            if (button.getText().equals("Shopping Cart")) {
////                ShoppingCart shoppingCartFrame = new ShoppingCart();
//                shoppingCart.setVisible(true);
//            } else if (button.getText().equals("Add to Cart")) {
//                if (table.getSelectedRow() != -1) {
//                    int selectedRow = table.getSelectedRow();
//                    Product product = productList.get(selectedRow);
////                    ShoppingCart shoppingCart = new ShoppingCart();
//                    if (shoppingCart.getCartList().size() == 0) {
//                        shoppingCart.addProduct(product);
//                    } else {
//                        boolean productExists = false;
//                        for (int i = 0; i < shoppingCart.getCartList().size(); i++) {
//                            if (shoppingCart.getCartList().get(i).getProductId().equals(product.getProductId())) {
//                                productExists = true;
//                                break;
//                            }
//                        }
//                        if (!productExists) {
//                            shoppingCart.addProduct(product);
//                        }
//                    }
//                }
//            }
        }
    }
}
