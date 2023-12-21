/**
 * This class represents the user shopping cart
 *
 * @author Isuru Gimhana
 */
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShoppingCart extends JFrame {
    private ArrayList<Product> cartList;
    private JPanel cartPanel;
    JPanel finalPricePanel;

    public ArrayList<Product> getCartList() {
        return cartList;
    }
    public void setCartList(ArrayList<Product> cartList) {
        this.cartList = cartList;
    }

    public ShoppingCart() throws HeadlessException {
        this.cartList = new ArrayList<>();
        setTitle("Shopping Cart");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cartPanel = new JPanel();
        finalPricePanel = new JPanel();

        this.getContentPane().setLayout(new GridLayout(2,1));
        this.getContentPane().add(cartPanel);
        this.getContentPane().add(finalPricePanel);

        cartPanel.setBackground(Color.GRAY);
        finalPricePanel.setBackground(Color.LIGHT_GRAY);

        cartPanel.setLayout(new GridLayout(1,1));
        finalPricePanel.setLayout(new GridLayout(4,2));

        int quantity = 1;
        ShoppingCartTableModel tableModel = new ShoppingCartTableModel(cartList, quantity);
        JTable table = new JTable(tableModel);
        TableColumn column = table.getColumnModel().getColumn(0);

        table.getTableHeader().setPreferredSize(new Dimension(200, 30));
        table.setRowHeight(30);
        table.getTableHeader().setBackground(Color.LIGHT_GRAY);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        table.setShowVerticalLines(false);

        JScrollPane tableScrollPane = new JScrollPane(table);
        cartPanel.add(tableScrollPane);
    }

//        cartPanel.setLayout(new GridLayout(cartList.size() + 1, 3));
//        finalPricePanel.setLayout(new GridLayout(4, 2));

//        JLabel productLabel = new JLabel("Product");
//        JLabel productQuantityLabel = new JLabel("Quantity");
//        JLabel priceLabel = new JLabel("Price");
//        cartPanel.add(productLabel);
//        cartPanel.add(productQuantityLabel);
//        cartPanel.add(priceLabel);
//
//        for (int i = 0 ; i < cartList.size() ; i++) {
//            String description = "";
//            if (cartList.get(i) instanceof Electronics) {
//                Electronics electronics = (Electronics) cartList.get(i);
//                description =  electronics.getProductId() + "/n" + electronics.getProductName() + "/n" + electronics.getElectronicBrand() + "/n" + electronics.getElectronicWarrantyPeriod() + " months warranty";
//            } else {
//                Clothing clothing = (Clothing) cartList.get(i);
//                description = clothing.getProductId() + "/n" + clothing.getProductName() + "/n" + clothing.getClothingSize() + "/n" + clothing.getClothingColour();
//            }
//            JLabel productDescription = new JLabel(description);
//            cartPanel.add(productDescription);
//
//            JPanel productQuantityPanel = new JPanel();
//            cartPanel.add(productQuantityPanel);
//            productQuantityPanel.setLayout(new GridLayout(1, 3));
//
//            JButton increaseQuantity = new JButton("+");
//            JLabel defaultProductQuantity = new JLabel("1");
//            JButton decreaseQuantity = new JButton("-");
//            productQuantityPanel.add(decreaseQuantity);
//            productQuantityPanel.add(defaultProductQuantity);
//            productQuantityPanel.add(increaseQuantity);
//
//            int productObjectIndex = i;
////            int availableQuantity = cartList.get(i).getNumAvailableItems();
//            EventListener eventListener = new EventListener(defaultProductQuantity, productObjectIndex);
//            increaseQuantity.addActionListener(eventListener);
//            decreaseQuantity.addActionListener(eventListener);
//
//            JLabel productPrice = new JLabel(cartList.get(i).getProductPrice() + " LKR");
//            cartPanel.add(productPrice);
//        }
//    }

    public class EventListener implements ActionListener {
        JLabel defaultProductQuantity;
//        int availableQuantity;
        int productObjectIndex;
        public EventListener(JLabel defaultProductQuantity, int productObjectIndex) {
            this.defaultProductQuantity = defaultProductQuantity;
//            this.availableQuantity = availableQuantity;
            this.productObjectIndex = productObjectIndex;
        }
        public void actionPerformed(ActionEvent e) {
            int availableQuantity = cartList.get(productObjectIndex).getNumAvailableItems();
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("+")) {
                if (availableQuantity > 0) {
                    int quantity = Integer.parseInt(defaultProductQuantity.getText());
                    quantity++;
                    defaultProductQuantity.setText(quantity + "");
                    availableQuantity--;
                    Product product = cartList.get(productObjectIndex);
                    product.setNumAvailableItems(availableQuantity);
                }
            } else if (button.getText().equals("-")) {
                int quantity = Integer.parseInt(defaultProductQuantity.getText());
                if (quantity > 1) {
                    quantity--;
                    defaultProductQuantity.setText(quantity + "");
                    availableQuantity++;
                    Product product = cartList.get(productObjectIndex);
                    product.setNumAvailableItems(availableQuantity);
                }
            }
        }
    }
    public void addProduct(Product product) {
        this.cartList.add(product);
    }

    public void removeProduct(Product product) {
        this.cartList.remove(product);
    }

    public double getTotalCost() {
        double totalCost = 0;
        for (Product product : cartList) {
            totalCost += product.getProductPrice();
        }
        return totalCost;
    }
}
