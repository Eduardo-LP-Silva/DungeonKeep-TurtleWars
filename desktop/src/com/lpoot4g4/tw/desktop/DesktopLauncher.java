package com.lpoot4g4.tw.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lpoot4g4.tw.TurtleWars;

public class DesktopLauncher {
	public static void main (String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = TurtleWars.WIDTH;
		config.height = TurtleWars.HEIGHT;
		config.title = TurtleWars.TITLE;
		new LwjglApplication(new TurtleWars(), config);
	}
}
