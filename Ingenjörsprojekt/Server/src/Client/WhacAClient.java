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
    String hostname = "172.20.10.7";
    int port = 8080;
    PrintWriter writer;
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

        
        try (Socket clientsSocket = new Socket(hostname,port); PrintWriter writer = new PrintWriter(new OutputStreamWriter(clientsSocket.getOutputStream())); )
        {
            writer.println("00:11:22:33//10\n");
            writer.flush();
            
        } catch (UnknownHostException ex) {

            System.out.println("Server not found: ");

        } catch (IOException ex) {

            System.out.println("I/O error: ");
           // ex.printStackTrace();
        }
    }

    public void sendMessageToServer(String message) throws IOException
    {
        writer = new PrintWriter(new OutputStreamWriter(clientHandler.getOutputStream()));
        writer.write(message + "\n");
        writer.flush();
    }

}