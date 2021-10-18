package Controller;

import Client.WhacAClient;
import Model.Nodes;
import Server.WhacAServer;
import View.MainFrame;
import View.PanelCenter;
import View.PanelCenterEast;

import java.io.IOException;
import java.util.LinkedList;

public class Controller
{
    MainFrame view;
    Nodes nodes;
    WhacAServer server;
    WhacAClient client1;
    PanelCenter panelCenter;
    PanelCenterEast panelCenterEast;
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
    
    public void updateOnlineMKController(LinkedList<Nodes> nodes)
    {
        view.onlineUpdate(nodes);
    }

    public void updateOfflineMK(LinkedList<Nodes> nodes)
    {
        panelCenterEast.updateOfflineMK(nodes);
    }


}
