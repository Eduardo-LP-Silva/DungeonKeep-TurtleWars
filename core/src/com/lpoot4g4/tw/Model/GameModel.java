package com.lpoot4g4.tw.Model;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Timer;
import com.lpoot4g4.tw.TurtleWars;

import java.util.ArrayList;
import java.util.Random;

import static com.lpoot4g4.tw.Model.PowerUpModel.Effect.Null;

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
    private boolean gameOver = false;
    private boolean victory = false;

    public enum State {Menu, Play, Options, CharacterSelection, Exit};

    public GameModel()
    {
        floor = new PlatformModel(0, 0);
        ceiling = new PlatformModel(0, TurtleWars.HEIGHT + 30);
        player1 = new TurtleModel(5, 200, TurtleModel.TurtleClass.Light);
        player2 = new TurtleModel(500, 200, TurtleModel.TurtleClass.Light);
        missiles = new ArrayList<ProjectileModel>();
        powerUp = new PowerUpModel(0, 0, Null);
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

        if(player1.isBuffed())
        {
            player1.setBuffed(false);

            Timer.schedule(new Timer.Task()
            {
                @Override
                public void run()
                {
                    if(player1.getTurtleClass().toString().equals("Light"))
                    {
                        player1.setMelee_damage(TurtleModel.getLightTurtleMeleeDamage());
                        player1.setStomp_damage(TurtleModel.getLightTurtleStompDamage());
                    }
                    else
                    {
                        player1.setMelee_damage(TurtleModel.getHeavyTurtleMeleeDamage());
                        player1.setStomp_damage(TurtleModel.getHeavyTurtleStompDamage());
                    }
                }
            }, 5);
        }

        if(player2.isBuffed())
        {
            player2.setBuffed(false);

            Timer.schedule(new Timer.Task()
            {
                @Override
                public void run()
                {
                    if(player2.getClass().toString().equals("Light"))
                    {
                        player2.setMelee_damage(TurtleModel.getLightTurtleMeleeDamage());
                        player2.setStomp_damage(TurtleModel.getLightTurtleStompDamage());
                    }
                    else
                    {
                        player2.setMelee_damage(TurtleModel.getHeavyTurtleMeleeDamage());
                        player2.setStomp_damage(TurtleModel.getHeavyTurtleStompDamage());
                    }
                }
            }, 5);
        }

        if(player1.getHealth() == 0)
            gameOver = true;
        else
            if(player2.getHealth() == 0)
                victory = true;
    }

    public TurtleModel getPlayer1()
    {
        return player1;
    }

    public TurtleModel getPlayer2()
    {
        return player2;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isVictory() {
        return victory;
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

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }

    public void addMissile(ProjectileModel pm)
    {
        missiles.add(pm);
    }


    public void removeMissile(ProjectileModel pm)
    {
        missiles.remove(pm);
    }

    public void spawnPowerUp()
    {
        powerUp = new PowerUpModel(0,0, Null);

        Random rand = new Random();
        int option;

        option = rand.nextInt(2);

        switch(option)
        {
            case 0:
                powerUp.setEffect(PowerUpModel.Effect.Health);
                break;

            case 1:
                powerUp.setEffect(PowerUpModel.Effect.Damage);
                break;

            default:
                break;
        }

        getPowerUp().setX(rand.nextFloat() * TurtleWars.WIDTH);
        getPowerUp().setY(TurtleWars.HEIGHT );
    }

}
