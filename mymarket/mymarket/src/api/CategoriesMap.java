package api;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Alexander Damousis,Vasiliki Zerdali
 *A class that manages the categories and subcategories.
 * It is used to store, read and manage the products by their category/ subcategory.
 */
public class CategoriesMap
{
    private HashMap<String,String[]> categories;

    /**
     * The constructor initialize the categories by reading a file.
     * The keys are the categories and the values are arraylist that contain all the subectagories
     */
    public CategoriesMap()
    {
        categories = new HashMap<>();
        readFile();
    }

    /**
     * Reads the txt file and fills up the categories map
     * Each line in the file has the category and its corresponding subcategories
     */
    private void readFile()
    {
        try
        {
            Scanner reader = new Scanner(new File("src/Files/categories_subcategories.txt"));
            while(reader.hasNextLine())
            {
                String cat = reader.nextLine();
                String[] firstPart = cat.replace('(','&').split("&"); //use firstpart[0]
                String helper = firstPart[1].replace(')',' ').trim();
                String[] secondPart =helper.split("@");
                categories.put(firstPart[0],secondPart);
            }
            reader.close();

        }
        catch (FileNotFoundException e)
        {
            System.out.println("problem here");
        }
    }

    /**
     * Returns the entire map with all the information
     * @return a HashMap that the key is the category and the value is an array of the subcategories
     */
    public HashMap<String,String[]> getCategories()
    {
        return categories;
    }

    /**
     * Returns the subcategories that is associated with the given category
     * @param cat the name of the category
     * @return an array of the specific subcategories based on the category.
     */
    public String[] getValue(String cat){return categories.get(cat);}
}