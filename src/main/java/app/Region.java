package app;

import java.awt.*;

/*
Этот класс нужно будет переделать пожалуй, ибо сейчас он построен под игрока а не под любой обьект,
но я его сделал, чтобы потестить смену режима движения камеры, которое меняется при входе в регион(это прямоугольник) и выходе
 К тому же доработка нужна будет ибо сейчас коллизия проходит по проверке только с X-ом, без Y-ка.
 Коллизия также проверяется через Tick()
 */

public class Region extends WorldElement implements Tickable{

    int width, height;
    Player player;
    boolean in = false;
    boolean out = false;
    public Region(World world, Image image, int x, int y, int width, int height) {
        super(world, image, x, y);
        for(int i = 0; i < world.elements.size(); i++){
            if (world.elements.get(i) instanceof Player) {

                player = ((Player)(world.elements.get(i)));
                break;
            }
        }
        this.width = width;
        this.height = height;
    }

    public boolean inRegion(){
        if ((player.x + player.image.getWidth(null) > x) && (x + width> player.x)||
                (x + width > player.x) && (player.x + player.image.getWidth(null) > x)){
            world.camera.camMove = new CameraMovePatrol(world.camera);
            in = true;
            out = false;
            return true;
        } else{
            in = false;
            out = true;
            return false;
        }

    }

    public boolean outRegion(){
        if ((player.x + player.image.getWidth(null) < x) ||
                (x + width < player.x)){
            world.camera.camMove = new CameraMoveForPlayer(world.camera);
            out = true;
            in = false;
            return true;
        } else {
            out = false;
            in = true;
            return false;
        }
    }

    @Override
    public void tick() {
        if (!in) inRegion();
        if (!out) outRegion();
    }
}
