package Game.Controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * This class contains code the functions for both the click sound and the points sound
 *
 * @author Yasir Kakar
 * @version 4.0
 */
public class ClickController {
    private Clip clip;

    /**
     * This method plays the audio for the different sounds like when the player clicks
     * on a card or gets a point and so on
     */
    public void click(String filename) {

        try {
            File file = new File(filename);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
