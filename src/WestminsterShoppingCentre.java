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
    private JButton shoppingCartButton;
    private JButton addToCart;

    /**
     * Constructor
     * @throws HeadlessException
     */
    public WestminsterShoppingCentre() throws HeadlessException {
        user = new User();
        productList = WestminsterShoppingManager.getProductList();
        filteredProductList = new ArrayList<>();
        setTitle("Westminster Shopping Centre");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //content pane
        this.getContentPane().setLayout(new GridLayout(2,1));

        //main 2 panels
        tablePanel = new JPanel();
        footerPanel = new JPanel();

        //table panel properties
        tablePanel.setLayout(new BorderLayout());

        //footer panel properties
        footerPanel.setBorder(BorderFactory.createMatteBorder(1,0,0,0,Color.BLACK));
        footerPanel.setLayout(new BorderLayout());

        //table panel sub panels
        headerPanel = new JPanel();
        bodyPanel = new JPanel();

        headerPanel.setLayout(new BorderLayout());

        bodyPanel.setLayout(new GridLayout(1,1));
        bodyPanel.setBorder(BorderFactory.createEmptyBorder(10,10,30,10));

        sortPanel = new JPanel();

        sortPanel.setLayout(new FlowLayout());
        sortPanel.setBorder(BorderFactory.createEmptyBorder(40,0,0,0));

        shoppingCartButton = new JButton("Shopping Cart");

        JPanel shoppingCartPanel = new JPanel(new GridLayout(1,1));
        shoppingCartPanel.setBorder(BorderFactory.createEmptyBorder(0,0,20,10));

        //add shopping cart button to the shopping cart panel
        shoppingCartPanel.add(shoppingCartButton);

        headerPanel.add(shoppingCartPanel, BorderLayout.EAST);

        JLabel category = new JLabel("Select Product Category");

        //add category label to the sort panel
        sortPanel.add(category);

        String[] productCategory = {"All", "Electronics", "Clothing"};
        categoryDropDown = new JComboBox(productCategory);
        categoryDropDown.setBorder(new EmptyBorder(5,0,0,0));

        //add event listener to the category drop down
        EventListener categoryDropDownEventListener = new EventListener();
        categoryDropDown.addActionListener(categoryDropDownEventListener);

        //add category drop down to the sort panel
        sortPanel.add(categoryDropDown);

        //add sort panel to the header panel
        headerPanel.add(sortPanel, BorderLayout.CENTER);

        //add header panel to the table panel
        tablePanel.add(headerPanel, BorderLayout.NORTH);

        //sort the product list
        Collections.sort(productList);

        filteredProductList.addAll(productList);
        tableModel = new ProductTableModel(filteredProductList);
        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        //add table scroll pane to the body panel
        bodyPanel.add(tableScrollPane);

        //add body panel to the table panel
        tablePanel.add(bodyPanel, BorderLayout.CENTER);

        JTableHeader tableHeader = table.getTableHeader();

        //set table header properties
        for (int i = 0; i < table.getColumnCount(); i++) {
            tableHeader.getColumnModel().getColumn(i).setHeaderRenderer(new CustomTableHeaderCellRenderer());
        }

        table.getTableHeader().setPreferredSize(new Dimension(200, 30));
        table.setRowHeight(30);

        //set table cell properties
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new CustomTableRowColourRenderer());
        }

        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(500);

        //add event listener to the table
        EventListener tableEventListener = new EventListener(table);
        table.getSelectionModel().addListSelectionListener(tableEventListener);


        //add table panel to the content pane
        this.getContentPane().add(tablePanel);

        //details header label properties
        JLabel detailsHeader = new JLabel("Selected Product - Details");
        Font productDetailsFont = new Font("Arial", Font.BOLD,14);
        detailsHeader.setFont(productDetailsFont);
        detailsHeader.setBorder(BorderFactory.createEmptyBorder(10,50,0,0));

        //add details header to the footer panel
        footerPanel.add(detailsHeader, BorderLayout.NORTH);

        //add footer panel properties
        JPanel addToCartPanel = new JPanel();
        addToCartPanel.setLayout(new FlowLayout());

        // add to cart button properties
        addToCart = new JButton("Add to Cart");
        addToCart.setPreferredSize(new Dimension(150, 50));


        //info panel properties
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(6,1));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10,50,0,0));

        //add info panel to the footer panel
        footerPanel.add(infoPanel, BorderLayout.CENTER);

        //add footer panel to the content pane
        this.getContentPane().add(footerPanel);

        //create a new shopping cart object
        ShoppingCart shoppingCart = new ShoppingCart(user, infoPanel);

        //add event listener to the shopping cart button
        EventListener shoppingCartEventListener = new EventListener(shoppingCart);
        shoppingCartButton.addActionListener(shoppingCartEventListener);

        //add event listener to the add to cart button
        EventListener shoppingCartEventListener2 = new EventListener(table, shoppingCart);
        addToCart.addActionListener(shoppingCartEventListener2);

        // add add to cart button to the add to cart panel
        addToCartPanel.add(addToCart);

        //add add to cart panel to the footer panel
        footerPanel.add(addToCartPanel, BorderLayout.SOUTH);
    }

    /**
     * This method updates the info panel with the selected product details
     * @param product - the selected product
     */
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

        //check the instance type and set the value to be Electronic or Clothing
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
        //revalidate and repaint the info panel
        infoPanel.revalidate();
        infoPanel.repaint();
    }

    /**
     * This class represents a custom table header cell renderer
     */
    private class CustomTableHeaderCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            setBorder(BorderFactory.createMatteBorder(0,1,1,0,Color.BLACK));
            setHorizontalAlignment(SwingConstants.CENTER);
            return renderer;
        }
    }

    /**
     * This class represents a custom table row colour renderer
     */
    private class CustomTableRowColourRenderer extends DefaultTableCellRenderer {
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

    /**
     * This class represents a custom event listener
     */
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

        /**
         * This method is called when the table row is selected
         * @param e - the event
         */
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (table.getSelectedRow() != -1) {//if a row is selected
                int selectedRow = table.getSelectedRow();//get the selected row
                Product product = productList.get(selectedRow);//get the product object from the productList using the selected row
                updateInfoPanel(product);
            }
        }

        /**
         * This method is called when an action is performed
         * @param e - the event
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            //get the event source
            Object source = e.getSource();

            //check the event source
            if (source == categoryDropDown) {
                filteredProductList.clear();
                String selectedCategory = (String) categoryDropDown.getSelectedItem();

                //filter the product list according to the selected category
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

                        //show a message dialog if the product is sold out
                        JOptionPane.showMessageDialog(null, "Sold out! Please select a different product.");
                        return;
                    }

                    //set cart product quantity to 0
                    if (filteredProductList.get(selectedRow) instanceof Electronics) {
                        cartProduct = new Electronics(filteredProductList.get(selectedRow));
                        cartProduct.setNumAvailableItems(0);

                    } else {
                        cartProduct = new Clothing(filteredProductList.get(selectedRow));
                        cartProduct.setNumAvailableItems(0);
                    }

                    //check if the shopping cart is empty and add the product to the cart
                    if ((shoppingCart.getCartList().size() == 0) && (filteredProductList.get(selectedRow).getNumAvailableItems() > 0)) {
                        cartProduct.setNumAvailableItems(cartProduct.getNumAvailableItems() + 1);
                        shoppingCart.addProduct(cartProduct);

                        //show product added to cart message
                        JOptionPane.showMessageDialog(null, "Product added to the cart!");

                    } else {
                        boolean productExists = false;
                        for (int i = 0; i < shoppingCart.getCartList().size(); i++) {

                            //check if the product already exists in the shopping cart
                            if (shoppingCart.getCartList().get(i).getProductId().equals(filteredProductList.get(selectedRow).getProductId())) {
                                productExists = true;

                                //show product already exists in the cart message
                                JOptionPane.showMessageDialog(null, "Product already exists in the cart!");
                                break;
                            }
                        }

                        //if the product does not exist in the shopping cart, add the product to the cart
                        if ((!productExists) && (filteredProductList.get(selectedRow).getNumAvailableItems() > 0)){
                            cartProduct.setNumAvailableItems(cartProduct.getNumAvailableItems() + 1);
                            shoppingCart.addProduct(cartProduct);
                            //show product added to cart message
                            JOptionPane.showMessageDialog(null, "Product added to the cart!");
                        }
                    }
                }
            }
        }
    }
}
