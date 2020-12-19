package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.sound.sampled.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Main extends JPanel implements ActionListener {


    Player pers = new Player();
    World world = new World();
    boolean monika = false;
    boolean sa = false;
    boolean uri = false;
    int count = 1;
    static int treestat = 0;
    Timer timer = new Timer(30, this);

    JFrame frame;
    static String message = "Character";
    boolean isA = false;
    boolean isD = false;
    boolean isJ = false;
    long startTime;
    long endTime;

    public Main(JFrame frame) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        this.frame = frame;
        timer.start();
        startTime = System.currentTimeMillis();
        world.generateNoTextureWorld(10000, frame);

//        ServerSocket sersock = new ServerSocket(5000);
//        System.out.println("server is ready");  //  message to know the server is running
//        Socket sock = sersock.accept();
//
//        InputStream istream = sock.getInputStream();
//        DataInputStream dstream = new DataInputStream(istream);
//        message = dstream.readLine();
//        System.out.println(message);
//        dstream .close(); istream.close(); sock.close(); sersock.close();


        this.frame.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                int key = e.getKeyCode();
                if (key == KeyEvent.VK_A)
                    isA = true;
                if (key == KeyEvent.VK_D)
                    isD = true;
                if (key == KeyEvent.VK_J)
                    isJ = true;



                if (isJ && pers.canKick && !pers.kickStatus){
                    endTime = System.currentTimeMillis();
                    if (pers.reload <= endTime - startTime) {
                        pers.kickStatus = true;
                        pers.canKick = false;
                        startTime = System.currentTimeMillis();
                    }

                }
                if (key == KeyEvent.VK_SPACE){
                    if (world.monikaMessage) {
                        world.monikaMessageCounter++;
                        if (world.monikaMessages.get(world.monikaMessageCounter).equals("close"))
                        world.monikaMessage = false;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_A)
                    isA = false;
                if (key == KeyEvent.VK_D)
                    isD = false;
                if (key == KeyEvent.VK_J)
                    isJ = false;
                if (!(isA || isD)){
                    if (!pers.stay) {
                        pers.stay = true;
                        pers.status = 0;
                    }
                }
                if (!isJ){
                  // pers.kickStatus = false;
                    pers.canKick = true;
                }
                //if (!isD) pers.left = true;
                //if (!isA) pers.left = false;
            }
        });


    }




    public void paint(Graphics g) {
        super.paint(g);
        if (isA && isD) pers.stay = true; else
        if (isA){
            pers.left = true;
            pers.stay = false;
            if(world.elements.get(0).x >=frame.getWidth() / 2 - 45){
                if (pers.orientationVert == false) {
                    for(int i = 0; i< world.enemies.size();i++) {
                        world.enemies.get(i).image = new ImageIcon(SuperGame.IMAGE_RESOURCE + "enemydied.png").getImage();
                        world.enemies.get(i).stop = true;
                    }
                    world.move(120, -100);
                    pers.orientationVert = true;
                }
                world.move(0, -30);
            }
             else
            world.move(30, 0);
        } else
        if (isD){
            pers.left = false;
            pers.stay = false;
            if (world.elements.get(0).y > frame.getHeight() / 2 - 20){
                if (pers.orientationVert == true) {
                    world.move(-100, 80);
                    pers.orientationVert = false;
                    for(int i = 0; i< world.enemies.size();i++) {
                        world.enemies.get(i).image = new ImageIcon(SuperGame.IMAGE_RESOURCE + "enemy.png").getImage();
                        world.enemies.get(i).stop = false;
                    }
                }
            }

            if (pers.orientationVert)
                world.move(0, 30); else
            world.move(-30, 0);
        }

        Graphics2D g2d = (Graphics2D) g;


        if (world.monikaMessage) {
            world.showMonica(g2d, frame);
        } else {
            for(int i = 0; i < world.enemies.size(); i++){
                if (pers.kickStatus){
                    if (pers.left){
                        if ((world.enemies.get(i).x <= frame.getWidth() / 2 + 20) && (world.enemies.get(i).x  >= frame.getWidth() / 2 - 150)){
                            world.enemies.get(i).image = new ImageIcon(SuperGame.IMAGE_RESOURCE + "enemydied.png").getImage();
                            world.enemies.get(i).died = true;
                            world.enemies.get(i).attackStatus = false;
                        }
                    } else if ((world.enemies.get(i).x <= frame.getWidth() / 2 + 50) && (world.enemies.get(i).x  >= frame.getWidth() / 2 - 20)){
                        world.enemies.get(i).image = new ImageIcon(SuperGame.IMAGE_RESOURCE + "enemydied.png").getImage();
                        world.enemies.get(i).died = true;
                        world.enemies.get(i).attackStatus = false;
                    }
                }
            }
            for (int i = 0; i < world.elements.size(); i++) {
                g2d.drawImage(world.elements.get(i).image, world.elements.get(i).x, world.elements.get(i).y, null);
            }
            for (int i = 0; i < world.hearts.size(); i++) {
                if ((world.hearts.get(i).x < frame.getWidth() / 2) && (world.hearts.get(i).x + 600 > frame.getWidth() / 2)) {
                    world.playerFoundHearts++;
                    world.hearts.remove(i);
                }
            }
            for(int k = 0; k < world.enemies.size(); k++){
                if (world.enemies.get(k).attackStatus)
                world.enemies.get(k).attack(); else world.enemies.get(k).fly();
                if ((world.enemies.get(k).y < 200) && (world.enemies.get(k).died)){
                    world.enemies.get(k).image = null;
                    for (int i = 0; i < world.elements.size(); i++) {
                        if (world.elements.get(i).image == null){
                            world.elements.remove(i);
                            world.enemies.remove(k);
                        }
                    }
                } else
                if  (!world.enemies.get(k).died)
                    if (((world.enemies.get(k).x <= frame.getWidth() / 2 ) && (world.enemies.get(k).x + 50 > frame.getWidth() / 2))
                        || ((world.enemies.get(k).x >= frame.getWidth() / 2 - 90) &&(world.enemies.get(k).x < frame.getWidth() / 2) ) )
                    {
                    pers.curHp -= 1;
                    world.explosions.add(new Explosion());
                    world.enemies.get(k).image = null;
                    for (int i = 0; i < world.elements.size(); i++) {
                        if (world.elements.get(i).image == null){
                            world.elements.remove(i);
                            world.enemies.remove(k);

                        }
                    }
                }                                                                                                                                                   
            }
            if (world.playerFoundHearts == world.allhearts) {
                world.monikaMessageCounter = 0;
                world.showMonica(g2d, frame);
            }
            for (int i = 0; i < world.hearts.size(); i++) {
                g2d.drawImage(world.hearts.get(i).image, world.hearts.get(i).x, world.hearts.get(i).y, null);
            }


            pers.animate();

            if (world.explosions.size() > 0){
                for(int i = 0; i < world.explosions.size(); i++){
                    if (world.explosions.get(i).status == 12){
                        world.explosions.remove(i);
                        continue;
                    }
                    world.explosions.get(i).animate();
                    g2d.drawImage(world.explosions.get(i).curPos, frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 100, null);
                }
            }
            //  g2d.drawImage(pers.getPos(), 100, frame.getHeight() / 2, null);
            if (pers.orientationVert) {
                g2d.rotate(-190, frame.getWidth() / 2, frame.getHeight() / 2);
                if (pers.left)
                    g2d.drawImage(pers.getPos(), frame.getWidth() / 2 , frame.getHeight() / 2, frame.getWidth() / 2 - pers.getPos().getWidth(null), pers.getPos().getHeight(null) + frame.getHeight() / 2, 0, 0, pers.getPos().getWidth(null), pers.getPos().getHeight(null), null);
                else
                    g2d.drawImage(pers.getPos(), frame.getWidth() / 2 - 50, frame.getHeight() / 2, null);
            } else
            if (pers.left)
                g2d.drawImage(pers.getPos(), frame.getWidth() / 2, frame.getHeight() / 2, frame.getWidth() / 2 - pers.getPos().getWidth(null), pers.getPos().getHeight(null) + frame.getHeight() / 2, 0, 0, pers.getPos().getWidth(null), pers.getPos().getHeight(null), null);
            else
                g2d.drawImage(pers.getPos(), frame.getWidth() / 2 - 50, frame.getHeight() / 2, null);
            if (pers.kickStatus){
                Image fire;
                switch (pers.kickProcStat){
                    case 0:
                        fire = new ImageIcon(SuperGame.IMAGE_RESOURCE + "fire1.png").getImage();
                        pers.kickProcStat++;
                        if (pers.left)
                            g2d.drawImage(fire, frame.getWidth() / 2, frame.getHeight() / 2, frame.getWidth() / 2 - fire.getWidth(null), fire.getHeight(null) + frame.getHeight() / 2, 0, 0, fire.getWidth(null), fire.getHeight(null), null);
                        else
                            g2d.drawImage(fire, frame.getWidth() / 2 - 50, frame.getHeight() / 2, null);
                        break;
                    case 1:
                        fire = new ImageIcon(SuperGame.IMAGE_RESOURCE + "fire2.png").getImage();
                        pers.kickProcStat++;
                        if (pers.left)
                            g2d.drawImage(fire, frame.getWidth() / 2, frame.getHeight() / 2, frame.getWidth() / 2 - fire.getWidth(null), fire.getHeight(null) + frame.getHeight() / 2, 0, 0, fire.getWidth(null), fire.getHeight(null), null);
                        else
                            g2d.drawImage(fire, frame.getWidth() / 2 - 50, frame.getHeight() / 2, null);
                        break;
                    case 2:
                        fire = new ImageIcon(SuperGame.IMAGE_RESOURCE + "fire3.png").getImage();
                        pers.kickProcStat++;
                        if (pers.left)
                            g2d.drawImage(fire, frame.getWidth() / 2, frame.getHeight() / 2, frame.getWidth() / 2 - fire.getWidth(null), fire.getHeight(null) + frame.getHeight() / 2, 0, 0, fire.getWidth(null), fire.getHeight(null), null);
                        else
                            g2d.drawImage(fire, frame.getWidth() / 2 - 50, frame.getHeight() / 2, null);
                        break;
                    case 3:
                        fire = new ImageIcon(SuperGame.IMAGE_RESOURCE + "fire4.png").getImage();
                        pers.kickProcStat++;
                        if (pers.left)
                            g2d.drawImage(fire, frame.getWidth() / 2, frame.getHeight() / 2, frame.getWidth() / 2 - fire.getWidth(null), fire.getHeight(null) + frame.getHeight() / 2, 0, 0, fire.getWidth(null), fire.getHeight(null), null);
                        else
                            g2d.drawImage(fire, frame.getWidth() / 2 - 50, frame.getHeight() / 2, null);
                        break;
                    case 4:
                        fire = new ImageIcon(SuperGame.IMAGE_RESOURCE + "fire5.png").getImage();
                        pers.kickProcStat++;
                        if (pers.left)
                            g2d.drawImage(fire, frame.getWidth() / 2, frame.getHeight() / 2, frame.getWidth() / 2 - fire.getWidth(null), fire.getHeight(null) + frame.getHeight() / 2, 0, 0, fire.getWidth(null), fire.getHeight(null), null);
                        else
                            g2d.drawImage(fire, frame.getWidth() / 2 - 50, frame.getHeight() / 2, null);
                        break;
                    case 5:
                        fire = new ImageIcon(SuperGame.IMAGE_RESOURCE + "fire6.png").getImage();
                        pers.kickProcStat++;
                        if (pers.left)
                            g2d.drawImage(fire, frame.getWidth() / 2, frame.getHeight() / 2, frame.getWidth() / 2 - fire.getWidth(null), fire.getHeight(null) + frame.getHeight() / 2, 0, 0, fire.getWidth(null), fire.getHeight(null), null);
                        else
                            g2d.drawImage(fire, frame.getWidth() / 2 - 50, frame.getHeight() / 2, null);
                        break;
                    case 6:
                        fire = new ImageIcon(SuperGame.IMAGE_RESOURCE + "fire7.png").getImage();
                        pers.kickProcStat++;
                        if (pers.left)
                            g2d.drawImage(fire, frame.getWidth() / 2, frame.getHeight() / 2, frame.getWidth() / 2 - fire.getWidth(null), fire.getHeight(null) + frame.getHeight() / 2, 0, 0, fire.getWidth(null), fire.getHeight(null), null);
                        else
                            g2d.drawImage(fire, frame.getWidth() / 2 - 50, frame.getHeight() / 2, null);
                        break;
                    case 7:
                        fire = new ImageIcon(SuperGame.IMAGE_RESOURCE + "fire8.png").getImage();
                        pers.kickProcStat++;
                        if (pers.left)
                            g2d.drawImage(fire, frame.getWidth() / 2, frame.getHeight() / 2, frame.getWidth() / 2 - fire.getWidth(null), fire.getHeight(null) + frame.getHeight() / 2, 0, 0, fire.getWidth(null), fire.getHeight(null), null);
                        else
                            g2d.drawImage(fire, frame.getWidth() / 2 - 50, frame.getHeight() / 2, null);
                        break;
                    case 8:
                        fire = new ImageIcon(SuperGame.IMAGE_RESOURCE + "fire9.png").getImage();
                        pers.kickProcStat++;
                        if (pers.left)
                            g2d.drawImage(fire, frame.getWidth() / 2, frame.getHeight() / 2, frame.getWidth() / 2 - fire.getWidth(null), fire.getHeight(null) + frame.getHeight() / 2, 0, 0, fire.getWidth(null), fire.getHeight(null), null);
                        else
                            g2d.drawImage(fire, frame.getWidth() / 2 - 50, frame.getHeight() / 2, null);
                        break;
                    case 9:
                        pers.kickStatus = false;
                        pers.kickProcStat = 0;

                        break;
                }
            }
            if (pers.orientationVert)
            g2d.rotate(190, frame.getWidth() / 2, frame.getHeight() / 2);
            Font f = new Font("Arial", Font.BOLD, 30);
            g2d.setColor(Color.red);
            g2d.setFont(f);
            g2d.drawString(message, 50, 50);
            g2d.drawString(Integer.toString(world.playerFoundHearts) + " / " + Integer.toString(world.allhearts), 50, 80);
            g2d.drawString(Integer.toString(pers.curHp) + " / " + Integer.toString(pers.maxHp), 50, 110);
            g2d.drawString("A : " + Boolean.toString(isA) , 50, 140);
            g2d.drawString("D : " + Boolean.toString(isD) , 50, 170);
            g2d.drawString("J : " + Boolean.toString(isJ) , 50, 200);
            g2d.drawString("kick status : " + Boolean.toString(pers.kickStatus) , 50, 230);
            g2d.drawString("kick can : " + Boolean.toString(pers.canKick) , 50, 260);
            g2d.drawString("is left : " + Boolean.toString(pers.left) , 50, 290);

            g2d.drawRect(frame.getWidth() / 2 - 2, frame.getHeight() / 2, 4, 4);
    }
    }
   static void tree(int x, int y, double a, int l, Graphics g) {
        int x1, y1, p, s;
        double a1;
        if (l < 15)
                return;
        x1 = (int) (x + l*Math.cos(a));
        y1 = (int) (y + l*Math.sin(a));
        treestat++;
        if (treestat > 1000) return;
        if (l > 100)
        p = 100;
	else
        p = l;
        if (p < 100) {
            //Генерация листьев
            if (Math.random() > 0.5)
            g.setColor(Color.black);
		else


            for (int i = 0; i < 3; i++)
                g.drawLine(x + i, y, x1, y1);

        }
	else {
            //Генерация веток

            for(int i = 0; i < p / 6; i++)
                g.drawLine(x + i - (p / 12), y, x1, y1);
        }
            //Следующие ветки
            for(int i = 0; i < Math.random()* 9; i++)
            {
            s= (int) (Math.random() * (l - l / 6) + (l / 6));
            a1=a + 1.6 * (0.5 - Math.random()); //Угол наклона веток
            x1=(int) (x + s * Math.cos(a));
            y1=(int) (y + s * Math.sin(a));
            tree(x1, y1, a1, (int) (p - 5 - Math.random()* 60), g); //Чем меньше вычетаем, тем пышнее дерево
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        repaint();
    }

}

