package com.lpoot4g4.tw.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoot4g4.tw.TurtleWars;

public abstract class TurtleView extends EntityView
{
    protected Sprite backwardsTurtle;

    public TurtleView(TurtleWars game) {
        super(game);
    }

    public Sprite getBackwardsTurtle()
    {
        return backwardsTurtle;
    }

    public abstract Sprite createSprite(TurtleWars game);

    public void drawBackwards(SpriteBatch batch)
    {
        backwardsTurtle.draw(batch);
    }
}