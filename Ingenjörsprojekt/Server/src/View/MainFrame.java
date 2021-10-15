package View;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;
import Controller.*;
import Model.Nodes;


public class MainFrame extends JFrame
{
    MainPanel panel;
    public static final int  width = 600;
    public static final int  height = 520;
    Controller controller;
    public  MainFrame(Controller controller)
    {
        this.controller = controller;
        setupFrame();
    }
    public void setupFrame()
    {
        final int offsetX = width/5;
        final int offsetY = height / 5;
        setPreferredSize(new Dimension(width,height));
        setTitle("Server");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(offsetX,offsetY);
        //setResizable(false);
        panel = new MainPanel(controller);
        setContentPane(panel);
        setResizable(false);
        pack();
        setVisible(true);
        
    }

    public void replaceArea(String msg)
    {
        panel.getCenterPanel().replaceArea(msg);
    }
    public void appendArea(String msg)
    {
        panel.getCenterPanel().appendArea(msg);
    }

    public void onlineUpdate(LinkedList<Nodes> nodes)
    {
        panel.getCenterPanel().east.updateOnlineMK(nodes);
    }

}
