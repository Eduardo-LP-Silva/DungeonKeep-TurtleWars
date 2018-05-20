package com.lpoot4g4.tw.Model;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

public class GameModel
{
    private State state;
    private ArrayList<CactusModel> cacti;
    private ArrayList<ProjectileModel> missiles;
    private PlatformModel floor;
    private TurtleModel player1;
    private TurtleModel player2;

    public enum State {Menu, Play, Options, CharacterSelection, Exit};

    public GameModel()
    {
        cacti = new ArrayList<CactusModel>();
        floor = new PlatformModel(0, 0);
        player1 = new TurtleModel(5, 200, TurtleModel.TurtleClass.Light);
        player2 = new TurtleModel(500, 200, TurtleModel.TurtleClass.Light);
        missiles = new ArrayList<ProjectileModel>();
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

    public ArrayList<ProjectileModel> getMissiles() {
        return missiles;
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

    public void addMissile(ProjectileModel pm)
    {
        missiles.add(pm);
    }

    public void removeMissile(ProjectileModel pm)
    {
        missiles.remove(pm);
    }
}
