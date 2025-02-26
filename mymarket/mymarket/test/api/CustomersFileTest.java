package api;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class CustomersFileTest
{

    @Test
    public void addInFile() throws IOException
    {
        Customer cust = new Customer("peter","damos","pd","dm");
        CustomersFile file = new CustomersFile();


        ArrayList<Customer> c = new ArrayList<>();
        Scanner fileReader = new Scanner(new File("src/Files/CustomerInfo.txt"));

        while(fileReader.hasNextLine())
        {
            String line = fileReader.nextLine();
            String info[] = line.split(" ");
            Customer helperCust = new Customer(info[0],info[1],info[2],info[3]);
            c.add(helperCust);
        }
        fileReader.close();
        file.addInFile(cust);

        assertTrue(file.alreadyRegistered(cust));
        FileWriter write = new FileWriter(new File("src/Files/CustomerInfo.txt"));
        write.close();
        for(int i=0;i<c.size();i++)
        {
            file.addInFile(c.get(i));
        }

    }

    @Test
    public void checkCustomer()
    {
        CustomersFile file = new CustomersFile();
        boolean cust1,cust2;
        cust1 = file.checkCustomer("user1", "password1");
        assertTrue(cust1);

        cust2 = file.checkCustomer("vasw","zerdal");
        assertFalse(cust2);
    }

    @Test
    public void alreadyRegistered()
    {
        CustomersFile file = new CustomersFile();
        boolean cust1,cust2;
        cust1 = file.checkCustomer("user2","password2");
        assertTrue(cust1);

        cust2 = file.checkCustomer("p","d");
        assertFalse(cust2);
    }

    @Test
    public void isEmpty()
    {
        CustomersFile file = new CustomersFile();
        assertFalse(file.isEmpty());
    }
}
