package api;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class CategoriesMapTest
{
    @Test
    public void readFile()
    {
        CategoriesMap cat = new CategoriesMap();
        int sum = cat.getCategories().size();
        assertEquals(sum,17);
    }

    @Test
    public void getCategories()
    {
        CategoriesMap map = new CategoriesMap();
        HashMap<String,String[]> cat = map.getCategories();
        assertNotNull(cat);

        //KATI AKOMA EDW

    }

    @Test
    public void getValue()
    {
        CategoriesMap map = new CategoriesMap();
        String[] sub = map.getValue("Φρέσκα τρόφιμα ");

        assertEquals(sub[0],"Φρούτα");
        assertEquals(sub[1],"Λαχανικά");

        String []sub2 = map.getValue("no cat");
        assertNull(sub2);
    }
}