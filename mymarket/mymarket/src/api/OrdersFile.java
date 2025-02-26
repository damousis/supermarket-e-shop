package api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Alexander Damousis,Vasiliki Zerdali
 * This class handles order-related operations, including adding orders to a file,
 * retrieving user-specific order history, and tracking product sales.
 */
public class OrdersFile
{

    final private File ordersFile;
    String fileName;

    /**
     * Constructor of the class.
     * It creates the txt file if it not exists
     * @throws IOException if an I/O error occurs while creating the file.
     */
    public OrdersFile(String name) throws IOException
    {
        fileName = name;
        ordersFile = new File(name);
        if(!ordersFile.exists())
        {
            ordersFile.createNewFile();
        }
    }

    /**
     * Adds a new customer to the txt file.
     * @param username is the username of the new customer.
     */
    public void addNewCustomer(String username)
    {
        try
        {
            FileWriter writer=new FileWriter(fileName,true);
            writer.write(username);
            writer.write(System.lineSeparator());
            writer.write(System.lineSeparator());
            writer.close();


        }
        catch (IOException e)
        {
            System.out.println("problem");
        }
    }
    /**
     * Records a new purchase made by a user and updates the txt file.
     * @param username is the username of the customer.
     * @param customerCart the list of products in the customer's cart.
     * @param quantityOfProduct a list of quantities for each product in the cart.
     * @param listOfProducts the complete list of products.
     */
    public void addNewPurchase(String username, ProductsList customerCart, ArrayList<String> quantityOfProduct,ProductsList listOfProducts)
    {
        ArrayList<String> measures = listOfProducts.findMeasures(customerCart);
        StringBuilder newOrder = new StringBuilder();
        LocalDateTime dayTime = LocalDateTime.now();
        DateTimeFormatter dayTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        newOrder.append("N E W  O R D E R \n\nORDER  DATE:  ").append(dayTime.format(dayTimeFormat)).append("  /  SUMMARY COST: "+customerCart.getFinalCost(quantityOfProduct)).append(" Euros").append(System.lineSeparator());//.append(System.lineSeparator());



        for (int i = 0; i < customerCart.getSize(); i++)
        {
            Products product = customerCart.getProduct(i);
            String quantity = quantityOfProduct.get(i);
            CleanString newPrice = new CleanString(product.getPrice());
            newOrder.append(product.getTitle()).append(" - "+quantity+" "+measures.get(i)+" - ").append(newPrice.showFinalPrice(quantity)+"€").append(System.lineSeparator());
        }
        newOrder.append("-----------------------------------------------------------------------------------------------------------------");
        newOrder.append(System.lineSeparator());

        try
        {

            ArrayList<String> fileContent = new ArrayList<>();
            Scanner fileReader = new Scanner(ordersFile);
            boolean userFound = false;

            while (fileReader.hasNextLine())
            {
                String line = fileReader.nextLine();
                fileContent.add(line);

                if (line.equals(username))
                {
                    userFound = true;
                    fileContent.add(newOrder.toString());
                }
            }
            fileReader.close();


            if (!userFound)
            {
                fileContent.add(username);
                fileContent.add("");
                fileContent.add(newOrder.toString());
            }

            FileWriter writer = new FileWriter(ordersFile, false); // Overwrite the file
            for (String line : fileContent) {
                writer.write(line + System.lineSeparator());
            }
            writer.close();


        }
        catch (IOException e)
        {
            System.out.println("problem5");
        }


    }

    /**
     * Founds the order history for a specific user.
     * @param username is the username of the customer whose orders we want.
     * @return a string containing the order history of the given user.
     * @throws IOException if an error occurs while reading the file.
     */
    public String getOrders(String username) throws IOException
    {
        ArrayList<String> userNames = getUserNames();
        boolean found = false;
        StringBuilder orders = new StringBuilder();
        try
        {
            Scanner fileReader = new Scanner(ordersFile);
            while(fileReader.hasNextLine())
            {
                String line = fileReader.nextLine();
                if(line.equals(username))
                {
                    found = true;
                }
                if(userNames.contains(line) && !line.equals(username) && found)
                {
                    return orders.toString();
                }
                if(found)
                {
                    orders.append(line+"\n");
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("problemo");
        }
        return orders.toString();
    }

    /**
     * Creates a list of the usernames from the txt file.
     * @return the list of the usernames we made.
     * @throws IOException if an error occurs while reading the file.
     */
    public ArrayList<String> getUserNames() throws IOException
    {
        String[] line;
        ArrayList<String> helper = new ArrayList<>();
        Scanner fileReader = new Scanner(new File("src/Files/CustomerInfo.txt"));
        while(fileReader.hasNextLine())
        {
            line = fileReader.nextLine().split(" ");
            helper.add(line[2]);
        }

        return helper;
    }

    /**
     * Calculates the number of times each product in the list has been sold in order to find the best sellers.
     * @param listOfProducts the list of products for which sales data is calculated.
     * @return a list of integers where each index represent the number of sales of a product.
     */
    public ArrayList<Integer> getSales(ProductsList listOfProducts)
    {
        ArrayList<Integer> sales  = new ArrayList<>();
        for (int i=0;i<listOfProducts.getSize();i++)
            sales.add(0);

        try
        {
            Scanner fileReader = new Scanner(ordersFile);
            while(fileReader.hasNextLine())
            {
                String line = fileReader.nextLine();
                if(line.contains("€"))
                {

                    String[] info = line.split("-");
                    int index =listOfProducts.getIndex(info[0].trim());
                    sales.set(index,sales.get(index)+1);
                }
            }
            return sales;
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }
}