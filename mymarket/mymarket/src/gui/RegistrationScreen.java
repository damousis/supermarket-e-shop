package gui;

import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class is used to represent the RegistrationScreen for the customers.
 */

/**
 * This class is used to represent the RegistrationScreen for the customers.
 */
public class RegistrationScreen implements ActionListener
{
    final private CustomersFile customerFile; //customers file
    final  private ManagersFile managerFile; //managers file

    final private OrdersFile ordersFile;
    final  private  JButton register = new JButton(); // button for registration
    final  private JButton signIn = new JButton(); //button for sign in
    final  private JFrame registrationScreen = new JFrame(); //the new window

    final private JTextField lastNameTextField = new JTextField();
    final  private JTextField firstNameTextField = new JTextField();

    final private JTextField registerUserNameTextField = new JTextField();
    final private JTextField registerUserPasswordTextField= new JTextField();

    private ProductsList listOfProducts ;


    /**
     * @author Alexander Damousis ,Vasiliki Zerdali.
     * @param file is the customers-file that stores all the data for the customers.
     * @param managerFile is the file that stores all the data for the managers.
     * This method is used to create the window for the registration screen.
     */
    public RegistrationScreen(CustomersFile file,ManagersFile managerFile,ProductsList listOfProducts,OrdersFile ordersFile)
    {
        registrationScreen.setTitle("REGISTRATION SCREEN");
        registrationScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        registrationScreen.setResizable(false);
        registrationScreen.setSize(400,600);
        registrationScreen.setLocationRelativeTo(null);
        registrationScreen.setLayout(null);
        registrationScreen.getContentPane().setBackground(new Color(221, 235, 250)); //221 235 250

        this.customerFile = file;
        this.managerFile = managerFile;
        this.ordersFile = ordersFile;
        this.listOfProducts = listOfProducts;

        setRegistrationLabels();
        registrationScreen.setVisible(true);

    }


    /**
     * @author Alexander Damousis ,Vasiliki Zerdali.
     * This method is used to create the Labels on this screen.
     * The labels that are created are the registration message, the firstname , the password,the lastname and the username.
     */
    private void setRegistrationLabels()
    {
        JLabel register = new JLabel();
        register.setText("CREATE ACCOUNT");
        register.setBounds(70,5,220,100);
        register.setForeground(new Color(204,0,0));
        register.setFont(new Font("Arial", Font.BOLD, 22));


        JLabel registrationName = new JLabel();
        registrationName.setText("FIRST NAME");
        registrationName.setFont(new Font("Arial", Font.BOLD, 16));
        registrationName.setBounds(15,80,100,100);

        JLabel registrationLastName = new JLabel();
        registrationLastName.setText("LAST NAME");
        registrationLastName.setFont(new Font("Arial", Font.BOLD, 16));
        registrationLastName.setBounds(15,170,100,100);

        JLabel registrationUserName = new JLabel();
        registrationUserName.setText("USERNAME");
        registrationUserName.setFont(new Font("Arial", Font.BOLD, 16));
        registrationUserName.setBounds(15,260,100,100);

        JLabel registrationPassword = new JLabel();
        registrationPassword.setText("PASSWORD");
        registrationPassword.setFont(new Font("Arial", Font.BOLD, 16));
        registrationPassword.setBounds(15,350,100,100);

        registrationScreen.add(registrationName);
        registrationScreen.add(register);
        registrationScreen.add(registrationLastName);
        registrationScreen.add(registrationUserName);
        registrationScreen.add(registrationPassword);

        registrationScreen.revalidate();
        registrationScreen.repaint();

        setRegistrationTextFields();
    }


    /**
     * @author Alexander Damousis, Vasiliki Zerdali.
     * This method is used to create the textfields on this screen.
     * The textfields that are created are about the firstname,the lastname,the username and the password.
     */
    private void setRegistrationTextFields()
    {
        firstNameTextField.setBounds(16,150,300,30);
        firstNameTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        firstNameTextField.setFont(new Font("Arial",Font.ITALIC,15));


        lastNameTextField.setBounds(16,235,300,30);
        lastNameTextField.setFont(new Font("Arial",Font.ITALIC,15));
        lastNameTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));


        registerUserNameTextField.setBounds(16,325,300,30);
        registerUserNameTextField.setFont(new Font("Arial",Font.ITALIC,15));
        registerUserNameTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));


        registerUserPasswordTextField.setBounds(16,420,300,30);
        registerUserPasswordTextField.setFont(new Font("Arial",Font.ITALIC,15));
        registerUserPasswordTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        //ADDING EVERYTHING
        registrationScreen.add(firstNameTextField);
        registrationScreen.add(lastNameTextField);
        registrationScreen.add(registerUserNameTextField);
        registrationScreen.add(registerUserPasswordTextField);

        registrationScreen.revalidate();
        registrationScreen.repaint();

        setUpRegistrationButton();
    }


    /**
     * @author Alexander Damousis, Vasiliki Zerdali.
     * This method is used to create the buttons on this screen.
     * The buttons that are created are about the registration of the user or to go back to the sign in screen.
     */
    private void setUpRegistrationButton()
    {
        register.setText("Create account");
        register.setBackground(new Color(255,255,255));
        register.setFont(new Font("Arial",Font.BOLD,15));
        register.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        register.setBounds(190,490,150,30);

        signIn.setText("BACK");
        signIn.setBackground(new Color(255,255,255));
        signIn.setBackground(new Color(255,255,255));
        signIn.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        signIn.setBounds(30,490,100,30);

        register.addActionListener(this);
        signIn.addActionListener(this);

        registrationScreen.add(register);
        registrationScreen.add(signIn);

        registrationScreen.revalidate();
        registrationScreen.repaint();
    }

    /**
     * @author Alexander Damousis , Vasiliki Zerdali.
     * @param e the event to be processed and the action that will be made.
     * This method is used for the behaviour of each button when is pressed.
     * If the register button is pressed a new customer will be made.
     * If the sign-in button is pressed then it will go back to the sign in screen.
     */
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==register) //CHECKING WHERE THE CLICK CAME FRO
        {
            if( !firstNameTextField.getText().isEmpty() && !lastNameTextField.getText().isEmpty() && !registerUserNameTextField.getText().isEmpty() &&! registerUserPasswordTextField.getText().isEmpty())
            {
                Customer c = new Customer(firstNameTextField.getText(), lastNameTextField.getText(), registerUserNameTextField.getText(), registerUserPasswordTextField.getText());
                if(!customerFile.alreadyRegistered(c))
                {
                    customerFile.addInFile(c);
                    ordersFile.addNewCustomer(registerUserNameTextField.getText());
                    registrationScreen.dispose();
                    CustomersMainScreen customerScreen = new CustomersMainScreen(listOfProducts,registerUserNameTextField.getText(),ordersFile);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"A account already exists with this username.Please enter another username.","User-name already exists",JOptionPane.PLAIN_MESSAGE);
                }

            }
            else //Add the ERROR
            {
                JOptionPane.showMessageDialog(null,"Some fields are empty. Please fill all the fields.","Registration problem",JOptionPane.PLAIN_MESSAGE);
            }
        }
        else if(e.getSource()==signIn)
        {
            registrationScreen.dispose();
            SignInScreen newSignInScreen = new SignInScreen(customerFile,managerFile,listOfProducts,ordersFile);
        }
    }

}