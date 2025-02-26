package api;

import org.junit.Test;

import static org.junit.Assert.*;

public class ManagerTest
{

    @Test
    public void getManagerUserName()
    {
        Manager m1 = new Manager("alex","d");
        assertEquals(m1.getManagerUserName(),"alex");
    }

    @Test
    public void getManagerPassword()
    {
        Manager m2 = new Manager("vasw","z");
        assertEquals(m2.getManagerPassword(),"z");
    }
}