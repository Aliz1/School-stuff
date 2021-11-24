package Game.Controller.multiplicationGame;

import Game.Controller.ClickController;
import Game.Controller.Controller;
import Game.Controller.MusicController;
import Game.Model.NumbersScanner;
import Game.View.JokerGameGUI;

import java.util.ArrayList;
import java.util.Random;

/**
 * The task to add new card drops with multiplication problems to the GUI.
 * The CardDropTasks are passed in Threads as arguments to manage their own
 * progress. Creates a card drop rain down the jokerGameGUI.
 *
 * @author Robert Rosencrantz
 * @version 4.0
 */
public class DropCardsThread implements Runnable {
    private MusicController musicController = new MusicController();
    private ClickController clickController = new ClickController();
    private Controller controller;
    private JokerGameGUI jokerGameGui;

    private Random random;

    private ArrayList<CardDropTask> fallingDropsList;                         // Stores drop threads.
    private boolean gameRunning = true;                                       // Set to false to stop thread.
    private int points = 0;                                                   // Store current score.
    private int matches = 0;
    private int NBR_OF_PROBLEMS_IN_BUFFER = 10;
    private int dropSpeed = 30;
    private boolean singlePlayer;

    /**
     * Construct and initialize a thread with this class' tasks.
     */
    public DropCardsThread(Controller controller, String title) {
        this.controller = controller;
        jokerGameGui = new JokerGameGUI(title);
        random = new Random();
        singlePlayer = false;
        fallingDropsList = new ArrayList<CardDropTask>(NBR_OF_PROBLEMS_IN_BUFFER);
        setupDropList();                                                             // Setup list of drop threads.
        new Thread(this).start();
    }

    /**
     * Construct and initialize a thread with this class' tasks.
     * A number of problems is passed as an argument to use this constructor in the single player mode,
     * as opposed to the originally intended bonus round in the multi player game.
     *
     * @param controller The shared controller class
     * @param problems   The number of problems to use in single player mode
     */
    public DropCardsThread(Controller controller, int problems, String title) {
        this.controller = controller;
        jokerGameGui = new JokerGameGUI(title);
        random = new Random();
        this.NBR_OF_PROBLEMS_IN_BUFFER = problems;
        singlePlayer = true;
        fallingDropsList = new ArrayList<CardDropTask>(NBR_OF_PROBLEMS_IN_BUFFER);
        setupDropList();                                                             // Setup list of drop threads.
        new Thread(this).start();
    }

    /**
     * The running task of adding new CardDropTasks down the JokerGameGUI.
     */
    @Override
    public void run() {
        try {
            dropACard();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when the game is over. Secure all thread drops and rain thread to terminate.
     * Update score from joker game to main memory game and closes joker GUI.
     */
    public void gameOver() {
        for (CardDropTask drop : fallingDropsList) {
            drop.setAlive(false);                               // Stop all card drop threads.
        }
        musicController.stopMusic();
        gameRunning = false;                                    // Stops this game thread.
        fallingDropsList.clear();                               // To not keep getting points after game over.
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jokerGameGui.setVisible(false);
        jokerGameGui.dispose();
        if (singlePlayer) {
            musicController.stopMusic();
            controller.switchToMenu();
        } else {
            controller.switchToBoard();
            musicController.playMusic("music/GameMusic.wav");
            controller.addJokerPoints();
        }
    }

    /**
     * Update points score and set it visible in joker GUI.
     */
    public void incrementPoints() {
        points += 2;
        jokerGameGui.setTextFieldPoints(points);
        clickController.click("music/Point.wav");
    }

    /**
     * Called in a CardDropTask when a correct answer is given from the user.
     */
    public void incrementMatches() {
        matches++;
    }

    /**
     * Check if the current score matches the total amount of problems to solve.
     *
     * @return true if all problems was answered correctly.
     */
    public boolean gotAllProblemsRight() {
        return matches == NBR_OF_PROBLEMS_IN_BUFFER;
    }

    /**
     * Returns the current point score.
     *
     * @return integer holding point score.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Release a card drop on a random interval.
     * Keep dropping as long as the game is going.
     */
    private void dropACard() throws InterruptedException {
        int problemsDropped = 0;
        while (gameRunning && (problemsDropped < NBR_OF_PROBLEMS_IN_BUFFER)) {
            Thread.sleep(random.nextInt(2000) + 1000);             // Delay next drop to random interval.
            if (!fallingDropsList.isEmpty()) {
                CardDropTask nextDrop = fallingDropsList.get(problemsDropped);
                nextDrop.setDropSpeed(dropSpeed);
                nextDrop.setAlive(true);// Start a new drop thread.
                if (singlePlayer) {
                    dropSpeed--;
                }
                jokerGameGui.addDropToGamePanel(
                        fallingDropsList.get(problemsDropped));   // Put the new drop on rain thread.
                problemsDropped++;
            }
        }
    }

    /**
     * Fill up the fallingDropsList with CardDropTasks.
     */
    private void setupDropList() {
        NumbersScanner scanner = new NumbersScanner();
        ArrayList<String> problemsBuffer = scanner.getProblems();           // Create a list of problem strings.
        ArrayList<String> solvedBuffer = scanner.getSolved();               // Create a list of answer strings.
        for (int i = 0; i < NBR_OF_PROBLEMS_IN_BUFFER; i++) {      // Fill upp the falling drops list with Runnables.
            String problem = problemsBuffer.remove(0);
            String solved = solvedBuffer.remove(0);
            CardDropTask cardDropTask = new CardDropTask(jokerGameGui, this, problem, solved);
            fallingDropsList.add(cardDropTask);
        }
    }
}