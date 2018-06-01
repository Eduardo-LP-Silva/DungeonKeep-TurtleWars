package com.lpoot4g4.tw.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.lpoot4g4.tw.Model.GameModel;
import com.lpoot4g4.tw.Model.TurtleModel;
import com.lpoot4g4.tw.TurtleWars;

public class CharacterSelectionView extends ScreenAdapter
{
    /**
     * The game instance.
     */
    private TurtleWars game;

    /**
     * The game model.
     */
    private GameModel gameModel;

    /**
     * The light turtle sprite.
     */
    private Sprite lightTurtle;

    /**
     * The heavy turtle sprite.
     */
    private Sprite heavyTurtle;

    /**
     * The start game button sprite.
     */
    private Sprite startGame;

    /**
     * The constructor for the character selection menu view.
     *
     * @param g The game itself.
     * @param gm The game model.
     */
    public CharacterSelectionView(TurtleWars g, GameModel gm)
    {
        game = g;
        gameModel = gm;
        loadAssets();
    }

    /**
     * Loads all the assets needed for the character selection menu view.
     */
    public void loadAssets()
    {
        game.getAssetManager().load("characterSelection.png", Texture.class);
        game.getAssetManager().load("bazookaTurtle.png", Texture.class);
        game.getAssetManager().load("heavyTurtle.png", Texture.class);
        game.getAssetManager().load("newGameBtn.png", Texture.class);
        game.getAssetManager().finishLoading();

        lightTurtle = new Sprite(game.getAssetManager().get("bazookaTurtle.png", Texture.class));
        lightTurtle.setPosition(200, 230);

        heavyTurtle = new Sprite(game.getAssetManager().get("heavyTurtle.png", Texture.class));
        heavyTurtle.setPosition(450, 230);

        startGame = new Sprite(game.getAssetManager().get("newGameBtn.png", Texture.class));
        startGame.setPosition(260, 160);
    }

    /**
     * Unloads all the assets previously used (that are not going to be needed in the next screen).
     */
    public void unloadAssets()
    {
        game.getAssetManager().unload("characterSelection.png");
        game.getAssetManager().unload("newGameBtn.png");
    }

    /**
     * Renders the screen.
     *
     * @param delta The time since last render (seconds).
     */
    @Override
    public void render(float delta)
    {
        handleInputs();

        game.getCamera().update();
        game.getBatch().setProjectionMatrix(game.getCamera().combined);

        game.getBatch().begin();
        game.getBatch().draw(game.getAssetManager().get("characterSelection.png", Texture.class), 0, 0, TurtleWars.WIDTH, TurtleWars.HEIGHT);
        lightTurtle.draw(game.getBatch());
        heavyTurtle.draw(game.getBatch());
        startGame.draw(game.getBatch());
        game.getBatch().end();
    }

    /**
     * Handles the inputs of the user.
     */
    public void handleInputs()
    {
        if(Gdx.input.isTouched())
        {
            Vector3 input = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.getCamera().unproject(input);

            if(lightTurtle.getBoundingRectangle().contains(input.x, input.y))
                gameModel.getPlayer1().setTurtleClass(TurtleModel.TurtleClass.Light);

            if(heavyTurtle.getBoundingRectangle().contains(input.x, input.y))
                gameModel.getPlayer1().setTurtleClass(TurtleModel.TurtleClass.Heavy);

            if(startGame.getBoundingRectangle().contains(input.x, input.y))
            {
                gameModel.getThemeSong().stop();
                game.setPlay(gameModel);
                //unloadAssets();
            }

        }
    }
}
