package SFCCh3;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Shark extends BaseActor{

    public boolean collected;


    public Shark(float x, float y, Stage stage) {
        super(x, y, stage);

        loadTexture("assets/sharky.png");
        setBoundaryPolygon(8);

        Action move = Actions.sequence(Actions.rotateBy(-10, 1),
                Actions.rotateBy(10, 1));
        this.addAction(Actions.forever(move));

        collected = false;
    }

    public boolean isCollected(){
        return collected;
    }

    public void collect(){

        collected = true;
        clearActions();
        addAction( Actions.fadeOut(1));
        addAction( Actions.after(Actions.removeActor()));
    }
}
