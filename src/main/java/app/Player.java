package app;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
    Им то и управляет игрок. А управление я вынес в класс PlayerControl
    move - флажок двигается ли игрок. Далее моожно будет добавить другие флаги и анимации
 */
public class Player extends WorldElement implements Tickable{

    public boolean move = false;
    public PlayerControl playerControl;

    
    public Player(World world, Image image,int x,int y){
        super(world, image, x, y);
        this.animations = new ArrayList<Animation>();
        animations.add(new Animation(this));
        animations.get(0).name = "run";
        for(int i = 1; i <=10; i++ ){
            animations.get(0).animation.add(new ImageIcon(SuperGame.IMAGE_RESOURCE + "run"+ i + ".png").getImage());
        }
        animations.add(new Animation(this));
        animations.get(1).name = "idle";
        for(int i = 1; i <=8; i++){
            animations.get(1).animation.add(new ImageIcon(SuperGame.IMAGE_RESOURCE + i + ".png").getImage());
        }
    }

    IdlePlayerTask task2 = new IdlePlayerTask(this);
    @Override
    public void tick() {
        if (!playerControl.control()) task2.doTask();
    }

}
