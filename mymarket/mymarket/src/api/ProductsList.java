package api;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Alexander Damousis, Vasiliki Zerdali
 * This class represent a List that contains 'Products' objects.
 * It has method in order to add products in the list,get the size of the list,to get a product based on an index,check if a product is already placed in the list
 * and to get the index of a Product.
 */

public class ProductsList
{

    final private ArrayList<Products> listOfProducts;

    /**
     * This is a constructor in order to initialize the list when an object is created.
     */
    public ProductsList()
    {
        listOfProducts = new ArrayList<>();
    }


    /**
     *This is a method used to read ALL the products from a txt file and place them in the list.
     * Doing this we can have access to the info of all the products.
     */
    public void readProductsFile() throws FileNotFoundException
    {

        Scanner filereader = new Scanner(new File("src/Files/Products.txt"));
        int i=1;
        ArrayList<String> productInfo = new ArrayList<>();
        while ((filereader.hasNextLine()))
        {
            String data = filereader.nextLine();
            if(!data.isEmpty())
            {
                productInfo.add(data.split(": ")[1]);
                i++;
                if (i == 7) {
                    Products product = new Products(productInfo.get(0), productInfo.get(1), productInfo.get(2), productInfo.get(3), productInfo.get(4), productInfo.get(5));
                    listOfProducts.add(product);
                    productInfo.clear(); // Clear list for the next product
                    i = 1;
                }
            }
        }
        filereader.close();
    }


    /**
     * This method is used, so we can add a Products object in the list.
     * @param product is a Products object that should be placed in the list.
     */
    public void addToList(Products product)
    {
        listOfProducts.add(product);
    }


    /**
     * This method is used to get the size of the list.
     * @return an integer that is the size of the list.
     */
    public int getSize()
    {
        return listOfProducts.size();
    }


    /**
     * This method is used to get a Product from the list based on an index.
     * @param i is the index that represent the position of the product we want.
     * @return a single Product.
     */
    public Products getProduct(int i)
    {
        return listOfProducts.get(i);
    }


    /**
     * This method is used to check if a product is already placed on the list based on the name of the product.
     * @param name is the name of the product that we are looking for.
     * @return true if the product already exists in the list.Else it returns false.
     */
    public boolean alreadyExists(String name)
    {
        for (Products listOfProduct : listOfProducts)
        {
            if (listOfProduct.getTitle().equals(name))
            {
                return true;
            }
        }
        return false;
    }


    /**
     * This method is used to return the index of a product in the list based on its name.
     * @param name is the name of the product that we are looking for.
     * @return the index of the product that we are looking for.If the product is not found it returns -1.
     */
    public int getIndex(String name)
    {
        for(int i=0;i<listOfProducts.size();i++)
        {
            if(listOfProducts.get(i).getTitle().equals(name))
                return i;
        }
        return -1;
    }

    /**
     * This method calculates the final cost of each product,based on the quantity and returns the sum of them.
     * @param quantityOfProducts a list of quantities for each product in the cart.
     * @return the final cost of all the products as String.
     */
    public String getFinalCost(ArrayList<String> quantityOfProducts)
    {
        double sum = 0;
        int i = 0;
        for (Products product : listOfProducts)
        {
            String price = product.getPrice();
            CleanString cleanPrice = new CleanString(price);
            sum += Double.parseDouble(cleanPrice.convertString()) * Double.parseDouble(quantityOfProducts.get(i));
            i++;
        }

        String semiFinalPrice = Double.toString(sum);
        CleanString finalPrice = new CleanString(semiFinalPrice);
        return finalPrice.convertString();
    }

    /**
     * This method checks if a String contains the word kg.
     * @param quantityLabel a String that represents the quantity of a product
     * @return it returns true if the String contains kg ,otherwise false
     */
    public boolean contains(String quantityLabel)
    {
        if(quantityLabel.contains("kg"))
            return true;

        return false;
    }

    /**
     * This method is used to remove a product of the list based on its index
     * @param index is an integer that shows the location of the product in the list
     */
    public void remove(int index)
    {
        listOfProducts.remove(index);
    }

    /**
     * This method checks if the requested quantity is available.
     * @param index is the pointer of the product in the list
     * @param quantity is the requested quantity for the product
     * @return true if the requested quantity is available ,otherwise false.
     */
    public boolean check(int index,double quantity)
    {
        String quantityLeft = listOfProducts.get(index).getQuantity();
        String finalQuantity;
        if(quantityLeft.contains("kg"))
        {
            finalQuantity = quantityLeft.replace("kg","").trim();
        }
        else
        {
            finalQuantity = quantityLeft.replace("τεμάχια","").trim();
        }

        if(quantity>Double.parseDouble(finalQuantity))
        {
            return false;
        }
        return true;
    }

    /**
     * This method creates a list that contains the measure for the products in the customerCart list (kg or τεμάχια)
     * @param customerCart a list that contains the products of the shopping cart
     * @return the list of the measures we created
     */
    public ArrayList<String> findMeasures(ProductsList customerCart)
    {
        ArrayList<String> measure=new ArrayList<>();
        for(int i=0;i<customerCart.getSize();i++)
        {
            for(int j=0;j< listOfProducts.size();j++)
            {
                if(listOfProducts.get(j).getTitle().contains(customerCart.getProduct(i).getTitle()))
                {
                    if(listOfProducts.get(j).getQuantity().contains("kg"))
                    {
                        measure.add("kg");
                    }
                    else
                    {
                        measure.add("τεμάχια");
                    }
                    break;
                }
            }
        }
        return measure;
    }

    /**
     *This method updates the list of products.
     * It removes the quantity that was bought by the customers and rewrites the txt file with the updated list.
     * @param customerCart the list that contains the products that are placed in the shopping cart.
     * @param quantityOfProducts a list of quantities for each product in the cart.
     */
    public void updateListOfProducts(ProductsList customerCart,ArrayList<String> quantityOfProducts)
    {

        ArrayList<String> measures = new ArrayList<>();
        for(Products product:listOfProducts)
        {
            if(product.getQuantity().contains("kg"))
            {
                measures.add("kg");
            }
            else
            {
                measures.add("τεμάχια");
            }
        }

        try
        {
            FileWriter writer=new FileWriter("src/Files/Products.txt",false);
            writer.close();
            for(int i=0;i<customerCart.getSize();i++)
            {
                Products product = customerCart.getProduct(i);

                for(Products searchingProduct: listOfProducts)
                {
                    if(searchingProduct.getTitle().equals(product.getTitle()))
                    {
                        String firstQuantity=searchingProduct.getQuantity();
                        String quantityToRemove=quantityOfProducts.get(i);

                        String first;
                        String helper = "";

                        if(firstQuantity.contains("kg"))
                        {
                            first=firstQuantity.replace("kg","").trim();
                            helper = "kg";
                        }
                        else
                        {
                            first=firstQuantity.replace("τεμάχια","").trim();
                            helper = "τεμάχια";
                        }

                        double finalQuantity = Double.parseDouble(first) - Double.parseDouble(quantityToRemove);
                        String semiFinalQuan = Double.toString(finalQuantity);
                        CleanString finalQuan = new CleanString(semiFinalQuan);
                        if(helper.equals("kg"))
                            searchingProduct.setQuantity(finalQuan.convertString()+helper);
                        else
                        {
                            String quan = finalQuan.convertString().replace(".0","").trim();
                            searchingProduct.setQuantity(quan + " " + helper);
                        }
                    }
                }
            }


            for(Products product :listOfProducts)
            {
                addToFile(product);
            }


        }
        catch (IOException e)
        {
            System.out.println("problem");
        }
    }

    /**
     * This method adds a given product in the txt file with all the other products.
     * @param product the product we want to add
     */
    public void addToFile(Products product)
    {
        try
        {
            FileWriter writer=new FileWriter("src/Files/Products.txt",true);//isws thelei new file
            writer.write(System.lineSeparator());
            writer.write(System.lineSeparator());
            writer.write("Τίτλος: "+product.getTitle());
            writer.write(System.lineSeparator());
            writer.write("Περιγραφή: "+product.getDescription());
            writer.write(System.lineSeparator());
            writer.write("Κατηγορία: "+product.getCategory());
            writer.write(System.lineSeparator());
            writer.write("Υποκατηγορία: "+product.getSubcategory());
            writer.write(System.lineSeparator());
            writer.write("Τιμή: "+product.getPrice());
            writer.write(System.lineSeparator());
            writer.write("Ποσότητα: "+product.getQuantity());
            writer.write(System.lineSeparator());
            writer.write(System.lineSeparator());
            writer.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Τhis method changes the information of a product based on the given list
     * @param index a pointer that shows where the product we want to change the info is in the list
     * @param productsNewInfo a list that contains new information for a product.
     */
    public void changeInfo(int index,ArrayList<String> productsNewInfo)
    {

        ArrayList<String> info  =new ArrayList<>();
        info.add(productsNewInfo.get(0));
        for(int i=1;i<productsNewInfo.size();i++)
        {
            String[] in = productsNewInfo.get(i).split(": ");
            info.add(in[1]);
        }

        listOfProducts.get(index).setTitle(info.get(0));
        listOfProducts.get(index).setDescription(info.get(1));
        listOfProducts.get(index).setCategory(info.get(2));
        listOfProducts.get(index).setSubcategory(info.get(3));
        listOfProducts.get(index).setPrice(info.get(4));
        listOfProducts.get(index).setQuantity(info.get(5));
    }

    /**
     *This method rewrites the txt file with the products using a previous method called addToFile.
     * @throws IOException if a problem occurs with the txt file
     */
    public void updateFile() throws IOException
    {
        try
        {
            FileWriter writer=new FileWriter("src/Files/Products.txt",false);
            writer.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("problem10");
        }

        for(Products products:listOfProducts)
        {

            addToFile(products);
        }

    }

    /**
     *This method updates the field of the quantity in the Jpanels based on the given list
     * @param panels a list of Jpanels that represents products
     * @param helperCart a list of products that helps in finding the products we want
     */
    public void updateCustomerPanels(ArrayList<JPanel> panels, ProductsList helperCart)
    {
        for(int i=0;i<panels.size();i++)
        {
            Component[] comp = panels.get(i).getComponents();
            JLabel titleLabel =(JLabel) comp[0];
            JLabel quan=  (JLabel)comp[5];

            try {
                if (titleLabel != null && quan != null) {
                    for (int j = 0; j < helperCart.getSize(); j++) {
                        Products product = helperCart.getProduct(j);
                        if (product.getTitle().equals(titleLabel.getText())) {
                            int index = this.getIndex(product.getTitle());
                            quan.setText("Quantity: " + listOfProducts.get(index).getQuantity());
                            break;
                        }

                    }
                }
            }
            catch (Exception e)
            {
                System.out.println("PROBLEM");
            }
        }
    }


}