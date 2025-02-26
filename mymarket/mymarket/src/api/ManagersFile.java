package api;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
import java.io.*;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Alexander Damousis, Vasiliki Zerdali.
 * This class is used to create a file that will save all the manager information such as the name and the password
 * of each manager.
 */
public class ManagersFile
{
    static final File managersFile = new File("src/Files/ManagersInfo.txt");

    /**
     * This is a constructor that creates a file that will store the data of the managers.The file will be created
     * only if the file does not already exist.
     */
    public ManagersFile() throws IOException
    {
        if(!managersFile.exists())
        {
            managersFile.createNewFile();
        }
    }

    /**
     * This is a method is used to add the manager's information in the file.
     * more than once,also it is used for the sign-in.
     * @param manager is the object that will be used in order to save the manager's data(the name and the password).
     */

    //adds a manager
    public void addInManagersFiles(Manager manager) throws IOException
    {
        FileWriter myWriter = new FileWriter("src/Files/ManagersInfo.txt",true);
        myWriter.write(manager.getManagerUserName());
        myWriter.write(" ");
        myWriter.write(manager.getManagerPassword());
        myWriter.write(System.lineSeparator());
        myWriter.close();
    }


    /**
     *  This is a method that checks if a manager is already saved in the file in order not to store the same manager
     * @param managerName is a String that is used for the manager's name.
     * @param managerPassword is a String that is used for the manager's password.
     * @return true if the manager exist in the file otherwise it returns false.
     */
    //checking if thr manager exists and the info is correct
    public boolean checkIfManagerExists(String managerName,String  managerPassword)
    {
        try
        {
            Scanner fileReader = new Scanner(managersFile);
            while(fileReader.hasNextLine())
            {
                String[] managerInfo = fileReader.nextLine().split(" ");
                if(managerInfo[0].equals(managerName) && managerInfo[1].equals(managerPassword))
                {
                    return true;
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("problem");
        }
        return false;
    }

    /**
     * This method checks if the file that contains the manager's info is empty
     * @return boolean variable.true if its empty otherwise false.
     */
    public boolean isEmpty()
    {
        return managersFile.length()==0;
    }

}
