package Game.Model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Creates two lists of text strings. This simulates some pairs of
 * multiplication problems with solved answers on corresponding positions in the lists.
 *
 * @author Robert Rosencrantz
 * @version 4.0
 */
public class NumbersScanner {
    private final int SIZE = 50;
    private Random random;
    private ArrayList<String> problems;     // The multiplication problems.
    private ArrayList<String> solved;       // The answers to the problems.

    /**
     * Construct and initialize the lists.
     */
    public NumbersScanner() {
        createLists();
    }

    /**
     * Creates the lists from a random variable, representing the problems and answers as strings.
     */
    private void createLists() {
        random = new Random();
        problems = new ArrayList<>();
        solved = new ArrayList<>();

        for (int i = 0; i < SIZE; i++) {
            int factor1 = random.nextInt(10);
            int factor2 = random.nextInt(10);
            // Even out (sufficiently) the probability of matching factors (Tested for a large number).
            if (factor2 != factor1) {
                factor2 = random.nextInt(10);
            }
            int product = (factor1 * factor2);
            String problem = factor1 + "*" + factor2;
            String productStr = String.valueOf(product);

            problems.add(problem);
            solved.add(productStr);
        }
    }

    /**
     * Access to the list representing some multiplication problems.
     *
     * @return the list
     */
    public ArrayList<String> getProblems() {
        return problems;
    }

    /**
     * Access to the list representing the answers to the problems list.
     *
     * @return the list
     */
    public ArrayList<String> getSolved() {
        return solved;
    }
}