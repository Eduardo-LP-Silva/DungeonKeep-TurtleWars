package com.lpoot4g4.tw.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.lpoot4g4.tw.Model.GameModel;
import com.lpoot4g4.tw.Model.TurtleModel;
import com.lpoot4g4.tw.TurtleWars;

public class CharacterSelectionView extends ScreenAdapter
{
    private TurtleWars game;
    private GameModel gameModel;
    private Sprite lightTurtle;
    private Sprite heavyTurtle;
    private Sprite startGame;

    public CharacterSelectionView(TurtleWars g, GameModel gm)
    {
        game = g;
        gameModel = gm;
        loadAssets();
    }

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
        heavyTurtle.setPosition(400, 230);

        startGame = new Sprite(game.getAssetManager().get("newGameBtn.png", Texture.class));
        startGame.setPosition(260, 50);
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        handleInputs();

        game.getBatch().begin();
        game.getBatch().draw(game.getAssetManager().get("characterSelection.png", Texture.class), 0, 0, TurtleWars.WIDTH, TurtleWars.HEIGHT);
        lightTurtle.draw(game.getBatch());
        heavyTurtle.draw(game.getBatch());
        startGame.draw(game.getBatch());
        game.getBatch().end();
    }

    public void handleInputs()
    {
        if(Gdx.input.isTouched())
        {
            if(lightTurtle.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()))
                gameModel.getPlayer1().setTurtleClass(TurtleModel.TurtleClass.Light);


            if(heavyTurtle.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()))
                gameModel.getPlayer1().setTurtleClass(TurtleModel.TurtleClass.Heavy);

            if(startGame.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()))
                game.setPlay(gameModel);

        }
    }
}
