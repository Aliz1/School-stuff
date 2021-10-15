package Server;

import Controller.Controller;
import Model.Nodes;

import javax.swing.*;



import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;


public class WhacAServer extends Thread implements Serializable {

    //Instansvariabler.

    Controller controller;

    private LinkedList<ClientHandler> clients = new LinkedList<ClientHandler>();

    ServerSocket serverSocket;
    Socket socket;
    ClientHandler ch;

    String id;
    String difficulty;


    public LinkedList<Nodes> clientList;

    Nodes node;

    
    // Used to send a message from server to clients. 
    public void ControllerBroadcast()
    {
        broadcast("Server","1200//Difficulty\n");
    }

    //This method is called when a message is taken from one of the nodes. 
    //The method broadcasts the message to all nodes except the one who sent it
    public void broadcast(String IDB, String MSGB)
    {
            int i = 0;
            ClientHandler tempNode;
            //System.out.println(clientList.get(0).getId());
            for(i = 0; i < clientList.size(); )
            {
                if (!clientList.get(i).getId().equals(IDB))
                {
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
            if(socket!= null)
            {
                socket.close();
            }
            
            stop();
            
            
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
            clientList = new LinkedList<>();
            
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

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String who = bufferedReader.readLine();
                    String[] split = who.split("//");

                    id = split[0];
                    
                    if (split.length >=2 ) 
                    {
                        difficulty = split[1];
                    }else difficulty = "Not sent";
                    

                        node = new Nodes(id , difficulty);

                    
                    ch = new ClientHandler(socket);
                    //node = new Nodes("11", "Sir");
                    clientList.add(node);
                    System.out.println(clientList);
                    controller.updateOnlineMKController(clientList);

                    
                  //  clients.add(ch);
                   // broadcast("servern",  Integer.toString(clientList.size()) + "//Number Of Nodes");
                    controller.appendArea("Node with mac: " + id + " Connected to the server");
                    System.out.println(clientList.size());
                     
                }
                

            }

            catch (IOException e)
            {
                controller.replaceArea("Server closed" );
            }

        }
       
    }

    public class ClientHandler extends Thread 
    {
        //Uses a list of strings for currently used ID's to keep them  unique.
       

        private Socket socket;

        private BufferedReader inputStream;
        private PrintWriter outputStream;

       

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

                    broadcast(id,msg+"//Difficulty");

                    controller.appendArea("Sent from: "+ id + " Message: " + msg);
                }

                catch (Exception e) 
                {
                }
            }

    }
        

    }
}


