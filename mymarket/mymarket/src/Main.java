import api.*;
import gui.*;

import java.io.IOException;


/**
 * Το πρόγραμμά σας πρέπει να έχει μόνο μία main, η οποία πρέπει να είναι η παρακάτω.
 * <p>
 * <p>
 * ************* ΜΗ ΣΒΗΣΕΤΕ ΑΥΤΗ ΤΗΝ ΚΛΑΣΗ ************
 */
public class Main
{

    public static void main(String[] args) throws IOException
    {

        ProductsList listOfProducts = new ProductsList();
        listOfProducts.readProductsFile();


        CustomersFile customerFile = new CustomersFile();
        ManagersFile managersFile = new ManagersFile();

        if(managersFile.isEmpty())
        {
            managersFile.addInManagersFiles(new Manager("admin1","password1"));
            managersFile.addInManagersFiles(new Manager("admin2","password2"));
        }

        if(customerFile.isEmpty())
        {
            Customer user1 = new Customer("alex","damos","user1","password1");
            Customer user2 = new Customer("vasw","zerdali","user2","password2");
            customerFile.addInFile(user1);
            customerFile.addInFile(user2);
        }

        OrdersFile ordersFile = new OrdersFile("src/Files/HistoryOfOrders.txt");


        SignInScreen screen = new SignInScreen(customerFile,managersFile,listOfProducts,ordersFile);

    }
}