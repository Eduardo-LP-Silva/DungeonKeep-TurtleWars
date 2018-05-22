package com.lpoot4g4.tw.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.lpoot4g4.tw.TurtleWars;

public class heavyTurtleView extends TurtleView
{
    public heavyTurtleView(TurtleWars game)
    {
        super(game);

        backwardsTurtle = new Sprite(game.getAssetManager().get("heavyTurtleBackwards.png", Texture.class));
    }

    @Override
    public Sprite createSprite(TurtleWars game)
    {
        return new Sprite(game.getAssetManager().get("heavyTurtle.png", Texture.class));
    }
}
