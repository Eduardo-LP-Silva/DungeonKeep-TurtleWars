package com.lpoot4g4.tw.Model;

import java.util.ArrayList;

public class GameModel
{
    private static GameModel instance;
    private State state;
    private ArrayList<CactusModel> cacti;
    private ArrayList<PlatformModel> platforms;
    private TurtleModel player1;
    private TurtleModel player2;

    public enum State {Menu, Play, Options};

    public GameModel()
    {
        cacti = new ArrayList<CactusModel>();
        platforms = new ArrayList<PlatformModel>();
        player1 = new TurtleModel(10, 10, TurtleModel.TurtleClass.Light);
        player2 = new TurtleModel(90, 10, TurtleModel.TurtleClass.Light);
        state = State.Menu;
    }

    public static GameModel getInstance()
    {
        return instance;
    }

    public ArrayList<CactusModel> getCacti()
    {
        return cacti;
    }

    public TurtleModel getPlayer1()
    {
        return player1;
    }

    public TurtleModel getPlayer2()
    {
        return player2;
    }

    public ArrayList<PlatformModel> getPlatforms()
    {
        return platforms;
    }

    public State getState()
    {
        return state;
    }

    public static void setInstance(GameModel instance)
    {
        GameModel.instance = instance;
    }

    public void setCacti(ArrayList<CactusModel> cacti)
    {
        this.cacti = cacti;
    }

    public void setPlayer1(TurtleModel player1)
    {
        this.player1 = player1;
    }

    public void setPlayer2(TurtleModel player2)
    {
        this.player2 = player2;
    }

    public void setPlatforms(ArrayList<PlatformModel> platforms)
    {
        this.platforms = platforms;
    }

    public void setState(State st)
    {
        state = st;
    }
}
