package Server;

import Controller.Controller;


import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class WhacAServer extends Thread implements Serializable {
    Controller contoller;
    ServerSocket serverSocket;

    public WhacAServer(Controller controller){
        this.contoller = controller;
        try
        {
            new StartServer(Integer.parseInt(JOptionPane.showInputDialog(null, "Välj port"))).start();
        }
        catch (Exception e)
        {
            controller.replaceArea("Failed to start server\nPort unavailable");
        }


    }
    public void StopServer()
    {
        try
        {
            serverSocket.close();
            contoller.replaceArea("Server closed");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    class StartServer extends Thread {
        private int port;

        StartServer(int port) {
            this.port = port;
        }

        @Override
        public void run() {
            try
            {
                Socket socket;
                serverSocket = new ServerSocket(port);
                contoller.replaceArea("Server Started");

                while (true)
                {
                    socket = serverSocket.accept();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String who = bufferedReader.readLine();
                    contoller.increaseNodes();
                    contoller.appendArea("IP: " + socket.getInetAddress() + " Number of nodes: " + contoller.getNumOfNodes());

                    //ClientHandler clientHandler = new ClientHandler(socket);
                }

            }
            catch (IOException e)
            {
                System.out.println("Server closed" );
            }

        }
    }
    class ClientHandler extends Thread {
        private BufferedReader bufferedReader;
        private BufferedWriter bufferedWriter;
        private Socket socket;

        ClientHandler(Socket socket) throws IOException {
            this.socket = socket;


            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            start();
        }

        @Override
        public void run() {
            while (true)
            {
                try
                {
                    String message = bufferedReader.readLine();
                    //String[] split = message.split(" ");

                }

                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        }
        public void sendMessage() throws IOException
        {
            bufferedWriter.write("something");
            bufferedWriter.flush();
            System.out.println("Something");

        }
    }
}


