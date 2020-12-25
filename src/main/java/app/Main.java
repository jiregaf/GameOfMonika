package app;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.sound.sampled.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
В прошлой версии здесь была почти вся логика программы, я постарался удалить лишнее и оставить здесь за графику.
 */


public class Main extends JPanel implements ActionListener {


    World world;
    int count = 1;
    Timer timer = new Timer(1, this);

    JFrame frame;
    static String message = "Boy of Monika";
    long startTime;
    long endTime;
    long timeOfGameStart = 0;
    Camera camera;
    public Main(JFrame frame, World world) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        this.frame = frame;
        timer.start();
        startTime = System.currentTimeMillis();
        this.world = world;

        for(int i = 0; i < world.elements.size(); i++) if (world.elements.get(i) instanceof Player) {
            frame.addKeyListener(new PlayerControl((Player) world.elements.get(i)));
            camera = new Camera(world,  ((Player) world.elements.get(i)), 0, 0);
        }
    }




    public void paint(Graphics g) {
        long a1;
        a1 = System.currentTimeMillis();
        super.paint(g);
         Graphics2D g2d = (Graphics2D) g;
            for (int i = 0; i < world.elements.size(); i++) {

                g2d.setColor(Color.blue);
                if (world.elements.get(i) instanceof Enemy) g2d.setColor(Color.red);
                if (world.elements.get(i) instanceof  Player) g2d.setColor(Color.green);
                if (world.elements.get(i) instanceof  Region) {
                    g2d.setColor(Color.pink);
                    g2d.drawRect(world.elements.get(i).x - camera.x, world.elements.get(i).y - camera.y, ((Region) world.elements.get(i)).width, ((Region) world.elements.get(i)).height);
                }
                if (world.elements.get(i).image != null) {
                    if ( world.elements.get(i) instanceof Player || world.elements.get(i) instanceof Monika)
                        if ( world.elements.get(i).left == false)  g2d.setTransform(AffineTransform.getScaleInstance(1, 1)); else {

                            g2d.setTransform(AffineTransform.getScaleInstance(-1, 1));
                            g2d.translate( -( (world.elements.get(i).x - camera.x) * 2 + world.elements.get(i).image.getWidth(null)) , 0);
                        }
                    g2d.drawImage(world.elements.get(i).image, world.elements.get(i).x - camera.x, world.elements.get(i).y - camera.y, null);
                    g2d.drawRect(world.elements.get(i).x - camera.x, world.elements.get(i).y - camera.y, world.elements.get(i).image.getWidth(null), world.elements.get(i).image.getHeight(null));
                    g2d.setTransform(AffineTransform.getScaleInstance(1, 1));
                }

            }

            if (timeOfGameStart == 0) timeOfGameStart = System.currentTimeMillis();
            Font f = new Font("Arial", Font.BOLD, 30);
            g2d.setColor(Color.red);
            g2d.setFont(f);
            g2d.drawString(message, 50, 50);
            g2d.drawString("camera.x = " + camera.x, 50, 80);
            g2d.drawString("player.x = " + camera.player.x, 50, 110);
            g2d.drawString("count of world elements: " + world.elements.size(), 50, 140);
            int tickcount = 0;
            for(int i = 0; i < world.elements.size(); i++) if (world.elements.get(i) instanceof Tickable) tickcount++;
            g2d.drawString("count of tickable world elements : " + tickcount, 50, 170);
            long curTime = System.currentTimeMillis();
            world.gameTime = (curTime - timeOfGameStart) / 1000 + "." + (curTime - timeOfGameStart) % 1000;
            g2d.drawString("time : " +world.gameTime, 50, 200);
            g2d.drawRect(frame.getWidth() / 2 - 2, frame.getHeight() / 2, 4, 4);

        g2d.drawString(Long.toString(System.currentTimeMillis() - a1), 50, 230);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        repaint();
    }

}

