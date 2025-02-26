package api;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CleanStringTest {

    @Test
    public void convertString()
    {
        CleanString string = new CleanString("12,54€");
        assertEquals(string.convertString(),"12.54");

        CleanString string2 = new CleanString("123,554");
        assertEquals(string2.convertString(),"123.55");
    }

    @Test
    public void showFinalPrice()
    {
        CleanString string = new CleanString("12,54€");
        String cost = string.showFinalPrice("2");
        assertEquals(cost,"25.08");
    }

    @Test
    public void removeMeasure()
    {
        CleanString string = new CleanString("150kg");
        assertEquals(string.removeMeasure(),"150");

        CleanString  string2 = new CleanString("10 τεμάχια");
        assertEquals(string2.removeMeasure(),"10");
    }
}