package com.lpoot4g4.tw.Controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.lpoot4g4.tw.Model.EntityModel;
import com.lpoot4g4.tw.Model.GameModel;

import java.util.ArrayList;

import static com.lpoot4g4.tw.View.PlayView.PIXEL_TO_METER;

public class GameWorld
{
    private ArrayList<CactusBody> cacti;
    private World world;
    private TurtleBody player1;
    private TurtleBody player2;
    private PlatformBody floor;
    private GameModel gameModel;

    public GameWorld(GameModel gm)
    {
        world = new World(new Vector2(0, -9.8f), true);
        gameModel = gm;
        player1 = new TurtleBody(world, gameModel.getPlayer1());
        //player2 = new TurtleBody(world, gameModel.getPlayer2());
        cacti = new ArrayList<CactusBody>();
        floor = new PlatformBody(world, gameModel.getFloor());
    }

    public World getWorld()
    {
        return world;
    }

    public void update(float delta)
    {
        gameModel.update();

        world.step(1/60f, 6,2);

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies)
            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x / PIXEL_TO_METER, body.getPosition().y / PIXEL_TO_METER);

    }
}
