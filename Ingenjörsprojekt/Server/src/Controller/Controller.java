package Controller;

import Client.WhacAClient;
import Model.Nodes;
import Server.WhacAServer;
import Server.WhacAServer.ClientHandler;
import View.MainFrame;

import java.io.IOException;
import java.net.Socket;

public class Controller
{
    MainFrame view;
    Nodes nodes;
    WhacAServer server;
    WhacAClient client1;
    public Controller() throws IOException {
        view = new MainFrame(this);

    }
    public void replaceArea(String msg)
    {
        view.replaceArea(msg);
    }
    public void appendArea(String msg)
    {
        view.appendArea(msg);
    }

    public void startServer() throws IOException
    {
        server = new WhacAServer(this);
        
    }
    public void stopServer()
    {
        server.StopServer();
    }
    public void startClient()
    {
        client1 = new WhacAClient(this);
    }
    public void broadcast()
    {
        server.ControllerBroadcast();
        
    }
}
