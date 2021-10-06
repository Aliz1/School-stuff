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
public class Client {
    Controller controller;
    public Client(Controller controller)
    {
        this.controller = controller;
        setupClient();
    }

    public void setupClient()
    {
        String hostname = "127.0.0.1";
        int port = 13;

        try (Socket socket = new Socket(hostname, port)) {

            InputStream input = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);
            //controller.updateView("Hello World I am a client!");
        } catch (UnknownHostException ex) {

            System.out.println("Server not found: ");

        } catch (IOException ex) {

            System.out.println("I/O error: ");
            ex.printStackTrace();
        }
    }

}