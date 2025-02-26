package api;

import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest
{

    @Test
    public void getCustomerFirstName()
    {
        Customer c1 = new Customer("alex","damos","damos","ad");
        assertEquals(c1.getCustomerFirstName(),"alex");
    }

    @Test
    public void getCustomerLastName()
    {
        Customer c1 = new Customer("alex","damousis","damos","ad");
        assertEquals(c1.getCustomerLastName(),"damousis");

    }

    @Test
    public void getCustomerUserName()
    {
        Customer c1 = new Customer("vasiliki","zerdali","vasw","vz");
        assertEquals(c1.getCustomerUserName(),"vasw");

    }

    @Test
    public void getCustomerPassword()
    {
        Customer c1 = new Customer("vasiliki","zerdali","vasw","vz");
        assertEquals(c1.getCustomerPassword(),"vz");
    }
}