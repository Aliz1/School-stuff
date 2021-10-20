package View;

import Controller.Controller;

import javax.swing.*;

import java.awt.*;

public class PanelCenter extends JPanel
{
    public static final int  width = 600;
    public static final int  height = 520;
    private JTextArea area;
    private JScrollPane scroll ;
    private Controller controller;
    private BorderLayout layout;
    PanelCenterEast east;
    

    public PanelCenter(Controller controller)
    {
        this.controller = controller;
       setupCenter();
    }

    

    public void setupCenter()
    {
        layout = new BorderLayout();
        setLayout(layout);

        area = new JTextArea();
        east = new PanelCenterEast(controller);
        scroll = new JScrollPane(area);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setAutoscrolls(true);
        scroll.setPreferredSize(new Dimension(width/2, height));
        area.setBackground(new Color (60,60,60));
        area.setForeground(Color.white);

        area.setText("Hello Player,\nHope you enjoy whacking that mole!");
        area.setEditable(false);

        add(scroll,BorderLayout.CENTER);
        add(east, BorderLayout.EAST);
        
    }
    public void replaceArea(String msg)
    {
        area.setText(msg);
    }
    public void appendArea(String msg)
    {
        area.append("\n"+ msg);
    }
    public PanelCenterEast getPanelCenterEast()
    {
        return east;
    }
}
