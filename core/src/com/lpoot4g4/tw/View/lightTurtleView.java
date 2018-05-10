package com.lpoot4g4.tw.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.lpoot4g4.tw.TurtleWars;

public class lightTurtleView extends TurtleView
{
    public lightTurtleView(TurtleWars game)
    {
        super(game);
    }

    @Override
    public Sprite createSprite(TurtleWars game)
    {
        return new Sprite(game.getAssetManager().get("bazookaTurtle.png", Texture.class));
    }
}
