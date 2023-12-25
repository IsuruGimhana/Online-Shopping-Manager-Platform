/**
 * This class represents the user shopping cart
 *
 * @author Isuru Gimhana
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//public class ShoppingCart extends JFrame {
//    private ArrayList<Product> cartList;
//    private JPanel cartPanel;
//    JPanel finalPricePanel;
//
//    public ArrayList<Product> getCartList() {
//        return cartList;
//    }
//    public void setCartList(ArrayList<Product> cartList) {
//        this.cartList = cartList;
//    }
//
//    public ShoppingCart() throws HeadlessException {
//        this.cartList = new ArrayList<>();
//        setTitle("Shopping Cart");
//        setSize(800, 600);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//        cartPanel = new JPanel();
//        finalPricePanel = new JPanel();
//
//        this.getContentPane().setLayout(new GridLayout(2,1));
//        this.getContentPane().add(cartPanel);
//        this.getContentPane().add(finalPricePanel);
//
//        cartPanel.setBackground(Color.GRAY);
//        finalPricePanel.setBackground(Color.LIGHT_GRAY);
//
//        cartPanel.setLayout(new GridLayout(1,1));
//        finalPricePanel.setLayout(new GridLayout(4,2));
//
//        int quantity = 1;
//        ShoppingCartTableModel tableModel = new ShoppingCartTableModel(cartList, quantity);
//        JTable table = new JTable(tableModel);
//        TableColumn column = table.getColumnModel().getColumn(0);
//
//        table.getTableHeader().setPreferredSize(new Dimension(200, 30));
//        table.setRowHeight(30);
//        table.getTableHeader().setBackground(Color.LIGHT_GRAY);
//        table.setShowGrid(true);
//        table.setGridColor(Color.BLACK);
//        table.setShowVerticalLines(false);
//
//        JScrollPane tableScrollPane = new JScrollPane(table);
//        cartPanel.add(tableScrollPane);
//    }
//
////        cartPanel.setLayout(new GridLayout(cartList.size() + 1, 3));
////        finalPricePanel.setLayout(new GridLayout(4, 2));
//
////        JLabel productLabel = new JLabel("Product");
////        JLabel productQuantityLabel = new JLabel("Quantity");
////        JLabel priceLabel = new JLabel("Price");
////        cartPanel.add(productLabel);
////        cartPanel.add(productQuantityLabel);
////        cartPanel.add(priceLabel);
////
////        for (int i = 0 ; i < cartList.size() ; i++) {
////            String description = "";
////            if (cartList.get(i) instanceof Electronics) {
////                Electronics electronics = (Electronics) cartList.get(i);
////                description =  electronics.getProductId() + "/n" + electronics.getProductName() + "/n" + electronics.getElectronicBrand() + "/n" + electronics.getElectronicWarrantyPeriod() + " months warranty";
////            } else {
////                Clothing clothing = (Clothing) cartList.get(i);
////                description = clothing.getProductId() + "/n" + clothing.getProductName() + "/n" + clothing.getClothingSize() + "/n" + clothing.getClothingColour();
////            }
////            JLabel productDescription = new JLabel(description);
////            cartPanel.add(productDescription);
////
////            JPanel productQuantityPanel = new JPanel();
////            cartPanel.add(productQuantityPanel);
////            productQuantityPanel.setLayout(new GridLayout(1, 3));
////
////            JButton increaseQuantity = new JButton("+");
////            JLabel defaultProductQuantity = new JLabel("1");
////            JButton decreaseQuantity = new JButton("-");
////            productQuantityPanel.add(decreaseQuantity);
////            productQuantityPanel.add(defaultProductQuantity);
////            productQuantityPanel.add(increaseQuantity);
////
////            int productObjectIndex = i;
//////            int availableQuantity = cartList.get(i).getNumAvailableItems();
////            EventListener eventListener = new EventListener(defaultProductQuantity, productObjectIndex);
////            increaseQuantity.addActionListener(eventListener);
////            decreaseQuantity.addActionListener(eventListener);
////
////            JLabel productPrice = new JLabel(cartList.get(i).getProductPrice() + " LKR");
////            cartPanel.add(productPrice);
////        }
////    }
//
//    public class EventListener implements ActionListener {
//        JLabel defaultProductQuantity;
////        int availableQuantity;
//        int productObjectIndex;
//        public EventListener(JLabel defaultProductQuantity, int productObjectIndex) {
//            this.defaultProductQuantity = defaultProductQuantity;
////            this.availableQuantity = availableQuantity;
//            this.productObjectIndex = productObjectIndex;
//        }
//        public void actionPerformed(ActionEvent e) {
//            int availableQuantity = cartList.get(productObjectIndex).getNumAvailableItems();
//            JButton button = (JButton) e.getSource();
//            if (button.getText().equals("+")) {
//                if (availableQuantity > 0) {
//                    int quantity = Integer.parseInt(defaultProductQuantity.getText());
//                    quantity++;
//                    defaultProductQuantity.setText(quantity + "");
//                    availableQuantity--;
//                    Product product = cartList.get(productObjectIndex);
//                    product.setNumAvailableItems(availableQuantity);
//                }
//            } else if (button.getText().equals("-")) {
//                int quantity = Integer.parseInt(defaultProductQuantity.getText());
//                if (quantity > 1) {
//                    quantity--;
//                    defaultProductQuantity.setText(quantity + "");
//                    availableQuantity++;
//                    Product product = cartList.get(productObjectIndex);
//                    product.setNumAvailableItems(availableQuantity);
//                }
//            }
//        }
//    }

public class ShoppingCart extends JFrame {
    private ArrayList<Product> cartList = new ArrayList<>() {{
        add(new Electronics("E001", "iPhone 12", 10, 200000, "Apple", 12));
        add(new Clothing("C001", "T-Shirt", 20, 1000, "M", "Red"));}};

    String[] columnNames = {"Product", "Quantity", "Price"};
    JPanel productPanel;
    JPanel completeProductDescriptionPanel;
    JButton removeProductButton;
    JButton plus;
    JButton minus;

    public ShoppingCart() throws HeadlessException {
//        this.cartList = new ArrayList<>();
        setTitle("Shopping Cart");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.getContentPane().setLayout(new GridLayout(2,1));
        productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(cartList.size() + 1, 3));
        productPanel.setName("productPanel");
        productPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        updateProductPanel();

//        for (String columnName : columnNames) {
//            JLabel header = new JLabel(columnName);
//            header.setOpaque(true);
//            header.setBackground(Color.LIGHT_GRAY);
//            header.setPreferredSize(new Dimension(250,30));
//            header.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//            header.setHorizontalAlignment(JLabel.CENTER);
//            productPanel.add(header);
//        }
//
//        for (Product product : cartList) {
//            JPanel productDescriptionPanel = new JPanel();
//            productDescriptionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//            productDescriptionPanel.setPreferredSize(new Dimension(250, 60));
//            productDescriptionPanel.setLayout(new GridLayout(3,1));
//            JLabel prductId = new JLabel(product.getProductId());
//            prductId.setHorizontalAlignment(JLabel.CENTER);
//            JLabel productName = new JLabel(product.getProductName());
//            productName.setHorizontalAlignment(JLabel.CENTER);
//            JLabel temp;
//            if (product instanceof Electronics) {
//                Electronics electronics = (Electronics) product;
//                temp = new JLabel(electronics.getElectronicBrand() + ", " + electronics.getElectronicWarrantyPeriod() + " months warranty");
//            } else {
//                Clothing clothing = (Clothing) product;
//                temp = new JLabel(clothing.getClothingSize() + ", " + clothing.getClothingColour());
//            }
//            temp.setHorizontalAlignment(JLabel.CENTER);
//            productDescriptionPanel.add(prductId);
//            productDescriptionPanel.add(productName);
//            productDescriptionPanel.add(temp);
//
//            JLabel defaultProductQuantity = new JLabel("1");
//            JLabel productPrice = new JLabel(product.getProductPrice() + " LKR");
//            productPrice.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//            productPrice.setPreferredSize(new Dimension(250, 60));
//            productPrice.setHorizontalAlignment(JLabel.CENTER);
//            JPanel quantityPanel = new JPanel();
//            quantityPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
//            quantityPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//            quantityPanel.setPreferredSize(new Dimension(250, 60));
//            JButton plus = new JButton("+");
//            JButton minus = new JButton("-");
//            plus.setPreferredSize(new Dimension(20, 20));
//            minus.setPreferredSize(new Dimension(20, 20));
//            quantityPanel.add(minus);
//            quantityPanel.add(defaultProductQuantity);
//            quantityPanel.add(plus);
//            productPanel.add(productDescriptionPanel);
//            productPanel.add(quantityPanel);
//            productPanel.add(productPrice);
//        }

        JPanel headerPanel = new JPanel(new FlowLayout());
        headerPanel.add(productPanel);
        JScrollPane scrollPane = new JScrollPane(headerPanel);
        this.getContentPane().add(scrollPane);


        // Final price panel
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());
        footerPanel.setPreferredSize(new Dimension(400, 120));
        JPanel leftFooterPanel = new JPanel();
        leftFooterPanel.setLayout(new GridLayout(4,1));
        JPanel rightFooterPanel = new JPanel();
        rightFooterPanel.setLayout(new GridLayout(4,1));
        JLabel totalLabel = new JLabel("Total: ");
        totalLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel totalValueLabel = new JLabel("0");
        totalValueLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel firstPurchaseDiscountLabel = new JLabel("First purchase discount(10%): ");
        firstPurchaseDiscountLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel firstPurchaseDiscountValueLabel = new JLabel("-0");
        firstPurchaseDiscountValueLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel sameCategoryDiscountLabel = new JLabel("Three Items in the same category discount(20%): ");
        sameCategoryDiscountLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel sameCategoryDiscountValueLabel = new JLabel("-0");
        sameCategoryDiscountValueLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel finalTotalLabel = new JLabel("Final Total: ");
        finalTotalLabel.setHorizontalAlignment(JLabel.RIGHT);
        JLabel finalTotalValueLabel = new JLabel("0");
        finalTotalValueLabel.setHorizontalAlignment(JLabel.RIGHT);

        leftFooterPanel.add(totalLabel);
        rightFooterPanel.add(totalValueLabel);
        leftFooterPanel.add(firstPurchaseDiscountLabel);
        rightFooterPanel.add(firstPurchaseDiscountValueLabel);
        leftFooterPanel.add(sameCategoryDiscountLabel);
        rightFooterPanel.add(sameCategoryDiscountValueLabel);
        leftFooterPanel.add(finalTotalLabel);
        rightFooterPanel.add(finalTotalValueLabel);

        footerPanel.add(leftFooterPanel, BorderLayout.WEST);
        footerPanel.add(rightFooterPanel, BorderLayout.CENTER);

        JPanel finalTotalPanel = new JPanel(new FlowLayout());
        finalTotalPanel.add(footerPanel);
        this.getContentPane().add(finalTotalPanel);
    }

    public void updateProductPanel() {
        GridLayout exsistingLayout = (GridLayout) productPanel.getLayout();
        exsistingLayout.setRows(cartList.size() + 1);
        productPanel.removeAll();
        for (String columnName : columnNames) {
            JLabel header = new JLabel(columnName);
            header.setOpaque(true);
            header.setBackground(Color.LIGHT_GRAY);
            header.setPreferredSize(new Dimension(250,30));
            header.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            header.setHorizontalAlignment(JLabel.CENTER);
            productPanel.add(header);
        }

        for (int i = 0 ; i < cartList.size() ; i++) {
            JPanel productDescriptionPanel = new JPanel();
//            productDescriptionPanel.setName("productDescriptionPanel" + i);
            productDescriptionPanel.setLayout(new GridLayout(3,1));
            productDescriptionPanel.setPreferredSize(new Dimension(200,60));
            JLabel prductId = new JLabel(cartList.get(i).getProductId());
            prductId.setHorizontalAlignment(JLabel.CENTER);
            JLabel productName = new JLabel(cartList.get(i).getProductName());
            productName.setHorizontalAlignment(JLabel.CENTER);
            JLabel temp;
            if (cartList.get(i) instanceof Electronics) {
                Electronics electronics = (Electronics) cartList.get(i);
                temp = new JLabel(electronics.getElectronicBrand() + ", " + electronics.getElectronicWarrantyPeriod() + " months warranty");
            } else {
                Clothing clothing = (Clothing) cartList.get(i);
                temp = new JLabel(clothing.getClothingSize() + ", " + clothing.getClothingColour());
            }
            temp.setHorizontalAlignment(JLabel.CENTER);
            productDescriptionPanel.add(prductId);
            productDescriptionPanel.add(productName);
            productDescriptionPanel.add(temp);
            completeProductDescriptionPanel = new JPanel(new FlowLayout());
            completeProductDescriptionPanel.setName("completeProductDescriptionPanel");
            completeProductDescriptionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//            completeProductDescriptionPanel.setPreferredSize(new Dimension(250, 60));
            removeProductButton = new JButton("-");
            //Set the button name
            removeProductButton.setName("removeProductButton" + i);

            EventListener removeProductListener = new EventListener();
            removeProductButton.addActionListener(removeProductListener);

            removeProductButton.setPreferredSize(new Dimension(20, 20));
            completeProductDescriptionPanel.add(removeProductButton);
            completeProductDescriptionPanel.add(productDescriptionPanel);

            JLabel defaultProductQuantity = new JLabel("1");
            String formattedProductPrice = String.format("%.2f", cartList.get(i).getProductPrice());
            JLabel productPrice = new JLabel(formattedProductPrice + " LKR");
            productPrice.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            productPrice.setPreferredSize(new Dimension(250, 60));
            productPrice.setHorizontalAlignment(JLabel.CENTER);
            JPanel quantityPanel = new JPanel();
            quantityPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
            quantityPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            quantityPanel.setPreferredSize(new Dimension(250, 60));
            plus = new JButton("+");
            minus = new JButton("-");
            plus.setPreferredSize(new Dimension(20, 20));
            minus.setPreferredSize(new Dimension(20, 20));
            quantityPanel.add(minus);
            quantityPanel.add(defaultProductQuantity);
            quantityPanel.add(plus);
            productPanel.add(completeProductDescriptionPanel);
            productPanel.add(quantityPanel);
            productPanel.add(productPrice);
        }
        productPanel.revalidate();
        productPanel.repaint();
    }

    private class EventListener implements ActionListener {
        private ArrayList<String> similarButtons;
        public EventListener() {
            this.similarButtons = new ArrayList<>();
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            similarButtons.clear();
            JButton clickedButton = (JButton) e.getSource();
            String commonButtonName = clickedButton.getName().substring(0,19);
            String clickedButtonIndex = clickedButton.getName().substring(19);
            int toRemoveIndex = Integer.parseInt(clickedButtonIndex);
            System.out.println(commonButtonName);

            if (commonButtonName.equals("removeProductButton")) {
                System.out.println("yes");
//                boolean toBeRemoved = false;
//                int toRemoveIndex = 0;

                for (Component componentPanel : productPanel.getComponents()) {
                    if (componentPanel instanceof JPanel && componentPanel.getName() != null && (componentPanel.getName().equals("completeProductDescriptionPanel"))) {
                        JPanel completeProductDescriptionPanel = (JPanel) componentPanel;
                        for (Component buttonComponent : completeProductDescriptionPanel.getComponents()) {
                            if (buttonComponent instanceof JButton && buttonComponent.getName().startsWith("removeProductButton")) {
                                JButton btn = (JButton) buttonComponent;
                                String buttonName = btn.getName();
                                similarButtons.add(buttonName);
                            }
                        }
                    }
                }
                for (String similarButton : similarButtons) {
                    System.out.println(similarButton);
                }

                for (int i = 0; i < similarButtons.size(); i++) {
                    if (similarButtons.get(i).equals("removeProductButton" + clickedButtonIndex)) {
                        cartList.remove(toRemoveIndex);
                        updateProductPanel();
                    }
                }
            }

//            if (text.equals()) {
//                String buttonIndex = ((JButton) source).getName().substring(19);
//                int integerButtonIndex = Integer.parseInt(buttonIndex);
//                System.out.println(buttonIndex);
//                boolean toBeRemoved = false;
//                int toRemoveIndex = 0;
//                for (int i = 0; i < cartList.size(); i++) {
//                    if (i == integerButtonIndex) {
//                        toBeRemoved = true;
//                        toRemoveIndex = i;
//                    }
//                }
//                if (toBeRemoved) {
//                    cartList.remove(toRemoveIndex);
//                    updateProductPanel();
//                }
//            }
//             else if (source == plus) {
//
//            } else if (source == minus) {
//
//            }
        }
    }

    public ArrayList<Product> getCartList() {
        return cartList;
    }
    public void setCartList(ArrayList<Product> cartList) {
        this.cartList = cartList;
    }

    public void addProduct(Product product) {
        this.cartList.add(product);
        updateProductPanel();
    }

    public void removeProduct(Product product) {
        this.cartList.remove(product);
        updateProductPanel();
    }

    public double getTotalCost() {
        double totalCost = 0;
        for (Product product : cartList) {
            totalCost += product.getProductPrice();
        }
        return totalCost;
    }
}
