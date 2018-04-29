package com.lpoot4g4.tw.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoot4g4.tw.Model.GameModel;
import com.lpoot4g4.tw.TurtleWars;

public class GameView extends ScreenAdapter
{

	/**
	 * How much meters does a pixel represent.
	 */
	public final static float PIXEL_TO_METER = 0.04f;

	/**
	 * The width of the viewport in meters. The height is
	 * automatically calculated using the screen ratio.
	 */
	private static final float VIEWPORT_WIDTH = 30;


	/**
	 * The game this screen belongs to.
	 */
	private final TurtleWars game;

	/**
	 * The camera used to show the viewport.
	 */
	//private final OrthographicCamera camera;

	/**
	 * Creates this screen.
	 *
	 * @param game The game this screen belongs to
	 */
	public GameView(TurtleWars game)
	{
		this.game = game;
		GameModel.setInstance(new GameModel());

		loadAssets();

		//camera = createCamera();
	}

	/**
	 * Creates the camera used to show the viewport.
	 *
	 * @return the camera
	 *
	private OrthographicCamera createCamera()
	{

	} */

	/**
	 * Renders this screen.
	 *
	 * @param delta time since last renders in seconds.
	 */

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.getBatch().begin();

		String mode = GameModel.getInstance().getState().toString();

		if(mode.equals("Menu"))
			renderMenu();

		game.getBatch().end();

	}

	public void renderMenu()
	{
		Texture menu = game.getAssetManager().get("main_menu.png", Texture.class);
		game.getBatch().draw(menu, 0, 0, TurtleWars.WIDTH, TurtleWars.HEIGHT);
	}

	public void loadAssets()
	{
		this.game.getAssetManager().load("main_menu.png", Texture.class);
		this.game.getAssetManager().finishLoading();
	}

}
