package com.lpoot4g4.tw.desktop.Tests;

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

        gameWorld.getPlayer1().jump();

        for(int i = 0; i < 300; i++)
            gameWorld.update(1/60f);

        assertEquals(true, gameModel.getPlayer1().getY() > gameModel.getPLAYER1_STARTING_POSITION().y);

        float previous_x = gameModel.getPlayer1().getX();

        for(int i = 0; i < 300; i++)
        {
            gameWorld.getPlayer1().moveLeft();
            gameWorld.update(1/60f);
        }

        assertEquals(true, gameModel.getPlayer1().getX() < previous_x);
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
            gameWorld.update(1/60f);

        assertEquals(95, gameModel.getPlayer2().getHealth());
    }

    @Test
    public void fireMissile()
    {
        GameModel gameModel = new GameModel();
        GameWorld gameWorld = new GameWorld(gameModel);
        gameWorld.setTest(true);
        gameWorld.update(1/60f);

        assertEquals(0, gameWorld.getMissiles().size());

        gameWorld.update(1/60f);
        gameWorld.FireTurtle1();
        gameWorld.update(1/60);

        assertEquals(1, gameWorld.getMissiles().size());

        for(int i = 0; i < 300; i++)
        {
            gameWorld.update(1/60f);
            gameWorld.removeFlagged();
        }

        assertEquals(80,  gameModel.getPlayer2().getHealth());
    }

    @Test
    public void jumpAttack()
    {
        GameModel gameModel = new GameModel();
        gameModel.getPlayer1().setX(gameModel.getPlayer2().getX());
        gameModel.getPlayer1().setY(300);
        GameWorld gameWorld = new GameWorld(gameModel);
        gameWorld.setTest(true);

        assertEquals(100, gameModel.getPlayer2().getHealth());

        for(int i = 0; i < 300; i++)
            gameWorld.update(1/60f);

        assertEquals(true, gameModel.getPlayer2().getHealth() < 100);
    }

    @Test
    public void Victory()
    {
        GameModel gameModel = new GameModel();
        GameWorld gameWorld = new GameWorld(gameModel);
        gameWorld.setTest(true);
        gameWorld.update(1/60f);

        assertEquals(false, gameModel.isVictory());

        while(gameModel.getPlayer2().getHealth() > 0)
        {
            gameWorld.FireTurtle1();
            gameWorld.update(1/60f);
        }

        gameWorld.update(1/60f);

        assertEquals(0, gameModel.getPlayer2().getHealth());
        assertEquals(true, gameModel.isVictory());
    }

    @Test
    public void defeat()
    {
        GameModel gameModel = new GameModel();
        GameWorld gameWorld = new GameWorld(gameModel);
        gameWorld.setTest(true);
        gameWorld.update(1/60f);

        assertEquals(false, gameModel.isGameOver());

        while(gameModel.getPlayer1().getHealth() > 0)
        {
            gameWorld.fireTurtle2();
            gameWorld.update(1/60f);
        }

        gameWorld.update(1/60f);

        assertEquals(true, gameModel.isGameOver());
    }
}
