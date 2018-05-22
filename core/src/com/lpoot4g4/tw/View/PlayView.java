package com.lpoot4g4.tw.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Timer;
import com.lpoot4g4.tw.Controller.GameWorld;
import com.lpoot4g4.tw.Controller.ProjectileBody;
import com.lpoot4g4.tw.Model.GameModel;
import com.lpoot4g4.tw.Model.ProjectileModel;
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
        this.game.getAssetManager().load("explosion.png", Texture.class);
        this.game.getAssetManager().load("D0.png", Texture.class);
        this.game.getAssetManager().load("D1.png", Texture.class);
        this.game.getAssetManager().load("D2.png", Texture.class);
        this.game.getAssetManager().load("D3.png", Texture.class);
        this.game.getAssetManager().load("D4.png", Texture.class);
        this.game.getAssetManager().load("D5.png", Texture.class);
        this.game.getAssetManager().load("D6.png", Texture.class);
        this.game.getAssetManager().load("D7.png", Texture.class);
        this.game.getAssetManager().load("D8.png", Texture.class);
        this.game.getAssetManager().load("D9.png", Texture.class);

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
        this.game.getAssetManager().unload("explosion.png");
        this.game.getAssetManager().unload("D0.png");
        this.game.getAssetManager().unload("D1.png");
        this.game.getAssetManager().unload("D2.png");
        this.game.getAssetManager().unload("D3.png");
        this.game.getAssetManager().unload("D4.png");
        this.game.getAssetManager().unload("D5.png");
        this.game.getAssetManager().unload("D6.png");
        this.game.getAssetManager().unload("D7.png");
        this.game.getAssetManager().unload("D8.png");
        this.game.getAssetManager().unload("D9.png");
    }

    @Override
    public void render(float delta)
    {
        gameWorld.removeFlagged();
        handleInputs(delta);
        gameWorld.update(delta);

        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        game.getBatch().begin();
        game.getBatch().draw(game.getAssetManager().get("background.png", Texture.class), 0, 0, game.WIDTH, game.HEIGHT);
        floor.draw(game.getBatch());

        player1.getSprite().setPosition(gameModel.getPlayer1().getX(), gameModel.getPlayer1().getY());
        player2.getSprite().setPosition(gameModel.getPlayer2().getX(), gameModel.getPlayer2().getY());

        if((gameModel.getPlayer1().isBackwards() && !player1.getSprite().isFlipX()) ||
                (!gameModel.getPlayer1().isBackwards() && player1.getSprite().isFlipX()))
            player1.getSprite().flip(true, false);

        if((gameModel.getPlayer2().isBackwards() && !player2.getSprite().isFlipX()) ||
                (!gameModel.getPlayer2().isBackwards() && player2.getSprite().isFlipX()))
            player2.getSprite().flip(true, false);

        player1.draw(game.getBatch());
        player2.draw(game.getBatch());

        for(int i = 0; i < gameModel.getMissiles().size(); i++)
        {
            ProjectileView pv = new ProjectileView(game);

            if(gameModel.getMissiles().get(i).isBackwards())
                pv.getSprite().flip(true, false);

            pv.getSprite().setPosition(gameModel.getMissiles().get(i).getX(), gameModel.getMissiles().get(i).getY());

            if(gameModel.getMissiles().get(i).isFlaggedForRemoval())
               if((System.nanoTime() - gameModel.getMissiles().get(i).getDissipationTimeStart()) / Math.pow(10,7) > 15)
                {
                    gameModel.removeMissile(gameModel.getMissiles().get(i));
                }
                else
                {
                    game.getBatch().draw(game.getAssetManager().get("explosion.png", Texture.class),
                            gameModel.getMissiles().get(i).getX() - game.getAssetManager().get("explosion.png",
                                    Texture.class).getWidth() / 2, gameModel.getMissiles().get(i).getY());
                }
            else
                pv.draw(game.getBatch());
        }


        int health = gameModel.getPlayer1().getHealth(), digit, x = 50;
        Texture tex;

        //Turtle 1
        do
        {
            digit = health % 10;
            health /= 10;

            tex = getDigit(digit);

            game.getBatch().draw(tex, x, 300);

            x -= 40;
        }
        while(health > 0);

        //Turtle 2

        health = gameModel.getPlayer2().getHealth();
        x = 700;

        do
        {
            digit = health % 10;
            health /= 10;

            tex = getDigit(digit);

            game.getBatch().draw(tex, x, 300);

            x -= 40;
        }
        while(health > 0);

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

        if(Gdx.input.isKeyJustPressed(Input.Keys.Q) && !gameModel.getPlayer1().isFiring())
            gameWorld.FireTurtle1();
    }

    public Texture getDigit(int digit)
    {
        switch(digit)
        {
            case 0:
                return game.getAssetManager().get("D0.png", Texture.class);

            case 1:
                return game.getAssetManager().get("D1.png", Texture.class);

            case 2:
                return game.getAssetManager().get("D2.png", Texture.class);

            case 3:
                return game.getAssetManager().get("D3.png", Texture.class);

            case 4:
                return game.getAssetManager().get("D4.png", Texture.class);

            case 5:
                return game.getAssetManager().get("D5.png", Texture.class);

            case 6:
                return game.getAssetManager().get("D6.png", Texture.class);

            case 7:
                return game.getAssetManager().get("D7.png", Texture.class);

            case 8:
                return game.getAssetManager().get("D8.png", Texture.class);

            case 9:
                return game.getAssetManager().get("D9.png", Texture.class);

            default:
                return null;
        }
    }
}
