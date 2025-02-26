package gui;


import api.OrdersFile;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Locale;

public class HistoryScreen
{
    private String username;
    private OrdersFile ordersFile;
    JFrame historyWindow = new JFrame();

    public HistoryScreen(String username, OrdersFile ordersFile) throws IOException {
        this.username=username;
        this.ordersFile = ordersFile;
        historyWindow.setSize(500,500);
        historyWindow.setResizable(true);
        historyWindow.setTitle(username + "'s Order's History");
        historyWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        historyWindow.setVisible(true);
        setLabel();
    }

    public void setLabel() throws IOException
    {
        JTextArea customerOrders = new JTextArea();
        customerOrders.setBounds(20, 20, 400, 500);
        customerOrders.setEditable(false);
        customerOrders.setLineWrap(true);
        customerOrders.setWrapStyleWord(true);
        customerOrders.setBackground(new Color(199, 223, 252));

        String orders = ordersFile.getOrders(username).replace(username,"");
        String[] splitOrders = orders.split("New order");
        StringBuilder finalText = new StringBuilder();


        for (String order : splitOrders) {
            finalText.append(order.trim());
            finalText.append("\n\n");
        }

        customerOrders.setText(finalText.toString());


        historyWindow.add(customerOrders);
        setScrollPane(customerOrders);
    }

    public void setScrollPane(JTextArea customerOrders)
    {
        JScrollPane scroll=new JScrollPane(customerOrders);
        historyWindow.setPreferredSize(new Dimension(400,500));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(30,0,10,30);

        historyWindow.add(scroll);
        SwingUtilities.invokeLater(() ->
        {
            scroll.getViewport().setViewPosition(new Point(0, 0));
        });
    }


}
