package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel
{
    public static final int  width = 420;
    public static final int  height = 300;
   private JTextArea area = new JTextArea();
    private Controller controller;

    public CenterPanel(Controller controller)
    {
        this.controller = controller;
       setupCenter();
    }

    public void setupCenter()
    {
        add(area);
        area.setText("Hello World!");
        area.setEditable(false);
        area.setPreferredSize(new Dimension(width,height));
    }
    public void replaceArea(String msg)
    {
        area.setText(msg);
    }
    public void appendArea(String msg)
    {
        area.append("\n"+ msg);
    }
}
