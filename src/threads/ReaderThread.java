package threads;

import java.io.IOException;
import java.net.Socket;

import static javax.swing.JOptionPane.showMessageDialog;

import org.json.JSONException;
import org.json.JSONObject;

import data.User;
import gui.LoginScreen;

public class ReaderThread extends Thread
{
    private Socket socket;
    private LoginScreen loginScreen;

    public ReaderThread(Socket socket, LoginScreen loginScreen)
    {
        this.socket = socket;
        this.loginScreen = loginScreen;
    }

    @Override
    public void run()
    {
        byte[] data = new byte[2048];

        while (true)
        {
            try 
            {
                socket.getInputStream().read(data);
                String input = new String(data);
                int code = getCode(input);

                switch(code)
                {
                    // Exception error
                    case 1:
                    {
                        String error = getError(input);
                        showMessageDialog(null, error);
                    }

                    // Auth success
                    case 2:
                    {
                        getUser(input);
                        showMessageDialog(null, "Welcome to ChatMe, " + User.get().getName());
                        loginScreen.setStatus(1);
                        System.out.println(loginScreen.getStatus());
                    }
                }
            } 
            catch (IOException e) 
            {

            } 
            catch (JSONException e) 
            {

            }
        }
    }

    private int getCode(String input) throws JSONException
    {
        JSONObject json = new JSONObject(input);
        return Integer.parseInt(json.getString("code"));        
    }

    private String getError(String input) throws JSONException
    {
        JSONObject json = new JSONObject(input);
        return json.getString("error");
    }

    private void getUser(String input) throws JSONException
    {
        JSONObject json = new JSONObject(input);

        int id = Integer.parseInt(json.getString("id"));
        String name = json.getString("name");
        String pass = json.getString("pass");

        User.get().setId(id);
        User.get().setName(name);
        User.get().setPass(pass);
    }
}