package Server;

import Controller.Controller;
import Model.Nodes;
import Model.Message;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;


public class WhacAServer extends Thread implements Serializable {
    Controller controller;

    ServerSocket serverSocket;

    ObjectOutputStream oos;
    ObjectInputStream ois;

    private HashMap<Nodes, ClientHandler> map = new HashMap<>();
    private ArrayList<Nodes> clientList;

    Nodes node;

    public WhacAServer(Controller controller){
        this.controller = controller;

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
            controller.replaceArea("Server closed");
            //bufferedReader.close();
            
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    class StartServer extends Thread
     {
        private int port;
        
        

        StartServer(int port)
        {
            this.port = port;
            clientList = new ArrayList<Nodes>();
        }

        @Override
        public void run() {
            try
            {
                Socket socket;
                serverSocket = new ServerSocket(port);
                
                controller.replaceArea("Server Started");

                while (true)
                {
                    socket = serverSocket.accept();
                   
                    System.out.println("Client connected");
                    node = new Nodes("Node");
                    clientList.add(node);
                    System.out.println(clientList.size());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("Hello Client");
                   String who = bufferedReader.readLine();
                    controller.appendArea(who);
                    //System.out.println(who.toString());
                 // ClientHandler ch = ClientHandler(socket, node);
                // String[] split = who.split(" ");
                    

                    
                    
                     
                   // clientList.add(node);
                    //System.out.println(clientList.contains(node));
                    //ClientHandler ch = new ClientHandler(socket, node);
                    //map.putIfAbsent(node, ch);
                    //controller.appendArea(Integer.toString(map.size()));
                    
                    //System.out.println("IP :" + socket.getInetAddress() + " Sensortype: " + node.getClass().getSimpleName() + " Number of nodes: " + map.size() + " NodeID: " + node.setID(Integer.toString(map.size())));
                    //controller.appendArea("IP: " + socket.getInetAddress() + " Number of nodes: " + map.size());

                    //ClientHandler clientHandler = new ClientHandler(socket);
                }

            }

            catch (IOException e)
            {
                System.out.println("Server closed" );
            }

        }
    }
    class ClientHandler extends Thread 
    {
        private BufferedReader bufferedReader;
        private BufferedWriter bufferedWriter;
        private Socket Socket;
        private Nodes Nodes;

        ClientHandler(Socket socket, Nodes nodes) throws IOException 
        {
            this.Socket = socket;
            this.Nodes = nodes;


           bufferedReader = new BufferedReader(new InputStreamReader(Socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(Socket.getOutputStream()));
            System.out.println("Hello world!");
            start();
        }

        @Override
        public void run() 
        {
            while (true)
            {
                try
                {
                    String message = bufferedReader.readLine();
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
        public void sendMessage(Message msg) throws IOException 
        {
            bufferedWriter.write(msg.getInfo());
            bufferedWriter.flush();
            System.out.println(msg.getInfo());
        }
    }
}


