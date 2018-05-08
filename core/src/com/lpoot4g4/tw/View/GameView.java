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

	private MenuView mainMenu;
	private PlayView playScreen;

	/**
	 * Creates this screen.
	 *
	 * @param game The game this screen belongs to
	 */
	public GameView(TurtleWars game)
	{
		this.game = game;

		/*
		mainMenu = new MenuView(game);
		playScreen = new PlayView(game);
		mainMenu.loadAssets();
		*/
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
		/*
		String state = GameModel.getInstance().getState().toString();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		handleInput(delta);

		if(state.equals("Menu"))
			mainMenu.render(delta);
		else
			if(state.equals("Play"))
			{
				if(mainMenu.isLoadPlayAssets())
				{
					playScreen.loadAssets();
					mainMenu.setLoadPlayAssets(false);
				}

				playScreen.render(delta);

			}

			if(state.equals("Exit")) //TODO Actually Exit and unload resources
				return;
 */
	}

	public void handleInput(float delta)
	{
		/*if(GameModel.getInstance().getState().toString().equals("Menu"))
			mainMenu.handleInputs(delta); */
	}

	public PlayView getPlayScreen()
	{
		return playScreen;
	}
}
