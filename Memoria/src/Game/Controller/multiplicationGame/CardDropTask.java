package Game.Controller.multiplicationGame;

import Game.Controller.ClickController;
import Game.Model.Card;
import Game.View.JokerGameGUI;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Represents a multiplication problem to drop down on visible on a Card. The card drop is
 * to be passed as an argument to a Thread on a separate DropCardsThread task.
 *
 * @author Robert Rosencrantz
 * @version 4.0
 */
public class CardDropTask extends Card implements Runnable {
    private DropCardsThread dropCardsThread;
    private JokerGameGUI jokerGameGui;
    private ClickController clickController = new ClickController();

    private boolean alive = false;
    private int xPosition = new Random().nextInt(800);               // Problems appear random to this number.
    private int yPosition = 0;                                              // Origin of the problem appearing.
    private int dropSpeed = 30;                                             // Initial speed in milliseconds.

    private String problem;                                     // The question for the user to answer on this task.
    private String solved;                                      // The correct answer for the problem.

    /**
     * Constructs and initialize this Runnable.
     *
     * @param jokerGameGui    Where the game is played.
     * @param dropCardsThread the runnable where this drop thread will be initiated.
     * @param problem         the question for the user to answer on this task.
     * @param solved          the correct answer for the problem.
     */
    public CardDropTask(JokerGameGUI jokerGameGui, DropCardsThread dropCardsThread, String problem, String solved) {
        this.jokerGameGui = jokerGameGui;
        this.dropCardsThread = dropCardsThread;
        this.problem = problem;
        this.solved = solved;
        hideSymbol(65, 64);   // Method in super.
        setFocusable(false);
        setupDrop(Color.BLACK, Color.WHITE, problem);
    }

    /**
     * The running task of dropping a card down the JokerGameGUI.
     */
    @Override
    public void run() {
        try {
            keepDropping();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts a new thread if argument passed when called is true.
     * Stops thread if argument is false.
     *
     * @param alive true if a new thread is to be started with this task
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
        if (alive) {
            new Thread(this).start();
        }
    }

    /**
     * Set the delay between each update of where the card drop is painted.
     *
     * @param dropSpeed the new speed in milliseconds
     */
    public void setDropSpeed(int dropSpeed) {
        this.dropSpeed = dropSpeed;
    }

    /**
     * Update appearance of this card drop.
     *
     * @param colorF  text color
     * @param colorB  background color
     * @param message the problem or correct answer
     */
    private void setupDrop(Color colorF, Color colorB, String message) {
        setText(message);
        setFont(new Font("monospaced", Font.BOLD, 15));
        setSize((new Dimension(60, 88)));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createRaisedBevelBorder()
        ));
        setHorizontalTextPosition(JButton.CENTER);
        setVerticalTextPosition(JButton.BOTTOM);
        setForeground(colorF);
        setBackground(colorB);
        setLocation(xPosition, yPosition);
    }

    /**
     * Override to not revalidate the whole GUI for each update in this thread.
     * This component is now always root. This prevents unpredictable events in the GUI.
     *
     * @return boolean is always true.
     */
    @Override
    public boolean isValidateRoot() {
        return true;
    }

    /**
     * This card drop tread keeps running until there is a match typed in by the user,
     * or the drop reaches the end of the panel.
     *
     * @throws InterruptedException
     */
    private void keepDropping() throws InterruptedException {
        while (alive) {
            Thread.sleep(dropSpeed);
            yPosition++;

            setupDrop(Color.BLACK, Color.WHITE, problem);
            correctAnswer();
            gameOver();
        }
    }

    /**
     * Update the game if a dropping card is matching user input.
     */
    private void correctAnswer() throws InterruptedException {
        if (jokerGameGui.getAnswerTyped().equals(solved)) {
            jokerGameGui.resetLabelTyping();        // Reset typing area after a correct answer.
            displayMatch();
            alive = false;
            dropCardsThread.incrementPoints();
            Thread.sleep(2500);
            setVisible(false);
            dropCardsThread.incrementMatches();    // Synchronizes points updates
        }
    }

    /**
     * Game over when the user get all answers right or a card drop reaches bottom of frame.
     */
    private void gameOver() {
        if (dropCardsThread.gotAllProblemsRight()) {
            clickController.click("music/JokerWin.wav");
            jokerGameGui.addPointsText();
            dropCardsThread.gameOver();
            jokerGameGui.revalidate();
            jokerGameGui.repaint();
        } else if (yPosition > 409) {
            clickController.click("music/GameOver.wav");
            jokerGameGui.addPointsText();
            dropCardsThread.gameOver();
            jokerGameGui.revalidate();
            jokerGameGui.repaint();
        }
    }

    /**
     * Setup for displaying a card drop after a correct input from a user.
     */
    private void displayMatch() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ImageIcon imageShowing = new ImageIcon("images/Joker2.png");
                Image imageToScale = imageShowing.getImage();
                Image scaledImage = imageToScale.getScaledInstance(40, 58, Image.SCALE_SMOOTH);
                ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
                setIcon(scaledImageIcon);
                setupDrop(Color.WHITE, Color.BLACK, (problem + "=" + solved));
            }
        });
    }
}