package Server;

import Controller.Controller;
import Model.Nodes;



import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;


public class WhacAServer extends Thread {

    //Instansvariabler.

    Controller controller;

    private LinkedList<ClientHandler> clients = new LinkedList<ClientHandler>();

    ServerSocket serverSocket;
    Socket socket;
    ClientHandler ch;

    String mac;
    String difficulty;
    String mmrString;

    String collectionOfMacAdresses = "";
    String lastTwoMac = "";


    public LinkedList<Nodes> onlineClientList;
    public LinkedList<Nodes> offlineClientList;
    public LinkedList<Nodes> viewList = new LinkedList<>();

    Nodes node;
    Nodes viewNode;

    
    // Used to send a message from server to clients. 
    public void ControllerBroadcast()
    {
        broadcast("B8:F0:09:CC:45:F1//","10//Difficulty/3000\n");
    }

    //This method is called when a message is taken from one of the nodes. 
    //The method broadcasts the message to all nodes except the one who sent it
    public void broadcast(String MacBroadcast, String MSGBroadcast)
    {
            int i = 0;
            ClientHandler tempNode;
            controller.appendArea(MacBroadcast + MSGBroadcast + "\nFrom server!!!");
           // System.out.println(MacBroadcast + MSGBroadcast);
            //System.out.println(clientList.get(0).getId());
            for(i = 0; i < onlineClientList.size(); )
            {
                
               // System.out.println(!onlineClientList.get(i).getMac().equals(MacBroadcast));
               // System.out.println(onlineClientList.get(i).getMac());
                //System.out.println(MacBroadcast);
                if (!onlineClientList.get(i).getMac().equals(MacBroadcast))
                {
                    tempNode = clients.get(i);
                    tempNode.outputStream.write(MacBroadcast + MSGBroadcast);
                    tempNode.outputStream.flush();
                }
                i++;
            }
            
    }

    public WhacAServer(Controller controller){
        this.controller = controller;
        

        try
        {
            new StartServer(8080).start();
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
            clients.clear();
            onlineClientList.clear();
            controller.updateOnlineMKController(onlineClientList);
            
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
            onlineClientList = new LinkedList<>();
            offlineClientList = new LinkedList<>();
            
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
                    controller.appendArea(who);
                   // System.out.println(who);
                    collectionOfMacAdresses = "";

                    mac = split[0];
                    String[] macSplit = mac.split(":");
                    lastTwoMac = macSplit[5];
                    mmrString =  "1000";
                    
                    if (split.length >=2 ) 
                    {
                        difficulty = split[1];
                        
                    }else difficulty = "Not sent";
                    

                   

                    node = new Nodes(mac , difficulty , mmrString );
                    viewNode = new Nodes(lastTwoMac , difficulty , mmrString );
                    ch = new ClientHandler(socket);
                    onlineClientList.add(node);
                    viewList.add(viewNode);
                    clients.add(ch);
                    controller.updateOnlineMKController(viewList);
                    controller.appendArea("Node with mac: " + mac + " Connected to the server");
                    
                        
                    for (int i = 0; i < onlineClientList.size() ; i++)
                    {
                        if(collectionOfMacAdresses.contains(onlineClientList.get(i).getMac()))
                        {
                            //Do nothing
                        }
                        else
                        {
                            collectionOfMacAdresses += "//" + onlineClientList.get(i).getMac() ;
                        }
                        
                    }
                        broadcast("Connected mac addresses", collectionOfMacAdresses);
                        System.out.println(collectionOfMacAdresses);
                    
                     
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
        private Socket socket;

        private BufferedReader inputStream;
        private PrintWriter outputStream;

       

        public ClientHandler(Socket Socket) throws IOException
	{
		this.socket = Socket;
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

                    String[] splitInClientHandler = message.split("//");
                    String[] splitInClientHandler2 = message.split("/");
                    
                    mac = splitInClientHandler[0];
                    String[] macSplitInClientHandler = mac.split(":");
                    mmrString = splitInClientHandler2[3];
                    lastTwoMac = macSplitInClientHandler[5];
                    String msg = splitInClientHandler[1];
                    System.out.println(mac);
                    
                    for (int i = 0; i < viewList.size(); i++) 
                    {
                        if (lastTwoMac.equals(viewList.get(i).getMac())) 
                        {
                            System.out.println("Hello darkness my old friend!");
                            viewList.get(i).setMmr(mmrString);
                            controller.updateOnlineMKController(viewList);
                        }   
                    }

                    if(message.contains("Disconnected"))
                    {
                        
                        for(int i = 0; i < onlineClientList.size() ; i++)
                        {
                            
                            if (onlineClientList.get(i).getMac().equals(msg)) 
                            {
                                
                                offlineClientList.add(viewList.get(i));
                                //broadcast(mac, "//" +msg + "//Disconnected");
                                controller.appendArea("Sent from: "+ mac + "  " + msg+ "Disconnected\n");
                                onlineClientList.remove(i);
                                viewList.remove(i);
                                clients.get(i).socket.close();
                                clients.remove(i);

                                controller.updateOnlineMKController(viewList);
                                controller.updateOfflineMK(viewList);
                                
                                
                            }
                        }
                    }
                    else if (message.contains("starting")) 
                    {
                        //Do nothing
                    }
                    else
                    {
                        broadcast(mac,"//"+msg+"//Difficulty");
                        controller.appendArea("Sent from: "+ mac + " Message: " + msg);
                    }


                    
                }

                catch (Exception e) 
                {
                }
            }

    }
        

    }
}


