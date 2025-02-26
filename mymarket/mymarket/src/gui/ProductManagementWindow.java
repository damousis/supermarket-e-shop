package gui;

import api.OrdersFile;
import api.ProductsList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProductManagementWindow implements ActionListener
{
    protected int width = 500;
    protected int height = 750;
    protected ArrayList<JTextField> textFields=new ArrayList<>();
    protected ProductsList listOfProducts;
    protected OrdersFile ordersFile;
    protected JButton confirmButton;
    protected JFrame mainWindow;

    protected JFrame newWindow;

    protected ArrayList<String> fields = new ArrayList<>();

    public ProductManagementWindow(ProductsList listOfProducts, OrdersFile ordersFile,JFrame mainWindow)
    {
        this.listOfProducts=listOfProducts;
        this.ordersFile=ordersFile;
        this.mainWindow=mainWindow;

        newWindow = new JFrame();
        newWindow.setResizable(false);
        newWindow.setSize(width,height);
        newWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        newWindow.getContentPane().setBackground(new Color(241, 245, 250));
        newWindow.setLayout(null);
        newWindow.setLocationRelativeTo(null);
        newWindow.setVisible(true);
        setTexts();
    }

    public void setTexts()
    {
        fields.add("Title");
        fields.add("Description");
        fields.add("Category");
        fields.add("Subcategory");
        fields.add("Price");
        fields.add("Quantity");
        for(int i=0;i<fields.size();i++)
        {
            JLabel newField = new JLabel();
            newField.setText(fields.get(i));
            newField.setFont(new Font("Arial", Font.BOLD, 20));
            newField.setBounds(20, i * 100 + 45, 180, 30);
            newWindow.add(newField);

        }
        setConfirmButton();
    }


    public void setConfirmButton()
    {
        confirmButton = new JButton();
        confirmButton.addActionListener(this);
        confirmButton.setText("CONFIRM");
        confirmButton.setFont(new Font("Arial",Font.BOLD,17));
        confirmButton.setBounds(300,650,150,30);
        //confirmButton.setBackground(new Color(10,243,10));
        newWindow.add(confirmButton);
    }

    public void actionPerformed(ActionEvent e)
    {

    }
}