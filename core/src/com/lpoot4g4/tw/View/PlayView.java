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
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Timer;
import com.lpoot4g4.tw.Controller.GameWorld;
import com.lpoot4g4.tw.Model.GameModel;
import com.lpoot4g4.tw.TurtleWars;

public class PlayView extends ScreenAdapter implements GestureDetector.GestureListener
{
    /**
     * The game itself.
     */
    private TurtleWars game;

    /**
     * The game model.
     */
    private GameModel gameModel;

    /**
     * The game world.
     */
    private GameWorld gameWorld;

    /**
     * The turtle view for the first player.
     */
    private TurtleView player1;

    /**
     * The turtle view for the AI player.
     */
    private TurtleView player2;

    /**
     * The platform view for the floor.
     */
    private PlatformView floor;

    /**
     * The font of used.
     */
    private BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));

    /**
     * The camera of the game.
     */
    private OrthographicCamera camera;

    /**
     * The debug renderer of the game.
     */
    private Box2DDebugRenderer debugRenderer;

    /**
     * Flag to debug physics.
     */
    private static boolean debugPhysics = false;

    /**
     * The gesture detector of the game.
     */
    private GestureDetector gestureDetector;

    /**
     * Sprite for the bite button.
     */
    private Sprite biteBtn;

    /**
     * Sprite for the shoot button.
     */
    private Sprite fireBtn;

    /**
     * Sprite for the jump button.
     */
    private Sprite jumpBtn;

    /**
     * Pixel to meter float constant.
     */
    public final static float PIXEL_TO_METER = 0.01f;

    /**
     * Play view constructor.
     *
     * @param g The game itself.
     * @param gm The game model.
     */
    public PlayView(TurtleWars g, GameModel gm)
    {
        game = g;
        gameModel = gm;
        gameWorld = new GameWorld(gameModel);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, TurtleWars.WIDTH * PIXEL_TO_METER, TurtleWars.HEIGHT * PIXEL_TO_METER);
        camera.position.set(TurtleWars.WIDTH / 2, TurtleWars.HEIGHT / 2, 0);

        debugRenderer = new Box2DDebugRenderer();
        gestureDetector = new GestureDetector(this);
        gestureDetector.setTapCountInterval(0.4f);
        Gdx.input.setInputProcessor(gestureDetector);
        loadAssets();
    }

    /**
     * Loads all the assets needed for the play view.
     */
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
        this.game.getAssetManager().load("biteBtn.png", Texture.class);
        this.game.getAssetManager().load("fireBtn.png", Texture.class);
        this.game.getAssetManager().load("jumpBtn.png", Texture.class);

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

        jumpBtn = new Sprite(game.getAssetManager().get("jumpBtn.png", Texture.class));
        jumpBtn.setPosition(10, 10);

        biteBtn = new Sprite(game.getAssetManager().get("biteBtn.png", Texture.class));
        biteBtn.setPosition(630, 10);

        fireBtn = new Sprite(game.getAssetManager().get("fireBtn.png", Texture.class));
        fireBtn.setPosition(700, 10);
    }

    /**
     * Unloads all the assets previously used.
     */
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
        this.game.getAssetManager().unload("biteBtn.png");
        this.game.getAssetManager().unload("jumpBtn.png");
        this.game.getAssetManager().unload("fireBtn.png");
    }

    /**
     * Renders the screen.
     *
     * @param delta The time since last render (seconds).
     */
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

        jumpBtn.draw(game.getBatch());
        biteBtn.draw(game.getBatch());
        fireBtn.draw(game.getBatch());

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

    /**
     *  Updates the turtle sprite.
     *
     * @param delta The time since the last update (seconds).
     */
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

    /**
     * Handles the inputs.
     *
     * @param delta Time since last input handled.
     */
    public void handleInputs(float delta)
    {
        Vector3 input = new Vector3(-1, -1, -1);

        if(Gdx.input.isTouched())
        {
            input.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.getCamera().unproject(input);
        }

       if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.getAccelerometerY() < -1)
          gameWorld.getPlayer1().moveLeft();

        if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.getAccelerometerY() > 2)
            gameWorld.getPlayer1().moveRight();

        if(Gdx.input.isKeyJustPressed(Input.Keys.W) || jumpBtn.getBoundingRectangle().contains(input.x, input.y))
            gameWorld.getPlayer1().jump();

        if(Gdx.input.isKeyJustPressed(Input.Keys.E) || biteBtn.getBoundingRectangle().contains(input.x, input.y))
            gameWorld.getPlayer1().bite();

        if(Gdx.input.isKeyJustPressed(Input.Keys.Q) || fireBtn.getBoundingRectangle().contains(input.x, input.y))
            gameWorld.FireTurtle1();
    }

    /**
     * Implementation of touchDown function from Gesture Listener
     *
     * @param x The x coordinate of the input.
     * @param y The y coordinate of the input.
     * @param pointer The pointer.
     * @param button The button.
     * @return True if it accepted/registered the input.
     */
    @Override
    public boolean touchDown(float x, float y, int pointer, int button)
    {
        return true;
    }

    /**
     * Implementation of tap function from Gesture Listener
     *
     * @param x The x coordinate of the input.
     * @param y The y coordinate of the input.
     * @param count The number of taps
     * @param button The button.
     * @return True if it registered the input.
     */
    @Override
    public boolean tap(float x, float y, int count, int button)
    {
        if(count > 1)
            gameWorld.getPlayer1().jump();

        return true;
    }

    /**
     * Implementation of longPress function from Gesture Listener
     *
     * @param x The x coordinate of the input.
     * @param y The y coordinate of the input.
     * @return True if it registered the input.
     */
    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    /**
     * Implementation of fling function from Gesture Listener
     *
     * @param velocityX  velocity on x in seconds
     * @param velocityY velocity on y in seconds
     * @param button The button.
     * @return True if it registered the input.
     */
    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    /**
     * Implementation of pan function from Gesture Listener
     *
     * @param x The x coordinate of the input.
     * @param y The y coordinate of the input.
     * @param deltaX the difference in pixels to the last drag event on x.
     * @param deltaY the difference in pixels to the last drag event on y.
     * @return True if it registered the input.
     */
    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    /**
     * Implementation of panStop function from Gesture Listener
     *
     * @param x The x coordinate of the input.
     * @param y The y coordinate of the input.
     * @param pointer The pointer.
     * @param button The button.
     * @return True if it registered the input.
     */
    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    /**
     * Implementation of zoom function from Gesture Listener
     *
     * @param initialDistance distance between fingers when the gesture started.
     * @param distance current distance between fingers.
     * @return True if it registered the input.
     */
    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    /**
     * Implementation of pinch function from Gesture Listener
     *
     * @param initialPointer1 The initial pointer 1.
     * @param initialPointer2 The initial pointer 2.
     * @param pointer1 Pointer 1.
     * @param pointer2 Pointer 2.
     * @return True if it registered the input.
     */
    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    /**
     * Implementation of pinchStop function from Gesture Listener
     */
    @Override
    public void pinchStop() {

    }
}
