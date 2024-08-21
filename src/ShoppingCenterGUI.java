import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ShoppingCenterGUI extends JFrame {
    public static ShoppingCart  userSelectCart = new ShoppingCart();
    public static Product selectedProduct;
    static JComboBox<String> productType;
    JButton shoppingCartBtn;
    JButton addToCartBtn;
    JTextArea productInfo;
    private JTable table;

    String[] productTableColumn = {"Product ID", "Name", "Category", "Price", "Information"};// create the table column

    DefaultTableModel tableModel = new DefaultTableModel(productTableColumn, 0);// create the table model

    public ShoppingCenterGUI() {
        ImageIcon favicon = new ImageIcon("src/Images/westminster_icon.png");
        this.setIconImage(favicon.getImage());
        this.setTitle("Westminster Shopping Center");
        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);// close the frame when the user click the close button

        this.setBounds(0, 0, 690, 730);
        this.setResizable(false);
        JLabel background = new JLabel();
        background.setBounds(0, 0, 690, 730);
        this.add(background);
        JLabel selectProType = new JLabel("Select Product Category");
        selectProType.setBounds(40, 20, 200, 30);
        selectProType.setFont(new Font("Fira Sans", Font.BOLD, 13));
        this.add(selectProType);
        JLabel tableName = new JLabel ("Available Items in the Store");
        tableName.setBounds(40, 60, 200, 30);
        this.add(tableName);
        JLabel productInfo = new JLabel("Product Information");
        productInfo.setBounds(40, 360, 200, 30);
        productInfo.setFont(new Font("Fira Sans", Font.BOLD, 13));
        this.add(productInfo);
        background.add(createTable());
        background.add(productInfo());
        background.add(dropDownItems());
        background.add(shoppingCartBtn());
        shoppingCartBtnHandler shoppingCartBtnHandler = new shoppingCartBtnHandler();
        shoppingCartBtn.addActionListener(shoppingCartBtnHandler);
        background.add(addToCartBtn());
        addToCartBtnHandler addToCartBtnHandler = new addToCartBtnHandler();
        addToCartBtn.addActionListener(addToCartBtnHandler);
        this.setVisible(true);// make the frame visible

    }

    public JComboBox<String> dropDownItems() {
        String[] dropDownItems = {"All", "Electronics", "Clothing"};
        productType = new JComboBox<>(dropDownItems);
        productType.setEditable(true);
        productType.addActionListener(this::clickActionEvent);
        productType.setBounds(220, 25, 200, 20);
        return productType;
    }

    private JScrollPane createTable() {
        // Add all products to the model
        for(Product product : WestminsterShoppingManager.getProducts()){
            if (product instanceof Electronics){
                Object[] rowData = {product.getProductID(), product.getProductName(), product.getClass().getSimpleName()
                        , product.getPrice(), ((Electronics) product).getBrand()+ "," +
                        ((Electronics) product).getWarranty_period() + "Months"};
                tableModel.addRow(rowData);
            }else if (product instanceof Clothing){
                Object[] rowData = {product.getProductID(), product.getProductName(), product.getClass().getSimpleName()
                        , product.getPrice(), ((Clothing) product).getSize()};
                tableModel.addRow(rowData);
            }
        }

        table = new JTable(tableModel);// create the table
        table.setRowHeight(20); // set the row height
        table.setFont(new Font("Fira Sans", Font.PLAIN, 15));
        table.getTableHeader().setFont(new Font("Fira Sans", Font.BOLD, 15));
        table.getTableHeader().setOpaque(false);// make the table header transparent
        table.getTableHeader().setBackground(new Color(150, 80, 100, 202));
        table.getTableHeader().setForeground(new Color(255, 255, 255, 255));
        table.getTableHeader().setBorder(null);
        table.getTableHeader().setReorderingAllowed(true);
        table.getTableHeader().setResizingAllowed(true);
        table.setShowGrid(true);
        table.setOpaque(false);
        table.setFillsViewportHeight(true);// make the table fit to the scroll pane
        table.setDefaultEditor(Object.class, null);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);// center the table column
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        table.getSelectionModel().addListSelectionListener(e -> {// when the user select a row from the table
            if (table.getSelectedRow() !=-1){// if the user select a row
                selectedProduct = WestminsterShoppingManager.getProducts().get(table.getSelectedRow());
                for (Product product : WestminsterShoppingManager.getProducts()){
                    if (product instanceof Electronics){//when click the electronic product the user will get the information about the product
                        if (product.getProductID().equals(table.getValueAt(table.getSelectedRow(), 0))){
                            productInfo.setText(product.toString());
                        }
                    }else if (product instanceof Clothing){//when click the clothing product the user will get the information about the product
                        if (product.getProductID().equals(table.getValueAt(table.getSelectedRow(), 0))){
                            productInfo.setText(product.toString());
                        }
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);// add the table to the scroll pane
        scrollPane.setBounds(40, 100, 600, 250);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        return scrollPane;

    }

    public void clickActionEvent(ActionEvent e) {
        if (e.getSource() == productType) {
            String selectedProductType = (String) productType.getSelectedItem();
            if (selectedProductType.equals("All")) {// if the user select all
                tableModel.setRowCount(0);
                for (Product product : WestminsterShoppingManager.getProducts()) {
                    Object[] rowData = {product.getProductID(), product.getProductName(),
                            product.getClass().getSimpleName(), product.getPrice()};
                    tableModel.addRow(rowData);
                }
            } else if (selectedProductType.equals("Electronics")) {// if the user select electronics
                tableModel.setRowCount(0);
                for (Product product : WestminsterShoppingManager.getProducts()) {
                    if (product instanceof Electronics) {
                        Object[] rowData = {product.getProductID(), product.getProductName(),
                                product.getClass().getSimpleName(), product.getPrice()};
                        tableModel.addRow(rowData);
                    }
                }
            } else if (selectedProductType.equals("Clothing")) {// if the user select clothing from the dropdown menu
                tableModel.setRowCount(0);
                for (Product product : WestminsterShoppingManager.getProducts()) {
                    if (product instanceof Clothing) {
                        Object[] rowData = {product.getProductID(), product.getProductName(),
                                product.getClass().getSimpleName(), product.getPrice()};
                        tableModel.addRow(rowData);
                    }
                }
            }
        }
    }

    public JTextArea productInfo() {
        productInfo = new JTextArea();
        productInfo.setBounds(40, 400, 600, 230);
        productInfo.setEditable(false);
        productInfo.setBackground(new Color(112, 112, 112, 255));
        productInfo.setForeground(new Color(255, 255, 255, 255));
        productInfo.setFont(new Font("Fira Sans", Font.BOLD, 17));
        return productInfo;
    }

    public JButton shoppingCartBtn() {
        ImageIcon shoppingCartIcon = new ImageIcon("src/Images/icons8-shopping-cart-24.png");
        shoppingCartBtn = new JButton("Shopping Cart");
        shoppingCartBtn.setBounds(490, 25, 170, 30);
        shoppingCartBtn.setForeground(new Color(255, 255, 255, 255));
        shoppingCartBtn.setBackground(new Color(150, 80, 100, 202));
        shoppingCartBtn.setOpaque(true);
        shoppingCartBtn.setBorderPainted(true);
        shoppingCartBtn.setFocusable(false);
        shoppingCartBtn.setIcon(shoppingCartIcon);
        shoppingCartBtn.setFont(new Font("Fira Sans", Font.PLAIN, 15));
        return shoppingCartBtn;
    }

    public JButton addToCartBtn() {
        addToCartBtn = new JButton("Add to Cart");
        addToCartBtn.setBounds(250, 640, 150, 30);
        addToCartBtn.setBackground(new Color(150, 80, 100, 202));
        addToCartBtn.setForeground(new Color(255, 255, 255, 255));
        addToCartBtn.setOpaque(true);//make the button transparent
        addToCartBtn.setFocusable(false);//remove the focus
        return addToCartBtn;

    }

    private  class addToCartBtnHandler implements ActionListener {
        public void actionPerformed(ActionEvent e)// when the add to cart button is clicked
        {
            if (selectedProduct != null){// if the user select a product
                for (Product product : WestminsterShoppingManager.getProducts()){// add the selected product to the cart
                    if (product instanceof Electronics){
                        if (product.getProductID().equals(table.getValueAt(table.getSelectedRow(), 0))){
                            userSelectCart.addProduct(product);
                            // when adding to cart, reduce the availability of the product by 1
                            product.setNumberOfAvailability(product.getNumberOfAvailability()-1);
                            productInfo.setText(product.toString());

                        }
                    }else if (product instanceof Clothing){// when adding to cart, reduce the availability of the product by 1
                        if (product.getProductID().equals(table.getValueAt(table.getSelectedRow(), 0))){
                            userSelectCart.addProduct(product);
                            product.setNumberOfAvailability(product.getNumberOfAvailability()-1);
                            productInfo.setText(product.toString());
                        }
                    }
                }
                //message for the user to know the product is added to the cart
                JOptionPane.showMessageDialog(null, "Selected product added to cart", "Success", JOptionPane.INFORMATION_MESSAGE);
            }else{
                //message for the user to know the product is not selected
                JOptionPane.showMessageDialog(null, "No product selected", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class shoppingCartBtnHandler implements ActionListener {// when the shopping cart button is clicked
        private boolean isShoppingCartGUIOpen = false;
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == shoppingCartBtn) {
                // Check if ShoppingCartGUI is already open
                if (!isShoppingCartGUIOpen) {
                    if (ShoppingCart.productArrayList.size() != 0) {
                        // Set the flag to true before opening the ShoppingCartGUI
                        isShoppingCartGUIOpen = true;

                        // Create and display the ShoppingCartGUI
                        ShoppingCartGUI shoppingCartGUI = new ShoppingCartGUI();


                        // Add a window listener to the ShoppingCartGUI to detect when it's closed
                        shoppingCartGUI.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                // Set the flag to false when ShoppingCartGUI is closed
                                isShoppingCartGUIOpen = false;
                            }
                        });
                    } else {
                        // Display an error message if the cart is empty
                        JOptionPane.showMessageDialog(null, "No products in the cart", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else {// Display an error message if the cart is already open
                    JOptionPane.showMessageDialog(null, "Shopping cart is already open", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
