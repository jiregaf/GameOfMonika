package app;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/*
Содержит все игровые элементы. Пока тут есть кое какая генерация карты из кода, но потом я планирую сделаю загрузку
данных карты из файла.
width, height - размеры ккарты, которые вполне себе преодолимы
 */
public class World {
    public ArrayList<WorldElement> elements = new ArrayList<WorldElement>();
    int width, height;
    String gameTime;
    Camera camera;

    public void add(WorldElement e) {
        elements.add(e);
    }

    public void generateNoTextureWorld(int width, int height, JFrame frame) {
        this.width = width;
        this.height = height;
        for (int i = 0; i < width ; i += 300) {
            add(new WorldElement(this, new ImageIcon(SuperGame.IMAGE_RESOURCE + "notexturetrava.png").getImage(), i, frame.getHeight() / 2 + 100));

        }

        add(new Player(this, new ImageIcon(SuperGame.IMAGE_RESOURCE + "1.png").getImage(), 700, 500));
        add(new Region(this, null, 3000, 400, 500, 300));

        add(new Monika(this, new ImageIcon(SuperGame.IMAGE_RESOURCE + "Monika.png").getImage(), 700, 500));

    }



}