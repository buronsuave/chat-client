package gui;

import java.awt.BorderLayout;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class ContactsScreen extends JFrame
{
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private JScrollPane scrollPane;
    private JList<String> userListBox;

    private int status;

    private Socket socket;

    public ContactsScreen(Socket socket)
    {
        super("ChatMe");
        status = 0;
        init(socket);
    }

    private void init(Socket socket)
    {
        // General screen options
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);

        List<String> userList = new ArrayList<>();
        for (int index = 0; index < 100; index++) 
        {
           userList.add("List Item " + index);
        }
        userListBox = new JList<String>(userList.toArray(new String[userList.size()]));
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(userListBox);
        userListBox.setLayoutOrientation(JList.VERTICAL);
        add(scrollPane);

        this.socket = socket;
    }

    public int getStatus() 
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }
}
