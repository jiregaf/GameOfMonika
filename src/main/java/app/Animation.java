package app;

import java.awt.*;
import java.util.ArrayList;

/* КЛАСС ДЛЯ ХРАНЕНИЯ И ВОСПРОИЗВЕДЕНИЯ АНИМАЦИИ. СОДЕРЖИТ ОБЬЕКТ ДЛЯ КОТОРОГО АНИМАЦИЯ - "Owner",
    СПИСОК КАРТИНОК "animation",
    СЧЁТЧИК "i" ПОСЛЕДОВАТЕЛЬНО ОБХОДИТ ЭТОТ СПИСОК И ВЫБИРАЕТ СООТВЕТСТВУЮЩУЮ КАРТИНКУ,
    "iter" и "speed" ИСПОЛЬЗУЮТСЯ ДЛЯ КОНТРОЛЯ СКОРОСТИ ВОСПРОИЗВЕДЕНИЯ АНИМАЦИИ
    "name" - НАЗВАНИЕ АНИМАЦИИ
    "play()" - ПРОИГРЫВАЕТ АНИМАЦИЮ
 */

public class Animation {
    String name;
    ArrayList<Image> animation;
    int speed = 3;
    int iter = 1;
    WorldElement owner;
    int i = 0;
    public Animation(WorldElement owner){
        animation = new ArrayList<Image>();
        this.owner = owner;
    }

    void play(){
        if (iter % speed == 0){
            i++;
            if (i < animation.size()){
                owner.image = animation.get(i);
            } else i = 0;
        }
        iter++;
    }

}
