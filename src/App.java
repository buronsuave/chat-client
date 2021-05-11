import java.io.IOException;
import java.net.Socket;

import gui.LoginScreen;
import gui.ChatScreen;
import gui.ContactsScreen;
import threads.ReaderThread;

public class App 
{
    public static void main(String[] args) throws Exception 
    {
        try 
        {
            // Creates the socket and screens
            Socket socket = new Socket("192.168.0.3", 1234);
            LoginScreen loginScreen = new LoginScreen(socket);
            ContactsScreen contactsScreen = new ContactsScreen(socket);
            ChatScreen chatScreen = new ChatScreen("Default", "David");

            // Create reader thread
            ReaderThread reader = new ReaderThread(socket, loginScreen);
            reader.start();

            // Launches login screen and wait for result (change status)
            loginScreen.setVisible(true);

            while (loginScreen.getStatus() != 1)
            { 
                Thread.sleep(500);
            }

            System.out.println("Login success");
            loginScreen.setVisible(false);
            contactsScreen.setVisible(true);
            chatScreen.setVisible(true);
        } 
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
