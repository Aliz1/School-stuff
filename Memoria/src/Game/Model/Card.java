package Game.Model;

import Game.Controller.ClickController;

import javax.swing.*;
import java.awt.*;

/**
 * The card class represents a card in the memory game.
 *
 * @author Robert Rosencrantz, Adel Sabanovic
 * @version 4.0
 */
public class Card extends JButton {
    private ClickController clickcontroller = new ClickController();
    private String pathSymbol;          // Location of image.
    private ImageIcon imageShowing;     // The visible front side of the card.
    private boolean matched = false;    // True if this card is matched with another card.

    /**
     * Initiate and construct a card.
     */
    public Card() {
        setPreferredSize((new Dimension(67, 89)));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.WHITE);
        setFocusPainted(false);
    }

    /**
     * Turn card to show symbol. The image scales to fit the Card size to to
     * maximize the image size without altering its format.
     */
    public void revealSymbol() {
        imageShowing = new ImageIcon(pathSymbol);
        clickcontroller.click("music/CardClick.wav");
        double imageWidth = imageShowing.getIconWidth();
        double imageHeight = imageShowing.getIconHeight();
        double cardHeight = getHeight();
        double cardWidth = getWidth();

        double scaleHeight = cardHeight / imageHeight;
        double scaleWidth = cardWidth / imageWidth;
        double scale = Math.min(scaleHeight, scaleWidth); // Get appropriate scale for this cards image.

        int width = (int) (imageWidth * scale);
        int height = (int) (imageHeight * scale);

        Image imageToScale = imageShowing.getImage();
        Image scaledImage = imageToScale.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        setIcon(scaledImageIcon);
    }

    /**
     * Turn card symbol down, backside up.
     */
    public void hideSymbol(int width, int height) {

        imageShowing = new ImageIcon("images/BackMan.png");

        Image imageToScale = imageShowing.getImage();
        Image scaledImage = imageToScale.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        setIcon(scaledImageIcon);
    }

    public String getPathSymbol() {
        return pathSymbol;
    }

    public void setPathSymbol(String pathSymbol) {
        this.pathSymbol = pathSymbol;
    }

    public void setMatched(boolean matched) {
        this.setBackground(new Color(215, 215, 215));
        this.matched = matched;
    }

    public boolean isMatched() {
        return matched;
    }
}
