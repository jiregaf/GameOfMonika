package app;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import java.io.File;
import java.io.IOException;

/*
Здесь также прошли изменения. Я сделал второй поток для вычисления логики мира. Первый отвечает за графику.
Возможно потом нужно будет добавить для отлова приходящих по сети сообщений
 */
public class SuperGame {

    public static String IMAGE_RESOURCE = "images/";
    public static int win_width = 1366;
    public static int win_height = 768;

    public  static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        JFrame frame = new JFrame("JustGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
        World world = new World();
        world.generateNoTextureWorld(10000, 1000, frame);
        Main graphics = new Main(frame, world);
        frame.add(graphics);
        frame.setVisible(true);
        Thread worldLogicTick = new Thread(){
            @Override
            public void run() {
                graphics.camera.tick();
                for(int i = 0; i < world.elements.size();i++){
                    if (world.elements.get(i) instanceof Tickable){
                        ((Tickable) world.elements.get(i)).tick();
                    }
                }
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        while(true){
            worldLogicTick.run();

        }
    }
}
