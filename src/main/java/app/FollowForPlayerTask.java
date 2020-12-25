package app;

public class FollowForPlayerTask extends Task{

    /*
        Задача, благодаря выполнению которой какая-то штука будет двигаться за игроком
     */
    Player player;

    public FollowForPlayerTask(WorldElement owner){
        for(int i = 0; i < owner.world.elements.size(); i++){
            if (owner.world.elements.get(i) instanceof Player) {
                player = (Player) owner.world.elements.get(i);
                break;
            }
        }
        this.owner = owner;
    }

    @Override
    void doTask(){

            if (owner.x > player.x) owner.left = false; else owner.left = true;
            if (owner.x - player.x > 100) {
                owner.x -= 1;
            }
            if (player.x - owner.x > 100) {
                owner.x += 1;
            }

    }
}
