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
    private JButton sendMsgFromClientBtn;
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
        sendMsgFromClientBtn = new JButton("Send message to server");
        startButton.addActionListener(this);
        startClient.addActionListener(this);
        stopButton.addActionListener(this);
        sendMsgFromClientBtn.addActionListener(this);
        add(startButton);
        add(startClient);
        add(stopButton);
        add(sendMsgFromClientBtn);
        stopButton.setEnabled(false);
        sendMsgFromClientBtn.setEnabled(false);
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
                sendMsgFromClientBtn.setEnabled(true);
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
        if(e.getSource() == sendMsgFromClientBtn)
        {
            try {
                controller.sendMessageToServer("Hello server I am client");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }



    }

}
