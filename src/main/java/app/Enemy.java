package app;

import javax.swing.*;
import java.awt.*;

public class Enemy extends WorldElement{
    public boolean stop = false;
    JFrame frame;
    boolean attackStatus = true;
    boolean died = false;
    double forY = 0;
    int xSpeed;
    double ySpeed;
    int amp;
    public Enemy(Image image, int x, int y, JFrame frame) {
        super(image, x, y);
        this.frame = frame;
        xSpeed = (int) (Math.random() * 13 + 7);
        amp = (int) (Math.random() * 20 + 20);
        ySpeed = Math.random();
    }

    public void attack(){
        if (!stop)
        if (x > frame.getWidth() / 2){
            x-=xSpeed;
            y = (int) (Math.sin(forY) * amp) + frame.getHeight() / 2;
            forY+=ySpeed;
        }else {
            x+=xSpeed;
            y = (int) (Math.sin(forY) * amp) + frame.getHeight() / 2;
            forY+=ySpeed;
        }
    }

    public void fly(){
        y-=3;
    }
}
