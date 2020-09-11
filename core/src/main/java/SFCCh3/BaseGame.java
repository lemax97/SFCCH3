package SFCCh3;

import com.badlogic.gdx.Game;

public abstract class BaseGame extends Game{

    private static BaseGame game;

    public BaseGame() {

        game = this;
    }

    public static void setActvieScreen(BaseScreen screen){

        game.setScreen(screen);
    }
}
