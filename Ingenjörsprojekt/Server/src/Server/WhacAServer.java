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
import java.util.TimerTask;


public class WhacAServer extends Thread implements Serializable {
    Controller controller;

    private LinkedList<String> IDs = new LinkedList<String>();

    private LinkedList<ClientHandler> clients = new LinkedList<ClientHandler>();
    ListIterator<Nodes> iterator; 


    ServerSocket serverSocket;

    ObjectOutputStream oos;
    ObjectInputStream ois;

    Socket socket;

    ClientHandler ch;
    String id;
    Nodes currentNode;
    Nodes nextNode;
    boolean sentToFirstElement = false;
    boolean nextNodeChanged = false;

    private HashMap<Nodes, ClientHandler> map = new HashMap<>();
    private LinkedList<Nodes> clientList;

    Nodes node;

    

    public void ControllerBroadcast()
    {
        broadcast("Server", "Hello I am sending from button!");
    }

    public void broadcast(String ID, String MSG)
    {
            
            iterator = clientList.listIterator();
            ClientHandler tempNode;

            for(int i = 0; i < clientList.size(); i++)
            {
                if (clientList.get(i).getId() != ID)
                {
                    tempNode = clients.get(i);
                    tempNode.outputStream.write(MSG);
                    tempNode.outputStream.flush();
                }
            }
            
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
            stop();
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
                    
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    String who = bufferedReader.readLine();
                    String[] split = who.split("//");
                    id = split[0];
                    String difficulty;
                    if (split.length >=2 ) 
                    {
                        difficulty = split[1];
                    }else difficulty = "Not sent";
                    

                        node = new Nodes(id , difficulty);

                    clientList.add(node);
                    ch = new ClientHandler(socket);
                    /*for(int i = 0; i < clientList.size(); i++)
                    {
                        System.out.println(clientList.get(i).toString()); 
                    }*/
                    broadcast(id, difficulty);
                    System.out.println("Broadcasted line 167\n");
                    
                     
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
                    System.out.println(id);
                    broadcast(id,message);
                    controller.appendArea(message);
                }

                catch (Exception e) 
                {
                   // e.printStackTrace();
                }
            }

    }
        

    }
}


