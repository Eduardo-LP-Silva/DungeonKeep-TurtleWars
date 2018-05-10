package com.lpoot4g4.tw;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoot4g4.tw.Model.GameModel;
import com.lpoot4g4.tw.View.CharacterSelectionView;
import com.lpoot4g4.tw.View.MenuView;
import com.lpoot4g4.tw.View.OptionsView;
import com.lpoot4g4.tw.View.PlayView;

public class TurtleWars extends Game
{
    private SpriteBatch batch;
    private AssetManager assetManager;

    public static final float WIDTH = 800.0f;
    public static final float HEIGHT = 400.0f;
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
        Gdx.gl.glClearColor(0.1f,0.9f,0.9f,1);
        startGame();
    }

    /**
     * Starts the game.
     */
    private void startGame()
    {
        GameModel gm = new GameModel();

        setScreen(new MenuView(this, gm));
    }

    public void setMenu(GameModel gm)
    {
        setScreen(new MenuView(this, gm));
    }

    public void setOptions(GameModel gm)
    {
        setScreen(new OptionsView(this, gm));
    }

    public void setCharacterSelection(GameModel gm)
    {
        setScreen(new CharacterSelectionView(this, gm));
    }

    public void setPlay(GameModel gm)
    {
        setScreen(new PlayView(this, gm));
    }

    public void exit()
    {
        return;
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
