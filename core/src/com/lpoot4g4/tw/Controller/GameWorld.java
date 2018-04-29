package com.lpoot4g4.tw.Controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoot4g4.tw.Model.GameModel;

import java.util.ArrayList;

public class GameWorld
{
    private static GameWorld instance;
    private ArrayList<CactusBody> cacti;
    private ArrayList<PlatformBody> platforms;
    private World world;
    private TurtleBody player1;
    private TurtleBody player2;

    public GameWorld()
    {
        world = new World(new Vector2(0, 9.8f), true);

        player1 = new TurtleBody(world, GameModel.getInstance().getPlayer1());
        player2 = new TurtleBody(world, GameModel.getInstance().getPlayer2());
        cacti = new ArrayList<CactusBody>();
        platforms = new ArrayList<PlatformBody>();
    }

    public static GameWorld getInstance()
    {
        if (instance == null)
            instance = new GameWorld();
        return instance;

    }

    public World getWorld()
    {
        return world;
    }
}
