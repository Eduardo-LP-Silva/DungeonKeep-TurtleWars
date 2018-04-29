package com.lpoot4g4.tw.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.lpoot4g4.tw.TurtleWars;

public class PlatformView extends EntityView
{
    public PlatformView(TurtleWars game)
    {
        super(game);
    }

    public Sprite createSprite(TurtleWars game)
    {
        Texture tex = new Texture("platform.png");

        return new Sprite(tex, tex.getWidth(), tex.getHeight());
    }
}
