package app;

import app.WorldElement;

import javax.swing.*;
import java.awt.*;

public class Monika extends WorldElement {

    public boolean isShowMonika = false;

    public Monika(Image image, int x, int y) {
        super(image, x, y);
        System.out.println(getClass().toString());
    }


    public void logic(JFrame frame, Graphics2D g2d, World world, Main main){
        if (frame.getWidth() / 2 - x < 200)
        if (!isShowMonika){
            world.monikaMessageCounter = 0;
            isShowMonika = true;
            world.monikaMessage = true;
        }

        if ((main.pers.orientationVert == false) && (isShowMonika)){
            if (frame.getHeight() / 2 - y > 50) y+=10; else if (frame.getHeight() / 2 - y < 50) y -=25; else {
                if ((frame.getWidth() / 2 - x > 150) && (isShowMonika)) x += 25;
                else if ((x - frame.getWidth() / 2 > 150) && isShowMonika) x -= 25;
            }
        } else if (isShowMonika){
            if (frame.getWidth() / 2 - x > 50) x+=10; else if (frame.getWidth() / 2 - x < 50) x -=25; else{
                if ((frame.getHeight() / 2 - y > 150) && (isShowMonika)) y += 25;
                else if ((y - frame.getHeight() / 2 > 150) && isShowMonika) y -= 25;
            }

        }
    }
}
