package api;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductsTest {

    @Test
    public void getTitle()
    {
        Products pr1 = new Products("title1","description1","category1","subcategory1","price1","quantity1");
        assertEquals(pr1.getTitle(),"title1");
    }

    @Test
    public void getDescription()
    {
        Products pr1 = new Products("title1","description1","category1","subcategory1","price1","quantity1");
        assertEquals(pr1.getDescription(),"description1");
    }

    @Test
    public void getCategory()
    {
        Products pr1 = new Products("title1","description1","category1","subcategory1","price1","quantity1");
        assertEquals(pr1.getCategory(),"category1");
    }

    @Test
    public void getSubcategory()
    {
        Products pr1 = new Products("title1","description1","category1","subcategory1","price1","quantity1");
        assertEquals(pr1.getSubcategory(),"subcategory1");
    }

    @Test
    public void getPrice()
    {
        Products pr1 = new Products("title1","description1","category1","subcategory1","price1","quantity1");
        assertEquals(pr1.getPrice(),"price1");
    }

    @Test
    public void getQuantity()
    {
        Products pr1 = new Products("title1","description1","category1","subcategory1","price1","quantity1");
        assertEquals(pr1.getQuantity(),"quantity1");
    }

    @Test
    public void setTitle()
    {
        Products pr1 = new Products("title1","description1","category1","subcategory1","price1","quantity1");
        pr1.setTitle("change");
        assertEquals(pr1.getTitle(),"change");
    }

    @Test
    public void setDescription()
    {
        Products pr1 = new Products("title1","description1","category1","subcategory1","price1","quantity1");
        pr1.setDescription("change");
        assertEquals(pr1.getDescription(),"change");
    }

    @Test
    public void setCategory()
    {
        Products pr1 = new Products("title1","description1","category1","subcategory1","price1","quantity1");
        pr1.setCategory("change");
        assertEquals(pr1.getCategory(),"change");
    }

    @Test
    public void setSubcategory()
    {
        Products pr1 = new Products("title1","description1","category1","subcategory1","price1","quantity1");
        pr1.setSubcategory("change");
        assertEquals(pr1.getSubcategory(),"change");
    }

    @Test
    public void setPrice()
    {
        Products pr1 = new Products("title1","description1","category1","subcategory1","price1","quantity1");
        pr1.setPrice("change");
        assertEquals(pr1.getPrice(),"change");
    }

    @Test
    public void setQuantity()
    {
        Products pr1 = new Products("title1","description1","category1","subcategory1","price1","quantity1");
        pr1.setQuantity("change");
        assertEquals(pr1.getQuantity(),"change");
    }

}