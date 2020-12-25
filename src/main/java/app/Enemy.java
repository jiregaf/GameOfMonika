package app;


import java.awt.*;

/*
В отличии от предудущих версий пока что потерял функциональность перемещенияя по синусоиде и нападать на игрока
Но это возможно решить при помощи создания задач "Task" нападения и передвижения по какой-угодно траектории и тд...
, вызове их через метод "Tick()" В этом методе можно оперировать будет разными Task-ами  создавая сложное поведение
 Пока что здесь выполняется одна задача(Task) это патрулировать вокруг какой-то точки*/

public class Enemy extends WorldElement implements Tickable{


    boolean died = false;
    Task task;
    public Enemy(World world, Image image, int x, int y) {
        super(world, image, x, y);
        task = new PatrolTask(this);
    }

    @Override
    public void tick() {
        task.doTask();
    }
}
