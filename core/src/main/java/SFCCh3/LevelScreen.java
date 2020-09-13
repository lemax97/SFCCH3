package SFCCh3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class LevelScreen extends BaseScreen{

    private Turtle turtle;
    private boolean win, loose;

    @Override
    public void initialize() {

        BaseActor ocean = new BaseActor(0,0, mainStage);
        ocean.loadTexture("assets/water-border.jpg");
        ocean.setSize(1200, 900);
        BaseActor.setWorldBounds(ocean);

        new Starfish(400, 400, mainStage);
        new Starfish(500, 100, mainStage);
        new Starfish(100, 450, mainStage);
        new Starfish(200, 250, mainStage);


        new Rock(200, 150, mainStage);
        new Rock(100, 300, mainStage);
        new Rock(300, 350, mainStage);
        new Rock(450, 200, mainStage);

        new Shark(220, 320, mainStage);

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

            BaseActor youLooseMessage = new BaseActor(0, 0, uiStage);
            youLooseMessage.loadTexture("assets/game-over.png");
            youLooseMessage.centerAtPosition(400,300);
            youLooseMessage.setOpacity(0);
            youLooseMessage.addAction(Actions.delay(0.5f));
            youLooseMessage.addAction(Actions.after(Actions.fadeIn(0.4f)));

//            turtle.remove();

        }

        if (BaseActor.count(mainStage, "SFCCh3.Starfish") == 0 && !win && !loose){

            win = true;

            removeActors();

            BaseActor youWinMessage = new BaseActor(0, 0, uiStage);
            youWinMessage.loadTexture("assets/message-continue.png");
            youWinMessage.centerAtPosition(400, 300);
            youWinMessage.setOpacity(0);
            youWinMessage.addAction(Actions.delay(1));
            youWinMessage.addAction(Actions.after(Actions.fadeIn(1)));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.R)){
            StarfishGame.setActvieScreen(new MenuScreen());
        }

        if (Gdx.input.isKeyPressed(Input.Keys.C) && win){
            StarfishGame.setActvieScreen(new LevelScreen2());
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

        turtle.remove();

    }


}
