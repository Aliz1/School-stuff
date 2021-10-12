package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PanelNorth extends JPanel implements ActionListener
{
    private JButton startButton;
    private JButton startClient;
    private JButton stopButton;
    private JButton broadcastMsgBtn;
    private final Controller controller;


    public PanelNorth(Controller controller)
    {
        this.controller = controller;
        setupNorth();
    }

    public void setupNorth()
    {
        startButton  = new JButton("Start server");
        startClient = new JButton("+1 client");
        stopButton = new JButton("Stop server");
        broadcastMsgBtn = new JButton("Broadcast message");
        startButton.addActionListener(this);
        startClient.addActionListener(this);
        stopButton.addActionListener(this);
        broadcastMsgBtn.addActionListener(this);
        add(startButton);
        add(startClient);
        add(stopButton);
        add(broadcastMsgBtn);
        stopButton.setEnabled(false);
        broadcastMsgBtn.setEnabled(true);
    }

    public void actionPerformed(ActionEvent e)
    {


        if (e.getSource() == startButton)
        {
            try
            {
                controller.startServer();
                

            }
            catch (IOException ioException)
            {
                System.out.println("Failed to start server");
            }
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
        }
        if (e.getSource() == startClient)
        {
            try
            {
                controller.startClient();
                broadcastMsgBtn.setEnabled(true);
            }
            catch (Exception ec)
            {
                System.out.println("Failed to start client");
                ec.printStackTrace();
            }

        }
        if (e.getSource() == stopButton)
        {
            controller.stopServer();
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        }
        if(e.getSource() == broadcastMsgBtn)
        {
            controller.broadcast();

        }
    }

}
