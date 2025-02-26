package api;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author vasiliki Zerdali, Alexander Damousis
 * This class is used to create buttons that we can modify more properly and faster.
 * It has method in order to put properties in the button and take the properties from the button.
 * Also, it has a method to put an image in the file.
 */
public class BetterButton extends JButton
{
    /**
     * This is a constructor for the class without any parameters.
     */
    public BetterButton()
    {
        super();
    }


    /**
     * This method is used to store the properties our button will need in order to change the cart of the customer.
     * @param product Is the product we will need to store.
     * @param customerCart is the whole cart of the customer that contains all the products
     * @param quantityOfProducts is the quantity of each product in the cart
     * @param shoppingQuantity is the label of the quantity.
     */
    public void addAllProperties(Products product, ProductsList customerCart, ArrayList<String> quantityOfProducts,JLabel shoppingQuantity,String type,JLabel priceLabel,JPanel panel)
    {
        this.putClientProperty("product",product);
        this.putClientProperty("cart",customerCart);
        this.putClientProperty("quantity",quantityOfProducts);
        this.putClientProperty("quantityLabel",shoppingQuantity);
        this.putClientProperty("type",type);
        this.putClientProperty("priceLabel",priceLabel);
        this.putClientProperty("panel",panel);
    }


    /**
     * This method is a getter for a Product that is stored in the button.
     * @return Products.This is the variable that stores the info of the product.
     */
    public Products getProduct(){return (Products) this.getClientProperty("product");}


    /**
     * This method is used to return the type of the button.
     * @return type.Type is the variable that holds the info about the type of command we want to do.
     */
    public String getType(){return (String)this.getClientProperty("type");}


    /**
     * This method a getter for a ProductList that is stored in the button.
     * @return a list that contain all the products we need.
     */
    public ProductsList getProductList(){return  (ProductsList) this.getClientProperty("cart");}


    /**
     * This method is used to return the label that contains the quantity.
     * @return the String that contains the quantity of the product we need.
     */
    public JLabel getQuantityLabel(){return  (JLabel)this.getClientProperty("quantityLabel");}


    /**
     * This is a getter for a list that is stored in the button.
     * @return a ArrayList that contains the quantity for all the product we need.
     */
    public ArrayList<String> getQuantityList(){return (ArrayList<String>)this.getClientProperty("quantity"); }


    /**
     * This method is a getter for a Jlabel
     * @return a Jlabel that contains the price of a Product we need.
     */
    public JLabel getPriceLabel(){return (JLabel) this.getClientProperty("priceLabel");}


    /**
     * This is a method to get the panel that is stored in the button.
     * @return a JPanel that contains info about the product we want.
     */
    public JPanel getPanel(){return (JPanel) this.getClientProperty("panel");}


    /**
     * This method is used to add a photo in the button.
     * @return a BetterButton that contains a photo.
     */
    public BetterButton addImage()
    {
        ImageIcon originalIcon = new ImageIcon("src/images/trash-can.jpg"); // Path to your image
        Image scaledImage = originalIcon.getImage().getScaledInstance(30, 28, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        this.setIcon(scaledIcon); // Set the image as the icon for the button

        // Ensure the icon is centered
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);

        return this; // Return the current BetterButton instance
    }


}