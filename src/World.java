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
   public World(){
       try {
           File file = new File("monika.txt");
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
   public void add(WorldElement e){
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

    public void generateNoTextureWorld(int width, JFrame frame){
      for(int i = -width / 2; i < width / 2; i+=300){
           add(new WorldElement(new ImageIcon("notexturetrava.png").getImage(), frame.getWidth()
                  / 2 + i,  frame.getHeight() / 2 + 100));
           add(new WorldElement(new ImageIcon("notexture.png").getImage(), frame.getWidth()
                   / 2 + i * 2,  frame.getHeight() / 2 - 600));

       }

       for(int i = 0; i < 5; i++){
           hearts.add(new WorldElement( new ImageIcon("heart.png").getImage(), 100, 100));
           hearts.get(i).x = - 100000 / 2 + i * 20000;
           hearts.get(i).y = frame.getHeight() / 2 - 300;
       }
       for(int i = 0; i < 100; i++) {
           Enemy enemy;
           enemy = new Enemy(new ImageIcon("enemy.png").getImage(), (int) (frame.getWidth() / 2 + Math.pow(-1, i) * i * 300), frame.getHeight() / 2, frame);
           add(enemy);
           enemies.add(enemy);
       }
       // add(new WorldElement(new ImageIcon("map.jpeg").getImage(), frame.getWidth()
        //        / 2 ,  0));
    }

    public void deleteWorld(){

    }

    public void showMonica(Graphics2D g, JFrame frame){
       Image image = new ImageIcon("monika.jpg").getImage();
       g.drawImage(image, 0, 0, null);
       Font font = new Font("TimesRoman", Font.BOLD, 22);
       g.setFont(font);
       g.setColor(Color.white);
       if (monikaMessages.get(monikaMessageCounter).equals("name")) {
           monikaMessages.set(monikaMessageCounter, Main.message);
       }
       g.drawString(monikaMessages.get(monikaMessageCounter), frame.getWidth() / 2 - 300, frame.getHeight() / 2 + 200);
    }
}
