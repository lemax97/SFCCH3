package SFCCh3;

public class StarfishCollector extends GameBeta{

    private Turtle turtle;
//    private Starfish starfish;
//    private BaseActor ocean;

    @Override
    public void initialize() {

        BaseActor ocean = new BaseActor(0,0, mainStage);
        ocean.loadTexture("assets/water.jpg");
        ocean.setSize(800, 600);

        new Starfish(380, 380, mainStage);

        turtle = new Turtle(20, 20, mainStage);

    }

    @Override
    public void update(float dt) {
        //code
    }
}
