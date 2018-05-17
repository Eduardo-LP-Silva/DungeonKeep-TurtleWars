package com.lpoot4g4.tw.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.lpoot4g4.tw.Controller.GameWorld;
import com.lpoot4g4.tw.Model.GameModel;
import com.lpoot4g4.tw.TurtleWars;

import java.util.ArrayList;

public class PlayView extends ScreenAdapter
{
    private TurtleWars game;
    private GameModel gameModel;
    private GameWorld gameWorld;
    private TurtleView player1;
    private TurtleView player2;
    private PlatformView floor;
    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;
    private static boolean debugPhysics = true;

    public final static float PIXEL_TO_METER = 0.01f;

    public PlayView(TurtleWars g, GameModel gm)
    {
        game = g;
        gameModel = gm;
        gameWorld = new GameWorld(gameModel);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, TurtleWars.WIDTH * PIXEL_TO_METER, TurtleWars.HEIGHT * PIXEL_TO_METER);
        camera.position.set(TurtleWars.WIDTH / 2, TurtleWars.HEIGHT / 2, 0);

        debugRenderer = new Box2DDebugRenderer();
        loadAssets();
    }

    public void loadAssets()
    {
        this.game.getAssetManager().load("background.png", Texture.class);
        this.game.getAssetManager().load("platform.png", Texture.class);
        this.game.getAssetManager().load("projectile.png", Texture.class);
        this.game.getAssetManager().load("cactus.png", Texture.class);
        this.game.getAssetManager().load("lightTurtleBackwards.png", Texture.class);
        this.game.getAssetManager().load("heavyTurtleBackwards.png", Texture.class);

        this.game.getAssetManager().finishLoading();

        if(gameModel.getPlayer1().getTurtleClass().toString().equals("Light"))
            player1 = new lightTurtleView(game);
        else
            player1 = new heavyTurtleView(game);

        if(gameModel.getPlayer2().getTurtleClass().toString().equals("Light"))
            player2 = new lightTurtleView(game);
        else
            player2 = new heavyTurtleView(game);

        floor = new PlatformView(game);
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

        if(gameModel.getPlayer1().getX() < gameModel.getPlayer2().getX())
        {
            player1.getSprite().setPosition(gameModel.getPlayer1().getX(), gameModel.getPlayer1().getY());
            player1.draw(game.getBatch());

            player2.getBackwardsTurtle().setPosition(gameModel.getPlayer2().getX(), gameModel.getPlayer2().getY());
            player2.drawBackwards(game.getBatch());
        }
        else
        {
            player1.getBackwardsTurtle().setPosition(gameModel.getPlayer1().getX(), gameModel.getPlayer1().getY());
            player1.drawBackwards(game.getBatch());

            player2.getSprite().setPosition(gameModel.getPlayer2().getX(), gameModel.getPlayer2().getY());
            player2.draw(game.getBatch());
        }

        for(int i = 0; i < gameModel.getMissiles().size(); i++)
        {
            ProjectileView pv = new ProjectileView(game);

            pv.getSprite().setPosition(gameModel.getMissiles().get(i).getX(), gameModel.getMissiles().get(i).getY());

            pv.draw(game.getBatch());
        }
        game.getBatch().end();

        if(debugPhysics)
            debugRenderer.render(gameWorld.getWorld(), camera.combined);

    }

    public void handleInputs(float delta)
    {
       if(Gdx.input.isKeyPressed(Input.Keys.A))
          gameWorld.getPlayer1().moveLeft();

        if(Gdx.input.isKeyPressed(Input.Keys.D))
            gameWorld.getPlayer1().moveRight();

        if(Gdx.input.isKeyJustPressed(Input.Keys.W))
            gameWorld.getPlayer1().jump();

        if(Gdx.input.isKeyJustPressed(Input.Keys.E))
            gameWorld.getPlayer1().bite();

        if(Gdx.input.isKeyJustPressed(Input.Keys.Q))
            gameWorld.FireTurtle1();
    }
}
