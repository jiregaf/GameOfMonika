package app;

/*
Задача патрулирования для какой-то точки startX, которая запомнится во время создания задачи
left соответственно для того чтобы вовремя поворачивать, когда от точки startX отошел на приличное расстояние
 */

public class PatrolTask extends Task{
    int startX;
    boolean left = false;

    public PatrolTask(WorldElement owner){
       this.owner = owner;
       startX = owner.x;
    }

    @Override
    public void doTask() {
        if (owner.left)
            if (owner.x + owner.image.getWidth(null) / 2 - startX > -300) owner.x-=2; else owner.left = !owner.left;
         if (!(owner).left)
             if (owner.x + owner.image.getWidth(null) / 2- startX < 300) owner.x+=2; else owner.left = !owner.left;
             owner.animations.get(0).play();
    }
}
