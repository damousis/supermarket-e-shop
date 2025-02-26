package api;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ManagersFileTest
{

    @Test
    public void checkIfManagerExists() throws IOException
    {
        ManagersFile file = new ManagersFile();

        assertTrue(file.checkIfManagerExists("admin1", "password1"));

        assertFalse(file.checkIfManagerExists("a", "d"));
    }

    @Test
    public void addInManagersFiles() throws IOException
    {
        ArrayList<Manager> managers = new ArrayList<>();
        ManagersFile file = new ManagersFile();
        file.addInManagersFiles(new Manager("v","z"));
        assertTrue(file.checkIfManagerExists("v","z"));

        Scanner filereader = new Scanner(new File("src/Files/ManagersInfo.txt"));
        while (filereader.hasNextLine())
        {
            String line;
            line = filereader.nextLine();
            String info[] = line.split(" ");
            Manager m = new Manager(info[0],info[1]);
            managers.add(m);
        }
        FileWriter writer = new FileWriter("src/Files/ManagersInfo.txt");
        writer.close();
        for (int i=0;i<managers.size()-1;i++)
        {
            file.addInManagersFiles(managers.get(i));
        }
    }

    @Test
    public void  isEmpty() throws IOException
    {
        ManagersFile file = new ManagersFile();
        assertFalse(file.isEmpty());

    }
}