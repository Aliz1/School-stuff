package Game.Model;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class handels the symbols for the cards in the game.
 *
 * @author Robert Rosencrantz, Sonja Peric.
 * @version 4.0
 */
public class CardDeck {

    /**
     * Creates and mixes paris of symbols for the boardGUI.
     */
    public ArrayList<String> addSymbols() {
        int MAX_SYMBOLS_PAIRS = 11;   // The number of pairs visible on the board. = 12 minus the pair of jokers.

        ArrayList<String> cardSymbolPaths = new ArrayList<String>(MAX_SYMBOLS_PAIRS); // The list of paired symbol strings to return.

        // Always add the jokers.
        cardSymbolPaths.add("images/Joker.png");
        cardSymbolPaths.add("images/Joker2.png");

        // Add symbol paths on corresponding index.
        ArrayList<String> l1 = SymbolListOne();
        ArrayList<String> l2 = SymbolListTwo();


        int TOTAL_SYMBOLS_AVAILABLE = l1.size(); // = l2.size(). Number of symbols in the bank in total.

        Random random = new Random();
        ArrayList<Integer> randomNbrs = new ArrayList<>(MAX_SYMBOLS_PAIRS);

        // Add pairs of symbol paths based on some random numbers.
        for (int i = 0; i < MAX_SYMBOLS_PAIRS; i++) {
            int newNbr = random.nextInt(TOTAL_SYMBOLS_AVAILABLE);   // Random numbers based on total symbol paths.
            if (!randomNbrs.contains(newNbr)) {                     // We want only one of each path.
                randomNbrs.add(newNbr);                             // Add to random numbers list.
                cardSymbolPaths.add(l1.get(newNbr));                // Add path based on random nbr.
                cardSymbolPaths.add(l2.get(newNbr));
            } else {
                i--;                                                // Adjust if already number i list.
            }
        }

        return cardSymbolPaths;
    }

    /**
     * This method stores symbols for the cards.
     *
     * @return cardSymbolPaths which contains one half of the symbols.
     */
    private ArrayList<String> SymbolListOne() {

        ArrayList<String> cardSymbolPaths = new ArrayList<>();

        cardSymbolPaths.add("images/Centimeter.png");
        cardSymbolPaths.add("images/Cylinder.png");
        cardSymbolPaths.add("images/Deci.png");
        cardSymbolPaths.add("images/DEka.png");
        cardSymbolPaths.add("images/Diameter.png");
        cardSymbolPaths.add("images/Dussin.png");
        cardSymbolPaths.add("images/Hekto.png");
        cardSymbolPaths.add("images/Kilo.png");
        cardSymbolPaths.add("images/Klot.png");
        cardSymbolPaths.add("images/Likbenttriangel.png");
        cardSymbolPaths.add("images/Medelvärde.png");
        cardSymbolPaths.add("images/Milli.png");
        cardSymbolPaths.add("images/Origo.png");
        cardSymbolPaths.add("images/Parallelogram.png");
        cardSymbolPaths.add("images/Radie.png");
        cardSymbolPaths.add("images/RektangelnsArea.png");
        cardSymbolPaths.add("images/Rtvinkel.png");
        cardSymbolPaths.add("images/SpetsigVinkel.png");
        cardSymbolPaths.add("images/Tjog.png");
        cardSymbolPaths.add("images/TriangelnsArea.png");
        cardSymbolPaths.add("images/TRubbigVinkel1.png");
        cardSymbolPaths.add("images/Typvärde.png");
        cardSymbolPaths.add("images/UddaTal.png");
        cardSymbolPaths.add("images/Agebra5.PNG");
        cardSymbolPaths.add("images/Algebra1.PNG");
        cardSymbolPaths.add("images/ALgebra3.PNG");
        cardSymbolPaths.add("images/Bråk.PNG");
        cardSymbolPaths.add("images/DIvision.PNG");
        cardSymbolPaths.add("images/minus.png");
        cardSymbolPaths.add("images/Multiplikation.png");
        cardSymbolPaths.add("images/pi3.jpg");
        cardSymbolPaths.add("images/plus.png");
        cardSymbolPaths.add("images/Unit.PNG");
        cardSymbolPaths.add("images/Ut.PNG");

        return cardSymbolPaths;
    }

    /**
     * This method stores symbols for the cards.
     *
     * @return cardSymbolPaths which contains the other half of the symbols.
     */
    private ArrayList<String> SymbolListTwo() {

        ArrayList<String> cardSymbolPaths = new ArrayList<>();

        cardSymbolPaths.add("images/Centimeter2.png");
        cardSymbolPaths.add("images/Cylinder2.png");
        cardSymbolPaths.add("images/Deci2.png");
        cardSymbolPaths.add("images/DEka2.png");
        cardSymbolPaths.add("images/Diameter2.png");
        cardSymbolPaths.add("images/Dussin2.png");
        cardSymbolPaths.add("images/Hekto2.png");
        cardSymbolPaths.add("images/Kilo2.png");
        cardSymbolPaths.add("images/Klot2.png");
        cardSymbolPaths.add("images/Likbenttriangel2.png");
        cardSymbolPaths.add("images/Medelvärde2.png");
        cardSymbolPaths.add("images/Milli2.png");
        cardSymbolPaths.add("images/Origo2.png");
        cardSymbolPaths.add("images/Parallellogram2.png");
        cardSymbolPaths.add("images/Radie2.png");
        cardSymbolPaths.add("images/RektangelnsArea2.png");
        cardSymbolPaths.add("images/Rtvinkel2.png");
        cardSymbolPaths.add("images/SpetsigVinkel2.png");
        cardSymbolPaths.add("images/Tjog2.png");
        cardSymbolPaths.add("images/TriangelnsArea2.png");
        cardSymbolPaths.add("images/TRubbigVinkel2.png");
        cardSymbolPaths.add("images/Typvärde2.png");
        cardSymbolPaths.add("images/UddaTal2.png");
        cardSymbolPaths.add("images/Agebra6.PNG");
        cardSymbolPaths.add("images/Algebra2.PNG");
        cardSymbolPaths.add("images/ALgebra4.PNG");
        cardSymbolPaths.add("images/Bråk2.PNG");
        cardSymbolPaths.add("images/DIvision2.PNG");
        cardSymbolPaths.add("images/minus2.png");
        cardSymbolPaths.add("images/Multiplikation2.PNG");
        cardSymbolPaths.add("images/pi2.jpg");
        cardSymbolPaths.add("images/plus2.png");
        cardSymbolPaths.add("images/Unit2.PNG");
        cardSymbolPaths.add("images/Ut2.PNG");

        return cardSymbolPaths;
    }

}
