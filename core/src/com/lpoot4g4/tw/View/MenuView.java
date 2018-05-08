package com.lpoot4g4.tw.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.lpoot4g4.tw.Controller.GameWorld;
import com.lpoot4g4.tw.Model.GameModel;
import com.lpoot4g4.tw.TurtleWars;

public class MenuView extends ScreenAdapter
{
    private TurtleWars game;
    private GameModel gameModel;
    private static int SPACING = 20;
    private Sprite newGameBtn;
    private Sprite optionsBtn;
    private Sprite exitBtn;

    public MenuView(TurtleWars g, GameModel gm)
    {
        game = g;
        gameModel = gm;

        loadAssets();
    }

    public void loadAssets()
    {
        this.game.getAssetManager().load("menuBackground.png", Texture.class);
        this.game.getAssetManager().load("newGameBtn.png", Texture.class);
        this.game.getAssetManager().load("optionsBtn.png", Texture.class);
        this.game.getAssetManager().load("exitBtn.png", Texture.class);
        this.game.getAssetManager().finishLoading();

        newGameBtn = new Sprite(this.game.getAssetManager().get("newGameBtn.png", Texture.class));
        optionsBtn = new Sprite(this.game.getAssetManager().get("optionsBtn.png", Texture.class));
        exitBtn = new Sprite(this.game.getAssetManager().get("exitBtn.png", Texture.class));

        newGameBtn.setPosition(newGameBtn.getHeight() / 2,  TurtleWars.HEIGHT - newGameBtn.getHeight() - SPACING);
        optionsBtn.setPosition(optionsBtn.getHeight() / 2, newGameBtn.getY() - SPACING - optionsBtn.getHeight());
        exitBtn.setPosition(exitBtn.getHeight() / 2, optionsBtn.getY() - SPACING - exitBtn.getHeight());
    }

    @Override
    public void render(float delta)
    {
        handleInputs(delta);

        Texture menuBackground = game.getAssetManager().get("menuBackground.png", Texture.class);
        game.getBatch().begin();
        game.getBatch().draw(menuBackground, 0, 0, TurtleWars.WIDTH, TurtleWars.HEIGHT);
        newGameBtn.draw(game.getBatch());
        optionsBtn.draw(game.getBatch());
        exitBtn.draw((game.getBatch()));
        game.getBatch().end();
    }

    public void unloadAssets()
    {
        this.game.getAssetManager().unload("menuBackground.png");
        this.game.getAssetManager().unload("newGameBtn.png");
        this.game.getAssetManager().unload("optionsBtn.png");
        this.game.getAssetManager().unload("exitBtn.png");
    }

    public void handleInputs(float delta)
    {
        if(Gdx.input.isTouched())
        {
            if(newGameBtn.getBoundingRectangle().contains(Gdx.input.getX(),  Gdx.graphics.getHeight() - Gdx.input.getY()))
                game.setCharacterSelection(gameModel);

            if(optionsBtn.getBoundingRectangle().contains(Gdx.input.getX(),  Gdx.graphics.getHeight() - Gdx.input.getY()))
                game.setOptions(gameModel);

            if(exitBtn.getBoundingRectangle().contains(Gdx.input.getX(),  Gdx.graphics.getHeight() - Gdx.input.getY()))
                game.exit();
        }
    }
}
