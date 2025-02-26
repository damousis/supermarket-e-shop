package api;

import org.junit.Test;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ProductsListTest
{

    @Test
    public void readProductsFile() throws FileNotFoundException
    {
        ProductsList list = new ProductsList();
        list.readProductsFile();
        assertEquals(list.getProduct(0).getTitle(),"Πορτοκάλια 1kg");
    }

    @Test
    public void addToList()
    {
        ProductsList list = new ProductsList();
        list.addToList(new Products("title1","description1","category1","subcategory1","price1","quantity1"));
        //listOfProducts.add(new Products("title1","description1","category1","subcategory1","price1","quantity1"));
        assertEquals(list.getSize(),1);
    }

    @Test
    public void getSize()
    {
        ProductsList list = new ProductsList();
        list.addToList(new Products("title1","description1","category1","subcategory1","price1","quantity1"));
        list.addToList(new Products("title2","description2","category2","subcategory2","price2","quantity2"));
        assertEquals(list.getSize(),2);
    }

    @Test
    public void getProduct()
    {
        ProductsList list = new ProductsList();
        list.addToList(new Products("title1","description1","category1","subcategory1","price1","quantity1"));

        String title = list.getProduct(0).getTitle();

        assertEquals(title,"title1");

    }

    @Test
    public void alreadyExists() throws FileNotFoundException
    {
        ProductsList helper = new ProductsList();
        helper.readProductsFile();
        Products products1 = new Products("aa","description1","category1","subcategory1","price1","quantity1");
        assertEquals(helper.alreadyExists(products1.getTitle()),false);
        Products product2 = new Products("Φιλέτο Σολομού 300g","description1","category1","subcategory1","price1","quantity1");
        assertEquals(helper.alreadyExists(product2.getTitle()),true);
    }

    @Test
    public void getIndex() throws FileNotFoundException
    {
        ProductsList helper = new ProductsList();
        helper.readProductsFile();
        assertEquals(helper.getIndex("Πορτοκάλια 1kg"),0);
    }

    @Test
    public void getFinalCost() throws FileNotFoundException
    {
        ProductsList list1 = new ProductsList();
        ProductsList list2 = new ProductsList();

        list1.readProductsFile();

        ProductsList helper1 = new ProductsList();
        helper1.addToList(list1.getProduct(0));

        ProductsList helper2 = new ProductsList();
        helper2.addToList(list1.getProduct(1));

        ArrayList<String> helper3 = new ArrayList<>();
        helper3.add("1");

        ArrayList<String> helper4 = new ArrayList<>();
        helper4.add("2");


        String sum1 = helper1.getFinalCost(helper3);
        String sum2 = helper2.getFinalCost(helper4);

        Double finalP = Double.parseDouble(sum1) + Double.parseDouble(sum2);


        list2.addToList(list1.getProduct(0));
        list2.addToList(list1.getProduct(1));
        ArrayList<String> quan = new ArrayList<>();
        quan.add("1");
        quan.add("2");

        boolean val;
        if(Math.abs(finalP-Double.parseDouble(list2.getFinalCost((quan))))<=0.01)
            val = true;
        else
            val = false;

        assertTrue(val);
        //assertEquals(list2.getFinalCost(quan),finalP.toString());
    }

    @Test
    public void contains() throws FileNotFoundException
    {
        ProductsList helper = new ProductsList();
        helper.readProductsFile();
        assertTrue(helper.contains(helper.getProduct(0).getQuantity()));
        assertFalse(helper.contains(helper.getProduct(3).getQuantity()));
    }

    @Test
    public void remove() throws FileNotFoundException
    {
        ProductsList helper = new ProductsList();
        helper.readProductsFile();
        int size = helper.getSize();
        helper.remove(0);
        assertEquals(helper.getSize(),size-1);
    }

    @Test
    public void check() throws FileNotFoundException
    {
        ProductsList helper = new ProductsList();
        helper.readProductsFile();
        double quan = 210;
        double quan2= 10;
        if(!helper.getProduct(0).getQuantity().contains("220"))
            assertEquals(helper.check(0,quan),false);
        else
            assertEquals(helper.check(0,quan),true);

        assertEquals(helper.check(10,quan2),true);
    }

    @Test
    public void findMeasures() throws FileNotFoundException
    {
        ProductsList helper = new ProductsList();
        helper.readProductsFile();
        ProductsList helperProd = new ProductsList();
        Products pr1 = (new Products("Πορτοκάλια 1kg","description1","category1","subcategory1","price1","quantity1kg"));
        Products pr2 = (new Products("Φιλέτο Σολομού 300g","description2","category2","subcategory2","price2","quantity2 τεμάχια"));
        helperProd.addToList(pr1);
        helperProd.addToList(pr2);
        ArrayList <String> meas = helper.findMeasures(helperProd);

        assertEquals(meas.get(0),"kg");
        assertEquals(meas.get(1),"τεμάχια");

    }

    @Test
    public void updateListOfProducts() throws FileNotFoundException
    {
        ProductsList list1 = new ProductsList();
        list1.readProductsFile();
        ProductsList cart = new ProductsList();
        String want = list1.getProduct(0).getQuantity();
        String finalQuan = want.replace("kg","").trim();
        double val = Double.parseDouble(finalQuan) -10;



        Products products = new Products("Πορτοκάλια 1kg","Φρέσκα πορτοκάλια, ιδανικά για χυμό ή κατανάλωση.","Φρέσκα τρόφιμα","Φρούτα","1,20€","200kg");
        cart.addToList(products);
        ArrayList<String> quan = new ArrayList<>();
        quan.add("10");
        list1.updateListOfProducts(cart,quan);

        assertEquals(list1.getProduct(0).getQuantity(),Double.toString(val)+"kg");

        quan.remove(0);
        quan.add("-10");
        list1.updateListOfProducts(cart,quan);
    }

    @Test
    public void addToFile() throws IOException
    {

        ProductsList list1 = new ProductsList();
        list1.readProductsFile();
        int size = list1.getSize();
        ProductsList list2 = new ProductsList();
        list2.readProductsFile();
        list2.addToFile(new Products("αγγουρια","φρεσκα","τροφιμα","λαχανικα","2","100kg"));
        list2.addToList(new Products("αγγουρια","φρεσκα","τροφιμα","λαχανικα","2","100kg"));
        assertEquals(list2.getSize()-1,size);

        list2.remove(size);
        list2.updateFile();


    }

    @Test
    public void changeInfo() throws FileNotFoundException
    {

        ProductsList list = new ProductsList();
        list.readProductsFile();
        String quan = list.getProduct(0).getQuantity();
        int index = 0;
        ArrayList<String> info = new ArrayList<>();
        info.add("Τίτλος: Πορτοκάλια 1kg");
        info.add("Περιγραφή: Φρέσκα πορτοκάλια, ιδανικά για χυμό ή κατανάλωση.");
        info.add("Κατηγορία: Φρέσκα τρόφιμα");
        info.add("Υποκατηγορία: Φρούτα");
        info.add("Τιμή: 1,20€");
        info.add("Ποσότητα: 250kg");
        list.changeInfo(index,info);
        assertEquals(list.getProduct(0).getQuantity(),"250kg");

        info.set(5,"Ποσότητα: "+quan);
        list.changeInfo(index,info);
        assertEquals(list.getProduct(0).getQuantity(),quan);


    }

    @Test
    public void updateFile() throws IOException
    {
        ProductsList list = new ProductsList();
        list.readProductsFile();
        Products products = new Products("αγγουρια","φρεσκα","τροφιμα","λαχανικα","2","100kg");

        list.addToList(products);
        list.updateFile();

        Scanner filereader = new Scanner(new File("src/Files/Products.txt"));
        boolean found = false;
        while ((filereader.hasNextLine()))
        {
            String data = filereader.nextLine();
            if(data.contains("αγγουρια"))
            {
                found = true;
                break;
            }
        }

        assertTrue(found);
        list.remove(list.getSize()-1);
        list.updateFile();

    }

    @Test
    public void  updateCustomerPanels()
    {
        JPanel panel = new JPanel();
        ArrayList<JPanel> list = new ArrayList<>();

        ProductsList Plist = new ProductsList();
        Products products = new Products("αγγουρια","φρεσκα","τροφιμα","λαχανικα","2","100kg");

        ProductsList list1 = new ProductsList();

        list.add(panel);
        list1.addToList(products);

        try
        {
            Plist.updateCustomerPanels(list, list1);
        }
        catch (Exception e)
        {
            assertEquals("Index 0 out of bounds for length 0", e.getMessage());
        }


    }
}