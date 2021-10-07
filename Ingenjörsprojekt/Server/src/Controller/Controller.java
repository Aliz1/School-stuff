package Controller;

import Client.WhacAClient;
import Model.Nodes;
import Server.WhacAServer;
import View.MainFrame;

import java.io.IOException;
import java.net.Socket;

public class Controller
{
    MainFrame view;
    Nodes nodes;
    WhacAServer server;
    WhacAClient client;
    public Controller() throws IOException {
        view = new MainFrame(this);
        nodes = new Nodes(this);

    }
    public void replaceArea(String msg)
    {
        view.replaceArea(msg);
    }
    public void appendArea(String msg)
    {
        view.appendArea(msg);
    }

    public int getNumOfNodes()
    {
        return nodes.getNumOfNodes();
    }
    public void increaseNodes()
    {
        nodes.IncreaseNodes();
    }
    public void resetNodes()
    {
        nodes.ResetNodes();
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
        client = new WhacAClient(this);
    }

    public void sendMessageToServer(String msg) throws IOException
    {
      client.sendMessageToServer(msg);
    }
}
