package com.lpoot4g4.tw.Model;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.lpoot4g4.tw.TurtleWars;

import java.util.ArrayList;
import java.util.Random;

import static com.lpoot4g4.tw.Model.PowerUpModel.Effect.Null;

public class GameModel
{
    /**
     * Starting coordinates of player one.
     */
    private final Vector2 PLAYER1_STARTING_POSITION = new Vector2(5, 48);

    /**
     * Starting coordinates of player two.
     */
    private final Vector2 PLAYER2_STARTING_POSITION = new Vector2(500, 48);

    /**
     * The game state.
     */
    private State state;

    /**
     * The missiles ArrayList.
     */
    private ArrayList<ProjectileModel> missiles;

    /**
     * The floor model.
     */
    private PlatformModel floor;

    /**
     * The ceiling model
     */
    private PlatformModel ceiling;

    /**
     * Player 1's model.
     */
    private TurtleModel player1;

    /**
     * Player 2's model.
     */
    private TurtleModel player2;

    /**
     * The menu music song.
     */
    private Music themeSong;

    /**
     * The current power up.
     */
    private PowerUpModel powerUp;

    /**
     * True if the game has ended with a defeat.
     */
    private boolean gameOver = false;

    /**
     * True if the game as ended with a victory.
     */
    private boolean victory = false;

    /**
     * The states of the game.
     */
    public enum State {Menu, Play, Options, CharacterSelection, Exit};

    /**
     * Constructor of the game. Initializes both players, ceiling and floor, being the first state the Menu State.
     */
    public GameModel()
    {
        floor = new PlatformModel(0, 0);
        ceiling = new PlatformModel(0, TurtleWars.HEIGHT + 30);
        player1 = new TurtleModel(PLAYER1_STARTING_POSITION.x, PLAYER1_STARTING_POSITION.y, TurtleModel.TurtleClass.Light);
        player2 = new TurtleModel(PLAYER2_STARTING_POSITION.x, PLAYER2_STARTING_POSITION.y, TurtleModel.TurtleClass.Light);
        missiles = new ArrayList<ProjectileModel>();
        powerUp = new PowerUpModel(0, 0, Null);
        state = State.Menu;
    }


    /**
     * Update method for the Game's model. It checks player's relative positions to each other and if the game has ended.
     */
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

        player1.update();
        player2.update();

        if(player1.getHealth() == 0)
            gameOver = true;
        else
            if(player2.getHealth() == 0)
                victory = true;
    }

    /**
     * Returns the player 1's starting position.
     *
     * @return The position.
     */
    public Vector2 getPLAYER1_STARTING_POSITION() {
        return PLAYER1_STARTING_POSITION;
    }

    /**
     * Returns the first player's model.
     *
     * @return The player 1's model.
     */
    public TurtleModel getPlayer1()
    {
        return player1;
    }

    /**
     * Returns the second player's model.
     *
     * @return The player 2's model.
     */
    public TurtleModel getPlayer2()
    {
        return player2;
    }

    /**
     * Checks if player one has lost the game.
     *
     * @return True if the game has ended with defeat, false otherwise.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Checks if player 1 has won the game.
     *
     * @return True if the game has ended with victory, false otherwise.
     */
    public boolean isVictory() {
        return victory;
    }

    /**
     * Returns the floor's model.
     *
     * @return The floor.
     */
    public PlatformModel getFloor()
    {
        return floor;
    }

    /**
     * Returns the ceiling's model
     *
     * @return The ceiling.
     */
    public PlatformModel getCeiling()
    {
        return ceiling;
    }

    /**
     * Returns the power up's model.
     *
     * @return The power up.
     */
    public PowerUpModel getPowerUp() {
        return powerUp;
    }

    /**
     * Returns the theme song.
     *
     * @return The theme song.
     */
    public Music getThemeSong() {
        return themeSong;
    }

    /**
     * Returns the missiles ArrayList.
     *
     * @return The missiles ArrayList.
     */
    public ArrayList<ProjectileModel> getMissiles() {
        return missiles;
    }

    /**
     * Sets the theme song.
     *
     * @param themeSong The theme song.
     */
    public void setThemeSong(Music themeSong) {
        this.themeSong = themeSong;
    }

    /**
     * Adds missile to missile array.
     *
     * @param pm The missile to add.
     */
    public void addMissile(ProjectileModel pm)
    {
        missiles.add(pm);
    }

    /**
     * Removes missile from the missile array.
     *
     * @param pm The missile to remove.
     */
    public void removeMissile(ProjectileModel pm)
    {
        missiles.remove(pm);
    }

    /**
     * Spawns a new power-up (in the model only).
     */
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
        getPowerUp().setY(TurtleWars.HEIGHT);
    }
}
