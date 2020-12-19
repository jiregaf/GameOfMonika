import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MyPane extends JPanel {

    public MyPane(Graphics g){
        Main.tree(220, 580, 3 * Math.PI / 2, 200, g);
    }

    @Override
    public void paint(Graphics g){
        //super.paint(g);

    }


}


