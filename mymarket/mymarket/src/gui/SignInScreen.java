package gui;
import api.CustomersFile;
import api.ManagersFile;
import api.OrdersFile;
import api.ProductsList;


import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignInScreen implements ActionListener
{

    final JFrame signInScreen = new JFrame();

    final JButton  signInButton = new JButton();
    final JButton registerButton = new JButton();
    final JTextField userNameTextField = new JTextField();

    final JPasswordField passwordTextField = new JPasswordField();

    final private CustomersFile customerFile;
    final private ManagersFile managerFile;

    final private OrdersFile ordersFile;

    private ProductsList listOfProducts;
    public SignInScreen(CustomersFile file, ManagersFile managerFile, ProductsList listOfProducts,OrdersFile ordersFile )
    {
        passwordTextField.setEchoChar('*');
        this.customerFile = file;
        this.managerFile = managerFile;
        this.ordersFile = ordersFile;
        this.listOfProducts = listOfProducts;
        setUpScreen();
    }


    /**
     * @author Alexandros Damousis , Vasiliki Zerdali
     *This class is used to create the window of the Sign in Screen.
     *It does not return anything, but it calls the setLabels in order to put the labels oj the screen.
     */
    private void setUpScreen()
    {

        signInScreen.setSize(400,500);
        signInScreen.setTitle("Login");
        signInScreen.setLocationRelativeTo(null);
        signInScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        signInScreen.getContentPane().setBackground(new Color(221, 235, 250)); //222 238 255
        signInScreen.setResizable(false);
        signInScreen.setLayout(null);

        setUpLabels();
        signInScreen.setVisible(true);
    }


    /**
     * @author Vasiliki zerdali
     * This method creates the labels that are used in the Sign in Screen.
     * The labels are for the sign in message,the username and the password.
     */

    private void setUpLabels()
    {
        //SETTING THE LABELS
        //SIGN IN
        JLabel signIn = new JLabel();
        signIn.setText("SIGN IN");
        signIn.setBounds(20,25,130,100);
        signIn.setFont(new Font("Arial",Font.BOLD |Font.ITALIC,22));

        JLabel my = new JLabel("My");
        my.setFont(new Font("Arial",Font.BOLD |Font.ITALIC,35));
        my.setForeground(Color.RED);
        my.setBounds(100,-0,120,50);
        signInScreen.add(my);

        JLabel market = new JLabel("market");
        market.setFont(new Font("Arial",Font.BOLD | Font.ITALIC,35));
        market.setForeground(Color.BLUE);
        market.setBounds(150,-0,140,50);
        signInScreen.add(market);

        //USERNAME
        JLabel userNameLabel = new JLabel();
        userNameLabel.setText("USERNAME");
        userNameLabel.setBounds(20,70,120,100);
        userNameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        //PASSWORD
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("PASSWORD");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setBounds(20,160,100,100);

        JLabel registration = new JLabel();
        //registration.setText("If you don't have an account click here");
        registration.setText("Don't you have an account?");
        registration.setBounds(80,345,320,100);
        registration.setFont(new Font("Arial",Font.PLAIN,16));


        //signInScreen.add(myMarket);
        signInScreen.add(passwordLabel);
        signInScreen.add(userNameLabel);
        signInScreen.add(signIn);
        signInScreen.add(registration);

        //SOS!!!
        signInScreen.revalidate(); //HAVE THIS AFTER ADDING ANYTHING IN THE SCREEN SO I REFRESHES AND IT SHOWS EVERYTHING PROPERLY!!!
        signInScreen.repaint();

        setUpTextFields();
    }



    /**
     * @author Alexander Damousis, Vasiliki Zerdali
     * This method is used to manage the Textfields that the user will put the input to.
     * The textfields that are made are for the username and the password.
     */
    private void setUpTextFields()
    {
        //SETTING THE ENTRIES

        //USER INPUT
        userNameTextField.setBounds(20,140,330,30);
        userNameTextField.setFont(new Font("Arial",Font.ITALIC,15));
        userNameTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        //PASSWORD INPUT
        passwordTextField.setBounds(20,230,330,30);
        passwordTextField.setFont(new Font("Arial",Font.ITALIC,15));
        passwordTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        signInScreen.add(userNameTextField);
        signInScreen.add(passwordTextField);

        signInScreen.revalidate();
        signInScreen.repaint();

        setUpRegistrationScreen();
    }


    /**
     * This method is used to create all the buttons in this screen.
     * The button that are created are in order for the user to sign in or to register
     */

    private void setUpRegistrationScreen()
    {
        //BUTTON FOR REGISTRATION
        registerButton.setBounds(120,405,120,30);
        registerButton.setText("Create account");
        registerButton.setBackground(new Color(255,255,255));
        registerButton.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        registerButton.setFont(new Font("Dialog",Font.BOLD,16));
        registerButton.setBackground(new Color(221, 235, 250));
        registerButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));//200,200,200
        registerButton.addActionListener(this);


        signInButton.setBounds(22 ,300,330,30);
        signInButton.setText("LOGIN");
        signInButton.setBackground(new Color(197,229,255));
        signInButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        signInButton.setFont(new Font("Arial", Font.BOLD, 16));
        signInButton.addActionListener(this);

        signInScreen.add(signInButton);
        signInScreen.add(registerButton);
        signInScreen.revalidate();
        signInScreen.repaint();
    }



    /**
     * @param e the event to be processed
     * This method is used for the behavior of each button when it is clicked.If the sign-in button is pressed then
    it will sign in if the input is correct. If the register button is clicked then it will lead to the registration page.
     */
    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource()== registerButton)
        {
            signInScreen.dispose();
            RegistrationScreen regScreen = new RegistrationScreen(customerFile,managerFile,listOfProducts,ordersFile);
        }
        else if(e.getSource()==signInButton)
        {
            if (userNameTextField.getText().equals("") || passwordTextField.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "You have some empty fields! Please fill all the fields", "Sign in problem.", JOptionPane.PLAIN_MESSAGE);
            }
            else
            {

                if (customerFile.checkCustomer(userNameTextField.getText(), new String(passwordTextField.getPassword())))
                {
                    signInScreen.dispose();
                    CustomersMainScreen customerScreen = new CustomersMainScreen(listOfProducts,userNameTextField.getText(),ordersFile);
                    userNameTextField.setText("");
                    passwordTextField.setText("");
                }
                else if (managerFile.checkIfManagerExists(userNameTextField.getText(), passwordTextField.getText()))
                {
                    ManagersMainScreen managerScreen = new ManagersMainScreen(listOfProducts,ordersFile);
                    signInScreen.dispose();
                    userNameTextField.setText("");
                    passwordTextField.setText("");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "The account was not found. Please try again.", "Sign in problem", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }

    }
}