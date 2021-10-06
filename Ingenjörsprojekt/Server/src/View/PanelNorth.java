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
        startButton.addActionListener(this);
        startClient.addActionListener(this);
        stopButton.addActionListener(this);
        add(startButton);
        add(startClient);
        add(stopButton);
        stopButton.setEnabled(false);
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
            }
            catch (Exception ec)
            {
                System.out.println("Failed to start client");
            }

        }
        if (e.getSource() == stopButton)
        {
            controller.stopServer();
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        }



    }

}
