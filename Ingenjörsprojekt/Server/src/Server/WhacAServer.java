package Server;

import Controller.Controller;
import Model.Nodes;
import Model.Message;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class WhacAServer extends Thread implements Serializable {
    Controller contoller;
    ServerSocket serverSocket;
    BufferedReader bufferedReader;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    private HashMap<Nodes, ClientHandler> map = new HashMap<>();
    boolean done = false;
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
            bufferedReader.close();
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    class StartServer extends Thread {
        private int port;
        Nodes node;

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

                    ClientHandler ch = new ClientHandler(socket, node);
                    map.putIfAbsent(node, ch);
                    contoller.appendArea(Integer.toString(map.size()));

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
        private Socket Socket;
        private Nodes Nodes;

        ClientHandler(Socket socket, Nodes nodes) throws IOException {
            this.Socket = socket;
            this.Nodes = nodes;


            bufferedReader = new BufferedReader(new InputStreamReader(Socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(Socket.getOutputStream()));

            start();
        }

        @Override
        public void run() {
            while (true)
            {
                try
                {
                    String message = bufferedReader.readLine();
                    contoller.appendArea(message);
                    if(message!= null)
                    {
                        String[] split = message.split(" ");
                        Message msg = new Message(split[0], Nodes);
                        sendMessage(msg);
                    }
                    
                    
                }

                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        }
        public void sendMessage(Message msg) throws IOException {
            bufferedWriter.write(msg.getInfo());
            bufferedWriter.flush();
            System.out.println(msg.getInfo());
        }
    }
}


