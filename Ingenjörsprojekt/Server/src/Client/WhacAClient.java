package Client;

import Controller.Controller;

import java.net.*;

import javax.swing.JOptionPane;

import java.io.*;

/**
 */
public class WhacAClient {
    Controller controller;
    String hostname = "127.0.0.1";
    int port = 8080;
    PrintWriter writer;
    Socket clientHandler;
    public WhacAClient(Controller controller)
    {
        this.controller = controller;
        String mac = JOptionPane.showInputDialog("Mac adress: ");
        setupClient(mac);
    }

    public void setupClient(String mac)   
    {

        
        try (Socket clientsSocket = new Socket(hostname,port); PrintWriter writer = new PrintWriter(new OutputStreamWriter(clientsSocket.getOutputStream())); )
        {
            writer.println(mac + "//" + 10 + "/1000");
            writer.flush();
            
        } catch (UnknownHostException ex) {

            System.out.println("Server not found: ");

        } catch (IOException ex) {

            System.out.println("I/O error: ");
        }
    }

}