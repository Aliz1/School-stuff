package Game.Controller;

import Game.Controller.multiplicationGame.DropCardsThread;
import Game.Model.Card;
import Game.Model.InfoReader;
import Game.Model.User;
import Game.View.BoardGUI;
import Game.View.LogInGUI;
import Game.View.MenuGUI;

import javax.swing.*;
import java.util.Arrays;

/**
 * Handles interaction between different views.
 * Most of the memory game logic is handled in this class.
 * The memory game has som inspiration from
 * https://codereview.stackexchange.com/questions/85833/basic-memory-match-game-in-java
 * Stackexchange 20.04.14.
 *
 * @author Robert Rosencrantz, Adel Sabanovic, Sonja Peric, Yasir Kakar, Joakim Tell
 * @version 4.0
 */
public class Controller {
    private ClickController clickController = new ClickController();
    private MusicController musicController = new MusicController();
    private BoardGUI boardGUI;
    private LogInGUI logInPlayer1;
    private LogInGUI logInPlayer2;
    private MenuGUI menuGUI;
    private DropCardsThread dropCardsThread;
    private InfoReader infoReader;

    private Card[] pairOfCards = new Card[2];   // To handle the two cards for each round of pairings.
    private User[] multiPlayer = new User[2];   // Keeps information of logged in users.
    private boolean turnPlayer1 = true;         // Track which players turn it is.

    /**
     * Construct the controller and initialize a login view.
     */
    public Controller() {
        logInPlayer1 = new LogInGUI(this, "Player One ");
        infoReader = new InfoReader("textfiles/infopanel.txt", "textfiles/symbol.txt");
        musicController.playMusic("music/MenuMusic.wav");
    }

    /**
     * Check if chosen card is first or second in a round.
     * Delay and turn back cards two cards have been selected and if it is not a match.
     * Method is improved and adapted from:
     * https://codereview.stackexchange.com/questions/85833/basic-memory-match-game-in-java
     * Stackexchange 20.04.14.
     */
    public void doTurn(Card card) {
        if (pairOfCards[0] == null) {
            pairOfCards[0] = card;
            pairOfCards[0].revealSymbol();
        } else {
            if ((pairOfCards[0] != card) && (pairOfCards[1] == null)) {
                pairOfCards[1] = card;
                pairOfCards[1].revealSymbol();
                boardGUI.getTimer().start();
            }
        }
    }

    /**
     * Check if a selected pair of cards are matching. Switch user if no match.
     * User plays again and gets points if it is a match, or switch to joker game view
     * if joker symbols are matching.
     * Method is improved and adapted from:
     * https://codereview.stackexchange.com/questions/85833/basic-memory-match-game-in-java
     * Stackexchange 20.04.14.
     */
    public void checkForMatch(String title) {
        String firstSymbol = pairOfCards[0].getPathSymbol().substring(0, 9);
        String secondSymbol = pairOfCards[1].getPathSymbol().substring(0, 9);
        final int POINTS_PER_MATCH = 10;

        if (firstSymbol.equals(secondSymbol)) {
            // Disable buttons and flag as matched.
            pairOfCards[0].setEnabled(false);
            pairOfCards[1].setEnabled(false);
            pairOfCards[0].setMatched(true);
            pairOfCards[1].setMatched(true);
            if (secondSymbol.equals("images/Jo")) {
                dropCardsThread = new DropCardsThread(this, title);
                boardGUI.setVisible(false);
                musicController.stopMusic();
                musicController.playMusic("music/JokerRound.wav");
            } else {
                clickController.click("music/Point.wav");
                incrementScore(POINTS_PER_MATCH);
                showInfoOnPanel();
                if (isGameWon()) {
                    announceWinner();
                }
            }
        } else {
            for (Card card : pairOfCards) {
                card.hideSymbol(65, 83);
            }
            switchPlayers();
        }
        Arrays.fill(pairOfCards, null);                     // Empty the pair of cards after each round.
    }

    /**
     * Access the points achieved from the joker bonus round, and update score.
     */
    public void addJokerPoints() {
        int jokerPoints = dropCardsThread.getPoints();
        incrementScore(jokerPoints);
        if (isGameWon()) {
            announceWinner();
        }
    }

    /**
     * Checks if the game is over. Returns true when all cards are ultimately out of play.
     * Method is reused and adapted from:
     * https://codereview.stackexchange.com/questions/85833/basic-memory-match-game-in-java
     * Stackexchange 20.04.14.
     */
    private boolean isGameWon() {
        for (Card card : boardGUI.getCards()) {
            if (!card.isMatched()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Create a user based on user name input from login window.
     * After called for user number one, view switch to menu.
     * After called for user number two, view switch to memory game.
     */
    public void createUser() {
        if (multiPlayer[0] == null) {
            String name = logInPlayer1.getTxtUsername().getText();
            multiPlayer[0] = new User(name);
            JOptionPane.showMessageDialog(null, "Välkommen spelare 1: " + name);
            menuGUI = new MenuGUI(this);
        } else {
            String name = logInPlayer2.getTxtUsername().getText();
            multiPlayer[1] = new User(name);
            JOptionPane.showMessageDialog(null, "Välkommen spelare 2: " + name);
            boardGUI = new BoardGUI(this);
            boardGUI.getPnlPlayer1().setBorder(BorderFactory.createTitledBorder(multiPlayer[0].getUserName()));
            boardGUI.getPnlPlayer2().setBorder(BorderFactory.createTitledBorder(multiPlayer[1].getUserName()));
        }
    }

    /**
     * Match key secondsymbol with info text from the info reader hashmap.
     * Displays the info in the info panel in view, when called.
     */
    private void showInfoOnPanel() {
        String secondSymbol = pairOfCards[1].getPathSymbol().substring(0, 9);
        String newString = infoReader.getInfoMap().get(secondSymbol);
        boardGUI.getTxtInfoArea().setText("");
        boardGUI.getTxtInfoArea().setText(newString);
    }

    /**
     * Open up a new login GUI for a second user.
     */
    public void logInSecondPlayerView() {
        logInPlayer2 = new LogInGUI(this, "Player Two");
    }

    /**
     * Go back to memory game view.
     */
    public void switchToBoard() {
        boardGUI.setVisible(true);
    }

    /**
     * Go back to menu view.
     */
    public void switchToMenu() {
        menuGUI.setVisible(true);
    }

    /**
     * Switch indicators in memory game view for which users playing turn it is.
     */
    private void switchPlayers() {
        if (turnPlayer1) {
            boardGUI.highlightPlayer2();
            turnPlayer1 = false;
        } else {
            boardGUI.highlightPlayer1();
            turnPlayer1 = true;
        }
    }

    /**
     * Check for the winner and ends the game.
     */
    private void announceWinner() {
        int score1 = multiPlayer[0].getGameScore();
        int score2 = multiPlayer[1].getGameScore();
        int score1total = multiPlayer[0].getTotalPoints();
        int score2total = multiPlayer[1].getTotalPoints();
        if (score1 > score2) {
            String messageWin = "Grattis";
            endOfGame(multiPlayer[0].getUserName(), messageWin);
        } else if (score2 > score1) {
            String messageWin = "Grattis";
            endOfGame(multiPlayer[1].getUserName(), messageWin);
        } else {
            String messageWin = "Poängställningen blev lika!";
            endOfGame("", messageWin);
        }
        score1total += score1;
        score2total += score2;
        multiPlayer[0].setTotalPoints(score1total);
        multiPlayer[1].setTotalPoints(score2total);
        multiPlayer[0].setGameScore(0);
        multiPlayer[1].setGameScore(0);
    }

    /**
     * Shows a panel of who won the game and asks if they want to play again.
     * if yes a new boardGUI will appear, if no a new menuGUI will appear, if cancel the game will shut down.
     *
     * @param name       of who won
     * @param messageWin message depending on result
     */
    private void endOfGame(String name, String messageWin) {
        musicController.stopMusic();
        clickController.click("music/JokerWin.wav");

        Object[] options = {"Avsluta", "Nej", "Ja"};
        int reply = JOptionPane.showOptionDialog(null, messageWin + " " + name
                        + "\n" + "Vill ni spela igen?", "Spelomgång slut",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

        options[0] = JOptionPane.YES_OPTION;
        options[1] = JOptionPane.NO_OPTION;
        options[2] = JOptionPane.CANCEL_OPTION;

        if (reply == JOptionPane.CANCEL_OPTION) {
            boardGUI.dispose();
            turnPlayer1 = true;
            boardGUI = new BoardGUI(this);
            boardGUI.getPnlPlayer1().setBorder(BorderFactory.createTitledBorder(multiPlayer[0].getUserName()));
            boardGUI.getPnlPlayer2().setBorder(BorderFactory.createTitledBorder(multiPlayer[1].getUserName()));
            boardGUI.revalidate();
        } else if (reply == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else if (reply == JOptionPane.NO_OPTION) {
            boardGUI.setVisible(false);
            menuGUI = new MenuGUI(this);
            musicController.playMusic("music/MenuMusic.wav");
        }
    }

    /**
     * Updated a users score and update in the view.
     */
    private void incrementScore(int pointsToAdd) {
        if (turnPlayer1) {
            int score = multiPlayer[0].getGameScore() + pointsToAdd;
            multiPlayer[0].setGameScore(score);
            boardGUI.setLblScore(score);
        } else {
            int score = multiPlayer[1].getGameScore() + pointsToAdd;
            multiPlayer[1].setGameScore(score);
            boardGUI.setLblScore2(score);
        }
    }
}
