package Game.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class contains everything concerning the HelpGUI
 *
 * @author Yasir Kakar, Adel Sabanovic
 * @version 4.0
 */
public class HelpGUI extends JFrame {

    private CardLayout card = new CardLayout();
    private JPanel container = new JPanel();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();
    private JPanel panel5 = new JPanel();
    private JPanel panel6 = new JPanel();
    private JPanel panel7 = new JPanel();
    private JPanel panel8 = new JPanel();

    private ImageIcon next = new ImageIcon("images/fwdButton.png");
    private ImageIcon back = new ImageIcon("images/backButton.png");
    private ImageIcon ok = new ImageIcon("images/okButton.png");


    private JButton btnNext[] = new JButton[8];
    private JButton btnBack[] = new JButton[8];
    private JButton btnOK[] = new JButton[8];

    /**
     * This constructor brings all the methods together for the HelpGUI
     */
    public HelpGUI() {

        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        add(container);

        container.setLayout(card);
        card.show(container, "1");

        setLayouts();
        createButtons();
        addButtons();
        addInnerClasses();
        addPanels();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * This method let the developers place the panels anywhere necessary in the Frame
     */
    private void setLayouts() {
        panel1.setLayout(null);
        panel2.setLayout(null);
        panel3.setLayout(null);
        panel4.setLayout(null);
        panel5.setLayout(null);
        panel6.setLayout(null);
        panel7.setLayout(null);
        panel8.setLayout(null);

    }

    /**
     * This method adds the constructors of the inner classes to the panels shown in the HelpGUI
     */
    private void addInnerClasses() {
        panel1.add(new Panel1());
        panel2.add(new Panel2());
        panel3.add(new Panel3());
        panel4.add(new Panel4());
        panel5.add(new Panel5());
        panel6.add(new Panel6());
        panel7.add(new Panel7());
        panel8.add(new Panel8());
    }

    /**
     * This method adds the Panels in the HelpGUI
     */
    private void addPanels() {
        container.add(panel1, "2");
        container.add(panel2, "3");
        container.add(panel3, "4");
        container.add(panel4, "5");
        container.add(panel5, "6");
        container.add(panel6, "7");
        container.add(panel7, "8");
        container.add(panel8, "9");

    }

    /**
     * This method adds the buttons in the HelpGUI
     */
    private void addButtons() {
        panel1.add(btnNext[0]);
        panel2.add(btnNext[1]);
        panel3.add(btnNext[2]);
        panel4.add(btnNext[3]);
        panel5.add(btnNext[4]);
        panel6.add(btnNext[5]);
        panel7.add(btnNext[6]);
        panel8.add(btnNext[7]);

        panel1.add(btnBack[0]);
        panel2.add(btnBack[1]);
        panel3.add(btnBack[2]);
        panel4.add(btnBack[3]);
        panel5.add(btnBack[4]);
        panel6.add(btnBack[5]);
        panel7.add(btnBack[6]);
        panel8.add(btnBack[7]);

        panel1.add(btnOK[0]);
        panel2.add(btnOK[1]);
        panel3.add(btnOK[2]);
        panel4.add(btnOK[3]);
        panel5.add(btnOK[4]);
        panel6.add(btnOK[5]);
        panel7.add(btnOK[6]);
        panel8.add(btnOK[7]);
    }

    /**
     * This method creates the buttons in the HelpGUI
     */
    private void createButtons() {

        for (int i = 0; i < btnNext.length; i++) {
            btnNext[i] = new JButton(next);
            btnNext[i].setBounds(530, 630, 60, 46);
            btnNext[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
            btnNext[i].addActionListener(new NextListener());
        }

        for (int i = 0; i < btnBack.length; i++) {
            btnBack[i] = new JButton(back);
            btnBack[i].setBounds(100, 630, 56, 47);
            btnBack[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
            btnBack[i].addActionListener(new BackListener());
        }

        for (int i = 0; i < btnOK.length; i++) {
            btnOK[i] = new JButton(ok);
            btnOK[i].setBounds(320, 630, 56, 46);
            btnOK[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
            btnOK[i].addActionListener(new OKListener());
        }
    }

    /**
     * Each panel class below contains the images that are presented in the HelpGUI
     *
     * @author Yasir Kakar
     */
    private class Panel1 extends JPanel {
        private ImageIcon hjälp1Bild = new ImageIcon("images/hjälp1.PNG");

        private JLabel lblbild = new JLabel(hjälp1Bild);

        public Panel1() {
            setSize(700, 700);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            add(lblbild);
            setBackground(Color.WHITE);
        }
    }

    private class Panel2 extends JPanel {
        private ImageIcon hjälp2Bild = new ImageIcon("images/hjälp2.PNG");

        private JLabel lblbild = new JLabel(hjälp2Bild);

        public Panel2() {
            setSize(700, 700);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            lblbild.setBounds(0, 0, 550, 300);
            add(lblbild);
            setBackground(Color.WHITE);
        }
    }

    private class Panel3 extends JPanel {
        private ImageIcon hjälp3Bild = new ImageIcon("images/hjälp3.PNG");

        private JLabel lblbild = new JLabel(hjälp3Bild);

        public Panel3() {
            setSize(700, 700);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            lblbild.setBounds(0, 0, 550, 300);
            add(lblbild);
            setBackground(Color.WHITE);
        }
    }

    private class Panel4 extends JPanel {
        private ImageIcon hjälp4Bild = new ImageIcon("images/hjälp4.PNG");

        private JLabel lblbild = new JLabel(hjälp4Bild);

        public Panel4() {
            setSize(700, 700);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            add(lblbild);
            setBackground(Color.WHITE);
        }
    }

    private class Panel5 extends JPanel {
        private ImageIcon hjälp5Bild = new ImageIcon("images/hjälp5.PNG");

        private JLabel lblbild = new JLabel(hjälp5Bild);

        public Panel5() {
            setSize(700, 700);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            add(lblbild);
            setBackground(Color.WHITE);
        }
    }

    private class Panel6 extends JPanel {
        private ImageIcon hjälp6Bild = new ImageIcon("images/hjälp6.PNG");

        private JLabel lblbild = new JLabel(hjälp6Bild);

        public Panel6() {
            setSize(700, 700);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            add(lblbild);
            setBackground(Color.WHITE);
        }
    }

    private class Panel7 extends JPanel {
        private ImageIcon hjälp6Bild = new ImageIcon("images/hjälp7.PNG");

        private JLabel lblbild = new JLabel(hjälp6Bild);

        public Panel7() {
            setSize(700, 700);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            add(lblbild);
            setBackground(Color.WHITE);
        }
    }

    private class Panel8 extends JPanel {
        private ImageIcon hjälp6Bild = new ImageIcon("images/hjälp8.PNG");

        private JLabel lblbild = new JLabel(hjälp6Bild);

        public Panel8() {
            setSize(700, 700);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            add(lblbild);
            setBackground(Color.WHITE);
        }
    }

    /**
     * This class contains the functions for the "Next" button in the HelpGUI
     *
     * @author Yasir Kakar
     */
    private class NextListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < btnNext.length; i++) {
                if (e.getSource() == btnNext[0]) {
                    card.show(container, "" + 3);
                }
                if (e.getSource() == btnNext[1]) {
                    card.show(container, "" + 4);
                }
                if (e.getSource() == btnNext[2]) {
                    card.show(container, "" + 5);
                }
                if (e.getSource() == btnNext[3]) {
                    card.show(container, "" + 6);
                }
                if (e.getSource() == btnNext[4]) {
                    card.show(container, "" + 7);
                }
                if (e.getSource() == btnNext[5]) {
                    card.show(container, "" + 8);
                }
                if (e.getSource() == btnNext[6]) {
                    card.show(container, "" + 9);
                }
                if (e.getSource() == btnNext[7]) {
                    card.show(container, "" + 2);
                }
            }
        }
    }

    /**
     * A class that contains functions for the "Back" button
     *
     * @author Adel Sabanovic
     */

    private class BackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < btnBack.length; i++) {
                if (e.getSource() == btnBack[0]) {
                    card.show(container, "" + 7);
                }
                if (e.getSource() == btnBack[1]) {
                    card.show(container, "" + 2);
                }
                if (e.getSource() == btnBack[2]) {
                    card.show(container, "" + 3);
                }
                if (e.getSource() == btnBack[3]) {
                    card.show(container, "" + 4);
                }
                if (e.getSource() == btnBack[4]) {
                    card.show(container, "" + 5);
                }
                if (e.getSource() == btnBack[5]) {
                    card.show(container, "" + 6);
                }
                if (e.getSource() == btnBack[6]) {
                    card.show(container, "" + 7);
                }
                if (e.getSource() == btnBack[7]) {
                    card.show(container, "" + 6);
                }
            }
        }
    }

    /**
     * This class contains the functions for the "OK" button in the HelpGUI
     *
     * @author Yasir Kakar
     */
    class OKListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}
