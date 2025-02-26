package api;

/**
 * @author  Vasiliki Zerdali, Alexander Damousis
 * This class is used to manage all the products that will be created.
 */
public class Products
{

    private String title,description,category,subcategory,price,quantity;

    /**
     * This is a constructor for each 'Products' object that is created.
     *
     * @param title is the title of the product
     * @param description is a String that holds the description of the product
     * @param category is a String that holds the category of each product
     * @param subcategory is a String that holds the subcategory of each product
     * @param price is a String that holds the price of the product
     * @param quantity is a String that holds the quantity of each product
     */
    public Products(String title,String description,String category,String subcategory,String price ,String quantity)
    {
        this.title=title;
        this.description=description;
        this.category=category;
        this.subcategory=subcategory;
        this.price=price;
        this.quantity=quantity;
    }


    /**
     *A method to have access to the title of the product.
     * @return a String that is the title of the product.
     */
    public String getTitle(){return title;}

    /**
     * A method to have access to the description of the product.
     * @return a String that is the description of the product.
     */

    public String getDescription(){return description;}


    /**
     * A method to have access to the category of the product.
     * @return a String that is the category of the product.
     */
    public String getCategory(){return category;}


    /**
     * A method to have access to the subcategory of the product.
     * @return a String that is the subcategory of the product.
     */
    public String getSubcategory(){return subcategory;}


    /**
     * A method to have access to the price of the product.
     * @return a String that is the price of the product.
     */
    public String getPrice(){return price;}


    /**
     * A method to have access to the quantity of the product.
     * @return a String that is the quantity of the product.
     */
    public String getQuantity(){return quantity;}


    /**
     * This method is used to change the title of a product.
     * @param title is a String that holds the new name of the product.
     */
    public void setTitle(String title){this.title = title;}


    /**
     * This method is used to change the description of a product.
     * @param description is a String that holds the new description of the product.
     */
    public void setDescription(String description){this.description =description;}


    /**
     * This method is used to change the category of a product.
     * @param category is a String that holds the new category of the product.
     */
    public void setCategory(String category){this.category = category;}


    /**
     * This method is used to change the subcategory of a product.
     * @param subcategory is a String that holds the new subcategory of the product.
     */
    public void setSubcategory(String subcategory){this.subcategory =subcategory;}



    /**
     * This method is used to change the price of a product.
     * @param price is a String that holds the new price of the product.
     */
    public void setPrice(String price){this.price=price;}


    /**
     * This method is used to change the quantity of a product.
     * @param quantity is a String that holds the new quantity of the product.
     */
    public void setQuantity(String quantity){this.quantity=quantity;}



}