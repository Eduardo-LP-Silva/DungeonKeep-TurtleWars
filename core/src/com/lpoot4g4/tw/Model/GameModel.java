package com.lpoot4g4.tw.Model;

import com.badlogic.gdx.audio.Music;
import com.lpoot4g4.tw.TurtleWars;

import java.util.ArrayList;

public class GameModel
{
    private State state;
    private ArrayList<ProjectileModel> missiles;
    private PlatformModel floor;
    private PlatformModel ceiling;
    private TurtleModel player1;
    private TurtleModel player2;
    private Music themeSong;
    private PowerUpModel powerUp;

    public enum State {Menu, Play, Options, CharacterSelection, Exit};

    public GameModel()
    {
        floor = new PlatformModel(0, 0);
        ceiling = new PlatformModel(0, TurtleWars.HEIGHT + 30);
        player1 = new TurtleModel(5, 200, TurtleModel.TurtleClass.Light);
        player2 = new TurtleModel(500, 200, TurtleModel.TurtleClass.Light);
        missiles = new ArrayList<ProjectileModel>();
        powerUp = new PowerUpModel(0, 0, PowerUpModel.Effect.Null);
        state = State.Menu;
    }

    public void update()
    {
        if(player1.getX() < player2.getX())
        {
            player1.setBackwards(false);
            player2.setBackwards(true);
        }
        else
        {
            player1.setBackwards(true);
            player2.setBackwards(false);
        }

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

    public PlatformModel getCeiling()
    {
        return ceiling;
    }

    public PowerUpModel getPowerUp() {
        return powerUp;
    }

    public State getState()
    {
        return state;
    }

    public Music getThemeSong() {
        return themeSong;
    }

    public ArrayList<ProjectileModel> getMissiles() {
        return missiles;
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

    public void setThemeSong(Music themeSong) {
        this.themeSong = themeSong;
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
