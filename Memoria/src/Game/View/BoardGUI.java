package Game.View;

import Game.Controller.Controller;
import Game.Controller.MusicController;
import Game.Model.Card;
import Game.Model.CardDeck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class contains the game board
 *
 * @author Adel Sabanovic, Robert Rosencrantz, Joakim Tell
 * @version 4.0
 */
public class BoardGUI extends JFrame {
    MusicController musicController = new MusicController();
    private JPanel pnlMain = new JPanel();
    private JPanel pnlCards = new JPanel();
    private JPanel pnlMain2 = new JPanel();
    private JPanel pnlEast = new JPanel();
    private JPanel pnlPlayer1 = new JPanel();
    private JPanel pnlPlayer2 = new JPanel();
    private JPanel pnlScore = new JPanel();
    private JPanel pnlCardsLayout = new JPanel();
    private JPanel pnlPlayer1Color = new JPanel();
    private JPanel pnlPlayer1Color2 = new JPanel();
    private JPanel pnlPlayer2Color = new JPanel();
    private JPanel pnlPlayer2Color2 = new JPanel();

    private Font myFont = new Font("SansSerif", Font.ROMAN_BASELINE | Font.PLAIN, 15);

    private JTextArea txtInfoArea = new JTextArea();

    private ImageIcon iconHelp = new ImageIcon("images/questionMark1.jpg");
    private ImageIcon iconInstructions = new ImageIcon("images/gear1.jpg");
    private ImageIcon iconMemoriaLogo = new ImageIcon("images/mem2.jpg");
    private ImageIcon iconBlueStripe = new ImageIcon("images/blue5.jpg");
    private ImageIcon iconBlueStripe2 = new ImageIcon("images/blue4.jpg");
    private ImageIcon iconMathLogo = new ImageIcon("images/mathLogo.JPG");
    private ImageIcon iconPi = new ImageIcon("images/pi.jpg");
    private ImageIcon iconBack = new ImageIcon("images/back.jpg");

    private JButton btnSettings = new JButton(iconInstructions);
    private JButton btnInstructions = new JButton(iconHelp);
    private JButton btnBack = new JButton(iconBack);

    private JLabel lblEmptyLogo = new JLabel();
    private JLabel lblEmptyLogo2 = new JLabel();
    private JLabel lblMemoriaLogo = new JLabel(iconMemoriaLogo);
    private JLabel lblScore = new JLabel("0");
    private JLabel lblScore2 = new JLabel("0");
    private JLabel lblBlueStripe = new JLabel(iconBlueStripe);
    private JLabel lblBlueStripe2 = new JLabel(iconBlueStripe);
    private JLabel lblBlueStripe3 = new JLabel(iconBlueStripe);
    private JLabel lblBlueStripe4 = new JLabel(iconBlueStripe);
    private JLabel lblGhost5 = new JLabel();
    private JLabel lblGhost6 = new JLabel();
    private JLabel lblBlueStripe5 = new JLabel(iconBlueStripe);
    private JLabel lblBlueStripe6 = new JLabel(iconBlueStripe);
    private JLabel lblGhost9 = new JLabel(iconMathLogo);
    private JLabel lblGhost10 = new JLabel();
    private JLabel lblGhost11 = new JLabel();
    private JLabel lblGhost12 = new JLabel();
    private JLabel lblGhost13 = new JLabel();
    private JLabel lblPi = new JLabel(iconPi);
    private JLabel lblPi2 = new JLabel(iconPi);

    private Timer timer;
    private ArrayList<Card> cards;

    Controller controller;

    /**
     * A constructor with one parameter
     *
     * @param controller
     */
    public BoardGUI(Controller controller) {
        this.controller = controller;
        musicController.stopMusic();
        musicController.playMusic("music/GameMusic.wav");
        setupFrame();
        setupComponentsSizes();
        setupComponents();
        addComponents();
        setLocationRelativeTo(null);
        setupTheGame();
        addListeners();
        XButtonPressed();
    }

    /**
     * Sets up the frame and adds the main panel
     */
    private void setupFrame() {
        setSize(900, 590);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(pnlMain);
    }

    /**
     * Adds components to the main panel
     */
    private void addComponents() {
        pnlMain.add(btnBack);
        pnlMain.add(lblMemoriaLogo);
        pnlMain.add(lblGhost9);
        pnlMain.add(lblGhost10);
        pnlMain.add(pnlMain2);
        pnlEast.add(lblBlueStripe3);
        pnlEast.add(lblBlueStripe4);
        pnlEast.add(pnlPlayer1);
        pnlEast.add(pnlPlayer2);
        pnlEast.add(txtInfoArea);
        pnlEast.add(lblGhost6);
        //pnlEast.add(lblGhost5);
        pnlEast.add(lblPi);
        lblPi.setVisible(false);
        pnlEast.add(lblEmptyLogo);
        pnlEast.add(btnSettings);
        btnSettings.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        pnlEast.add(lblPi2);
        lblPi2.setVisible(false);
        pnlEast.add(lblEmptyLogo2);
        pnlEast.add(btnInstructions);
        btnInstructions.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        pnlEast.add(lblBlueStripe5);
        pnlEast.add(lblBlueStripe6);
        pnlMain2.add(pnlCards);
        pnlMain2.add(pnlEast);
        pnlCards.add(lblBlueStripe);
        pnlCards.add(pnlCardsLayout);
        pnlCards.add(lblBlueStripe2);
        pnlPlayer1.add(lblGhost11);
        pnlPlayer1.add(pnlPlayer1Color);
        pnlPlayer1.add(lblScore);
        pnlPlayer1.add(pnlPlayer1Color2);
        pnlPlayer2.add(lblGhost12);
        pnlPlayer2.add(pnlPlayer2Color);
        pnlPlayer2.add(lblScore2);
        pnlPlayer2.add(pnlPlayer2Color2);
    }

    /**
     * Sets the sizes for all the components
     */

    private void setupComponentsSizes() {
        pnlMain2.setPreferredSize(new Dimension(850, 500));
        btnBack.setPreferredSize(new Dimension(25, 20));
        lblMemoriaLogo.setPreferredSize(new Dimension(250, 55));
        lblEmptyLogo.setPreferredSize(new Dimension(50, 50));
        lblEmptyLogo2.setPreferredSize(new Dimension(50, 50));
        pnlCards.setPreferredSize(new Dimension(470, 488));
        pnlCardsLayout.setPreferredSize(new Dimension(500, 386));
        lblBlueStripe.setPreferredSize(new Dimension(430, 20));
        lblBlueStripe2.setPreferredSize(new Dimension(430, 20));
        lblBlueStripe3.setPreferredSize(new Dimension(125, 20));
        lblBlueStripe4.setPreferredSize(new Dimension(125, 20));
        //lblGhost5.setPreferredSize(new Dimension(240, 22));
        lblGhost6.setPreferredSize(new Dimension(240, 65));
        lblBlueStripe5.setPreferredSize(new Dimension(125, 20));
        lblBlueStripe6.setPreferredSize(new Dimension(125, 20));
        lblGhost9.setPreferredSize(new Dimension(135, 35));
        lblGhost10.setPreferredSize(new Dimension(265, 35));
        lblGhost11.setPreferredSize(new Dimension(10, 5));
        lblGhost12.setPreferredSize(new Dimension(10, 5));
        lblGhost13.setPreferredSize(new Dimension(50, 46));
        lblPi.setPreferredSize(new Dimension(50, 46));
        lblPi2.setPreferredSize(new Dimension(50, 46));
        txtInfoArea.setPreferredSize(new Dimension(257, 140));
        txtInfoArea.setLineWrap(true);
        txtInfoArea.setWrapStyleWord(true);
        btnSettings.setPreferredSize(new Dimension(48, 51));
        btnInstructions.setPreferredSize(new Dimension(38, 65));
        pnlScore.setPreferredSize(new Dimension(126, 100));
        pnlEast.setPreferredSize(new Dimension(265, 488));
        pnlPlayer1.setPreferredSize(new Dimension(126, 100));
        pnlPlayer2.setPreferredSize(new Dimension(126, 100));
        pnlPlayer1Color.setPreferredSize(new Dimension(100, 10));
        pnlPlayer2Color.setPreferredSize(new Dimension(100, 10));
        pnlPlayer1Color2.setPreferredSize(new Dimension(100, 10));
        pnlPlayer2Color2.setPreferredSize(new Dimension(100, 10));
    }

    /**
     * Sets colors and title borders to the different components
     */
    private void setupComponents() {
        pnlMain.setBackground(Color.WHITE);
        pnlCards.setBackground(Color.WHITE);
        pnlCardsLayout.setBackground(Color.WHITE);
        pnlEast.setBackground(Color.WHITE);
        pnlPlayer1.setBackground(Color.WHITE);
        pnlPlayer2.setBackground(Color.WHITE);
        pnlMain2.setBackground(Color.WHITE);
        txtInfoArea.setBackground(Color.WHITE);
        txtInfoArea.setFont(myFont);
        pnlScore.setBackground(Color.WHITE);
        pnlPlayer1.setBorder(BorderFactory.createTitledBorder("Player one score"));
        pnlPlayer1Color.setBackground(Color.GREEN);
        pnlPlayer1Color.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlPlayer1Color2.setBackground(Color.GREEN);
        pnlPlayer1Color2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlPlayer2.setBorder(BorderFactory.createTitledBorder("Player two score"));
        pnlPlayer2Color.setBackground(Color.WHITE);
        pnlPlayer2Color2.setBackground(Color.WHITE);
        txtInfoArea.setBorder(BorderFactory.createTitledBorder("Info"));
        txtInfoArea.setEditable(false);
        pnlScore.setBorder(BorderFactory.createTitledBorder("Highscore"));
        btnBack.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        lblScore.setText("0");
        lblScore2.setText("0");
    }

    /**
     * Adds the listeners to the buttons
     */
    private void addListeners() {
        btnSettings.addMouseListener(new MouseSettings());
        btnInstructions.addMouseListener(new MouseHelp());
        btnBack.addActionListener(new ActionListener());
    }

    /**
     * Adds and sets upp the cards on the board panel with listeners and a timer.
     * Method has inspiration and partially adopted from:
     * https://codereview.stackexchange.com/questions/85833/basic-memory-match-game-in-java
     * Stackexchange 20.04.14.
     */
    private void setupTheGame() {
        cards = new ArrayList<Card>();
        ArrayList<String> cardSymbolPaths;

        cardSymbolPaths = new CardDeck().addSymbols();  // Add images to cards.
        Collections.shuffle(cardSymbolPaths);           // Randomize symbols.
        for (String symbol : cardSymbolPaths) {         // Setup 24 cards.
            Card card = new Card();
            card.setPathSymbol(symbol);
            card.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    controller.doTurn(card);
                }
            });
            card.hideSymbol(65, 83);       // Turn backside up.
            cards.add(card);
        }

        setupTimer();
        for (Card card : cards) {                       // Add 24 cards to board.
            pnlCardsLayout.add(card);
        }
        revalidate();
        repaint();
    }


    /**
     * Sets up a timer with a delay after each pairing in the game, before the cards are turned back.
     */
    private void setupTimer() {
        timer = new Timer(1500, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                controller.checkForMatch("JOKER ROUND");
            }
        });
        timer.setRepeats(false);
    }

    public Timer getTimer() {
        return timer;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public JPanel getPnlPlayer1() {
        return pnlPlayer1;
    }

    public JPanel getPnlPlayer2() {
        return pnlPlayer2;
    }

    /**
     * Controls the color on the panel which indicate that its player 1's turn
     */
    public void highlightPlayer1() {
        pnlPlayer1Color.setBackground(Color.GREEN);
        pnlPlayer1Color2.setBackground(Color.GREEN);
        pnlPlayer1Color2.setVisible(true);
        pnlPlayer1Color.setVisible(true);
        pnlPlayer2Color2.setBackground(Color.WHITE);
        pnlPlayer2Color.setBackground(Color.WHITE);
        pnlPlayer2Color2.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        pnlPlayer2Color.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        pnlPlayer1Color2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlPlayer1Color.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /**
     * Controls the color on the panel which indicate that its player 2's turn
     */
    public void highlightPlayer2() {
        pnlPlayer2Color.setBackground(Color.GREEN);
        pnlPlayer2Color2.setBackground(Color.GREEN);
        pnlPlayer2Color2.setVisible(true);
        pnlPlayer2Color.setVisible(true);
        pnlPlayer1Color2.setBackground(Color.WHITE);
        pnlPlayer1Color.setBackground(Color.WHITE);
        pnlPlayer1Color2.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        pnlPlayer1Color.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        pnlPlayer2Color2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlPlayer2Color.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /**
     * An inner class that makes it so that a pi logo shows next to the button the mouse is hovering over
     * it and shows the settings menu if clicked
     */
    private class MouseSettings implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            new SettingsGUI();

        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
            lblPi.setVisible(true);
            lblEmptyLogo.setVisible(false);
        }

        public void mouseExited(MouseEvent e) {
            lblPi.setVisible(false);
            lblEmptyLogo.setVisible(true);
        }
    }

    /**
     * An inner class that makes it so that a pi logo shows next to the button the mouse is hovering over
     * it and shows the help menu if clicked
     */
    private class MouseHelp implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            new HelpGUI();
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
            lblPi2.setVisible(true);
            lblEmptyLogo2.setVisible(false);
        }

        public void mouseExited(MouseEvent e) {
            lblPi2.setVisible(false);
            lblEmptyLogo2.setVisible(true);
        }
    }

    /**
     * An inner class that controls the function for the back button
     */
    public class ActionListener implements java.awt.event.ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnBack) {
                dispose();
                controller.switchToMenu();
                musicController.stopMusic();
            }
        }
    }

    /**
     * If X on frame i pressed asks the user if the want to close the program.
     */
    public void XButtonPressed() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int x = JOptionPane.showConfirmDialog(null,
                        "Do you really want to close Memoria?");
                if (x == JOptionPane.YES_OPTION) {
                    e.getWindow().dispose();
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, "We are happy you are back :)");
                }
            }
        });
    }

    public void setLblScore(int score) {
        lblScore.setText(String.valueOf(score));
    }

    public void setLblScore2(int score2) {
        lblScore2.setText(String.valueOf(score2));
    }

    public JTextArea getTxtInfoArea() {
        return txtInfoArea;
    }
}
