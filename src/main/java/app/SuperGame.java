package app;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import java.io.File;
import java.io.IOException;

public class SuperGame {

    public static String IMAGE_RESOURCE = "images/";

    public  static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        JFrame frame = new JFrame("JustGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.add(new Main(frame));
        frame.setVisible(true);
    }
}
