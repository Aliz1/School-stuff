package Controller;

import Client.WhacAClient;
import Model.Nodes;
import Server.WhacAServer;
import View.MainFrame;
import View.PanelCenter;
import View.PanelCenterEast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.DefaultListModel;

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
        System.out.println(nodes  + "line 56\n");
        view.onlineUpdate(nodes);
    }

    public void updateOfflineMK(LinkedList<Nodes> nodes)
    {
       System.out.println(panelCenterEast+ " line 62\n"); 
        panelCenterEast.updateOfflineMK(nodes);
    }


}
