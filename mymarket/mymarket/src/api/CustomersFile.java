package api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Alexander Damousis, Vasiliki Zerdali.
 * This class is used to create the file that will store all the customers of the shop.
 * If the user is registered then the data will be stored in the file.
 */
public class CustomersFile
{
    static final File customerFileInfo = new File("src/Files/CustomerInfo.txt");

    /**
     * This is a constructor that creates the file only if the files do not already exists.If the file exists then the
     constructor will not to anything.
     */
    public CustomersFile()
    {
        if(!customerFileInfo.exists())
        {
            try
            {
                customerFileInfo.createNewFile();
            }
            catch (IOException ex)
            {
                throw new RuntimeException(ex);
            }
        }
    }


    /**
     * This method is used to write all the customers information in the file.It also checks if something goes wrong, and then
     * it throws a IOException.
     * @param customer is the object that contains all the information about the customer that is saved in the file.
     */
    public void addInFile(Customer customer)
    {
        try
        {
            FileWriter myWriter = new FileWriter("src/Files/CustomerInfo.txt", true);
            myWriter.write(customer.getCustomerFirstName());
            myWriter.write(" ");
            myWriter.write(customer.getCustomerLastName());
            myWriter.write(" ");
            myWriter.write(customer.getCustomerUserName());
            myWriter.write(" ");
            myWriter.write(customer.getCustomerPassword());
            myWriter.write(System.lineSeparator()); // Adds a newline after each customer's details
            myWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("problem");
        }
    }

    /**
     * This method checks if the info that a user gives us for signing in is correct and exist in the file that holds
     * the customer's info.
     * @param username is a String that contains the username of the customer.
     * @param password is a String that contains the password of the customer.
     * @return true if the customer is on the customer file and the data that
     */
    public boolean checkCustomer(String username,String password)
    {
        try
        {
            Scanner fileReader = new Scanner(customerFileInfo);
            while(fileReader.hasNextLine())
            {
                String[] customerInfo = fileReader.nextLine().split(" ");
                if(customerInfo[2].equals(username) && customerInfo[3].equals(password))
                {
                    return true;
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("problem");
        }
        return false;
    }

    /**
     * This method Checks if a customer is already register or not based on the txt file.
     * @param c the customer we want to check
     * @return true is the customer is registered ,otherwise false.
     */
    public boolean alreadyRegistered(Customer c)
    {
        try
        {
            Scanner fileReader = new Scanner(customerFileInfo);
            if (customerFileInfo.length()==0)
                return false;

            while(fileReader.hasNextLine())
            {
                String[] customerUserName = fileReader.nextLine().split(" ");
                if (customerUserName[2].equals(c.getCustomerUserName()))
                    return true;
            }
            return false;
        }
        catch (FileNotFoundException e)
        {
            System.out.println("problem.File not found!");
        }

        return false;
    }

    /**
     * This method is used to check if the file that contains the customers info is empty.
     * @return a boolean value. If it is empty it returns true else false.
     */
    public boolean isEmpty()
    {
        if(customerFileInfo.length()==0)
            return true;

        return false;
    }

}
