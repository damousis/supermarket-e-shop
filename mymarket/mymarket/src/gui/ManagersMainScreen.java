package gui;

import api.CleanString;
import api.OrdersFile;
import api.Products;
import api.ProductsList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;



public class ManagersMainScreen extends MainWindow
{

    final private JButton addNewProduct = new JButton();
    //JButton editButton;
    private OrdersFile ordersFile;
    final private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public ManagersMainScreen(ProductsList listOfProducts,OrdersFile ordersFile)
    {
        super(listOfProducts);
        this.ordersFile = ordersFile;
        showAvailability();
        showBestSellers();
        addButtons();
    }

    public void showAvailability()
    {
        for(int i=0;i<listOfProducts.getSize();i++)
        {
            CleanString cleanQuan= new CleanString(listOfProducts.getProduct(i).getQuantity());
            String quan=cleanQuan.removeMeasure();
            if(Double.parseDouble(quan)==0)
            {
                JLabel outOfStock=new JLabel();
                outOfStock.setText("OUT OF STOCK");
                outOfStock.setForeground(new Color(210,53,53));
                outOfStock.setFont(new Font("Arial",Font.BOLD,16));
                outOfStock.setBounds(13,315,140,30);
                panelOfProduct.get(i).add(outOfStock);
            }
        }
    }

    public void showBestSellers()
    {
        ArrayList<Integer> sales = new ArrayList<>();
        sales = ordersFile.getSales(listOfProducts);
        int max =0;

        //if(!sales.isEmpty())
        boolean counter = false;
        for(int j=0;j<sales.size();j++)
        {
            if(sales.get(j)!=0)
            {
                counter = true;
                break;
            }
        }
        if(counter)
        {
            for (int i = 0; i < sales.size(); i++)
            {
                if (sales.get(i) > max)
                    max = sales.get(i);
            }

            for (int i = 0; i < listOfProducts.getSize(); i++) {
                if (sales.get(i) == max) {
                    JLabel topSales = new JLabel();
                    topSales.setText("TOP IN SALES!");
                    topSales.setFont(new Font("Arial", Font.BOLD, 16));
                    topSales.setBounds(245, 290, 140, 30);
                    topSales.setForeground(new Color(102, 178, 255));
                    panelOfProduct.get(i).add(topSales);
                }
            }
        }
    }

    public void addButtons()
    {
        addNewProduct.setText("Add New Product");
        addNewProduct.setFont(new Font("Arial",Font.BOLD,14));
        addNewProduct.setBounds((int)screenSize.getWidth()-220,23,170,40);
        addNewProduct.addActionListener(this);
        productsPanel.add(addNewProduct);

        for(JPanel panel: panelOfProduct)
        {
            JButton editButton=new JButton();
            editButton.addActionListener(this);
            editButton.putClientProperty("productsInfo",panel.getClientProperty("product"));
            editButton.putClientProperty("panel",panel);
            editButton.setBounds(250,325,120,20); //260!!!!
            editButton.setText("Edit Product");
            panel.add(editButton);
        }
        mainWindow.revalidate();
        mainWindow.repaint();
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==addNewProduct)
        {
            NewProductWindow addProductWindow = new NewProductWindow(listOfProducts,ordersFile,mainWindow);
        }
        else if(e.getSource() instanceof JButton )
        {
            JButton source = (JButton) e.getSource();
            if(source.getText().equals("Edit Product"))
            {
                Products product = (Products) source.getClientProperty("productsInfo");
                JPanel panel = (JPanel) source.getClientProperty("panel");
                EditProductWindow editProductWindow = new EditProductWindow(listOfProducts, ordersFile, mainWindow, product, panel);
            }
            else
                super.actionPerformed(e);
        }
        else
        {
            super.actionPerformed(e);
        }
    }



}
