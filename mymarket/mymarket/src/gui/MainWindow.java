package gui;

import api.*;

import javax.swing.ButtonGroup;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import static gui.CustomersMainScreen.shoppingCart;


/**
 * This class is a general main window that holds a big panel with smaller panels inside of it.The smaller panels contain a product with its info each.
 * This class will be inherited by the main screens of the customers and the managers.
 */
public class MainWindow implements ActionListener, MouseListener
{
    public static String[] pictureNames = {"πορτοκάλια.jpg","καρότα.jpg","Φιλέτο Σολομού.jpg","Κιμάς Μοσχαρίσιος.jpg","Κατεψυγμένες Πίτσες.jpg","Κατεψυγμένα Γεύματα.jpg","Γιαούρτι.jpg","Γάλα.jpg","Βούτυρο.jpg",
            "Σαλάμι Αέρος.jpg","Μπέικον.jpg","Κρασί.jpg","Ούζο.jpg","Τσίπουρο.jpg","Coca Cola.jpg","Νερό.jpg","Red Bull.jpg","Καθαριστικό Τζαμιών.jpg",
            "Καθαριστικό Κουζίνας.jpg","Υγρό Πλυντηρίου.jpg","Μαλακτικό.jpg","Ρουζ.jpg","Λοσιόν.jpg","Οδοντόβουρτσα.jpg","Listerine.jpg","Πάνες Ενηλίκων.jpg","Μούσλι.png",
            "Βρώμη.jpg","Κριθαράκι.jpg","Ταλιατέλες.png","Κράκερς.jpg","Μπάρες.jpg","Ηλιέλαιο.jpg","Σογιέλαιο.jpg","Φασόλια.jpg","Ροδάκινα.jpg",
            "Χαρτοπετσέτες.jpg","Χαρτομάντηλα.jpg"};

    protected JComboBox categoriesCombo = new JComboBox<>();
    protected JComboBox subCategory = new JComboBox();
    private JPanel productDetailsPanel;
    Color originalColor;

    private JButton okButton;
    private JButton backButton;
    protected JButton logOut;
    private CategoriesMap categories;

    protected JFrame mainWindow = new JFrame();
    JFrame frame = new JFrame();
    JButton searchButton = new JButton();
    JTextField searchBar;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected JPanel productsPanel = new JPanel();
    protected ProductsList listOfProducts;

    protected ArrayList<JPanel> panelOfProduct = new ArrayList<>(); //this list contains all the panels


    /**
     * This method is used to create the main part of the window.(such as the width,height ect).
     *
     * @param listOfProducts is an ArrayList that holds all the products with the information of each product.
     */
    public MainWindow(ProductsList listOfProducts)
    {
        this.listOfProducts = listOfProducts;
        mainWindow.setSize((int) screenSize.getWidth() + 10, (int) screenSize.getHeight());
        mainWindow.setResizable(true);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setTitle("My market");
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setLayout(new BorderLayout());
        mainWindow.add(productsPanel, BorderLayout.CENTER);

// Set background color of the productsPanel
        productsPanel.setBackground(new Color(230, 240, 247)); // 1) 221, 235, 250  2) 3)230, 240, 247

        setWindowLabels();
        logOutButton();
        mainWindow.setVisible(true);
    }

    public void logOutButton()
    {
        logOut=new JButton();
        logOut.setText("Log out");
        logOut.setBounds((int)screenSize.getWidth()-190,70,140,40);
        logOut.setFont(new Font("Arial",Font.BOLD,16));
        logOut.setForeground(Color.RED);
        logOut.addActionListener(this);
        productsPanel.add(logOut);
    }
    /**
     * This method is used to create ALL the small panels that hold a product each.
     * Each panel is placed in a special position and all the panels have the same width and height.
     */
    public void setWindowLabels()
    {


        productsPanel.setLayout(null);
        JLabel title1 = new JLabel("My");
        title1.setFont(new Font("Arial",Font.BOLD | Font.ITALIC,40));
        title1.setForeground(Color.RED);
        title1.setBounds(120,20,140,65);
        productsPanel.add(title1);

        JLabel title2 = new JLabel("market");
        title2.setFont(new Font("Arial",Font.BOLD | Font.ITALIC,40));
        title2.setForeground(Color.BLUE);
        title2.setBounds(180,20,140,65);
        productsPanel.add(title2);


        int productWidth = 380;
        int productHeight = 360;
        int padding = 50;

        String path1 = "src/images/";

        int productsPerRow = ((int) screenSize.getWidth() / productWidth) - 1;

        for (int i = 0; i < listOfProducts.getSize(); i++)
        {
            JPanel productPanel = new JPanel();
            productPanel.setLayout(null);
            productPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            productPanel.setBackground(new Color(255, 255, 255));

            Products helpProduct = listOfProducts.getProduct(i);

            int x = (i % productsPerRow) * (productWidth + padding) + 110;
            int y = (i / productsPerRow) * (productHeight + padding) + 145;
            productPanel.setBounds(x, y, productWidth, productHeight);

            // Add name label with more space below
            JLabel nameLabel = new JLabel(helpProduct.getTitle());
            nameLabel.setBounds(10, 10, 380, 30);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
            nameLabel.setForeground(new Color(80,80,80));
            productPanel.add(nameLabel);


            JTextArea productDescription = new JTextArea("Description: " + helpProduct.getDescription());
            productDescription.setBounds(10, 150, 350, 50); // 100!!!!!!!!!!!!!!!!
            productDescription.setLineWrap(true);
            productDescription.setWrapStyleWord(true);
            productDescription.setEditable(false);
            productDescription.setOpaque(false);
            productDescription.setFont(new Font("Arial", Font.ITALIC, 16));
            productPanel.add(productDescription);


            JLabel productCategory = new JLabel("Category: " + helpProduct.getCategory());
            productCategory.setBounds(10, 195, 300, 30); //145!!!!!!!!!!
            productCategory.setFont(new Font("Arial", Font.ITALIC, 16));
            productPanel.add(productCategory);


            JLabel productSubCategory = new JLabel("Subcategory: " + helpProduct.getSubcategory());
            productSubCategory.setBounds(10, 220, 300, 30); //170!!!!!!
            productSubCategory.setFont(new Font("Arial", Font.ITALIC, 16));
            productPanel.add(productSubCategory);


            // Add price label with more space above
            JLabel priceLabel = new JLabel("Price :" + helpProduct.getPrice());
            priceLabel.setBounds(10, 245, 100, 30); // 195!!!!!
            priceLabel.setFont(new Font("Arial", Font.ITALIC, 16));
            productPanel.add(priceLabel);

            JLabel quantityLabel = new JLabel("Quantity: " + helpProduct.getQuantity());
            quantityLabel.setBounds(10, 269, 300, 30); //219!!!!!
            quantityLabel.setFont(new Font("Arial", Font.ITALIC, 16));
            productPanel.add(quantityLabel);


            if(i>=pictureNames.length)
            {
                JLabel image =new JLabel("no picture yet :(");
                image.setBounds(120,40,140,100);
                productPanel.add(image);
            }
            else
            {
                String path2 = pictureNames[i];
                Image originalImage = new ImageIcon(path1+path2).getImage();
                Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(scaledImage);
                JLabel image =new JLabel(imageIcon);
                image.setBounds(x+120,y+40,100,100);
                productsPanel.add(image);
            }




            productPanel.putClientProperty("product", helpProduct);


            productsPanel.add(productPanel);
            panelOfProduct.add(productPanel);
        }


        int totalRows = (int) Math.ceil((listOfProducts.getSize() + 1.5) / ((double) productsPerRow) + 0.5); //change the 10 when it is needed.
        int panelWidth = productsPerRow * (productWidth + padding) + 120;
        int panelHeight = totalRows * (productHeight + padding) + 110;

        productsPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));

        JScrollPane scrollPane = new JScrollPane(productsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(100);
        scrollPane.getVerticalScrollBar().setBlockIncrement(100);
        mainWindow.add(scrollPane);


        //IT STARTS THE PANE FROM THE BEGINNING OF THE SCREEN.
        SwingUtilities.invokeLater(() ->
        {
            scrollPane.getViewport().setViewPosition(new Point(0, 0));
        });


        createSearchPart();
    }


    /**
     * This method is used in order to create a search bar.
     * The search bar will bve used to find a product based from the category and the sub category.
     */
    public void createSearchPart()
    {
        JLabel searchTitle = new JLabel();
        searchTitle.setText("Search by title: ");
        searchTitle.setFont(new Font("Arial", Font.BOLD, 18));
        searchTitle.setBounds(350, 15, 230, 40);
        searchTitle.setForeground(new Color(27,0,159));



        JLabel searchCat = new JLabel();
        searchCat.setText("Search by Category/Subcategory:");
        searchCat.setFont(new Font("Arial", Font.BOLD, 18));
        searchCat.setForeground(new Color(27,0,159));
        searchCat.setBounds(350, 65, 300, 40);


        searchBar = new JTextField();
        // int searchBarWidth = 250;
        searchBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));//200,200,200
        searchBar.setBounds(500, 15, 390, 40);
        searchBar.setFont(new Font("Arial", Font.PLAIN, 20));


        ImageIcon originalIcon = new ImageIcon("src/images/search icon.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(70, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        searchButton = new JButton(scaledIcon);
        searchButton.setBackground(new Color(255,255,255 ));
        originalColor = searchButton.getBackground();
        searchButton.setBounds(910, 15, 80, 40); //60 40
        searchButton.setForeground(Color.BLACK);
        searchButton.addMouseListener(this);
        //searchButton.setText("Search");
        searchButton.setFont(new Font("Arial",Font.BOLD,14));
        searchButton.addActionListener(this);


        categories = new CategoriesMap();
        categoriesCombo = new JComboBox<>();
        categoriesCombo.addItem("Category");
        for (String category : categories.getCategories().keySet()) {
            categoriesCombo.addItem(category);
        }
        categoriesCombo.setBounds(650, 72, 190, 30);
        categoriesCombo.addActionListener(this);


        subCategory = new JComboBox();
        subCategory.addItem("SubCategory");
        subCategory.setBounds(850, 72, 190, 30);


        productsPanel.add(subCategory);
        productsPanel.add(categoriesCombo);
        productsPanel.add(searchCat);
        productsPanel.add(searchTitle);
        productsPanel.add(searchButton);
        productsPanel.add(searchBar);
        mainWindow.revalidate();
        mainWindow.repaint();
    }



    public void mouseEntered(MouseEvent e)
    {
        searchButton.setBackground(new Color(255,255,255));
    }

    public void mouseClicked(MouseEvent e)
    {
        searchButton.setBackground(Color.WHITE);
    }

    public void mousePressed(MouseEvent e)
    {

    }

    public void mouseReleased(MouseEvent e)
    {

    }

    public void mouseExited(MouseEvent e)
    {
        searchButton.setBackground(originalColor);
    }


    /**
     * This method is used to handle what each button will do when it is pressed.
     *
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e)
    {
        //COMBO BOX FOR CATEGORIES
        if (e.getSource() == categoriesCombo)
        {
            String cat = (String) categoriesCombo.getSelectedItem();
            if (cat != null && !cat.equals("Category"))
            {
                subCategory.removeAllItems();
                subCategory.addItem("SubCategory");
                String[] sub = categories.getValue(cat);
                for (String value : sub)
                {
                    subCategory.addItem(value);
                }
            }
            else
            {
                subCategory.removeAllItems();
                subCategory.addItem("SubCategory");
            }
        }

        //LOGOUT
        else if (e.getSource() == logOut)
        {
            for (ActionListener al : logOut.getActionListeners())
            {
                logOut.removeActionListener(al);
            }
            for (ActionListener al : shoppingCart.getActionListeners())
            {
                shoppingCart.removeActionListener(al);
            }
            for (Window window : Window.getWindows())
            {
                window.dispose();
            }


            //mainWindow.dispose();
            try
            {
                CustomersFile customersFile = new CustomersFile();
                ManagersFile managersFile = new ManagersFile();
                OrdersFile ordersFile = new OrdersFile("src/Files/HistoryOfOrders.txt");
                CustomersMainScreen.counter = 0;
                SignInScreen in = new SignInScreen(customersFile, managersFile, listOfProducts, ordersFile);
            }
            catch (IOException ex)
            {
                throw new RuntimeException(ex);
            }
        }

        //COMBOBOX FOR SUB
        else if (e.getSource() == subCategory)
        {
            String selectedSubCategory = (String) subCategory.getSelectedItem();
        }

        //SEARCH BUTTON
        else if (e.getSource() == searchButton)
        {
            String title = searchBar.getText();
            String category = (String) categoriesCombo.getSelectedItem();
            String subCat = (String) subCategory.getSelectedItem();
            boolean foundProduct = false;

            ArrayList<JPanel> searchPanels = new ArrayList<>();

            if (title.equals("") && category.equals("Category"))
            {
                for(JPanel panel: panelOfProduct)
                {
                    searchPanels.add(panel);
                }

                foundProduct = true;
            }

            else if(!title.equals(""))
            {
                boolean found = false;
                for(JPanel panel:panelOfProduct)
                {
                    Products prod =(Products) panel.getClientProperty("product");
                    String name =  prod.getTitle();
                    if(name.toLowerCase().contains(title.toLowerCase()))
                    {
                        searchPanels.add(panel);
                        found = true;
                        //break;
                    }
                }

                if(found && !category.equals("Category"))
                {
                    if(subCat.equals("SubCategory"))
                    {
                        for (JPanel panel : panelOfProduct)
                        {
                            Products prod = (Products) panel.getClientProperty("product");
                            if (category.contains(prod.getCategory()) && !searchPanels.contains(panel))
                                searchPanels.add(panel);
                        }
                    }
                    else
                    {
                        for (JPanel panel : panelOfProduct)
                        {
                            Products prod = (Products) panel.getClientProperty("product");
                            if (category.contains(prod.getCategory()) && subCat.contains(prod.getSubcategory()))
                                searchPanels.add(panel);
                        }
                    }
                }
            }

            else if(!category.equals("Category"))
            {
                if(subCat.equals("SubCategory"))
                {
                    for (JPanel panel : panelOfProduct)
                    {
                        Products prod = (Products) panel.getClientProperty("product");
                        String cat = prod.getCategory();
                        if (category.contains(cat))
                        {
                            searchPanels.add(panel);
                        }
                    }
                }
                else
                {

                    for (JPanel panel : panelOfProduct)
                    {
                        Products prod = (Products) panel.getClientProperty("product");
                        String cat = prod.getCategory();
                        String sub = prod.getSubcategory();

                        if (category.contains(cat) && subCat.contains(sub))
                        {
                            searchPanels.add(panel);
                        }
                    }
                }
            }

            //showing the options in the customer
            if(searchPanels.size()!=0)
            {

                ButtonGroup buttonGroup = new ButtonGroup();
                ArrayList<JRadioButton> radioButtons = new ArrayList<>();

                int i =0;
                for (JPanel panel : searchPanels)
                {
                    Products product = (Products) panel.getClientProperty("product");

                    JRadioButton productRadioButton = new JRadioButton();
                    productRadioButton.setText(product.getTitle());
                    productRadioButton.putClientProperty("product",product);
                    radioButtons.add(productRadioButton);
                }
                SearchWindow searchWindow = new SearchWindow(radioButtons,panelOfProduct);
            }
            else if(!foundProduct)
            {
                JOptionPane.showMessageDialog(null,"We did not find a products matching your info :(","No result",JOptionPane.PLAIN_MESSAGE);
            }
        }

    }
}