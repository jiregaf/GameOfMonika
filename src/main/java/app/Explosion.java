package app;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Explosion {
    int status = 0;
    Image curPos;
    ArrayList<Image> sprites = new ArrayList<>();


    public Explosion(){
        for(int i = 1; i <= 12; i++){
            sprites.add(new ImageIcon("explosion" + Integer.toString(i) + ".png").getImage());
        }
    }

    void animate(){
        if (status < 12){
            curPos = sprites.get(status);
            status++;
        }
    }
}
