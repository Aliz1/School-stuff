package Client;

import Controller.Controller;

import java.net.*;
import java.io.*;

/**
 */
public class WhacAClient2 {
    Controller controller;
    String hostname = "192.168.43.214";
    int port = 40000;
    PrintWriter writer;
    Socket clientHandler;
    public WhacAClient2(Controller controller)
    {
        this.controller = controller;
        setupClient();
    }

    public void setupClient()
    { 
        try (Socket clientsSocket = new Socket(hostname,port); PrintWriter writer = new PrintWriter(new OutputStreamWriter(clientsSocket.getOutputStream()));)
        {
            int i = 2;
            writer.println("Hello I am client " + i + "\n");
            writer.flush();
            
        } catch (UnknownHostException ex) {

            System.out.println("Server not found: ");

        } catch (IOException ex) {

            System.out.println("I/O error: ");
            ex.printStackTrace();
        }
    }

}