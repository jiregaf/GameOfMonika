import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player {
    ArrayList<Image> animgo;
    ArrayList<Image> stayAnim;
    private Image defPos = new ImageIcon("1.png").getImage();
    private Image curPos = defPos;
    boolean stay = true;
    int status = 0;
    public boolean left = false;
    int curHp = 100;
    int maxHp = 100;
    boolean kickStatus = false;
    int kickProcStat = 0;
    boolean canKick = true;
    long reload = 600;
    boolean orientationVert = false;
    
    public Player(){
        animgo = new ArrayList<Image>();
        animgo.add(new ImageIcon("run1.png").getImage());
        animgo.add(new ImageIcon("run2.png").getImage());
        animgo.add(new ImageIcon("run3.png").getImage());
        animgo.add(new ImageIcon("run4.png").getImage());
        animgo.add(new ImageIcon("run5.png").getImage());
        animgo.add(new ImageIcon("run6.png").getImage());
        animgo.add(new ImageIcon("run7.png").getImage());
        animgo.add(new ImageIcon("run8.png").getImage());
        animgo.add(new ImageIcon("run9.png").getImage());
        animgo.add(new ImageIcon("run10.png").getImage());

        stayAnim = new ArrayList<Image>();
        for(int i = 0; i < 8; i++){
            stayAnim.add(new ImageIcon(Integer.toString(i+1)+ ".png").getImage());
        }
    }

    public void animate(){
        if (stay) stay(); else go();
    }


    public void stay(){
        if (status < 7){
            status+=1;
            curPos = stayAnim.get(status);
        } else status = 0;
    }

    public void go(){

        if (status < 9){
            status+=1;
            curPos = animgo.get(status);
        } else {
            status = 0;
        }
    }
    public Image getPos(){
        return curPos;
    }
    public void setPos(int i) {
        curPos = animgo.get(i);
    }
}
