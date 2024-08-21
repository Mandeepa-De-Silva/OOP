import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ShoppingCartGUI extends JFrame {
    private JPanel priceList;
    String[] shoppingCartColumnNames = {"Product","Quantity", "Price"};
    DefaultTableModel shoppingCartTableModel = new DefaultTableModel(shoppingCartColumnNames, 0);
    public ShoppingCartGUI() {
        ImageIcon favicon = new ImageIcon("src/Images/westminster_icon.png");
        this.setIconImage(favicon.getImage());//set favicon
        this.setTitle("Shopping Cart");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(680, 0, 630, 550);
        setResizable(false);
        JLabel shoppingCartBackground = new JLabel();
        shoppingCartBackground.setBounds(0, 0, 630, 550);
        this.add(shoppingCartBackground);
        shoppingCartBackground.add(createTable());

        JLabel totalLabel = new JLabel("Total: £");
        totalLabel.setBounds(100, 300, 330, 30);
        totalLabel.setFont(new Font("Fira Sans", Font.BOLD, 12));
        totalLabel.setForeground(Color.WHITE);
        shoppingCartBackground.add(totalLabel);

        JLabel newUserDiscountLabel = new JLabel("First Purchase Discount (10%)");
        newUserDiscountLabel.setBounds(100, 330, 330, 30);
        newUserDiscountLabel.setFont(new Font("Fira Sans", Font.BOLD, 12));
        newUserDiscountLabel.setForeground(Color.WHITE);
        shoppingCartBackground.add(newUserDiscountLabel);

        JLabel threeItemSameProduct = new JLabel("Three Items in same Discount (20%)");
        threeItemSameProduct.setBounds(100, 360, 330, 30);
        threeItemSameProduct.setFont(new Font("Fira Sans", Font.BOLD, 12));
        threeItemSameProduct.setForeground(Color.WHITE);
        shoppingCartBackground.add(threeItemSameProduct);

        JLabel finalTotal = new JLabel("Final Total: £");
        finalTotal.setBounds(100, 390, 330, 30);
        finalTotal.setFont(new Font("Fira Sans", Font.BOLD, 12));
        finalTotal.setForeground(Color.WHITE);
        shoppingCartBackground.add(finalTotal);

        //total without discount
        JLabel totalWithoutDis = new JLabel(String.valueOf(ShoppingCenterGUI.userSelectCart.getTotalCost()));
        totalWithoutDis.setBounds(355, 300, 80, 30);
        totalWithoutDis.setFont(new Font("Fira Sans", Font.BOLD, 15));
        totalWithoutDis.setForeground(Color.WHITE);
        shoppingCartBackground.add(totalWithoutDis);

        //first purchase discount
        Label fistDiscount = new Label();
        fistDiscount.setBounds(340, 330, 75, 30);
        fistDiscount.setFont(new Font("Fira Sans", Font.BOLD, 15));
        fistDiscount.setForeground(Color.WHITE);
        shoppingCartBackground.add(fistDiscount);

        //three items discount
        Label threeItemsDiscount = new Label(String.valueOf(ShoppingCenterGUI.userSelectCart.threeItemSameDiscount()));
        threeItemsDiscount.setBounds(300, 360, 10, 30);
        threeItemsDiscount.setFont(new Font("Fira Sans", Font.BOLD, 15));
        threeItemsDiscount.setForeground(Color.WHITE);
        shoppingCartBackground.add(threeItemsDiscount);

        //final total with discount
        JLabel finalTotalWithDis = new JLabel(String.valueOf(ShoppingCenterGUI.userSelectCart.finalCost()));
        finalTotalWithDis.setBounds(355, 390, 80, 30);
        finalTotalWithDis.setFont(new Font("Fira Sans", Font.BOLD, 15));
        finalTotalWithDis.setForeground(Color.WHITE);
        shoppingCartBackground.add(finalTotalWithDis);


        shoppingCartBackground.add(priceList());
        setVisible(true);
    }
    private JScrollPane createTable() {
        // Add all products to the model
        for(Product product : ShoppingCenterGUI.userSelectCart.getProductArrayList()){
            if (product instanceof Electronics){
                Object[] rowData = {product.getProductID()+ product.getProductName()+ ((Electronics) product).getBrand(),
                        1,product.getPrice()};
                shoppingCartTableModel.addRow(rowData);
            }else if (product instanceof Clothing){
                Object[] rowData = {product.getProductID()+ product.getProductName()+
                          ((Clothing) product).getSize()+ ((Clothing) product).getColour() , 1,product.getPrice()};
                shoppingCartTableModel.addRow(rowData);
            }
        }

        JTable table = new JTable(shoppingCartTableModel);
        table.setRowHeight(20);
        table.setFont(new Font("Fira Sans", Font.PLAIN, 15));
        table.getTableHeader().setFont(new Font("Fira Sans", Font.BOLD, 15));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(150, 80, 100, 202));
        table.getTableHeader().setForeground(new Color(255, 255, 255, 255));
        table.getTableHeader().setBorder(null);
        table.getTableHeader().setReorderingAllowed(true);
        table.getTableHeader().setResizingAllowed(true);
        table.setShowGrid(true);
        table.setOpaque(false);
        table.setFillsViewportHeight(true);//set the table to fill the scroll pane
        table.setDefaultEditor(Object.class, null);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();//set the table to center
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JScrollPane scrollPane = new JScrollPane(table);//add the table to the scroll pane
        scrollPane.setBounds(65, 35, 500, 250);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        return scrollPane;
    }
    public  JPanel priceList() {//create the price list panel
        priceList = new JPanel();
        priceList.setBounds(95, 300, 471, 150);
        priceList.setBackground(new Color(112, 112, 112, 255));
        priceList.setFont(new Font("Fira Sans", Font.PLAIN, 15));
        return priceList;
    }
}
