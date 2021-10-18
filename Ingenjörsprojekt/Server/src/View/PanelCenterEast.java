package View;

import Controller.Controller;
import Model.Nodes;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.util.LinkedList;

public class PanelCenterEast extends JPanel
{
    public static final int  width = 600;
    public static final int  height = 203;

    private BorderLayout layout;

    private JScrollPane scrollOnlinPane;
    private JScrollPane scrollOfflinPane;
    
    

    JList tfonlineMK = new JList();
    JList tfofflineMK = new JList();
    DefaultListModel OnlineListModel;
    DefaultListModel OfflineListModel;

    private Controller controller;
    

    public PanelCenterEast(Controller Controller)
    {
        this.controller = Controller;
       setupCenter();
    }

    

    public void setupCenter()
    {
        layout = new BorderLayout();
        setLayout(layout);

        scrollOnlinPane = new JScrollPane(tfonlineMK);
        scrollOfflinPane = new JScrollPane(tfofflineMK);

        scrollOnlinPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollOnlinPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollOnlinPane.setPreferredSize(new Dimension(width/3, height));
        
        scrollOfflinPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollOfflinPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollOfflinPane.setPreferredSize(new Dimension(width/3, height));
        

        OnlineListModel = new DefaultListModel();
        OfflineListModel = new DefaultListModel();

        tfonlineMK.setModel(OnlineListModel);
        tfofflineMK.setModel(OfflineListModel);

        tfonlineMK .setBackground(new Color(60, 60, 60));
        tfofflineMK .setBackground(new Color(60, 60, 60));
        scrollOfflinPane.setForeground(Color.red);
        scrollOnlinPane.setForeground(Color.green);
        tfofflineMK.setForeground(Color.red);
        tfonlineMK.setForeground(Color.green);
        
        /////
        TitledBorder offlineBorder = new TitledBorder(BorderFactory.createEtchedBorder(new Color(62, 134, 160), new Color(62, 134, 160)), "Offline");
        TitledBorder onlineBorder = new TitledBorder(BorderFactory.createEtchedBorder(new Color(62, 134, 160), new Color(62, 134, 160)), "Online");
        offlineBorder.setTitleColor(new Color(62, 134, 160));
        onlineBorder.setTitleColor(new Color(62, 134, 160));

        tfofflineMK.setBorder(offlineBorder);
        tfonlineMK.setBorder(onlineBorder);

        add(scrollOnlinPane, BorderLayout.NORTH);
        add(scrollOfflinPane, BorderLayout.SOUTH);
        
        
    }

    public void updateOnlineMK(LinkedList<Nodes> nodes) {
        OnlineListModel.clear();
        for (Nodes s: nodes) {

            OnlineListModel.addElement(s.getMac());
        }

        tfonlineMK.setModel(OnlineListModel);

        tfonlineMK.repaint();


    }

    public void updateOfflineMK(LinkedList<Nodes> nodes){
        OfflineListModel.clear();


        for (Nodes s: nodes){

            OfflineListModel.addElement(s.getMac());
        }
        tfofflineMK.setModel(OfflineListModel);
        tfofflineMK.repaint();
    }
    
}
