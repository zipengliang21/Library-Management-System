package Utility;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

//The following code is mostly based on the code from one of the 210 TA!
//It was modified by Zipeng, Liang
public class GenerateSound {
    private Clip clip;
    private String wavName;
    public GenerateSound(String wavName) {
        this.wavName = wavName;
        generateMusic();
    }

    public void generateMusic() {
        try {
            File f = new File(wavName);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(f.getAbsoluteFile());
            try {
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (LineUnavailableException e1) {
                e1.printStackTrace();
            }
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void stopMusic() {
        if (clip.isActive())
        clip.stop();

        else if (!clip.isActive())
            clip.start();
    }

    public Clip getClip() {
        return clip;
    }
}
