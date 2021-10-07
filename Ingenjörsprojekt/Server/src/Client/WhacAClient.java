package Client;

import Controller.Controller;

import java.net.*;
import java.io.*;

/**
 * This program is a socket client application that connects to a time server
 * to get the current date time.
 *
 * @author www.codejava.net
 */
public class WhacAClient {
    Controller controller;
    String hostname = "192.168.43.214";
    int port = 40000;
    BufferedWriter writer;
    Socket clientHandler;
    public WhacAClient(Controller controller)
    {
        this.controller = controller;
        setupClient();
    }

    public void setupClient()
    {
      //  String hostname = "127.0.0.1";
       // int port = 13;

        
        try (Socket clientsSocket = new Socket(hostname,port);) {
            

            
            
            
            
        } catch (UnknownHostException ex) {

            System.out.println("Server not found: ");

        } catch (IOException ex) {

            System.out.println("I/O error: ");
            ex.printStackTrace();
        }
    }

    public void sendMessageToServer(String message) throws IOException
    {
        writer = new BufferedWriter(new OutputStreamWriter(clientHandler.getOutputStream()));
        writer.write(message + "\n");
        writer.flush();
    }

}