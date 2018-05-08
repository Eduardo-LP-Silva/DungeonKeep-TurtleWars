package com.lpoot4g4.tw.Model;

import java.util.ArrayList;

public class GameModel
{
    private State state;
    private ArrayList<CactusModel> cacti;
    private PlatformModel floor;
    private TurtleModel player1;
    private TurtleModel player2;

    public enum State {Menu, Play, Options, CharacterSelection, Exit};

    public GameModel()
    {
        cacti = new ArrayList<CactusModel>();
        floor = new PlatformModel(0, 0);
        player1 = new TurtleModel(5, 60, TurtleModel.TurtleClass.Light);
        player2 = new TurtleModel(90, 30, TurtleModel.TurtleClass.Light);
        state = State.Menu;
    }

    public void update()
    {

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

    public PlatformModel getFloor()
    {
        return floor;
    }

    public State getState()
    {
        return state;
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

    public void setFloor(PlatformModel floor)
    {
        this.floor = floor;
    }

    public void setState(State st)
    {
        state = st;
    }
}
