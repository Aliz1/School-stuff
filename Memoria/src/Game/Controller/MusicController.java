package Game.Controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


/**
 * This class contains all the code for the music functions in the SettingsGUI
 *
 * @author Yasir Kakar
 * @version 4.0
 */
public class MusicController {
    private static Clip clip;

    /**
     * This method plays the music chosen for the background music in a loop
     */
    public void playMusic(String filename) {
        try {
            File file = new File(filename);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This class allows the user to turn off the music either through
     * the settingsGUI or by closing the program
     */
    public void stopMusic() {
        clip.stop();
    }

}
