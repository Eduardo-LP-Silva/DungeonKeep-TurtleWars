package com.lpoot4g4.tw.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.lpoot4g4.tw.TurtleWars;

public abstract class TurtleView extends EntityView
{
    public TurtleView(TurtleWars game) {
        super(game);
    }

    public abstract Sprite createSprite(TurtleWars game);
}