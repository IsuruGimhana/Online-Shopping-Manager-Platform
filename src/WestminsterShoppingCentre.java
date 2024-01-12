/**
 * This class represents the westminster shopping centre
 *
 * @author Isuru Gimhana
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class WestminsterShoppingCentre extends JFrame{
    private User user;
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
    private JCheckBox ascendingOrder;
    private JCheckBox descendingOrder;
    private JButton shoppingCartButton;
    private JButton addToCart;

    public WestminsterShoppingCentre() throws HeadlessException {
        user = new User("Isuru", "1234");
        productList = WestminsterShoppingManager.getProductList();
        filteredProductList = new ArrayList<>();
        setTitle("Westminster Shopping Centre");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        headerPanel = new JPanel();
        bodyPanel = new JPanel();
        footerPanel = new JPanel();
        footerPanel.setBorder(BorderFactory.createMatteBorder(1,0,0,0,Color.BLACK));

        sortPanel = new JPanel();
        tablePanel = new JPanel();

        this.getContentPane().setLayout(new GridLayout(2,1));
        this.getContentPane().add(tablePanel);
        this.getContentPane().add(footerPanel);

        tablePanel.setLayout(new BorderLayout());
        headerPanel.setLayout(new BorderLayout());
        tablePanel.add(headerPanel, BorderLayout.NORTH);
        bodyPanel.setLayout(new GridLayout(1,1));
        bodyPanel.setBorder(BorderFactory.createEmptyBorder(10,10,30,10));
        tablePanel.add(bodyPanel, BorderLayout.CENTER);

//header
        shoppingCartButton = new JButton("Shopping Cart");
        sortPanel.setLayout(new FlowLayout());
        sortPanel.setBorder(BorderFactory.createEmptyBorder(40,0,0,0));

        JPanel shoppingCartPanel = new JPanel(new GridLayout(1,1));
        shoppingCartPanel.setBorder(BorderFactory.createEmptyBorder(0,0,20,10));
        shoppingCartPanel.add(shoppingCartButton);
        headerPanel.add(shoppingCartPanel, BorderLayout.EAST);

        JLabel category = new JLabel("Select Product Category");
        sortPanel.add(category);
        String[] productCategory = {"All", "Electronics", "Clothing"};
        categoryDropDown = new JComboBox(productCategory);
        categoryDropDown.setBorder(new EmptyBorder(5,0,0,0));
        sortPanel.add(categoryDropDown);

        headerPanel.add(sortPanel, BorderLayout.CENTER);

        EventListener categoryDropDownEventListener = new EventListener();
        categoryDropDown.addActionListener(categoryDropDownEventListener);

        Collections.sort(productList);
        filteredProductList.addAll(productList);
        tableModel = new ProductTableModel(filteredProductList);
        JTable table = new JTable(tableModel);
        JTableHeader tableHeader = table.getTableHeader();

        for (int i = 0; i < table.getColumnCount(); i++) {
            tableHeader.getColumnModel().getColumn(i).setHeaderRenderer(new CustomTableHeaderCellRenderer());
        }

        table.getTableHeader().setPreferredSize(new Dimension(200, 30));
        table.setRowHeight(30);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new CustomTableRowColourRenderer());
        }

        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(500);

        EventListener tableEventListener = new EventListener(table);
        table.getSelectionModel().addListSelectionListener(tableEventListener);

        JScrollPane tableScrollPane = new JScrollPane(table);
        bodyPanel.add(tableScrollPane);

        //footer
        footerPanel.setLayout(new BorderLayout());
        JLabel detailsHeader = new JLabel("Selected Product - Details");
        Font productDetailsFont = new Font("Arial", Font.BOLD,14);
        detailsHeader.setFont(productDetailsFont);
        detailsHeader.setBorder(BorderFactory.createEmptyBorder(10,50,0,0));
        footerPanel.add(detailsHeader, BorderLayout.NORTH);

        //Add to cart button
        JPanel addToCartPanel = new JPanel();
        addToCartPanel.setLayout(new FlowLayout());
        addToCart = new JButton("Add to Cart");
        addToCart.setPreferredSize(new Dimension(150, 50));
        addToCartPanel.add(addToCart);
        footerPanel.add(addToCartPanel, BorderLayout.SOUTH);

        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(6,1));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10,50,0,0));
        footerPanel.add(infoPanel, BorderLayout.CENTER);

        ShoppingCart shoppingCart = new ShoppingCart(user, infoPanel);
        EventListener shoppingCartEventListener = new EventListener(shoppingCart);
        shoppingCartButton.addActionListener(shoppingCartEventListener);

        EventListener shoppingCartEventListener2 = new EventListener(table, shoppingCart);
        addToCart.addActionListener(shoppingCartEventListener2);
    }

    public void updateInfoPanel(Product product) {
        GridLayout existingLayout = (GridLayout) infoPanel.getLayout();
        existingLayout.setRows(6);
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
        infoPanel.repaint();
    }

    private class CustomTableHeaderCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            setBorder(BorderFactory.createMatteBorder(0,1,1,0,Color.BLACK));
            setHorizontalAlignment(SwingConstants.CENTER);
            return renderer;
        }
    }

    private class CustomTableRowColourRenderer extends DefaultTableCellRenderer {
//        Color originalColor = null;
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            setBorder(BorderFactory.createMatteBorder(0,1,1,0,Color.BLACK));
            setHorizontalAlignment(SwingConstants.CENTER);
            if (filteredProductList.get(row).getNumAvailableItems() < 3) {
                renderer.setBackground(Color.RED);
            } else {
                renderer.setBackground(table.getBackground());
            }
            if (isSelected) {
                renderer.setBackground(Color.GRAY);
            }
            return renderer;
        }
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
                Product product = productList.get(selectedRow);//get the product object from the productList using the selected row
                updateInfoPanel(product);
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
                Collections.sort(filteredProductList);
                tableModel.setProductList(filteredProductList);
                tableModel.fireTableDataChanged();

            } else if (source == shoppingCartButton) {
                shoppingCart.setVisible(true);
            } else if (source == addToCart) {
                if (table.getSelectedRow() != -1) {
                    int selectedRow = table.getSelectedRow();
                    Product cartProduct;
                    if (filteredProductList.get(selectedRow).getNumAvailableItems() <= 0) {
                        JOptionPane.showMessageDialog(null, "Sold out! Please select a different product.");
                        return;
                    }
                    if (filteredProductList.get(selectedRow) instanceof Electronics) {
                        cartProduct = new Electronics(filteredProductList.get(selectedRow));
                        cartProduct.setNumAvailableItems(0);
                    } else {
                        cartProduct = new Clothing(filteredProductList.get(selectedRow));
                        cartProduct.setNumAvailableItems(0);
                    }
                    if ((shoppingCart.getCartList().size() == 0) && (filteredProductList.get(selectedRow).getNumAvailableItems() > 0)) {
                        cartProduct.setNumAvailableItems(cartProduct.getNumAvailableItems() + 1);
                        shoppingCart.addProduct(cartProduct);
                    } else {
                        boolean productExists = false;
                        for (int i = 0; i < shoppingCart.getCartList().size(); i++) {
                            if (shoppingCart.getCartList().get(i).getProductId().equals(filteredProductList.get(selectedRow).getProductId())) {
                                productExists = true;
                                break;
                            }
                        }
                        if ((!productExists) && (filteredProductList.get(selectedRow).getNumAvailableItems() > 0)){
                            cartProduct.setNumAvailableItems(cartProduct.getNumAvailableItems() + 1);
                            shoppingCart.addProduct(cartProduct);
                        }
                    }
                }
            }
        }
    }
}
