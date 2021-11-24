package Game.Model;

/**
 * A representation of a logged in user. The user has user name and a score.
 *
 * @author Robert Rosencrantz, Adel Sabanovic, Sonja Peric, Yasir Kakar, Joakim Tell
 * @version 4.0
 */
public class User {
    private String userName;
    private int totalPoints;
    private int gameScore;

    /**
     * Constructor
     *
     * @param userName User input
     */
    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
}
