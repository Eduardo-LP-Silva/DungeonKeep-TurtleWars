package com.lpoot4g4.tw.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Timer;
import com.lpoot4g4.tw.Controller.GameWorld;
import com.lpoot4g4.tw.Controller.ProjectileBody;
import com.lpoot4g4.tw.Model.GameModel;
import com.lpoot4g4.tw.Model.PowerUpModel;
import com.lpoot4g4.tw.Model.ProjectileModel;
import com.lpoot4g4.tw.TurtleWars;

import java.util.ArrayList;
import java.util.Random;

public class PlayView extends ScreenAdapter
{
    private TurtleWars game;
    private GameModel gameModel;
    private GameWorld gameWorld;
    private TurtleView player1;
    private TurtleView player2;
    private PlatformView floor;
    private BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));
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
        this.game.getAssetManager().load("lightTurtle.png", Texture.class);
        this.game.getAssetManager().load("heavyTurtleTR.png", Texture.class);
        this.game.getAssetManager().load("powerUpBite.png", Texture.class);
        this.game.getAssetManager().load("powerUpHealth.png", Texture.class);

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
        this.game.getAssetManager().unload("lightTurtle.png");
        this.game.getAssetManager().unload("heavyTurtleTR.png");
        this.game.getAssetManager().unload("powerUpBite.png");
        this.game.getAssetManager().unload("powerUpHealth.png");
    }

    @Override
    public void render(float delta)
    {

        if(!gameModel.isVictory() && !gameModel.isGameOver())
        {
            gameWorld.removeFlagged();
            handleInputs(delta);
            gameWorld.update(delta);
        }
        else
        {
            Timer.schedule(new Timer.Task()
            {
                @Override
                public void run()
                {
                    dispose();
                    game.setMenu(new GameModel());
                }
            }, 5);
        }


        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        game.getBatch().begin();



        game.getBatch().draw(game.getAssetManager().get("background.png", Texture.class), 0, 0, game.WIDTH, game.HEIGHT);
        floor.draw(game.getBatch());

        updateTurtles(delta);

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

        font.draw(game.getBatch(), Integer.toString(gameModel.getPlayer1().getHealth()), 5, 380);
        font.draw(game.getBatch(), Integer.toString(gameModel.getPlayer2().getHealth()), 700, 380);

        String effect = gameModel.getPowerUp().getEffect().toString();

        if(!effect.equals("Null"))
        {
            if (effect.equals("Health"))
                game.getBatch().draw(game.getAssetManager().get("powerUpHealth.png", Texture.class),
                        gameModel.getPowerUp().getX(), gameModel.getPowerUp().getY());
            else
                if (effect.equals("Shield"))
                    game.getBatch().draw(game.getAssetManager().get("powerUpShield.png", Texture.class),
                        gameModel.getPowerUp().getX(), gameModel.getPowerUp().getY());
                else
                    if (effect.equals("Damage"))
                        game.getBatch().draw(game.getAssetManager().get("powerUpBite.png", Texture.class),
                            gameModel.getPowerUp().getX(), gameModel.getPowerUp().getY());
        }

        if(gameModel.isGameOver())
            font.draw(game.getBatch(), "Game Over", 300, 300);
        else
            if(gameModel.isVictory())
                font.draw(game.getBatch(), "Victory", 300, 300);

        game.getBatch().end();

        if(debugPhysics)
            debugRenderer.render(gameWorld.getWorld(), camera.combined);

    }

    public void updateTurtles(float delta)
    {
        TextureRegion region;

        player1.getSprite().setPosition(gameModel.getPlayer1().getX(), gameModel.getPlayer1().getY());
        player2.getSprite().setPosition(gameModel.getPlayer2().getX(), gameModel.getPlayer2().getY());

        if(gameModel.getPlayer1().isBiting())
            player1.getSprite().setRegion(88 * 3, 0, 88, 59);
        else
            if(gameWorld.getPlayer1().getBody().getLinearVelocity().x != 0)
            {
                region = player1.walking.getKeyFrame(player1.animationTimer, true);
                player1.getSprite().setRegion(region);

                player1.animationTimer = gameModel.getPlayer1().getCurrentState() == gameModel.getPlayer1().getPreviousState()
                        ? player1.animationTimer + delta : 0;

                gameModel.getPlayer1().setPreviousState(gameModel.getPlayer1().getCurrentState());
            }
            else
                player1.getSprite().setRegion(0,0,88,59);

        if(gameModel.getPlayer2().isBiting())
            player2.getSprite().setRegion(88 * 3, 0, 88, 59);
        else
            if(gameWorld.getPlayer2().getBody().getLinearVelocity().x != 0)
            {
                region = player2.walking.getKeyFrame(player2.animationTimer, true);
                player2.getSprite().setRegion(region);

                player2.animationTimer = gameModel.getPlayer2().getCurrentState() == gameModel.getPlayer2().getPreviousState()
                        ? player2.animationTimer + delta : 0;

                gameModel.getPlayer2().setPreviousState(gameModel.getPlayer2().getCurrentState());
            }
            else
                player2.getSprite().setRegion(0,0,88,59);

        if((gameModel.getPlayer1().isBackwards() && !player1.getSprite().isFlipX()) ||
                (!gameModel.getPlayer1().isBackwards() && player1.getSprite().isFlipX()))
            player1.getSprite().flip(true, false);

        if((gameModel.getPlayer2().isBackwards() && !player2.getSprite().isFlipX()) ||
                (!gameModel.getPlayer2().isBackwards() && player2.getSprite().isFlipX()))
            player2.getSprite().flip(true, false);

        player1.draw(game.getBatch());
        player2.draw(game.getBatch());
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
