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
    CenterPanel centerPanel;
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
        centerPanel = new CenterPanel(controller);
        add(panelNorth,BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        setBackground(Color.GRAY);
    }
    public CenterPanel getCenterPanel()
    {
        return centerPanel;
    }

    public PanelNorth getPanelNorth()
     {
        return panelNorth;
    }
}
