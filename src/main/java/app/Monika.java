package app;

import app.WorldElement;

import javax.swing.*;
import java.awt.*;

public class Monika extends WorldElement implements Tickable{

    /*
        Это просто Моника, выполняющая задачу преследовать игрока
     */
    Task task = new FollowForPlayerTask(this);
    public Monika(World world, Image image, int x, int y) {
        super(world, image, x, y);
        left = true;
    }

    @Override
    public void tick() {
        task.doTask();
    }
}
