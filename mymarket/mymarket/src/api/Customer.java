package api;

/**
 * @author Alexander Damousis, Vasiliki Zerdali.
 * This class is used to create and manage a Customer Object.
 * We use it mainly for the log in and the orders file.
 */
public class Customer
{
    final private String customerFirstName,customerLastName,customerUserName,customerPassword;

    /**
     * This class is used to create the Customer object.
     * @param customerFirstName refers to the first name of the customer.
     * @param customerLastName refers to the last name of the customer.
     * @param customerUserName refers to the username of the customer.
     * @param customerPassword refers to the password of the customer.
     */

    public Customer(String customerFirstName,String customerLastName,String customerUserName,String customerPassword)
    {
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerUserName = customerUserName;
        this.customerPassword = customerPassword;
    }

    /**
     * This method is used to have access to the customer's first name as it returns it.
     * @return a String that refers to the first name of the customer.
     */

    public String getCustomerFirstName(){return customerFirstName;}

    /**
     * This method is used to have access to the last name of the customer.
     * @return a String that refers to the last name of the customer.
     */

    public String getCustomerLastName(){return customerLastName;}

    /**
     * This method is used to have access to the username of the customer.
     * @return a String that refers to the username of the password.
     */

    public String getCustomerUserName(){return customerUserName;}

    /**
     * This class is used to have access to the password of the customer.
     * @return a String that refers to the password of the customer.
     */
    public String getCustomerPassword(){return customerPassword;}
}
