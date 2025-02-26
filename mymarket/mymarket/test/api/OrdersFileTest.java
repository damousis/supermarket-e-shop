package api;

import com.sun.source.doctree.UnknownInlineTagTree;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class OrdersFileTest
{


    @Test
    public void addNewCustomer() throws IOException
    {
        OrdersFile file = new OrdersFile("src/Files/tester.txt");
        file.addNewCustomer("alex");
        Scanner reader = new Scanner(new File("src/Files/tester.txt"));
        boolean found = false;
        while(reader.hasNextLine())
        {
            String line = reader.nextLine();
            if(line.equals("alex"))
            {
                found = true;
            }
        }
        reader.close();
        assertTrue(found);

    }

    @Test
    public void addNewPurchase() throws IOException
    {
        OrdersFile file = new OrdersFile("src/Files/tester.txt");
        FileWriter writer = new FileWriter(new File("src/Files/tester.txt"));
        writer.close();
        Products pr1 = new Products("title1","description1","category1","subcategory1","15€","quantity1");
        ProductsList cart = new ProductsList();
        cart.addToList(pr1);
        ArrayList<String> quan = new ArrayList<>();
        quan.add("1");
        ProductsList list = new ProductsList();
        list.addToList((pr1));
        file.addNewPurchase("alex",cart,quan,list);


        Scanner reader = new Scanner(new File("src/Files/tester.txt"));
        boolean found = false;
        while (reader.hasNextLine())
        {
            String line = reader.nextLine();
            if(line.contains("title1") && line.contains("15.0€"))
                found = true;
        }
        reader.close();
        assertTrue(found);

    }

    @Test
    public void getOrders() throws IOException
    {
        OrdersFile file = new OrdersFile("src/Files/tester.txt");
        FileWriter writer = new FileWriter(new File("src/Files/tester.txt"));
        writer.close();

        Products pr1 = new Products("title1","description1","category1","subcategory1","15€","quantity1");
        Products pr2 = new Products("title2","description2","category2","subcategory2","20€","quantity2");

        ProductsList cart = new ProductsList();
        cart.addToList(pr1);
        cart.addToList(pr2);

        ArrayList<String> quan = new ArrayList<>();
        quan.add("1");
        quan.add("2");

        ProductsList list = new ProductsList();
        list.addToList((pr1));
        list.addToList(pr2);

        file.addNewPurchase("alex",cart,quan,list);
        file.addNewPurchase("vasw",cart,quan,list);

        String orders = file.getOrders("alex");
        boolean found1 = false;
        boolean found2 = false;
        Scanner reader = new Scanner(new File("src/Files/tester.txt"));
        while (reader.hasNextLine())
        {
            String line;
            line = reader.nextLine();
            if(line.contains("15.0€") && line.contains("title1"))
                found1 = true;
            if(line.contains("20.0€"))
            {
                found2=true;
            }
        }
        assertTrue(found1);
        assertFalse(found2);

    }

    @Test
    public void getUserNames() throws IOException
    {
        OrdersFile file = new OrdersFile("src/Files/tester.txt");
        ArrayList<String> names = file.getUserNames();
        assertTrue(names.contains("user1"));
        assertFalse(names.contains("p"));
    }

    @Test
    public void getSales() throws IOException
    {
        OrdersFile file = new OrdersFile("src/Files/tester.txt");
        FileWriter writer = new FileWriter(new File("src/Files/tester.txt"));
        writer.close();
        Products pr1 = new Products("title1","description1","category1","subcategory1","15€","quantity1");
        ProductsList cart = new ProductsList();
        cart.addToList(pr1);
        ArrayList<String> quan = new ArrayList<>();
        quan.add("1");
        ProductsList list = new ProductsList();
        list.addToList((pr1));
        file.addNewPurchase("alex",cart,quan,list);

        ArrayList<Integer> quantities = file.getSales(list);
        assertEquals(quantities.size(),1);

    }
}