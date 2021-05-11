package gui;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.json.JSONObject;     

public class LoginScreen extends JFrame implements ActionListener
{
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private JLabel nameLabel, passLabel;
    private JTextField nameBox, passBox;
    private JButton loginButton, signupButton;

    private int status;

    private Socket socket;

    public LoginScreen(Socket socket)
    {
        super("Welcome to ChatMe!");
        status = 0;
        init(socket);
    }

    private void init(Socket socket)
    {
        // General screen options
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        // Controllers
        nameLabel = new JLabel("Username");
        nameLabel.setLocation(100, 100);
        nameLabel.setSize(100, 25);
        add(nameLabel);

        passLabel = new JLabel("Password");
        passLabel.setLocation(100, 150);
        passLabel.setSize(100, 25);
        add(passLabel);

        nameBox = new JTextField();
        nameBox.setBounds(200, 100, 100, 30);
        add(nameBox);

        passBox = new JTextField();
        passBox.setBounds(200, 150, 100, 30);
        add(passBox);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 250, 100, 30);
        loginButton.addActionListener(this);
        add(loginButton);

        signupButton = new JButton("Signup");
        signupButton.setBounds(250, 250, 100, 30);
        signupButton.addActionListener(this);
        add(signupButton);

        // Socket configuration
        this.socket = socket;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if (actionEvent.getSource() == loginButton)
        {
            System.out.println("Login Click");
            try 
            {
                onClickLogin();
            } 
            catch (IOException e) 
            {
                System.out.println(e.getMessage());
            }
        } 
        else if (actionEvent.getSource() == signupButton)
        {
            System.out.println("Signup Click");
            try 
            {
                onClickSignup();
            } catch (IOException e) 
            {
                System.out.println(e.getMessage());
            }
        }
    }

    private void onClickLogin() throws IOException
    {
        String name = nameBox.getText();
        String pass = passBox.getText();

        Map<String, String> map = new HashMap<>();
        map.put("op", "1");
        map.put("name", name);
        map.put("pass", pass);

        JSONObject json = new JSONObject(map);
        String jsonString = json.toString();
        socket.getOutputStream().write(jsonString.getBytes());
    }

    private void onClickSignup() throws IOException
    {
        String name = nameBox.getText();
        String pass = passBox.getText();

        Map<String, String> map = new HashMap<>();
        map.put("op", "2");
        map.put("name", name);
        map.put("pass", pass);

        JSONObject json = new JSONObject(map);
        String jsonString = json.toString();
        socket.getOutputStream().write(jsonString.getBytes());
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
