package gui;

import api.CategoriesMap;
import api.OrdersFile;
import api.Products;
import api.ProductsList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class NewProductWindow extends ProductManagementWindow implements ActionListener
{

    JComboBox<String> categoryDropdown;
    JComboBox<String> subcategoryDropdown;
    HashMap<String, String[]> categoriesInfo;
    private int width = 500;
    private int height = 750;
    JLabel measure = new JLabel();
    private boolean updatingDropdown = false;

    public NewProductWindow(ProductsList listOfProducts, OrdersFile ordersFile, JFrame mainWindow) {
        super(listOfProducts, ordersFile, mainWindow);
        newWindow.setTitle("ADD A PRODUCT");
        setLabelsAndTextFields();
    }

    public void setLabelsAndTextFields()
    {
        JLabel priceLabel = new JLabel("€");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        newWindow.add(priceLabel);
        priceLabel.setBounds(330, 470, 50, 50);

        CategoriesMap categoriesAndSub = new CategoriesMap();
        categoriesInfo = categoriesAndSub.getCategories();
        measure.setBounds(320, 545, 100, 100);
        measure.setFont(new Font("Arial", Font.BOLD, 16));
        measure.setVisible(false);
        newWindow.add(measure);

        for (int i = 0; i < fields.size(); i++) {
            JTextField addTextField = new JTextField();
            addTextField.setBounds(20, i * 100 + 80, 300, 30);
            addTextField.setFont(new Font("Arial", Font.ITALIC, 19));
            addTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            textFields.add(addTextField);


            if (!fields.get(i).equals("Category") && !fields.get(i).equals("Subcategory"))
            {
                newWindow.add(addTextField);
            }
            else
            {
                if (fields.get(i).equals("Category"))
                {
                    categoryDropdown = new JComboBox<>();
                    categoryDropdown.addItem("Select Category");
                    for (String category : categoriesInfo.keySet()) {
                        categoryDropdown.addItem(category);
                    }
                    categoryDropdown.setBounds(20, 280, 280, 40);
                    categoryDropdown.addActionListener(this);
                    newWindow.add(categoryDropdown);
                }
                else
                {
                    subcategoryDropdown = new JComboBox<>();
                    subcategoryDropdown.setBounds(20, 380, 280, 40);
                    subcategoryDropdown.addActionListener(this);
                    subcategoryDropdown.addItem("Select Subcategory");
                    newWindow.add(subcategoryDropdown);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == categoryDropdown)
        {
            if (updatingDropdown) return;

            updatingDropdown = true;
            String category = (String) categoryDropdown.getSelectedItem();
            subcategoryDropdown.removeAllItems();
            subcategoryDropdown.addItem("Select Subcategory");

            if (category != null && !category.equals("Select Category")) {
                String[] subCategories = categoriesInfo.get(category);
                for (String sub : subCategories) {
                    subcategoryDropdown.addItem(sub);
                }
            }

            updatingDropdown = false;

        }
        else if (e.getSource() == subcategoryDropdown)
        {
            String subCat = (String) subcategoryDropdown.getSelectedItem();

            if (subCat != null && !subCat.equals("Select Subcategory"))
            {
                measure.setVisible(true);
                if (subCat.equals("Λαχανικά") || subCat.equals("Φρούτα")) {
                    measure.setText("kg");
                } else {
                    measure.setText("τεμάχια");
                }
            } else {
                measure.setVisible(false);
            }
        }
        else
        {
            String title = textFields.get(0).getText();
            String description = textFields.get(1).getText();
            String category = (String) categoryDropdown.getSelectedItem();
            String subCategory = (String) subcategoryDropdown.getSelectedItem();
            String price = textFields.get(4).getText();
            String quantity = textFields.get(5).getText();
            quantity = quantity.replace(",",".").trim();

            if (title.isEmpty() || description.isEmpty() || category == null || subCategory == null || price.isEmpty() || quantity.isEmpty() ||
                    category.equals("Select Category") || subCategory.equals("Select Subcategory")) {
                JOptionPane.showMessageDialog(null, "Some fields are empty. Please fill them all!", "Empty Fields", JOptionPane.PLAIN_MESSAGE);
                return;
            }

            try
            {
                if(measure.getText().contains("τεμάχια") && quantity.contains("."))
                    JOptionPane.showMessageDialog(null,"You have to give an integer value for the quantity","Error",JOptionPane.ERROR_MESSAGE);
                else
                {
                    price = price.replace(",", ".").trim();
                    double money = Double.parseDouble(price);
                    double quan = Double.parseDouble(quantity);
                    if (money <= 0)
                        JOptionPane.showMessageDialog(null, "The price must be a positive number!", "Error", JOptionPane.ERROR_MESSAGE);
                    else
                    {
                        String measure;
                        if (subCategory.equals("Φρούτα") || subCategory.equals("Λαχανικά")) {
                            measure = "kg";
                        } else {
                            measure = " τεμάχια";
                        }

                        price = price.replace(".", ",").trim();
                        Products newProduct = new Products(title, description, category, subCategory, price + "€", quantity + measure);
                        listOfProducts.addToList(newProduct);
                        listOfProducts.addToFile(newProduct);

                        newWindow.dispose();
                        ManagersMainScreen newScreen = new ManagersMainScreen(listOfProducts, ordersFile);
                        mainWindow.dispose();
                    }
                }
            }
            catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Price and Quantity must be numbers!", "ERROR", JOptionPane.PLAIN_MESSAGE);
            }
        }

    }
}
