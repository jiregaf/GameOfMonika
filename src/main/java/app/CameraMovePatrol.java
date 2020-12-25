package app;

public class CameraMovePatrol implements CameraMove {

    int startX;
    boolean left = false;
    Camera camera;

    public CameraMovePatrol(Camera camera){
        this.startX = startX;
        this.camera = camera;
        startX = camera.x;
    }
    @Override
    public void move() {
        if (!left){
            if (camera.x < startX + 400) camera.x+=3;
            else left = !left;
        } else{
            if (camera.x > startX - 400) camera.x-=3;
            else left = !left;
        }

    }
}
