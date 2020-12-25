package app;

public class IdlePlayerTask extends Task{

    /*
    Задача исключительно для игрока хотя это поправимо которая проигрывает анимацию нахождения на месте
     */
    public IdlePlayerTask(Player owner){
        this.owner = owner;
    }

    @Override
    public void doTask(){
        owner.animations.get(1).play();
    }
}
