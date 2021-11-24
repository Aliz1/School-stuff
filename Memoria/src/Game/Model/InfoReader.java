package Game.Model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Reader of two text files to get formatted strings to a map.
 * The map is a container for corresponding pairs of symbol strings and info strings.
 *
 * @author Joakim Tell och Robert Rozencrantz
 * @version 4.0
 */
public class InfoReader {

    private Map<String, String> infoMap;
    private List<String> infoList;
    private List<String> symbolsList;

    /**
     * Constructor.
     *
     * @param infoPath   source for
     * @param symbolPath reads the symbolfile
     */
    public InfoReader(String infoPath, String symbolPath) {
        infoMap = new HashMap<>();
        symbolsList = new ArrayList<>();
        infoList = new ArrayList<>();
        readTextFile(infoList, new File(infoPath));
        readTextFile(symbolsList, new File(symbolPath));
        createHashMap();
    }

    /**
     * Reads each line in a txt file and puts them as strings in a list.
     *
     * @param list the target list to store the strings
     * @param file the source file to get text
     */
    private void readTextFile(List<String> list, File file) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file), StandardCharsets.UTF_8))) {
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                list.add(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a container for corresponding pairs of symbol strings and info strings.
     * The symbol string acts as a key for searching.
     */
    public void createHashMap() {
        for (int i = 0; i < symbolsList.size(); i++) {
            String symbolKey = symbolsList.get(i);
            String infoValue = infoList.get(i);
            infoMap.put(symbolKey, infoValue);
        }
    }

    public Map<String, String> getInfoMap() {
        return infoMap;
    }
}
