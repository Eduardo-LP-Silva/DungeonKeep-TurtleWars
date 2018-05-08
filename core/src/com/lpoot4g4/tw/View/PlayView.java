package com.lpoot4g4.tw.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.lpoot4g4.tw.Controller.GameWorld;
import com.lpoot4g4.tw.Model.GameModel;
import com.lpoot4g4.tw.TurtleWars;

public class PlayView extends ScreenAdapter
{
    private TurtleWars game;
    private GameModel gameModel;
    private GameWorld gameWorld;
    private Sprite bazookaTurtle;
    private Sprite floor;
    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;

    public final static float PIXEL_TO_METER = 0.01f;

    public PlayView(TurtleWars g, GameModel gm)
    {
        game = g;
        gameModel = gm;
        gameWorld = new GameWorld(gameModel);

        camera = new OrthographicCamera();
        
        loadAssets();
    }

    public void loadAssets()
    {
        this.game.getAssetManager().load("background.png", Texture.class);
        this.game.getAssetManager().load("platform.png", Texture.class);
        this.game.getAssetManager().load("bazookaTurtle.png", Texture.class);
        this.game.getAssetManager().load("projectile.png", Texture.class);
        this.game.getAssetManager().load("cactus.png", Texture.class);
        this.game.getAssetManager().finishLoading();

        bazookaTurtle = new Sprite(this.game.getAssetManager().get("bazookaTurtle.png", Texture.class));

        floor = new Sprite(this.game.getAssetManager().get("platform.png", Texture.class));
        floor.setPosition(0,0);
    }

    public void unloadAssets()
    {
        this.game.getAssetManager().unload("platform.png");
        this.game.getAssetManager().unload("bazookaTurtle.png");
        this.game.getAssetManager().unload("projectile.png");
        this.game.getAssetManager().unload("cactus.png");
    }

    @Override
    public void render(float delta)
    {
        handleInputs(delta);
        gameWorld.update(delta);

        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        game.getBatch().begin();
        game.getBatch().draw(game.getAssetManager().get("background.png", Texture.class), 0, 0, game.WIDTH, game.HEIGHT);
        floor.draw(game.getBatch());

        bazookaTurtle.setPosition(gameModel.getPlayer1().getX(), gameModel.getPlayer1().getY());
        bazookaTurtle.draw(game.getBatch());
        game.getBatch().end();
    }

    public void handleInputs(float delta)
    {
       //if(Gdx.input.isKeyPressed(Input.Keys.A))
          // gameModel.getPlayer1().setX();

    }
}
