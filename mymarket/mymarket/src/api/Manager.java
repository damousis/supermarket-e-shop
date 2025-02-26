package api;


/**
 * @author Alexander Damousis, Vasiliki Zerdali.
 * This class is used to create and manage a manager Object.
 * It creates the object, and it has method that can return the name and the password of the manager.
 */
public class Manager
{
    private final String managerUserName,managerPassword;

    /**
     * @param managerUserName is the name for the manager.
     * @param managerPassword is the password for the manager.
     * This is a constructor that is used to create the manager object.
     */
    public Manager(String managerUserName,String managerPassword)
    {
        this.managerUserName = managerUserName;
        this.managerPassword = managerPassword;
    }

    /**
     * @return a String that represents the name of the manager.
     * This class is used to have access to the name of the manager.
     */
    public String getManagerUserName(){ return managerUserName;}

    /**
     *
     * @return a String that represents the password of the manager.
     * This class is used to have access to the password of the manager.
     */
    public String getManagerPassword(){return managerPassword;}
}
