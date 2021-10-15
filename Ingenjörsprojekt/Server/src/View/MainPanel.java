package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class MainPanel extends JPanel
{
    Controller controller;
    BorderLayout layout;

    PanelNorth panelNorth;
    PanelCenter centerPanel;
    PanelSouth panelSouth;
    public PanelCenterEast panelCenterEast;
    public MainPanel(Controller controller)
    {
        this.controller = controller;
        setupPanel();
    }
    public  void setupPanel()
    {
        layout = new BorderLayout();
        setLayout(layout);
        Border border = this.getBorder();
        Border margin = BorderFactory.createEmptyBorder(1,1,1,1);
        setBorder(new CompoundBorder(border,margin));
        panelNorth = new PanelNorth(controller);
      //  panelCenterEast = new PanelCenterEast(controller);
        centerPanel = new PanelCenter(controller);
        panelSouth = new PanelSouth(controller);
        add(panelNorth,BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(panelSouth, BorderLayout.SOUTH);
        setBackground(new Color(60,60,60));
    }
    public PanelCenter getCenterPanel()
    {
        return centerPanel;
    }

    public PanelNorth getPanelNorth()
     {
        return panelNorth;
    }
}
