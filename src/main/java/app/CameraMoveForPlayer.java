package app;

public class CameraMoveForPlayer implements CameraMove{

    Camera camera;

    public CameraMoveForPlayer(Camera camera){
        this.camera = camera;
    }
    @Override
    public void move() {
        if (Math.abs(camera.player.x + camera.player.image.getWidth( null) / 2 - camera.x) < SuperGame.win_width / 2 - 10) camera.x-=6;
        if (Math.abs(camera.player.x + camera.player.image.getWidth(null) / 2 - camera.x) > SuperGame.win_width / 2 + 10) camera.x+=6;

    }
}
