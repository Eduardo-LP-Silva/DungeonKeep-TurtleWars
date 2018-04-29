package com.lpoot4g4.tw;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoot4g4.tw.Model.GameModel;
import com.lpoot4g4.tw.View.GameView;

public class TurtleWars extends Game
{
    private SpriteBatch batch;
    private AssetManager assetManager;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    public static final String TITLE = "Turtle Wars";

    /**
     * Creates the game. Initializes the sprite batch and asset manager.
     * Also starts the game until we have a main menu.
     */
    @Override
    public void create ()
    {
        batch = new SpriteBatch();
        assetManager = new AssetManager();
        Gdx.gl.glClearColor(1,0,0,1);

        startGame();
    }

    /**
     * Starts the game.
     */
    private void startGame()
    {
        setScreen(new GameView(this));
    }

    /**
     * Disposes of all assets.
     */
    @Override
    public void dispose ()
    {
        batch.dispose();
        assetManager.dispose();
    }

    /**
     * Returns the asset manager used to load all textures and sounds.
     *
     * @return the asset manager
     */
    public AssetManager getAssetManager()
    {
        return assetManager;
    }

    /**
     * Returns the sprite batch used to improve drawing performance.
     *
     * @return the sprite batch
     */
    public SpriteBatch getBatch()
    {
        return batch;
    }
}
