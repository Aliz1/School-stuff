package View;

import javax.swing.JButton;
import javax.swing.JPanel;
import Controller.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelSouth extends JPanel implements ActionListener
{
    private JButton clearButton;
    private final Controller controller;
    public PanelSouth(Controller controller)
    {
       this.controller = controller;
       setupPanelSouth();
    }
    public void setupPanelSouth()
    {
        clearButton = new JButton("Clear area");
        clearButton.addActionListener(this);
        add(clearButton);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == clearButton)
        {
            controller.replaceArea("");

        }
    }
}
