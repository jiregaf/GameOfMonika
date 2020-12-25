package app;

import java.awt.*;
import java.util.ArrayList;

/*
Элемент мира. Конечно под вопросом стоит надобность всем элементам в картинке и списке анимации, наприме для региона
это не надо, Моника, Игрок, Враг наследуются от этого класса и расширяют его функционал
 */
public class WorldElement {
    Image image;
    int x, y;
    String name;
    ArrayList<Animation> animations;
    World world;
    boolean left = false;

    public WorldElement(World world, Image image, int x, int y){
        this.image = image;
        this.x = x;
        this.y = y;
        this.world = world;
    }

}
