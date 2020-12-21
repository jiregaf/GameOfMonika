package app;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class World {
    public ArrayList<WorldElement> elements = new ArrayList<WorldElement>();

    public boolean monikaMessage = true;
    int allhearts = 5;
    public ArrayList<WorldElement> hearts = new ArrayList<>();
    int playerFoundHearts = 0;
    public int monikaMessageCounter = 0;
    public ArrayList<String> monikaMessages = new ArrayList<>();
    public ArrayList<Enemy> enemies = new ArrayList<>();
    public ArrayList<Explosion> explosions = new ArrayList<>();
    int width, height;
    boolean secondWorld = false;
    String gameTime;
    int kills = 0;

    public World() {
        try {
            File file = new File(SuperGame.IMAGE_RESOURCE + "monika.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                // считываем остальные строки в цикле
                line = reader.readLine();
                monikaMessages.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(WorldElement e) {
        elements.add(e);
    }


    public void move(int x, int y) {


        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).x += x;
            elements.get(i).y += y;
        }

        for (int i = 0; i < hearts.size(); i++) {
            hearts.get(i).x += x;
            hearts.get(i).y += y;
        }


    }

    public void generateNoTextureWorld(int width, int height, JFrame frame) {
        this.width = width;
        this.height = height;
        for (int i = 0; i < width ; i += 300) {
            add(new WorldElement(new ImageIcon(SuperGame.IMAGE_RESOURCE + "notexturetrava.png").getImage(), i, frame.getHeight() / 2 + 100));


        }
        for (int i = elements.get(0).x ; i > -width; i -= 300) {
            add(new WorldElement(new ImageIcon(SuperGame.IMAGE_RESOURCE + "notexturetrava.png").getImage(), i + frame.getWidth() / 2, height + elements.get(0).y + 150));
        }

        for (int i = elements.get(0).y; i < elements.get(0).y + height; i += 300) {
            add(new WorldElement(new ImageIcon(SuperGame.IMAGE_RESOURCE + "notexturetravaVertical.png").getImage(), elements.get(0).x, i));
        }

        generateEnemies(50, frame,  -width, elements.get(0).x - 500, frame.getHeight() / 2 + height );
        generateEnemies(50, frame,  elements.get(0).x, 0, frame.getHeight() / 2 + 100 );

        add(new Monika(new ImageIcon(SuperGame.IMAGE_RESOURCE + "Monika.png").getImage(), -(width - frame.getWidth() / 2 - 600), height + elements.get(0).y + 40));
        // add(new WorldElement(new ImageIcon("map.jpeg").getImage(), frame.getWidth()
        //        / 2 ,  0));
    }

    public void deleteWorld() {

    }

    public void showMonica(Graphics2D g, JFrame frame) {
        Image image = new ImageIcon(SuperGame.IMAGE_RESOURCE + "monika.jpg").getImage();
        g.drawImage(image, 0, 0, null);
        Font font = new Font("TimesRoman", Font.BOLD, 22);
        g.setFont(font);
        g.setColor(Color.white);
        if (monikaMessages.get(monikaMessageCounter).equals("name")) {
            monikaMessages.set(monikaMessageCounter, Main.message);
        }
        g.drawString(monikaMessages.get(monikaMessageCounter), frame.getWidth() / 2 - 300, frame.getHeight() / 2 + 200);
    }


    public void generateNewWorld(int width, JFrame frame) {
        System.out.println("generated new world");
        elements.get(0).x = frame.getWidth() / 2;
        elements.get(0).y = frame.getHeight() / 2 + 100;
        elements.get(1).x = elements.get(0).x + 300;
        elements.get(1).y = frame.getHeight() / 2 + 100;
        elements.get(2).x = elements.get(1).x + 300;
        elements.get(2).y = frame.getHeight() / 2 + 100;
        for (int i = elements.size() - 1; i > 2; i--) {
            if (elements.get(i).getClass().toString().equals("class WorldElement")) elements.remove(i);
        }
        secondWorld = true;
    }


    public void generateEnemies(int count, JFrame frame , int x1, int x2, int y) {
        for (int i = 0; i < count; i++) {
            int r = (int) (Math.random() * 2);

                Enemy enemy;
                enemy = new Enemy(new ImageIcon(SuperGame.IMAGE_RESOURCE + "enemy.png").getImage(), (int)(Math.random() * (x1) + x2),  y, frame);
                add(enemy);
                enemies.add(enemy);

        }
    }
}