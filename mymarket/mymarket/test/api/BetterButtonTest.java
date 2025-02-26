package api;

import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class BetterButtonTest {

    @Test
    public void addAllProperties()
    {
        BetterButton b1 = new BetterButton();
        Products pr = new Products("a","b","c","d","e","f");
        ProductsList list = new ProductsList();
        ArrayList<String> quan = new ArrayList<>();
        JLabel label = new JLabel();
        JLabel price = new JLabel();
        JPanel panel = new JPanel();
        String type = "";

        b1.addAllProperties(pr,list,quan,label,type,price,panel);
        assertEquals(b1.getProduct().getTitle(),"a");
    }


    @Test
    public void getProduct()
    {
        BetterButton b1 = new BetterButton();
        Products pr = new Products("a","b","c","d","e","f");
        ProductsList list = new ProductsList();
        ArrayList<String> quan = new ArrayList<>();
        JLabel label = new JLabel();
        JLabel price = new JLabel();
        JPanel panel = new JPanel();
        String type = "";

        b1.addAllProperties(pr,list,quan,label,type,price,panel);
        assertEquals(b1.getProduct().getTitle(),"a");
    }



    @Test
    public void getType()
    {
        BetterButton b1 = new BetterButton();
        Products pr = new Products("a","b","c","d","e","f");
        ProductsList list = new ProductsList();
        ArrayList<String> quan = new ArrayList<>();
        JLabel label = new JLabel();
        JLabel price = new JLabel();
        JPanel panel = new JPanel();
        String type = "+";

        b1.addAllProperties(pr,list,quan,label,type,price,panel);
        assertEquals(b1.getType(),"+");
    }

    @Test
    public void getProductList()
    {
        BetterButton b1 = new BetterButton();
        Products pr = new Products("a","b","c","d","e","f");
        Products pr2 = new Products("ee","b","c","d","e","f");
        ProductsList list = new ProductsList();
        list.addToList(pr);
        list.addToList(pr2);
        ArrayList<String> quan = new ArrayList<>();
        JLabel label = new JLabel();
        JLabel price = new JLabel();
        JPanel panel = new JPanel();
        String type = "";

        b1.addAllProperties(pr,list,quan,label,type,price,panel);
        assertEquals(b1.getProductList().getSize(),2);
    }

    @Test
    public void getQuantityLabel()
    {
        BetterButton b1 = new BetterButton();
        Products pr = new Products("a","b","c","d","e","f");
        ProductsList list = new ProductsList();
        ArrayList<String> quan = new ArrayList<>();
        JLabel label = new JLabel();
        label.setText("QUANTITY");
        JLabel price = new JLabel();
        JPanel panel = new JPanel();
        String type = "+";

        b1.addAllProperties(pr,list,quan,label,type,price,panel);
        assertEquals(b1.getQuantityLabel().getText(),"QUANTITY");
    }

    @Test
    public void getQuantityList()
    {
        BetterButton b1 = new BetterButton();
        Products pr = new Products("a","b","c","d","e","f");
        ProductsList list = new ProductsList();
        ArrayList<String> quan = new ArrayList<>();
        quan.add("1");
        quan.add("2");
        JLabel label = new JLabel();
        JLabel price = new JLabel();
        JPanel panel = new JPanel();
        String type = "+";

        b1.addAllProperties(pr,list,quan,label,type,price,panel);
        assertEquals(b1.getQuantityList().size(),2);
        assertEquals(b1.getQuantityList().get(0),"1");
    }

    @Test
    public void getPriceLabel()
    {
        BetterButton b1 = new BetterButton();
        Products pr = new Products("a","b","c","d","e","f");
        ProductsList list = new ProductsList();
        ArrayList<String> quan = new ArrayList<>();
        JLabel label = new JLabel();
        JLabel price = new JLabel();
        price.setText("13€");
        JPanel panel = new JPanel();
        String type = "+";

        b1.addAllProperties(pr,list,quan,label,type,price,panel);
        assertEquals(b1.getPriceLabel().getText(),"13€");
    }

    @Test
    public void getPanel()
    {
        BetterButton b1 = new BetterButton();
        Products pr = new Products("a","b","c","d","e","f");
        ProductsList list = new ProductsList();
        ArrayList<String> quan = new ArrayList<>();
        JLabel label = new JLabel();
        JLabel price = new JLabel();
        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(price);
        String type = "+";

        b1.addAllProperties(pr,list,quan,label,type,price,panel);
        assertEquals(b1.getPanel().getComponents().length,2);
    }

    @Test
    public void addImage()
    {
        BetterButton button = new BetterButton();
        BetterButton helperButton = button.addImage();
        Icon icon = helperButton.getIcon();
        assertNotNull(icon);


    }
}