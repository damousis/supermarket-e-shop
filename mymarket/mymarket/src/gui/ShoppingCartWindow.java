package gui;

import api.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * This class is used to represent the window that will pop up when the customer wants to see his/hers Shopping cart.
 * @author Vasiliki Zerdali,Alexander Damousis
 */
public class ShoppingCartWindow implements ActionListener
{
    final private JFrame shoppingWindow = new JFrame();
    JLabel cost =new JLabel();
    final private String username;
    final private JPanel shoppingPanel = new JPanel();
    final private JButton purchaseButton=new JButton();
    private ArrayList<JPanel> panelOfProduct;
    final private JFrame mainWindow;

    private ProductsList helperProductCart;
    private ArrayList<String>  helperQuantity;

    private OrdersFile ordersFile;

    private ProductsList listOfProducts;
    int counter;

    /**
     * This is the constructor that set the main window that holds the panel where all the smaller panels will be placed.
     * @param customersCart is a list that contains the products that are already checked by the customer.These products should be shown in the window.
     * @param quantityOfProduct is a list that holds the quantity of each product that the customer has selected.it is used to calculate the price.
     *                   SOS ΟΙ ΛΙΣΤΕΣ ΕΙΝΑΙ ΠΑΡΑΛΛΗΛΕΣ.
     */
    public ShoppingCartWindow(ProductsList customersCart, ArrayList<String> quantityOfProduct,ProductsList listOfProducts,String username,OrdersFile ordersFile,JFrame mainWindow,ArrayList<JPanel> panelOfProduct)
    {
        helperProductCart = customersCart;
        this.username = username;
        this.mainWindow = mainWindow;
        this.panelOfProduct = panelOfProduct;
        helperQuantity = quantityOfProduct;
        this.listOfProducts = listOfProducts;
        this.ordersFile = ordersFile;

        shoppingWindow.setSize(750,500);
        shoppingWindow.setResizable(false);
        shoppingWindow.setTitle("Shopping Cart");
        shoppingWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        shoppingWindow.add(shoppingPanel);
        shoppingWindow.getContentPane().setBackground(new Color(242, 242, 242)); //221 235 250
        shoppingPanel.setBackground(new Color(253, 255, 255)); //199, 223, 252

        int padding = 30;
        int totalRows = customersCart.getSize();
        int panelsWidth = 750;
        int panelHeight = totalRows* (65+padding) + 110;
        shoppingPanel.setLayout(null);
        shoppingPanel.setPreferredSize(new Dimension(panelsWidth,panelHeight));

        setTitleLabels();
        setShoppingPanels(customersCart,quantityOfProduct);

        JScrollPane scroll=new JScrollPane(shoppingPanel);
        shoppingPanel.setPreferredSize(new Dimension(750, panelHeight));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(0,0,10,30);
        scroll.getVerticalScrollBar().setUnitIncrement(100);
        scroll.getVerticalScrollBar().setBlockIncrement(100);

        shoppingWindow.add(scroll);
        SwingUtilities.invokeLater(() ->
        {
            scroll.getViewport().setViewPosition(new Point(0, 0));
        });
        shoppingWindow.setVisible(true);

    }


    /**
     * This is a method that creates all the title labels that will be shown in the window.The labels are
     * 1)Products name
     * 2)sum price
     * 3)sum quantity
     */
    public void setTitleLabels()
    {
        JLabel productsName = new JLabel();
        productsName.setText("Product's name");
        productsName.setBounds(58,57,150,30);
        productsName.setFont(new Font("Arial", Font.ITALIC,17));
        shoppingPanel.add(productsName);

        JLabel price = new JLabel();
        price.setText("Price");
        price.setBounds(305,57,80,30);
        price.setFont(new Font("Arial", Font.ITALIC,17));
        shoppingPanel.add(price);

        JLabel quantityLabel=new JLabel();
        quantityLabel.setText("Quantity");
        quantityLabel.setBounds(480,57,80,35);
        quantityLabel.setFont(new Font("Arial", Font.ITALIC,17));
        shoppingPanel.add(quantityLabel);


    }


    /**
     *This is a method used to create All the small panels of each product that is stored in the customerCart list.
     * It creates a panel, and then it stores it in a bigger panel that already exists.
     * @param customerCart is a list that contains the products that are already checked by the customer.These products should be shown in the window.
     * @param quantityOfProduct is a list that holds the quantity of each product that the customer has selected.it is used to calculate the price.
     */
    public void setShoppingPanels(ProductsList customerCart,ArrayList<String> quantityOfProduct)
    {
        int panelsWidth = 650;
        int panelsHeight = 65;

        for(int i=0;i<customerCart.getSize();i++)
        {
            Products product=customerCart.getProduct(i);
            JPanel shoppingProductPanel = new JPanel();
            shoppingProductPanel.setBounds(50,(i+1)*85,panelsWidth,panelsHeight);
            shoppingProductPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            shoppingProductPanel.setBackground(new Color(248,248,248));

            String quantity = quantityOfProduct.get(i);
            setShoppingLabels(shoppingProductPanel,product,panelsHeight,quantity,customerCart,quantityOfProduct);

            shoppingPanel.add(shoppingProductPanel);
        }
        setPurchaseButton(customerCart,quantityOfProduct);

        cost.setBounds(15,12,165,35);
        cost.setText("Final Cost : " + customerCart.getFinalCost(quantityOfProduct)+"€");
        cost.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0)); // 5-pixel thick black border
        cost.setFont(new Font("Arial",  Font.BOLD | Font.ITALIC, 17));

        shoppingPanel.add(cost);


    }


    /**
     *This method sets up the smaller panels for the shopping cart.
     * @param shoppingProductPanel is the big panel that all the smaller panels are stored.
     * @param product is the product that holds the info that we want to store in the smaller panel.
     * @param panelsHeight is the height of all the smaller panels.
     * @param quantity is the quantity of the product that we want to store.
     */
    public void setShoppingLabels(JPanel shoppingProductPanel,Products product,int panelsHeight,String quantity,ProductsList customerCart,ArrayList<String> quantityOfProducts)
    {
        shoppingProductPanel.setLayout(null);

        int helper;
        if(product.getTitle().length()>20)
        {
            helper = 20;
        }
        else
        {
            helper = 10;
        }
        JTextArea shoppingTitle= new JTextArea();
        shoppingTitle.setText(product.getTitle());
        shoppingTitle.setBounds(10,panelsHeight/2-helper,190,panelsHeight/2+10);
        shoppingTitle.setLineWrap(true);
        shoppingTitle.setWrapStyleWord(true);
        shoppingTitle.setEditable(false);
        shoppingTitle.setOpaque(false);
        shoppingTitle.setFont(new Font("Arial", Font.ITALIC, 15));

        JLabel shoppingQuantity = new JLabel();
        shoppingQuantity.setText(quantity);
        shoppingQuantity.setBounds(440,panelsHeight/2-15,150,panelsHeight/2);
        shoppingQuantity.setFont(new Font("Arial",Font.ITALIC,15));

        double finalQuantity = Double.parseDouble(quantity);
        CleanString price=new CleanString(product.getPrice());
        double finalPrice = Double.parseDouble(price.convertString());
        double sumPrice = (finalPrice * finalQuantity);
        String labelPrice = Double.toString(sumPrice);
        CleanString finalLabelPrice = new CleanString(labelPrice);

        JLabel shoppingPrice = new JLabel();
        shoppingPrice.setText(finalLabelPrice.convertString()+"€");
        shoppingPrice.setBounds(250,panelsHeight/2-15,150,panelsHeight/2);
        shoppingPrice.setFont(new Font("Arial",Font.ITALIC,15));

        BetterButton increase = new BetterButton();
        increase.setText("+");
        increase.addActionListener(this);
        increase.setBounds(483,panelsHeight/2-10,46,20);
        increase.setFont(new Font("Arial", Font.BOLD,14));
        increase.addAllProperties(product,customerCart,quantityOfProducts,shoppingQuantity,"+",shoppingPrice,shoppingProductPanel);

        BetterButton decrease = new BetterButton();
        decrease.setText("-");
        decrease.addActionListener(this);
        decrease.setBounds(390,panelsHeight/2-10,46,20);
        decrease.setFont(new Font("Arial", Font.BOLD,14));
        decrease.addAllProperties(product,customerCart,quantityOfProducts,shoppingQuantity,"-",shoppingPrice,shoppingProductPanel);


        BetterButton removeProductHelper= new BetterButton();
        BetterButton removeProduct = removeProductHelper.addImage();
        removeProduct.setBackground(Color.WHITE);
        removeProduct.addActionListener(this);
        //removeProduct.setText("    Remove");
        removeProduct.setBounds(570,panelsHeight/2-18,32,35);
        removeProduct.setFont(new Font("Arial", Font.BOLD,10));
        removeProduct.addAllProperties(product,customerCart,quantityOfProducts,shoppingQuantity,"remove",shoppingPrice,shoppingProductPanel);

        shoppingProductPanel.add(decrease);
        shoppingProductPanel.add(increase);
        shoppingProductPanel.add(shoppingTitle);
        shoppingProductPanel.add(shoppingPrice);
        shoppingProductPanel.add(shoppingQuantity);
        shoppingProductPanel.add(removeProduct);

    }

    /**
     * This is a method that creates a button so the customer can check out the products he wants.
     * @param customersCart is a list that contains the products that are already checked by the customer.These products should be shown in the window.
     * @param quantityOfProducts is a list that holds the quantity of each product that the customer has selected.it is used to calculate the price.
     */
    public void setPurchaseButton(ProductsList customersCart,ArrayList<String> quantityOfProducts)
    {
        for (ActionListener listener : purchaseButton.getActionListeners())
        {
            purchaseButton.removeActionListener(listener);
        }
        purchaseButton.setText("PURCHASE");
        purchaseButton.setBackground(new Color(166,240,154));//180,240,180
        purchaseButton.setForeground(new Color(44,54,44));
        purchaseButton.setBounds(560,20,150,30);
        purchaseButton.setFont(new Font("Arial",Font.BOLD,13));
        purchaseButton.addActionListener(this);
        shoppingPanel.add(purchaseButton);
    }

    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource()==purchaseButton)
        {
            String finalCost = helperProductCart.getFinalCost(helperQuantity);
            if(Double.parseDouble(finalCost)>0)
            {
                int choice = JOptionPane.showConfirmDialog(null, "The final cost is: " + finalCost + "€.Are you sure you want to checkout?", "Complete the purchase.", JOptionPane.YES_NO_OPTION);

                if (choice == 0)
                {
                    shoppingWindow.dispose();
                    listOfProducts.updateListOfProducts(helperProductCart,helperQuantity);
                    listOfProducts.updateCustomerPanels(panelOfProduct,helperProductCart);
                    ordersFile.addNewPurchase(username,helperProductCart,helperQuantity,listOfProducts);
                    shoppingWindow.removeAll();

                    for(int i=0;i<helperProductCart.getSize();i++)
                    {
                        helperProductCart.remove(i);
                        helperQuantity.remove(i);
                        i--;
                    }

                    helperProductCart = new ProductsList();
                    CustomersMainScreen.counter =0;
                    CustomersMainScreen.shoppingCart.setText("Shopping Cart("+CustomersMainScreen.counter+")");

                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"You have no products in the cart!","ERROR MESSAGE",JOptionPane.PLAIN_MESSAGE);
            }
        }
        else
        {
            BetterButton source = (BetterButton) e.getSource();

            Products storedProduct = source.getProduct();
            String type = source.getType();

            helperProductCart = source.getProductList();
            helperQuantity = source.getQuantityList();
            JPanel helperPanel = source.getPanel();

            JLabel helperQuantityLabel = source.getQuantityLabel();
            JLabel helperPriceLabel = source.getPriceLabel();



            BigDecimal temp = new BigDecimal(helperQuantityLabel.getText());
            int bigIndex = listOfProducts.getIndex(storedProduct.getTitle());
            int index = helperProductCart.getIndex(storedProduct.getTitle());

            if(type.equals("+"))
            {

                if(helperProductCart.contains(helperProductCart.getProduct(index).getQuantity()))
                {
                    if(listOfProducts.check(bigIndex,Double.parseDouble(helperQuantityLabel.getText())+0.1))
                        temp = temp.add(new BigDecimal("0.1"));
                    else
                        JOptionPane.showMessageDialog(null,"There is no more quantity left!","Exceeded quantity",JOptionPane.PLAIN_MESSAGE);

                }
                else
                {
                    if(listOfProducts.check(bigIndex,Double.parseDouble(helperQuantityLabel.getText())+1))
                        temp = temp.add(new BigDecimal("1"));
                    else
                        JOptionPane.showMessageDialog(null,"There is no more quantity left!","Exceeded quantity",JOptionPane.PLAIN_MESSAGE);

                }
            }

            else if(type.equals("-"))
            {
                if(helperProductCart.contains(helperProductCart.getProduct(index).getQuantity()))
                {
                    BigDecimal temp2 = temp.add(new BigDecimal("-0.10"));
                    if(temp2.compareTo(BigDecimal.ZERO) >0)
                        temp = temp.add(new BigDecimal("-0.10"));
                }
                else
                {
                    BigDecimal temp2 = temp.add(new BigDecimal("-1"));
                    if(temp2.compareTo(BigDecimal.ZERO) >0)
                        temp = temp.add(new BigDecimal("-1"));
                }
            }

            else //remove method
            {
                CustomersMainScreen.counter -= 1;
                CustomersMainScreen.shoppingCart.setText("Shopping Cart("+CustomersMainScreen.counter+")");
                shoppingPanel.removeAll();
                shoppingPanel.repaint();
                shoppingPanel.revalidate();
                String name = storedProduct.getTitle();
                int position = helperProductCart.getIndex(name);
                helperProductCart.remove(position);
                helperQuantity.remove(position);
                setShoppingPanels(helperProductCart,helperQuantity);
                setTitleLabels();
            }

            if(type.equals("+") || type.equals("-"))
            {
                helperQuantityLabel.setText(temp.toString());
                helperQuantity.set(index, temp.toString());


                String euroPrice = helperProductCart.getProduct(index).getPrice();
                CleanString tempPrice = new CleanString(euroPrice);
                helperPriceLabel.setText(tempPrice.showFinalPrice(helperQuantityLabel.getText()) + "€");

                cost.setText("Final Cost : " + helperProductCart.getFinalCost(helperQuantity) + "€");
            }
        }
    }

}