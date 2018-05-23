package com.lpoot4g4.tw.View;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.lpoot4g4.tw.TurtleWars;
import com.badlogic.gdx.graphics.g2d.Animation;

public abstract class TurtleView extends EntityView
{
    protected Animation<TextureRegion> walking;
    protected float animationTimer = 0;

    public TurtleView(TurtleWars game) {
        super(game);
    }

    public abstract Sprite createSprite(TurtleWars game);
}