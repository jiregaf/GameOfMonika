package app;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
Класс для управления Player-ом. Идея такая. У Игрока будут разные флажки типа move, через инпут они будут меняться.
Затем в методе "control", который вызывается "Player-ом" с каждым тиком выполняется анализ этих флагов и вызывается какая-то
функция наприме "run", которая здесь например и двигает персонажа и анимирует.
 */
public class PlayerControl extends KeyAdapter {

    public Player owner;
    public static boolean move = false;
    public static boolean left = false;
    public PlayerControl(Player owner){
        this.owner = owner;
        owner.playerControl = this;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_A) {
            move = left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            move = true;
            left = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        if (e.getKeyCode() == KeyEvent.VK_A) move = false;
        if (e.getKeyCode() == KeyEvent.VK_D) move = false;
    }

    boolean control(){
        if (move) {
            run(left);
            return true;
        } else return false;
    }
    void run(boolean left){
        owner.left = left;
        owner.animations.get(0).play();
        if (left) owner.x-=4; else owner.x+=4;
    }
}
