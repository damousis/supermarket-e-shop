package gui;

import api.Products;
//import gui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchWindow implements ActionListener
{

    JFrame searchFrame;
    JButton selectButton;
    ButtonModel selectedModel;
    ArrayList<JRadioButton> radioButtons;
    ArrayList<JPanel> panelOfProduct;
    JPanel radioPanel;

    public SearchWindow(ArrayList<JRadioButton> radioButtons, ArrayList<JPanel> panelOfProduct)
    {
        this.radioButtons = radioButtons;
        this.panelOfProduct = panelOfProduct;

        searchFrame = new JFrame();
        searchFrame.setResizable(false);
        searchFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        searchFrame.setSize(500, 500);
        searchFrame.setLayout(null);
        searchFrame.getContentPane().setBackground(new Color(221, 235, 250));
        searchFrame.setTitle("Search Results");

        JLabel message = new JLabel("Which product would you like to see?");
        message.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        message.setBounds(20, 5, 350, 60);
        searchFrame.add(message);

        // Panel to hold radio buttons
        radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
        radioPanel.setBackground(new Color(221, 235, 250));

        ButtonGroup group = new ButtonGroup();


        for (JRadioButton button : radioButtons)
        {
            group.add(button);
            button.setFont(new Font("Arial", Font.BOLD, 17));
            button.setBackground(new Color(221, 235, 250));
            radioPanel.add(button);
        }

        JScrollPane scrollPane = new JScrollPane(radioPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBounds(20, 60, 450, 300);
        searchFrame.add(scrollPane);

        selectButton = new JButton("Select");
        selectButton.setBounds(320, 390, 150, 40);
        selectButton.setFont(new Font("Arial", Font.BOLD, 15));
        searchFrame.add(selectButton);
        selectButton.addActionListener(this);

        // Show the frame
        searchFrame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == selectButton)
        {
            ButtonGroup group = new ButtonGroup();
            for (JRadioButton button : radioButtons)
            {
                if (button.isSelected())
                {
                    String selectedText = button.getText();
                    Products product = (Products) button.getClientProperty("product");

                    for (JPanel panel : panelOfProduct) {
                        Products pr = (Products) panel.getClientProperty("product");
                        if (pr.getTitle().equals(product.getTitle())) {
                            showResult(panel);
                            return;
                        }
                    }
                }
            }
        }
    }

    private void showResult(JPanel panel)
    {
        searchFrame.getContentPane().removeAll();

        JLabel message = new JLabel();
        message.setText("The product you were looking for is:");
        message.setBounds(40,20,340,30);
        message.setFont(new Font("Arial",Font.ITALIC | Font.BOLD,19));
        JPanel copiedPanel = copy(panel);
        copiedPanel.setBackground(Color.WHITE);

        searchFrame.add(message);
        searchFrame.add(copiedPanel);

        int n = MainWindow.pictureNames.length;
        Products products = (Products) panel.getClientProperty("product");
        String productsName = products.getTitle();

        int index = -1;
        for(int i=0;i<n;i++)
        {
            String path;
            if(MainWindow.pictureNames[i].contains("jpg"))
            {
                path = MainWindow.pictureNames[i].replace(".jpg", "").trim();

            }

            else
                path = MainWindow.pictureNames[i].replace(".png","").trim();

            if(productsName.toLowerCase().contains(path.toLowerCase()))
            {

                index =i;
                break;
            }
        }

        if(index!=-1)
        {
            Image originalImage = new ImageIcon("src/images/"+MainWindow.pictureNames[index]).getImage();
            Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(scaledImage);
            JLabel image =new JLabel(imageIcon);
            image.setBounds(120,50,100,100);

            copiedPanel.add(image);
        }

        searchFrame.repaint();
        searchFrame.revalidate();
    }

    private JPanel copy(JPanel originalPanel)
    {
        JPanel helper = originalPanel;
        JPanel copied = new JPanel();
        Products product =(Products) originalPanel.getClientProperty("product");

        JTextField helpField = new JTextField();

        copied.setBounds(40,70,400,370);
        copied.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        copied.setLayout(null);

        for(Component component:helper.getComponents())
        {
            if(component instanceof JLabel)
            {
                JLabel label = (JLabel) component;
                JLabel copyLabel = new JLabel();
                copyLabel.setBounds(label.getBounds());
                copyLabel.setFont(label.getFont());
                copyLabel.setText(label.getText());
                if(copyLabel.getText().equals("OUT OF STOCK"))
                    copyLabel.setForeground(Color.RED);
                copied.add(copyLabel);
            }
            else if(component instanceof JTextArea)
            {
                JTextArea area = (JTextArea) component;
                JTextArea copyArea = new JTextArea();
                copyArea.setBounds(area.getBounds());
                copyArea.setText(area.getText());
                copyArea.setFont(area.getFont());
                copyArea.setLineWrap(true);
                copyArea.setWrapStyleWord(true);
                copyArea.setEditable(false);
                copyArea.setOpaque(false);
                copied.add(copyArea);
            }
            else if(component instanceof JTextField)
            {
                JTextField field = (JTextField) component;
                JTextField copyField = new JTextField();
                copyField.setBounds(field.getBounds());
                copyField.setText(field.getText());
                helpField = copyField;
                copied.add(copyField);
            }
            else if(component instanceof JButton)
            {
                JButton button = (JButton) component;
                JButton copyButton = new JButton();
                copyButton.setText(button.getText());
                copyButton.setBounds(button.getBounds());
                copyButton.setFont(button.getFont());
                copyButton.putClientProperty("product",product);
                copyButton.putClientProperty("productsInfo",product);

                if(button.getText().equals("+"))
                    copyButton.putClientProperty("type","+");

                else if(button.getText().equals("-"))
                    copyButton.putClientProperty("type","-");

                else if(button.getText().equals("Add To Card"))
                    copyButton.putClientProperty("type","add");

                else if(button.getText().equals("Edit Product"))
                    copyButton.putClientProperty("panel",originalPanel);

                copyButton.addActionListener(button.getActionListeners()[0]);
                copied.add(copyButton);
            }
        }

        for(Component component:copied.getComponents())
        {
            if(component instanceof JButton)
            {
                JButton button = (JButton) component;
                button.putClientProperty("TextField",helpField);
            }
        }
        return copied;
    }

}
