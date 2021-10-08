package Server;

import Controller.Controller;
import Model.Nodes;
import Model.Message;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;


public class WhacAServer extends Thread implements Serializable {
    Controller controller;

    private LinkedList<String> IDs = new LinkedList<String>();

    private LinkedList<ClientHandler> clients = new LinkedList<ClientHandler>();
    ListIterator<ClientHandler> iterator; 


    ServerSocket serverSocket;

    ObjectOutputStream oos;
    ObjectInputStream ois;

    Socket socket;

    ClientHandler ch;

    private HashMap<Nodes, ClientHandler> map = new HashMap<>();
    private LinkedList<Nodes> clientList;

    Nodes node;

    public void ControllerBroadcast()
    {
        
           
            broadcast("Hello Per I am sending from button!");
       
        
    }

    public void broadcast(String message)
    {
            
      iterator = clients.listIterator();
            ClientHandler currentNode;
            while(iterator.hasNext())
            {
                currentNode = iterator.next();
                currentNode.outputStream.write(message);
                currentNode.outputStream.flush();
            }
            //  currentNode = clients.element();
            //System.out.println(message);
            
    }

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
            clientList = new LinkedList<Nodes>();
        }

        @Override
        public void run() {
            try
            {
                
                serverSocket = new ServerSocket(port);
                
                controller.replaceArea("Server Started");

                while (true)
                {
                    socket = serverSocket.accept();
                   
                    System.out.println("Client connected");
                    //node = new Nodes("Node");
                    //clientList.add(node);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    

                    
                    
                     
                    
                    //System.out.println(clientList.contains(node));
                     ch = new ClientHandler(socket);
                    //map.putIfAbsent(node, ch);
                    //controller.appendArea(Integer.toString(map.size()));
                    
                    //System.out.println("IP :" + socket.getInetAddress() + " Sensortype: " + node.getClass().getSimpleName() + " Number of nodes: " + map.size() + " NodeID: " + node.setID(Integer.toString(map.size())));
                    //controller.appendArea("IP: " + socket.getInetAddress() + " Number of nodes: " + map.size());

                    //ControllerBroadcast();

                    //clientHandler.broadcast("hej");
                }
                

            }

            catch (IOException e)
            {
                System.out.println("Server closed" );
            }

        }
       
    }

    public class ClientHandler extends Thread 
    {
        //Uses a list of strings for currently used ID's to keep them  unique.
       

        private Socket socket;

        private BufferedReader inputStream;
        private PrintWriter outputStream;

        String ID;

       

        public ClientHandler(Socket socket) throws IOException
	{
		this.socket = socket;
		inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outputStream = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

        start();
	}

        @Override
        public void run() 
        {
            String message;
            clients.add(this);
            while (true) 
            {
                
            
            try 
            {
                
                
               
                
                
                message = inputStream.readLine();
                broadcast(message);
              //  message = inputStream.readLine();
              controller.appendArea(message);
                
                
               // System.out.println(message);
                
              
            }

            catch (Exception e) 
            {
                //TODO: handle exception
            }
        }

    }
        

    }
}


