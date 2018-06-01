package com.lpoot4g4.tw.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.lpoot4g4.tw.TurtleWars;

public class PlatformView extends EntityView
{
    /**
     * Constructs a platform view.
     *
     * @param game The game.
     */
    public PlatformView(TurtleWars game)
    {
        super(game);
    }

    /**
     * Creates a sprite that represents the platform.
     *
     * @param game The game.
     */
    public Sprite createSprite(TurtleWars game)
    {
        return new Sprite(game.getAssetManager().get("platform.png", Texture.class));
    }
}
