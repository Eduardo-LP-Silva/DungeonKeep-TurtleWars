package com.lpoot4g4.tw;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoot4g4.tw.Model.GameModel;
import com.lpoot4g4.tw.View.CharacterSelectionView;
import com.lpoot4g4.tw.View.MenuView;
import com.lpoot4g4.tw.View.PlayView;

import static com.badlogic.gdx.Application.LOG_DEBUG;

public class TurtleWars extends Game
{
    /**
     * The sprite batch rendered every frame.
     */
    private SpriteBatch batch;

    /**
     * The asset manager for the game.
     */
    private AssetManager assetManager;

    /**
     * The camera used for the entirety of the game.
     */
    private OrthographicCamera camera;

    /**
     * The game width in pixels.
     */
    public static final float WIDTH = 800.0f;

    /**
     * The game height in pixels.
     */
    public static final float HEIGHT = 400.0f;

    /**
     * The window title.
     */
    public static final String TITLE = "Turtle Wars";

    /**
     * Creates the game. Initializes the sprite batch and asset manager.
     */
    @Override
    public void create ()
    {
        batch = new SpriteBatch();
        assetManager = new AssetManager();

        camera = new OrthographicCamera(TurtleWars.WIDTH, TurtleWars.HEIGHT);
        camera.setToOrtho(false, TurtleWars.WIDTH, TurtleWars.HEIGHT);
        camera.update();

        Gdx.gl.glClearColor(0.1f,0.9f,0.9f,1);

        Gdx.app.setLogLevel(LOG_DEBUG);

        startGame();
    }

    /**
     * Starts the game.
     */
    private void startGame()
    {
        setScreen(new MenuView(this, new GameModel()));
    }

    /**
     * Returns the camera.
     *
     * @return The camera.
     */
    public OrthographicCamera getCamera() {
        return camera;
    }

    /**
     * Sets the screen to the main menu view.
     *
     * @param gm The game model.
     */
    public void setMenu(GameModel gm)
    {
        setScreen(new MenuView(this, gm));
    }

    /**
     * Sets the screen to the character selection view.
     *
     * @param gm The game model.
     */
    public void setCharacterSelection(GameModel gm)
    {
        setScreen(new CharacterSelectionView(this, gm));
    }

    /**
     * Sets the screen to the play view.
     *
     * @param gm The game model.
     */
    public void setPlay(GameModel gm)
    {
        setScreen(new PlayView(this, gm));
    }

    /**
     * Exits the game.
     */
    public void exit()
    {
        Gdx.app.exit();
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
