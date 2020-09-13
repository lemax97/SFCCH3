package SFCCh3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class LevelScreen2 extends BaseScreen {
    private Turtle turtle;
    private boolean win, loose;

    @Override
    public void initialize() {
        BaseActor ocean = new BaseActor(0,0, mainStage);
        ocean.loadTexture("assets/water-border.jpg");
        ocean.setSize(1200, 900);
        BaseActor.setWorldBounds(ocean);

        new Starfish(80, 850, mainStage);
        new Starfish(580, 270, mainStage);
        new Starfish(350, 350, mainStage);
        new Starfish(420, 450, mainStage);
        new Starfish(670, 160, mainStage);
        new Starfish(570, 20, mainStage);
        new Starfish(820, 410, mainStage);


        new Rock(200, 450, mainStage);
        new Rock(100, 300, mainStage);
        new Rock(300, 450, mainStage);
        new Rock(470, 600, mainStage);
        new Rock(200, 550, mainStage);
        new Rock(320, 300, mainStage);
        new Rock(640, 450, mainStage);
        new Rock(790, 100, mainStage);

        new Shark(120, 620, mainStage);
        new Shark(320, 620, mainStage);
        new Shark(720, 380, mainStage);
        new Shark(520, 720, mainStage);
        new Shark(220, 420, mainStage);
        new Shark(1020, 420, mainStage);
        new Shark(620, 780, mainStage);
        new Shark(660, 460, mainStage);

        turtle = new Turtle(20, 20, mainStage);

        win = false;
        loose = false;
    }

    @Override
    public void update(float dt) {

        for (BaseActor rockActor : BaseActor.getList(mainStage, "SFCCh3.Rock"))
            turtle.preventOverlap(rockActor);

        for (BaseActor starfishActor : BaseActor.getList(mainStage, "SFCCh3.Starfish")) {

            Starfish starfish = (Starfish) starfishActor;
            if (turtle.overlaps(starfish) && !starfish.isCollected()) {

                starfish.collected = true;
                starfish.clearActions();
                starfish.addAction(Actions.fadeOut(1));
                starfish.addAction(Actions.after(Actions.removeActor()));

                Whirlpool whirlpool = new Whirlpool(0, 0, mainStage);
                whirlpool.centerAtActor(starfish);
                whirlpool.setOpacity(0.25f);
            }
        }

        for (BaseActor sharkActor : BaseActor.getList(mainStage, "SFCCh3.Shark")) {

            Shark shark = (Shark) sharkActor;
            if (turtle.overlaps(shark)) {
//
//                shark.collected = true;
                turtle.clearActions();
                turtle.addAction(Actions.fadeOut(1));
//                turtle.addAction(Actions.after(Actions.removeActor()));

                Whirlpool whirlpool = new Whirlpool(0, 0, mainStage);
                whirlpool.centerAtActor(turtle);
                whirlpool.setOpacity(0.25f);
                loose = true;
            }
        }

        if (loose == true){

            removeActors();

            turtle.remove();

            BaseActor youLooseMessage = new BaseActor(0, 0, uiStage);
            youLooseMessage.loadTexture("assets/game-over.png");
            youLooseMessage.centerAtPosition(400,300);
            youLooseMessage.setOpacity(0);
            youLooseMessage.addAction(Actions.delay(0.5f));
            youLooseMessage.addAction(Actions.after(Actions.fadeIn(0.4f)));
        }

        if (BaseActor.count(mainStage, "SFCCh3.Starfish") == 0 && !win && !loose){

            win = true;

            removeActors();

            BaseActor youWinMessage = new BaseActor(0, 0, uiStage);
            youWinMessage.loadTexture("assets/you-win.png");
            youWinMessage.centerAtPosition(400, 300);
            youWinMessage.setOpacity(0);
            youWinMessage.addAction(Actions.delay(1));
            youWinMessage.addAction(Actions.after(Actions.fadeIn(1)));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.R)){
            StarfishGame.setActvieScreen(new MenuScreen());
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S) && loose){
            StarfishGame.setActvieScreen(new LevelScreen());
        }

    }


    private void removeActors() {

        for (BaseActor sharkActor : BaseActor.getList(mainStage, "SFCCh3.Shark")){

            sharkActor.addAction(Actions.removeActor());

            Whirlpool whirlpool = new Whirlpool(0, 0, mainStage);
            whirlpool.centerAtActor(sharkActor);
            whirlpool.setOpacity(0.25f);

        }

        for (BaseActor starfishActor : BaseActor.getList(mainStage, "SFCCh3.Starfish")){

            starfishActor.addAction(Actions.removeActor());

            Whirlpool whirlpool = new Whirlpool(0, 0, mainStage);
            whirlpool.centerAtActor(starfishActor);
            whirlpool.setOpacity(0.25f);

        }


    }
}
