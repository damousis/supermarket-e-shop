package gui;

import api.OrdersFile;
import api.ProductsList;
import api.Products;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;


//panelOfProduct   a list that contains the panel of each product
//listOfProducts a list with all the products

/**
 * This class represent the window that will show up for the customers
 * This class used the MainWindow class.So in this class we only have to add some more things.
 */
public class CustomersMainScreen extends MainWindow implements ActionListener
{
    static  JButton shoppingCart = new JButton();

    static int counter = 0;
    final private JButton historyOfOrders = new JButton();
    final private String username;
    private ArrayList<String> quantityOfProduct = new ArrayList<>();
    private ProductsList customersCart = new ProductsList();

    private OrdersFile ordersFile;


    final private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    /**
     * This is the constructor that calls the constructor of the MainWindow class and calls the other methods.
     * @param listOfProducts is a list that contains all the products from the txt file and holds the info for all the products.
     */
    public CustomersMainScreen(ProductsList listOfProducts,String username,OrdersFile ordersFile)
    {
        super(listOfProducts);
        this.username=username;
        this.ordersFile = ordersFile;
        setButtons();


    }


    /**
     * This method is used to add some buttons in the screen.
     * The buttons are
     * 1)The shopping cart, so the customer can see his/hers shop[ping cart.
     * 2)On each panel a button '+' and '-' so that the customer can change the quantity that he selects for each product.
     */
    public void setButtons()
    {
        historyOfOrders.setText("Orders History");
        historyOfOrders.setBounds((int)screenSize.getWidth()-380,23,140,40);
        historyOfOrders.addActionListener(this);
        historyOfOrders.putClientProperty("username",username);
        historyOfOrders.setFont(new Font("Arial",Font.BOLD,14));
        productsPanel.add(historyOfOrders);

        shoppingCart.setText("Shopping Cart("+counter+")");
        shoppingCart.setBounds((int)screenSize.getWidth()-190,23,140,40);
        shoppingCart.addActionListener(this);
        shoppingCart.setFont(new Font("Arial",Font.BOLD,13));
        productsPanel.add(shoppingCart);

        for(JPanel panel: panelOfProduct)
        {
            JButton addButton = new JButton();
            addButton.setText("+");
            addButton.setBounds(135,320,44,30); // 260!!!!!!!!!!!!!!!!!!!!!!!!!
            addButton.putClientProperty("product",panel.getClientProperty("product"));
            addButton.putClientProperty("type","+");
            addButton.addActionListener(this);
            panel.add(addButton);


            JButton minusButton = new JButton();
            minusButton.setText("-");
            minusButton.setBounds(15,320,44,30); //260!!!!!!!!!!!!!!!!!!!!!!!!
            minusButton.setFont(new Font("Arial", Font.BOLD,15));
            minusButton.putClientProperty("product",panel.getClientProperty("product"));
            minusButton.putClientProperty("type","-");
            minusButton.addActionListener(this);
            panel.add(minusButton);

            JButton addToCard = new JButton();
            addToCard.setText("Add To Card");
            addToCard.setBounds(210,320,150,30); //260
            addToCard.setFont(new Font("Arial",Font.BOLD,16));
            addToCard.putClientProperty("product",panel.getClientProperty("product"));
            addToCard.putClientProperty("type","add");
            addToCard.addActionListener(this);
            panel.add(addToCard);
            setTextFields(panel,addButton,addToCard,minusButton);
        }
    }


    /**
     * This method is used top create the field where the quantity that the user selects is stored and shown.
     * @param panel is the panel of each product.
     * @param addButton is the button '+' that increases the quantity of each product.
     * @param addToCard is the button that when it is pressed it adds a product in the cart of a customer.
     * @param minusButton is the button that when it is pressed it decreases the quantity of a product in the cart of the customer.
     */
    public void setTextFields(JPanel panel,JButton addButton,JButton addToCard,JButton minusButton)
    {

        JTextField productQuantity=new JTextField();
        productQuantity.setText("0");
        productQuantity.setBounds(75,320,44,30); //260!!!!!!!!!!!
        panel.add(productQuantity);

        addButton.putClientProperty("TextField",productQuantity);
        addToCard.putClientProperty("TextField",productQuantity);
        minusButton.putClientProperty("TextField",productQuantity);

        panel.revalidate();
        panel.repaint();

    }




    /**
     * This method is used to have access to what the buttons will do when they are pressed.
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==subCategory || e.getSource()==categoriesCombo || e.getSource()==searchButton || e.getSource()==logOut)
            super.actionPerformed(e);

        else if(e.getSource()!=historyOfOrders)
        {
            try
            {
                JButton sourceButton = (JButton) e.getSource();
                Products product = (Products) sourceButton.getClientProperty("product");
                String type = (String) sourceButton.getClientProperty("type");
                JTextField quantity = (JTextField) sourceButton.getClientProperty("TextField");
                String measure;
                JTextField quantityField = (JTextField) sourceButton.getClientProperty("TextField");

                if (product != null)
                {
                    if (product.getQuantity().contains("kg"))
                    {
                        measure = "kg";
                    }
                    else
                    {
                        measure = "τεμαχια";
                    }
                    quantity.setText(quantity.getText().replace(",",".").trim());
                    BigDecimal tempPrice = new BigDecimal(quantity.getText());
                    int biggerIndex = listOfProducts.getIndex(product.getTitle());

                    if (type.equals("+"))
                    {
                        if (measure.equals("kg"))
                        {
                            if (listOfProducts.check(biggerIndex, Double.parseDouble(quantity.getText()) + 0.1))
                                tempPrice = tempPrice.add(new BigDecimal("0.10"));
                            else
                                JOptionPane.showMessageDialog(null, "There is no more quantity left!", "Exceeded quantity", JOptionPane.PLAIN_MESSAGE);
                        }
                        else
                        {
                            if (listOfProducts.check(biggerIndex, Double.parseDouble(quantity.getText()) + 1))
                                tempPrice = tempPrice.add(new BigDecimal("1"));
                            else
                                JOptionPane.showMessageDialog(null, "There is no more quantity left!", "Exceeded quantity", JOptionPane.PLAIN_MESSAGE);
                        }
                    }

                    else if (type.equals("-"))
                    {
                        if (tempPrice.compareTo(BigDecimal.ZERO) > 0)
                        {
                            if (measure.equals("kg"))
                            {
                                tempPrice = tempPrice.add(new BigDecimal("-0.10"));
                            }
                            else
                            {
                                tempPrice = tempPrice.add(new BigDecimal("-1"));
                            }
                        }
                    }

                    else if (type.equals("add"))
                    {
                        if (Float.parseFloat(quantityField.getText()) > 0)
                        {
                            if(product.getQuantity().contains("τεμάχια") && quantityField.getText().contains("."))
                            {
                                JOptionPane.showMessageDialog(null, "You must give an integer.", "error", JOptionPane.ERROR_MESSAGE);
                                quantityField.setText("0");
                            }
                            else
                            {
                                if (!listOfProducts.check(biggerIndex, Double.parseDouble(quantityField.getText())))
                                {

                                    quantityField.setText("0");
                                    JOptionPane.showMessageDialog(null, "You have exceeded the quantity that exists!", "Exceeded quantity", JOptionPane.PLAIN_MESSAGE);
                                }
                                else
                                {
                                    if (!customersCart.alreadyExists(product.getTitle()))
                                    {
                                        counter += 1;
                                        shoppingCart.setText("Shopping Cart(" + counter + ")");
                                        String quantityToAdd = quantityField.getText();
                                        char[] characters = quantityToAdd.toCharArray();

                                        if (!measure.equals("kg") && characters[0] == '0')
                                            quantityToAdd = quantityToAdd.substring(1);

                                        quantityOfProduct.add(quantityToAdd);
                                        customersCart.addToList(product);
                                        mainWindow.repaint();
                                        mainWindow.revalidate();
                                    }
                                    else
                                    {
                                        int index = customersCart.getIndex(product.getTitle());
                                        quantityOfProduct.set(index, quantityField.getText());
                                    }
                                }
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"You must give a positive value.","Error",JOptionPane.ERROR_MESSAGE);
                            quantityField.setText("0");
                        }
                    }
                    if (type.equals("+") || type.equals("-"))
                    {
                        quantity.setText((tempPrice.toString()));
                    }

                }
                else
                {
                    ShoppingCartWindow shoppingWindow = new ShoppingCartWindow(customersCart, quantityOfProduct, listOfProducts, username, ordersFile, mainWindow, panelOfProduct);
                }
            }

            catch (NumberFormatException f)
            {
                JOptionPane.showMessageDialog(null,"You did not give a proper value.Please give a number","ERROR MESSAGE",JOptionPane.PLAIN_MESSAGE);

            }
        }
        else if(e.getSource()==historyOfOrders)
        {
            JButton source = (JButton) e.getSource();
            String name = (String) source.getClientProperty("username");
            try
            {
                HistoryScreen historyScreen = new HistoryScreen(name,ordersFile);
            }
            catch (IOException ex)
            {
                throw new RuntimeException(ex);
            }
        }

    }

}