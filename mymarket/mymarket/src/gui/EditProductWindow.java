package gui;

import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.jar.Manifest;

public class EditProductWindow extends ProductManagementWindow
{
    private JFrame editWindow=new JFrame();
    private JPanel panel;
    private Products product;
    private JFrame mainWindow;
    private ArrayList<JTextField> textFields = new ArrayList<>();
    JComboBox<String> categoryDropdown;
    HashMap<String, String[]> categoriesInfo;
    JComboBox<String> subcategoryDropdown;

    String measureOfProduct ="";

    final private int width=500;
    final private int height=650;
    public EditProductWindow(ProductsList listOfProducts, OrdersFile ordersFile, JFrame mainWindow, Products product,JPanel panel)
    {
        super(listOfProducts,ordersFile,mainWindow);
        newWindow.setTitle("EDIT PRODUCT");
        this.panel = panel;
        this.mainWindow=mainWindow;
        this.product = product;
        setTextFields(product);
    }

    public void setTextFields(Products product)
    {
        CategoriesMap categoriesAndSub = new CategoriesMap();
        categoriesInfo = categoriesAndSub.getCategories();

        JTextField titleField = new JTextField(product.getTitle());
        JTextField descField = new JTextField(product.getDescription());
        JTextField priceField = new JTextField(product.getPrice());
        JTextField quanField = new JTextField(product.getQuantity());
        JTextField subField = new JTextField(product.getSubcategory());

        subField.setEditable(true);
        JTextField catField = new JTextField(product.getCategory());
        catField.setEditable(true);

        textFields.add(titleField);
        textFields.add(descField);
        textFields.add(catField);
        textFields.add(subField);
        textFields.add(priceField);
        textFields.add(quanField);

        String measure = "";
        if(quanField.getText().contains("kg"))
            measureOfProduct = "kg";
        else
            measureOfProduct =" τεμάχια";

        for (int i = 0; i < textFields.size(); i++)
        {
            if (i != 2 && i != 3)
            {
                JTextField currentField = textFields.get(i);
                currentField.setBounds(20, i * 100 + 80, 300, 30);
                currentField.setFont(new Font("Arial", Font.ITALIC, 19));
                currentField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                newWindow.add(currentField);
            }

            else if (i == 2)
            {
                categoryDropdown = new JComboBox<>();
                categoryDropdown.setBounds(20, 280, 280, 40);
                categoryDropdown.addActionListener(this);
                if (measureOfProduct.contains("kg"))
                {
                    for (String category : categoriesInfo.keySet())
                    {
                        if (category.contains("Φρέσκα τρόφιμα"))
                        {
                            categoryDropdown.addItem(category);
                            break;
                        }
                    }
                }

                else
                {
                    for (String category : categoriesInfo.keySet())
                    {
                        categoryDropdown.addItem(category);
                    }
                }
                categoryDropdown.setSelectedItem(textFields.get(i).getText() + " ");
                newWindow.add(categoryDropdown);
            }

            else
            {
                subcategoryDropdown = new JComboBox<>();
                subcategoryDropdown.setBounds(20, 380, 280, 40);
                subcategoryDropdown.addActionListener(this);

                subcategoryDropdown.removeAllItems();
                String[] subcategories = categoriesAndSub.getValue(textFields.get(i - 1).getText().trim() + " ");

                if (measureOfProduct.equals("kg"))
                {
                    for (String val : subcategories) {
                        if (val.contains("Φρούτα") || val.contains("Λαχανικά")) {
                            subcategoryDropdown.addItem(val + " ");
                        }
                    }
                } else {
                    for (String val : subcategories) {
                        if (!val.contains("Φρούτα") && !val.contains("Λαχανικά")) {
                            subcategoryDropdown.addItem(val + " ");
                        }
                    }
                }

                subcategoryDropdown.setSelectedItem(product.getSubcategory().trim() + " ");
                newWindow.add(subcategoryDropdown);
            }
        }
    }

    public void actionPerformed(ActionEvent e)
    {

        if (e.getSource() == categoryDropdown)
        {
            String category = categoryDropdown.getSelectedItem().toString();

            if (subcategoryDropdown != null)
            {
                subcategoryDropdown.removeAllItems();
                String[] sub = categoriesInfo.get(category);

                if(measureOfProduct.equals("kg"))
                {
                    for (String val : sub)
                    {
                        if(val.contains("Φρούτα") || val.contains("Λαχανικά"))
                            subcategoryDropdown.addItem(val + " ");
                    }
                }

                else
                {
                    for (String val : sub)
                    {
                        if(val.equals("Φρούτα") || val.equals("Λαχανικά"))
                        {

                        }
                        else
                            subcategoryDropdown.addItem(val + " ");
                    }
                }
            }
        }



        boolean ok = true;
        for (JTextField field : textFields)
        {
            if (field.getText().equals(""))
            {
                ok = false;
                break;
            }
        }

        if(ok)
        {
            try
            {
                textFields.get(5).setText(textFields.get(5).getText().replace(",",".").trim());
                String firstQuan = textFields.get(5).getText();
                CleanString quantityCheck = new CleanString(textFields.get(5).getText());
                double numberQuan = Double.parseDouble(quantityCheck.removeMeasure());

                String priceCheck = new String(textFields.get(4).getText());
                double numberPrice = Double.parseDouble(priceCheck.replace("€","").replace(",",".").trim());
                if(firstQuan.contains("τεμάχια") && firstQuan.contains("."))
                    JOptionPane.showMessageDialog(null,"You must give a integer value for the quantity","Error",JOptionPane.ERROR_MESSAGE);
                else if(numberPrice<=0)
                {
                    JOptionPane.showMessageDialog(null,"You can not put a negative price!","Error",JOptionPane.ERROR_MESSAGE);
                }
                else {


                    int i = 0;
                    int index = listOfProducts.getIndex(product.getTitle());
                    ArrayList<String> productsNewInfo = new ArrayList<>();
                    ArrayList<JLabel> extra = new ArrayList<>();


                    if (e.getSource() == confirmButton) {
                        Component[] components = panel.getComponents();
                        int l = components.length;
                        for (Component component : components) {
                            String text = "";

                            if (component instanceof JLabel) {

                                if (i <= 5) {
                                    if (fields.get(i).equals("Title")) {
                                        text = "";
                                    } else
                                        text += fields.get(i) + ": ";

                                    JLabel label = (JLabel) component; //to change the values in the panel

                                    if (fields.get(i).equals("Category"))
                                        text += categoryDropdown.getSelectedItem().toString().trim();
                                    else if (fields.get(i).equals("Subcategory"))
                                        text += subcategoryDropdown.getSelectedItem().toString().trim();
                                    else
                                        text += textFields.get(i).getText();

                                    if (fields.get(i).equals("Quantity"))
                                    {
                                        if (productsNewInfo.get(3).contains("Subcategory: Φρούτα") || productsNewInfo.get(3).contains("Subcategory: Λαχανικά")) {
                                            if (!text.contains("kg"))
                                                text += "kg";

                                            if (text.split(" ").length == 3) {
                                                text = text.replace("kg", "").trim();
                                                text += "kg";
                                            }

                                        } else {
                                            if (!text.contains("τεμάχια"))
                                                text += " τεμάχια";

                                            if (text.split(" ").length == 2) {
                                                text = text.replace("τεμάχια", "").trim();
                                                text += " τεμάχια";
                                            }
                                        }

                                    } else if (fields.get(i).equals("Price")) {
                                        if (!text.contains("€"))
                                            text += "€";

                                        text = text.replace(".", ",");
                                    }

                                    label.setText(text);
                                    i += 1;
                                    productsNewInfo.add(text);

                                } else
                                    extra.add((JLabel) component);

                            } else if (component instanceof JTextArea) {
                                JTextArea area = (JTextArea) component;
                                text += "Description: " + textFields.get(i).getText();
                                area.setText(text);
                                productsNewInfo.add(text);
                                i += 1;
                            }
                        }
                        CleanString newQuan = new CleanString(productsNewInfo.get(5));
                        String quan = newQuan.removeMeasure();
                        String[] arr = quan.split(": ");
                        String finalQuan = arr[1];


                        for (JLabel label : extra) {
                            if (label.getText().equals("OUT OF STOCK")) {
                                if (Double.parseDouble(finalQuan) > 0) {
                                    label.setText("");
                                }
                                panel.revalidate();
                                panel.repaint();
                                mainWindow.repaint();
                                mainWindow.revalidate();
                            }
                        }
                        if ((extra.size() == 1 && !extra.get(0).getText().equals("OUT OF STOCK")) || extra.size() == 0) {

                            if (Double.parseDouble(finalQuan) == 0) {
                                JLabel outOfStock = new JLabel();
                                outOfStock.setText("OUT OF STOCK");
                                outOfStock.setForeground(new Color(210, 53, 53));
                                outOfStock.setFont(new Font("Arial", Font.BOLD, 16));
                                outOfStock.setBounds(13, 315, 140, 30);
                                panel.add(outOfStock);
                                panel.repaint();
                                panel.revalidate();
                                mainWindow.revalidate();
                                mainWindow.repaint();
                            }


                        }
                        listOfProducts.changeInfo(index, productsNewInfo);

                        newWindow.dispose();
                        JOptionPane.showMessageDialog(null, "Product's information has been updated!", "Successfully updated.", JOptionPane.PLAIN_MESSAGE);
                        try {
                            listOfProducts.updateFile();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
            catch (NumberFormatException k)
            {
                JOptionPane.showMessageDialog(null,"Price and Quantity must be numbers!!","ERROR",JOptionPane.ERROR_MESSAGE);

            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Some fields are empty!Please fill them all.","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }

}
