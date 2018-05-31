package com.lpoot4g4.tw.desktop.Tests;

import com.badlogic.gdx.Gdx;
import com.lpoot4g4.tw.Controller.GameWorld;
import com.lpoot4g4.tw.Model.GameModel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Tests
{
    @Test
    public void TurtleMove()
    {
        GameModel gameModel = new GameModel();
        GameWorld gameWorld = new GameWorld(gameModel);
        gameWorld.setTest(true);
        gameWorld.update(1/60f);

        assertEquals(gameModel.getPlayer1().getX(), gameModel.getPLAYER1_STARTING_POSITION().x, 0.5);

        for(int i = 0; i < 300; i++)
        {
            gameWorld.getPlayer1().moveRight();
            gameWorld.update(1/60f);
        }

        assertEquals(true, gameModel.getPlayer1().getX() > gameModel.getPLAYER1_STARTING_POSITION().x);

        float previous_x = gameModel.getPlayer1().getX();

        gameWorld.getPlayer1().jump();
        gameWorld.update(1/60f);
        assertEquals(true, gameModel.getPlayer1().getY() > gameModel.getPLAYER1_STARTING_POSITION().y);

    }

    @Test
    public void bite()
    {
        GameModel gameModel = new GameModel();
        GameWorld gameWorld = new GameWorld(gameModel);
        gameWorld.setTest(true);
        gameWorld.update(1/60f);

        while(!gameWorld.getPlayer1().isInBitingRange(gameWorld.getPlayer2().getX()))
        {
            gameWorld.getPlayer1().moveRight();
            gameWorld.update(1/60f);
        }

        gameWorld.getPlayer1().bite();

        for(int i = 0; i < 300; i++)
            gameWorld.update(1/300f);

        assertEquals(95, gameModel.getPlayer2().getHealth());
    }

    @Test
    public void fireMissile()
    {
        GameModel gameModel = new GameModel();
        GameWorld gameWorld = new GameWorld(gameModel);
        gameWorld.setTest(true);
        gameWorld.update(1/300f);

        assertEquals(0, gameWorld.getMissiles().size());

        gameWorld.update(1/300f);
        gameWorld.FireTurtle1();
        gameWorld.update(1/300);

        assertEquals(1, gameWorld.getMissiles().size());

        for(int i = 0; i < 300; i++)
        {
            gameWorld.update(1/300f);
            gameWorld.removeFlagged();
        }

        assertEquals(80,  gameModel.getPlayer2().getHealth());
    }
}
