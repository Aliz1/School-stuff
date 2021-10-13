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
    String difficulty;
    Nodes currentNode;
    Nodes nextNode;
    boolean sentToFirstElement = false;
    boolean nextNodeChanged = false;

    private HashMap<Nodes, ClientHandler> map = new HashMap<>();
    private LinkedList<Nodes> clientList;

    Nodes node;

    

    public void ControllerBroadcast()
    {
        broadcast("Server","//Sending from server button !\n");
    }

    public void broadcast(String IDB, String MSGB)
    {
            int i = 0;
            ClientHandler tempNode;
            for(i = 0; i < clientList.size(); )
            {
                if (!clientList.get(i).getId().equals(IDB))
                {
                    System.out.println(IDB + " Line 66");
                   //System.out.println( clientList.get(i).getId() + " Line 65");
                    tempNode = clients.get(i);
                    tempNode.outputStream.write(MSGB);
                    tempNode.outputStream.flush();
                }
                i++;
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
                    if (split.length >=2 ) 
                    {
                        difficulty = split[1];
                    }else difficulty = "Not sent";
                    

                        node = new Nodes(id , difficulty);

                    
                    ch = new ClientHandler(socket);
                    clientList.add(node);
                    clients.add(ch);
                    /*for(int i = 0; i < clientList.size(); i++)
                    {
                        System.out.println(clientList.get(i).toString()); 
                    }*/
                   // broadcast(id, difficulty);
                    //System.out.println("Broadcasted line 167\n");
                    
                     
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
            
            while (true) 
            {
        
                try 
                {
                    
                    message = inputStream.readLine();
                   String[] splitInclient = message.split("//");
                    id = splitInclient[0];
                    String msg = splitInclient[1];
                    //System.out.println(id);
                    broadcast(id,msg);
                    controller.appendArea(msg);
                }

                catch (Exception e) 
                {
                }
            }

    }
        

    }
}


